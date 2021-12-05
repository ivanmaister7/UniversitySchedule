package com.schedule.proj.service;


import com.schedule.proj.model.Cooperation;
import com.schedule.proj.model.DTO.SubjectGroupDTO;
import com.schedule.proj.model.Student;
import com.schedule.proj.model.Subject;
import com.schedule.proj.repository.*;
import com.schedule.proj.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class CooperationService {

    @Autowired
    CooperationRepository cooperationRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    SubjectRepository subjectRepository;

    public String createCooperation(HttpServletRequest request , SubjectGroupDTO dto){
        String token = jwtProvider.getTokenFromRequest(request);
        String email = jwtProvider.getLoginFromToken(token);
        Subject subject = subjectRepository.findBySubjectGroupAndSubjectName(dto.getGroup(), dto.getSubjectname());
        Student student = studentRepository.getById(userRepository.findUserByEmail(email).getId());
        Cooperation c =null;
            try {
                c =cooperationRepository.coopIsPresent(student.getStudentId(),subject.getSubjectId());
                if(c.getStatus() != true)
                    throw  new RuntimeException("Student learn this subject");
            }
            catch(NullPointerException exception){
                c =new Cooperation();
                c.setStatus(true);
            }
            finally {
                    cooperationRepository.save(c);
            }
        return  "Student add this subject";
    }
    public Boolean showInformation(HttpServletRequest request,int subjectId){
        String token = jwtProvider.getTokenFromRequest(request);
        String email = jwtProvider.getLoginFromToken(token);
        Student student = studentRepository.getById(userRepository.findUserByEmail(email).getId());
        return (checkInfo(student.getStudentId(),subjectId));
    }

    public Boolean checkInfo(int sudentId,int subjectId){
        Cooperation cooperation = cooperationRepository.coopIsPresent(sudentId,subjectId);
        if(cooperation == null)  return false;
        if(cooperation.getStatus() == true)
            return true;
        else return false;
    }
}
