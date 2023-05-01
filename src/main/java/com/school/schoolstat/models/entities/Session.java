package com.school.schoolstat.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_session")
@Data @NoArgsConstructor @AllArgsConstructor
public class Session implements Serializable {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String username;

    private LocalDateTime lastConnectionAt;

    private LocalDateTime lastDisconnectionAt;


    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
