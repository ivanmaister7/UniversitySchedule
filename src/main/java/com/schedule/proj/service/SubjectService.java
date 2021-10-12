package com.schedule.proj.service;

import com.schedule.proj.model.Student;
import com.schedule.proj.model.Subject;
import com.schedule.proj.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public Subject createPerson(Subject person) {
        return subjectRepository.save(person);
    }

    public Optional<Subject> getPerson(Long id) {
        return subjectRepository.findById(id.intValue());
    }

    public Subject editPerson(Subject person) {
        return subjectRepository.save(person);
    }

    public void deletePerson(Subject person) {
        subjectRepository.delete(person);
    }

    public void deletePerson(Long id) {
        subjectRepository.deleteById(id.intValue());
    }

    public List<Subject> getAllPersons() {
        return subjectRepository.findAll();
    }

    public long countPersons() {
        return subjectRepository.count();
    }
}
