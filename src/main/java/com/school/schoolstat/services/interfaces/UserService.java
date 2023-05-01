package com.school.schoolstat.services.interfaces;

import com.school.schoolstat.models.dto.requests.UserRequestDto;
import com.school.schoolstat.models.entities.User;

import java.util.List;

public interface UserService {

    public User createUser(User user);

    public User retrieveUser(Long id);

    public List<User> retrieveUser();

    public User updateUser(User user);

    public void deleteUser(User user);

    /**
     * Enregistre un utilisateur donné dans la BDD
     * @param user
     * @param username
     * @param password
     * @return l'utilisateur enregistré
     */
    public User save(UserRequestDto user, String username, String password);

    /**
     * Retourne l'utilisateur ayant un compte donné
     * @param accId
     * @return un objet User
     */
    public User findUserByAccount(String accId);

}
