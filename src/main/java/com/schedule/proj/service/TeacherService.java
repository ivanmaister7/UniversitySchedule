package com.schedule.proj.service;

import com.schedule.proj.exсeption.DuplicateUserEmailException;
import com.schedule.proj.exсeption.TeacherNotFoundException;
import com.schedule.proj.exсeption.SubjectNotFoundException;
import com.schedule.proj.model.Teacher;
import com.schedule.proj.model.Subject;
import com.schedule.proj.repository.SubjectRepository;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.schedule.proj.repository.TeacherRepository;


import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private static final Logger logger = LogManager.getLogger();
    final static Marker MARKER_TEACHER = MarkerManager.getMarker("TeacherService");

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, SubjectRepository subjectRepository) {
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
    }

    public Teacher createTeacher(Teacher teacher) {

        return teacherRepository.save(teacher);
    }

    public Teacher getTeacher(Long id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);

        if (optionalTeacher.isEmpty())
            throw new TeacherNotFoundException();

        return optionalTeacher.get();
    }

    public void deleteTeacher(Teacher teacher) {
        teacherRepository.delete(teacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public long countTeachers() {
        return teacherRepository.count();
    }

    public Teacher addTeacher(Teacher teacher) {
        Teacher t = teacherRepository.save(teacher);
        ThreadContext.put("username",t.getFirstName() + " "+teacher.getLastName() );
        ThreadContext.put("ID", t.getUserId().toString());
        logger.info(MARKER_TEACHER,"Create teacher");
        ThreadContext.clearMap();
        return t;
    }

    @Transactional
    public Teacher updateTeacher(Teacher newTeacher) {
        Teacher teacher = teacherRepository.findById(newTeacher.getUserId())
                .orElseThrow(TeacherNotFoundException::new);

        if (newTeacher.getEmail() != null) {
//            if (teacherRepository.findByEmail(newTeacher.getEmail()).isPresent())
//                throw new DuplicateUserEmailException();

            teacher.setEmail(newTeacher.getEmail());
        }

        if (newTeacher.getCathedra() != null) {
            teacher.setCathedra(newTeacher.getCathedra());
        }

        if (newTeacher.getFaculty() != null) {
            teacher.setFaculty(newTeacher.getFaculty());
        }

        return teacher;
    }
    
}
