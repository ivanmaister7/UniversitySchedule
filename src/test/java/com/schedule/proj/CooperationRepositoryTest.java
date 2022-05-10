package com.schedule.proj;

import com.schedule.proj.repository.CooperationRepository;
import com.schedule.proj.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CooperationRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CooperationRepository cooperationRepository;

    @Test
    public void getCooperationTest() {
        var cooperation = cooperationRepository.coopIsPresent(1,1);
        Assert.assertEquals(cooperation.getStatus(),true);
    }

}