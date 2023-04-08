package com.school.schoolstat.services.interfaces;

import com.school.schoolstat.models.entities.User;

import java.util.List;

public interface UserService {

    public User createUser(User user);

    public User retrieveUser(Long id);

    public List<User> retrieveUser();

    public User updateUser(User user);

    public void deleteUser(User user);

}
