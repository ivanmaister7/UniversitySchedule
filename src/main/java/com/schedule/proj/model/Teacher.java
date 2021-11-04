package com.schedule.proj.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@DiscriminatorValue("3")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends User {
    @NotNull
    @NotEmpty
    private String faculty;

    @NotNull
    @NotEmpty
    private String cathedra;

    @NotEmpty
    private String rank;

    @OneToMany(mappedBy = "subjectTeacher", cascade = CascadeType.ALL)
    @JsonIgnore
    // todo: remove jsonignore
    private Set<Subject> subjects;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "accounts_id")
    private Accounts accounts;

    public Teacher(UserRole userRole, String email, String password, String firstName, String lastName,
                   String avatar, String faculty, String cathedra, String rank) {
        super(userRole, email, password, firstName, lastName, avatar);
        this.faculty = faculty;
        this.cathedra = cathedra;
        this.rank = rank;
    }
}
