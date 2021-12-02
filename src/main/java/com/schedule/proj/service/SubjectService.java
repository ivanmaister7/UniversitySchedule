package com.schedule.proj.service;

import com.schedule.proj.exсeption.SubjectNotFoundException;
import com.schedule.proj.model.Subject;
import com.schedule.proj.model.Teacher;
import com.schedule.proj.repository.SubjectRepository;
import com.schedule.proj.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private static final Logger logger = LogManager.getLogger();
    final static Marker MARKER_SUBJECT = MarkerManager.getMarker("SubjectService");

    public Subject createSubject(Subject subject) {
        Subject t = subjectRepository.save(subject);
        logger.info(MARKER_SUBJECT,"Create subject");
        return t;
    }

    public Subject getSubject(Long id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);

        if (optionalSubject.isEmpty())
            throw new SubjectNotFoundException();

        return optionalSubject.get();
    }

    @Transactional
    public Subject updateSubject(Subject newSubject) {
        Subject subject = subjectRepository.findById(newSubject.getSubjectId())
                .orElseThrow(SubjectNotFoundException::new);

        if (newSubject.getSubjectName() != null) {
            subject.setSubjectName(newSubject.getSubjectName());
        }

        if (newSubject.getDayOfWeek() != null) {
            subject.setDayOfWeek(newSubject.getDayOfWeek());
        }

        if (newSubject.getSubjectTime() != null) {
            subject.setSubjectTime(newSubject.getSubjectTime());
        }

        if (newSubject.getSubjectGroup() != null) {
            subject.setSubjectGroup(newSubject.getSubjectGroup());
        }

        return subject;
    }

    public void deleteSubject(Subject subject) {
        subjectRepository.delete(subject);
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public long countSubjects() {
        return subjectRepository.count();
    }



}
