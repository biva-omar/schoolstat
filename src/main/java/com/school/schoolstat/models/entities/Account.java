package com.school.schoolstat.models.entities;

import java.util.ArrayList;
import java.util.Collection;

import com.school.schoolstat.models.enums.AccounStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_account")
@Data @NoArgsConstructor @AllArgsConstructor
public class Account implements Serializable {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String pseudo;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private String password;

    private boolean disable;

    private boolean isEmailValidated = false;

    private boolean isPhoneValidated = false;

    private String phoneOtp;

    private String emailOtp;

    private String avatar;

    private AccounStatus Status;

    @ManyToMany(targetEntity=Role.class, fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinTable( name = "t_users_roles"
            , joinColumns = @JoinColumn( name = "role_id" ),
            inverseJoinColumns = @JoinColumn( name = "user_id" ) )
    private Collection<Role> roles = new ArrayList<Role>();

    /* referencement des sessions de l'utilisateur sans stockage des valeur des liasons
     * le skockage des liaisons se font dans sesions */
    //@ReadOnlyProperty
    //private List<Session> sessions;


}

