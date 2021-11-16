package com.schedule.proj.service.test_data_generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TestDataGenerator {

    public static void main(String[] args) throws IOException {
        File folder = new File("./src/main/resources/xlsx_data");
        File[] listOfFiles = folder.listFiles();

        Set<Subject> resultSubjectSet = new HashSet<>();
        Set<Teacher> resultTeacherSet = new HashSet<>();

        for (File nextFile : listOfFiles) {
            if (nextFile.isFile()) {
                XlsxFileParser xlsxFileParser = new XlsxFileParser(nextFile.getPath());
                xlsxFileParser.parseXlsxFile();
                resultSubjectSet.addAll(xlsxFileParser.getResultSubjectSet());
                resultTeacherSet.addAll(xlsxFileParser.getResultTeacherSet());
            } else if (nextFile.isDirectory()) {
                System.out.println("Directory " + nextFile.getName());
            }
        }

        List<Teacher> resultTeacherList = new ArrayList<>(resultTeacherSet);
        List<Subject> resultSubjectList = new ArrayList<>(resultSubjectSet);

        // resultSubjectList.forEach(System.out::println);

        LinkedList<String> resTeachers = new LinkedList<>();
        LinkedList<String> resSubjects = new LinkedList<>();
        LinkedList<String> resWeeks = new LinkedList<>();

        int lastIndex = 0;
        for (int i = 1; ; i += 100) {
            if (i > resultTeacherList.size()) {
                resTeachers.add(convertTeachersSetToDbSyntax(resultTeacherList.subList(
                        lastIndex, resultTeacherList.size())
                ));
                break;
            }

            resTeachers.add(convertTeachersSetToDbSyntax(resultTeacherList.subList(lastIndex, i)));
            lastIndex = i;
        }

        lastIndex = 0;
        for (int i = 1; ; i += 100) {
            if (i > resultSubjectList.size()) {
                resSubjects.add(convertSubjectsListToDbSyntax(resultSubjectList.subList(
                        lastIndex, resultSubjectList.size())
                ));
                break;
            }

            resSubjects.add(convertSubjectsListToDbSyntax(resultSubjectList.subList(lastIndex, i)));
            lastIndex = i;
        }

        lastIndex = 0;
        for (int i = 1; ; i += 10) {
            if (i > resultSubjectList.size()) {
                resWeeks.add(weeksTeacherInsert(resultSubjectList.subList(
                        lastIndex, resultSubjectList.size())
                ));
                break;
            }

            resWeeks.add(weeksTeacherInsert(resultSubjectList.subList(lastIndex, i)));
            lastIndex = i;
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/resources/data.sql"));

        writer.write("");
        resTeachers.forEach(x -> {
            try {
                writer.write(x);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        resSubjects.forEach(x -> {
            try {
                writer.write(x);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        resWeeks.forEach(x -> {
            try {
                writer.write(x);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

//        String subjectsDbInsertionList = convertSubjectsListToDbSyntax(resultSubjectList);
//        String weeksDbInsertion = weeksTeacherInsert(resultSubjectList);

//        writeFile("./src/main/resources/subjects_data.sql", subjectsDbInsertionList);
//        writeFile("./src/main/resources/teachers_data.sql", teachersDbInsertionList);
//        writeFile("./src/main/resources/weeks_data.sql", weeksDbInsertion);

//        writer.write(teachersDbInsertionList);
//        writer.append(subjectsDbInsertionList);
//        writer.append(weeksDbInsertion);

        writer.close();
    }

    private static void writeFile(String fileName, String str)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/resources/data.sql"));
        writer.write(str);

        writer.close();
    }

    private static void appendFile(String fileName, String str)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.append(str);

        writer.close();
    }

    private static String convertSubjectsListToDbSyntax(List<Subject> resultSubjectList) {
        // todo: protect from sql injection
        return "INSERT INTO SUBJECT " +
                "(user_id, subject_name, day_of_week, subject_time, subject_group, " +
                "subject_faculty, subject_speciality, education_format) " +
                "VALUES \n" +
                resultSubjectList
                        .stream()
                        .map(x -> String.format("(%s,'%s', %d, '%s', %d, '%s', '%s', '%s')",
                                "(SELECT t.TEACHER_ID FROM TEACHER t WHERE " +
                                        "t.FACULTY = '" + x.getTeacher().getFaculty() +
                                        "' AND (SELECT u.FIRST_NAME FROM USER u WHERE u.user_id = t.user_id) " +
                                        " = '" + x.getTeacher().getName() + "')",
                                x.getName(), x.getDayOfWeek().getValue(), x.getTime(),
                                x.getGroup(), x.getFaculty(), x.getSpeciality(), x.getEducationFormat()))
                        .collect(Collectors.joining(",\n")) + ";\n";
    }

    // todo: change if user is a field
    private static String convertTeachersSetToDbSyntax(List<Teacher> resultTeacherSet) {
        String userInsert = "INSERT INTO USER (first_name, last_name) VALUES \n" +
                resultTeacherSet
                        .stream()
                        .map(x -> String.format("('%s', '')", x.getName()))
                        .collect(Collectors.joining(",\n")) + ";\n";

        String teacherInsert = "INSERT INTO TEACHER (user_id, cathedra, faculty, rank) VALUES \n" +
                resultTeacherSet
                        .stream()
                        .map(x -> String.format("(%s, 'cat', '%s', '%s')",
                                "(SELECT t.USER_ID FROM USER t WHERE " +
                                        "t.FIRST_NAME = '" + x.getName() + "')",
                                x.getFaculty(), x.getRank()))
                        .collect(Collectors.joining(",\n")) + ";\n";

        return userInsert + '\n' + teacherInsert;
    }

    private static String weeksTeacherInsert(List<Subject> resultSubjectList) {
        return "INSERT INTO SUBJECT_WEEKS (SUBJECT_SUBJECT_ID, weeks) VALUES \n" +
                resultSubjectList.stream()
                .map(
                        x -> x.getWeeks().stream()
                        .map(y -> String.format("(SELECT s.SUBJECT_ID FROM SUBJECT s " +
                                "where (s.SUBJECT_NAME = '%s' AND s.SUBJECT_GROUP = %d AND " +
                                "s.SUBJECT_SPECIALITY = '%s' AND s.DAY_OF_WEEK = %d " +
                                "AND s.SUBJECT_TIME = '%s' AND s.EDUCATION_FORMAT = '%s' AND s.USER_ID = %s), " +
                                "%d)", x.getName(), x.getGroup(), x.getSpeciality(),
                                x.getDayOfWeek().getValue(), x.getTime(), x.getEducationFormat(),
                                "(SELECT t.TEACHER_ID FROM TEACHER t WHERE " +
                                "t.FACULTY = '" + x.getTeacher().getFaculty() +
                                        "' AND (SELECT u.FIRST_NAME FROM USER u WHERE u.user_id = t.user_id)" +
                                        " = '" + x.getTeacher().getName() + "')",
                                y))
                                .collect(Collectors.joining(",\n"))
                ).collect(Collectors.joining(",\n")) + ";\n";
    }
}
