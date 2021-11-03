package com.schedule.proj.service;

import com.schedule.proj.exсeption.StudentNotFoundException;
import com.schedule.proj.exсeption.SubjectNotFoundException;
import com.schedule.proj.model.Student;
import com.schedule.proj.model.Subject;
import com.schedule.proj.model.Teacher;
import com.schedule.proj.repository.StudentRepository;
import com.schedule.proj.repository.SubjectRepository;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService
{
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private static final Logger logger = LogManager.getLogger();
    final static Marker MARKER_STUDENT = MarkerManager.getMarker("StudentService");

    @Autowired
    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public Student createStudent(Student student) {
        Student t = studentRepository.save(student);
        ThreadContext.put("username",t.getFirstName() + " "+student.getLastName() );
        ThreadContext.put("ID", t.getUserId().toString());
        logger.info(MARKER_STUDENT,"Create student");
        ThreadContext.clearMap();
        return t;
    }

    public Student getStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isEmpty())
            throw new StudentNotFoundException();

        return optionalStudent.get();
    }

    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public long countStudents() {
        return studentRepository.count();
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Student newStudent) {
        Student student = studentRepository.findById(newStudent.getUserId())
                .orElseThrow(StudentNotFoundException::new);

        if (newStudent.getStudentYear() != null) {
            student.setStudentYear(newStudent.getStudentYear());
        }

        if (newStudent.getSpeciality() != null) {
            student.setSpeciality(newStudent.getSpeciality());
        }

        if (newStudent.getFaculty() != null) {
            student.setFaculty(newStudent.getFaculty());
        }

        // todo: manage subjects list,
        //  pass only ids: update the whole by id, or
        //  pass entities: create new course entities?

        return student;
    }

    @Transactional
    public void addSubject(Long studentId, Long subjectId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(StudentNotFoundException::new);

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(SubjectNotFoundException::new);

        student.getSubjectsList().add(subject);
    }
}
