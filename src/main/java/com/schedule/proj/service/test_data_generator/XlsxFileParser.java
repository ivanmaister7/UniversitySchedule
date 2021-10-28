package com.schedule.proj.service.test_data_generator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class XlsxFileParser {
    private static final Pattern WEEK_DAY_PATTERN = Pattern.compile("(Понеділок)|(Вівторок)|(Середа)|(Четвер)|(П'ятниця)|(Субота)|(Неділя)");
    private static final Pattern RANK_NAME_PATTERN = Pattern.compile("((?:ас\\.)|(?:доц\\.)|(?:проф\\.)|(?:ст\\.\\s*викл\\.)||(?:cт\\.\\s*викл\\.))\\s*(.+)");
    private final String fileName;
    private String lastTime;
    private String facultyName;
    private String specialityName;
    private final Set<Subject> resultSubjectSet;
    private final Set<Teacher> resultTeacherSet;
    private DayOfWeek dayOfWeek;


    // todo: fix empty lesson
    // todo: add speciality

    XlsxFileParser(String fileName) {
        this.fileName = fileName;
        this.resultSubjectSet = new HashSet<>();
        this.resultTeacherSet = new HashSet<>();
    }

    public void parseXlsxFile() throws IOException {
        File myFile = new File(fileName);
        System.out.println(fileName);
        FileInputStream fis = new FileInputStream(myFile); // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook (fis); // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(0); // Get iterator to all the rows in current sheet

        Iterator<Row> rowIterator = mySheet.iterator();

        // finding faculty name
        this.facultyName = findCellValueByWords(rowIterator, Collections.singletonList("факультет"), Collections.singletonList("декан"));
        // System.out.println(this.facultyName);

        this.specialityName = findCellValueByWords(rowIterator, Arrays.asList("спеціальність", "мп"), Collections.emptyList());
        // System.out.println(this.facultyName);

        // iterating over week days
        while (rowIterator.hasNext()) {
            // finding next week day
            Row weekDayRow = iterateUntilCellWithStringValue(rowIterator);

            if (weekDayRow == null)
                break;

            Cell weekDayCell = weekDayRow.getCell(0);

            if (weekDayCell.getCellType() != Cell.CELL_TYPE_STRING ||
                    !WEEK_DAY_PATTERN.matcher(weekDayCell.getStringCellValue()).matches())
                continue;

            this.dayOfWeek = ukrDayOfWeek(weekDayCell.getStringCellValue().toLowerCase());
            // System.out.printf(" ------------- %s ------------- \n", dayOfWeek);
            // todo: maybe errors!
            lastTime = "8:30";

            // iterating week day lessons
            Iterator<Cell> cellIterator = rowIterator.next().cellIterator();
            cellIterator.next();

            while (parseLessonRow(cellIterator)) {
                // System.out.println();
                cellIterator = rowIterator.next().cellIterator();
                cellIterator.next();
            }
        }

    }

    private String findCellValueByWords(Iterator<Row> rowIterator, List<String> wordsToInclude, List<String> wordsToExclude) {
        while (true) {
            Cell cell = iterateUntilCellWithStringValue(rowIterator).getCell(0);

            String cellValue = cell.getStringCellValue();

            boolean matches = wordsToInclude.stream().anyMatch(x -> cellValue.toLowerCase().contains(x)) &&
                    wordsToExclude.stream().noneMatch(x -> cellValue.toLowerCase().contains(x));

            if (matches)
                return cellValue;
        }
    }

    /**
     * Parses lesson row
     *
     * @return isWeekDayEnded
     * true if the input row contains info
     * false otherwise
     *
     * @param pairRowIterator
     * iterator of rows of cells in xlsx document
     */
    private boolean parseLessonRow(Iterator<Cell> pairRowIterator) {
        if (!pairRowIterator.hasNext())
            return false;

        Cell nextCell = pairRowIterator.next();

        String time = nextCell.getStringCellValue().split("-")[0];

        if (time.trim().isEmpty()) {
            // System.out.println(lastTime);
            time = lastTime;
        } else {
            lastTime = time;
        }

        if (time == null) {
            System.out.println("");
            throw new RuntimeException("Time is null for " + dayOfWeek + " " + lastTime + "  " + specialityName + "\n'" + nextCell.getStringCellValue() + "'");
        }

        if (!pairRowIterator.hasNext())
            return false;

        String subjectNameTeacher = pairRowIterator.next().getStringCellValue();
        if (subjectNameTeacher.isEmpty())
            return false;

        String[] subjectNameTeacherList = subjectNameTeacher.split(",");
        String subjectName = subjectNameTeacherList[0].trim();
        String subjectTeacherName = "No teacher";
        String rank = "no rank";
        if (subjectNameTeacherList.length > 1) {
            String[] rankName = splitOnRankAndName(subjectNameTeacherList[1].trim());
            rank = rankName[0];
            subjectTeacherName = rankName[1].trim();
        }

        Cell groupNumberCell = pairRowIterator.next();
        int subjectGroupNumber;
        if (groupNumberCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            subjectGroupNumber = (int) groupNumberCell.getNumericCellValue();
        } else {
            subjectGroupNumber = 0;
        }

        Cell weekCell = pairRowIterator.next();
        Set<Integer> weeks;
        if (weekCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            weeks = new HashSet<>();
            weeks.add((int) weekCell.getNumericCellValue());
        } else {
            weeks = parseWeek(weekCell.getStringCellValue());
        }

        String format = pairRowIterator.next().getStringCellValue();

        Teacher teacher = new Teacher(subjectTeacherName, facultyName, rank);
        this.resultTeacherSet.add(teacher);

        Subject subject = new Subject(subjectName, teacher, time, subjectGroupNumber, this.dayOfWeek, weeks, specialityName, format, facultyName);
        if (resultSubjectSet.contains(subject)) {
            // awful
            for (Subject f : resultSubjectSet) {
                if (f.equals(subject)) {
                    f.getWeeks().addAll(subject.getWeeks());
                    break;
                }
            }
        } else
            this.resultSubjectSet.add(subject);

        // System.out.println(resultSubjectList.get(resultSubjectList.size() - 1));

        return true;
    }

    private String[] splitOnRankAndName(String trim) {
        Matcher matcher = RANK_NAME_PATTERN.matcher(trim);
        String rank = "";
        String name = trim;

        if (matcher.matches()) {
            rank = matcher.group(1);
            name = matcher.group(2);
            // System.out.println(name + "  |||  " + rank);
        } else {
            System.err.println("Undefined rank: " + trim);
        }

        return new String[]{rank, name};
    }

    /**
     * Converts String of int values to Set<Integer>
     *
     * @return weekSet
     * Set<Integer>, sample {1,2,3,4,6,8,9,10}
     *
     * @param week
     * String, sample "1-3,4,6,8-10"
     */
    private static Set<Integer> parseWeek(String week) {
        String[] periods = week.split(",");

        for (int i = 0; i < periods.length; ++i) {
            periods[i] = periods[i].strip();
        }
        Set<String> res = new HashSet<>();

        for (String period : periods) {
            if (period.contains("-")) {
                Integer[] beginEnd = Arrays.stream(period.split("-")).map(String::trim).map(Integer::valueOf).toArray(Integer[]::new);

                for (int i = beginEnd[0]; i <= beginEnd[1]; ++i) {
                    res.add(String.valueOf(i));
                }
            } else {
                res.add(period);
            }
        }

        return res.stream().map(Integer::valueOf).collect(Collectors.toSet());
    }

    private Row iterateUntilCellWithStringValue(Iterator<Row> rowIterator) {
        while (true) {
            if (!rowIterator.hasNext())
                return null;

            Row nextRow = rowIterator.next();

            Iterator<Cell> cellIterator = nextRow.cellIterator();

            // empty row
            if (!cellIterator.hasNext())
                continue;

            Cell cell = cellIterator.next();

            if (cell.getCellType() == Cell.CELL_TYPE_STRING)
                return nextRow;
        }
    }

    public Set<Subject> getResultSubjectSet() {
        return resultSubjectSet;
    }

    public Set<Teacher> getResultTeacherSet() {
        return resultTeacherSet;
    }

    // govnocode
    private final Map<String, String> ukrEngDayOfWeekTranslation = new HashMap<>() {{
        put("понеділок", "monday");
        put("вівторок", "tuesday");
        put("середа", "wednesday");
        put("четвер", "thursday");
        put("п'ятниця", "friday");
        put("субота", "saturday");
    }};
    private final Map<String, Integer> ukrIntDayOfWeekTranslation = new HashMap<>() {{
        put("понеділок", 1);
        put("вівторок", 2);
        put("середа", 3);
        put("четвер", 4);
        put("п'ятниця", 5);
        put("субота", 6);
    }};

    private DayOfWeek ukrDayOfWeek(String dayOfWeek) {
        return DayOfWeek.valueOf(ukrEngDayOfWeekTranslation.get(dayOfWeek).toUpperCase());
    }
}
