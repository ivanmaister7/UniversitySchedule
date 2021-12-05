package com.schedule.proj.service;

import com.schedule.proj.exсeption.SubjectNotFoundException;
import com.schedule.proj.model.DTO.SubjectGroupDTO;
import com.schedule.proj.model.DTO.TeacherGeneralResponseDTO;
import com.schedule.proj.model.Subject;
import com.schedule.proj.model.Teacher;
import com.schedule.proj.model.User;
import com.schedule.proj.model.UserRole;
import com.schedule.proj.repository.SubjectRepository;
import com.schedule.proj.repository.TeacherRepository;
import com.schedule.proj.repository.UserRepository;
import com.schedule.proj.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private static final Logger logger = LogManager.getLogger();
    final static Marker MARKER_SUBJECT = MarkerManager.getMarker("SubjectService");

    public Subject createSubject(Subject subject) {
        Subject t = subjectRepository.save(subject);
        logger.info(MARKER_SUBJECT,"Create subject");
        return t;
    }

    public Subject getSubject(Integer id) {
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

    public void deleteSubject(Integer id) {
        subjectRepository.deleteById(id);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public long countSubjects() {
        return subjectRepository.count();
    }

    public List<Subject> findTeachersSubjectByToken(HttpServletRequest request) {
        String token = jwtProvider.getTokenFromRequest(request);
        String email = jwtProvider.getLoginFromToken(token);
        User user = userRepository.findUserByEmail(email);
        Teacher teacher = teacherRepository.getByUserId(user.getId());
        return subjectRepository.findAllBySubjectTeacher(teacher);

    }

    public Subject findSubject(SubjectGroupDTO dto){
            return subjectRepository.findAllBySubjectGroupAndAndSubjectName(dto.getGroup(),dto.getSubjectname());
    }


}
