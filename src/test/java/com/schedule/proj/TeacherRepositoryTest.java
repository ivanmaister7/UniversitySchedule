package com.schedule.proj;

import com.schedule.proj.repository.TeacherRepository;
import com.schedule.proj.repository.UserRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TeacherRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private TeacherRepository teacherRepository;
    @Test
    public void getTeacherTest() {
        var teacher = teacherRepository.getByUserId(1);
        Assert.assertEquals(teacher.getCathedra(),"Myltimedia");
    }

}
