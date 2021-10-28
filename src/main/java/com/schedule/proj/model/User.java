package com.schedule.proj.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Email(message = "Email should be valid")
    private String email;

    @NotNull
    @Size(min = 10, max = 30,
            message = "First name must be between 10 and 30 characters")
    private String password;

    @NotNull
    @Size(min = 4, max = 32,
            message = "First name must be between 4 and 32 characters")
    private String firstName;

    @NotNull
    @Size(min = 4, max = 32,
            message = "Last name must be between 4 and 32 characters")
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

    public int getId() {
        return userId;
    }

    public Role getRole() {
        return userRole;
    }

    public void setRole(Role role) {
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
