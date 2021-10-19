package com.schedule.proj.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
//@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;

    @Enumerated(EnumType.STRING)
    private Role userRole;

    @Column(unique=true)
    private String email;

    private String password;
    private String firstName;
    private String lastName;

    @OneToOne (mappedBy = "user")
    private Accounts userAccounts;

    public User() {
    }

    public User(Role userRole,
                String email,
                String password,
                String firstName,
                String lastName,
                String avatar) {
        this.userRole = userRole;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public int getUserId() {
        return userId;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role role) {
        this.userRole = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId)  && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", role=" + userRole +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                '}';
    }
}
