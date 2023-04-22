package com.school.schoolstat.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "t_exam_sub_center")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class ExamSubCenter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 127, unique = true)
    private String label;

    @ManyToOne
    @JoinColumn(name = "exam_center_id", nullable = false)
    private ExamCenter examCenter;
}
