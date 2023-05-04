package com.school.schoolstat.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_student")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    private String lastname;

    private Date birthday;

    @Column(name = "c_birth_place")
    private String birthplace;

    private String sex;

    private String tutorPhone;

    private boolean isPresent = true;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

}
