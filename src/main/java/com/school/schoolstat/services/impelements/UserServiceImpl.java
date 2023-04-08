package com.school.schoolstat.services.impelements;

import com.school.schoolstat.models.entities.User;
import com.school.schoolstat.services.interfaces.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User retrieveUser(Long id) {
        return null;
    }

    @Override
    public List<User> retrieveUser() {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(User user) {

    }
}
