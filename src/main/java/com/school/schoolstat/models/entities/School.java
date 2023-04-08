package com.school.schoolstat.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "t_school")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class School implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private String teachingOrder;

    @ManyToOne
    @JoinColumn(name = "exam_sub_center_id")
    private ExamSubCenter examSubCenter;
}
