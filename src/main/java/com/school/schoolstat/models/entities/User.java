package com.school.schoolstat.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class User implements Serializable {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String profile;

    private String firstname;

    private String lastname;

    private Date bornAt;

    private String gender;

    private String idCartRecto;

    private String idCartVerso;

    private String profession;

    private String country;

    private String city;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = true)
    private Account account;
}
