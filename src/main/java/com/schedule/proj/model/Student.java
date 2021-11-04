package com.schedule.proj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@DiscriminatorValue("2")
@DiscriminatorOptions(force=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User {
    @NotNull
    @NotEmpty
    private String faculty;

    @NotNull
    @NotEmpty
    private String speciality;

    @Min(value = 1, message = "Year must not be less than 1")
    @Max(value = 6, message = "Year must not be greater than 6")
    @Column(name = "student_year")
    private Integer studentYear;

    @Column(name="subjects_list")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Subject> subjectsList;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "accounts_id")
    private Accounts accounts;

    public Student(UserRole userRole, String email, String password, String firstName, String lastName,
                   String avatar, String speciality, String faculty, Integer studentYear) {
        super(userRole, email, password, firstName, lastName, avatar);
        this.speciality = speciality;
        this.faculty = faculty;
        this.studentYear = studentYear;
    }
}
