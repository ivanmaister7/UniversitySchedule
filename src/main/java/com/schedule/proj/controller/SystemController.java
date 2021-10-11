package com.schedule.proj.controller;


import com.schedule.proj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.AccountRepository;

@RestController
@RequestMapping("/system")
public class SystemController {


    @Autowired
    private  final AccountRepository accountRepository;

    public SystemController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String registerRoles() {

       try {
            int NUMBER_ADMINS = 1;
            int NUMBER_TEACHER = 3;
            int NUMBER_STUDENTS = 15;

            createAdmin(NUMBER_ADMINS);
            createTeacher(NUMBER_TEACHER);
            createStudents(NUMBER_STUDENTS);

            return "tables added";
        } catch (Exception ex) {
            return "The DataBase might have a set of test values, and method  " +
                    "\"GET http://localhost:8080/system/add\" " +
                    "was already called   \n \n Error:   " + ex.getMessage();
        }
    }

    private void createStudents(int number_students) {
        for (int i = 1; i <= number_students; i++) {

            User user = createOneUser(i, Role.ADMIN);
            accountRepository.save(createOneAccount(user, i));
        }
    }

    private void createTeacher(int number_teacher) {
        for (int i = 1; i <= number_teacher; i++) {

            User user = createOneUser(i, Role.TEACHER);
            accountRepository.save(createOneAccount(user, i));
        }
    }
    private void createAdmin(int numberAdmins) {
        for (int i = 1; i <= numberAdmins; i++) {

            User user = createOneUser(i, Role.STUDENT);
            accountRepository.save(createOneAccount(user, i));
        }
    }
    private Accounts createOneAccount(User user, int i) {
        Accounts a = new Accounts();
        a.setUser(user);
        return a;
    }

    private User createOneUser(int i, Role role) {

        User n = new User();
        n.setEmail(i + "_" + role.name() + "@email.com");
        n.setPassword("password");
        n.setFirst_name(i + "_" + role.name() + "FN");
        n.setLast_name(i + "_" + role.name() + "LN");
        n.setRole(role);
        return n;
    }



    public String getUser() {
        return "hi authentificaters";
    }


}
