package com.schedule.proj.service;

import com.schedule.proj.model.Student;
import com.schedule.proj.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService
{
    @Autowired
    private StudentRepository studentRepository;

    public Student createPerson(Student person) {
        return studentRepository.save(person);
    }

    public Optional<Student> getPerson(Long id) {
        return studentRepository.findById(id.intValue());
    }

    public Student editPerson(Student person) {
        return studentRepository.save(person);
    }

    public void deletePerson(Student person) {
        studentRepository.delete(person);
    }

    public void deletePerson(Long id) {
        studentRepository.deleteById(id.intValue());
    }

    public List<Student> getAllPersons() {
        return studentRepository.findAll();
    }

    public long countPersons() {
        return studentRepository.count();
    }
}
