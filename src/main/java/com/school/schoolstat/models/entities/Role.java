package com.school.schoolstat.models.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_role")
@Data @NoArgsConstructor @AllArgsConstructor
public class Role implements Serializable {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    //@ManyToMany(fetch = FetchType.EAGER, targetEntity = Account.class, mappedBy = "roles", cascade = { CascadeType.REFRESH })
    //private List<Account> users = new ArrayList<Account>();

    public Role(String name) {
        this.name = name;
    }


}