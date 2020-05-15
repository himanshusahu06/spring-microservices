package org.hsahu.springboot.dao;

import org.hsahu.springboot.dto.User;
import org.hsahu.springboot.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoService {

    private static final List<User> userList = new ArrayList<>();

    private static int userCount = 2;

    static {
        userList.add(new User(1, "Himanshu Sahu", "foo@bar.com", new Date()));
        userList.add(new User(2, "Adam Berry", "adam.berry@bar.com", new Date()));
    }

    public List<User> findAllUsers() {
        return userList;
    }

    public User createUser(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        userList.add(user);
        return user;
    }

    public User findUserById(Integer userId) throws UserNotFoundException {
        Optional<User> user = userList.stream().filter(x -> x.getId().equals(userId)).findFirst();
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserNotFoundException("User with user id %d could not be found in the database.", userId);
    }

    public void deleteUserById(Integer userId) throws UserNotFoundException {
        findUserById(userId);
        userList.removeIf(u -> u.getId().equals(userId));
    }
}
