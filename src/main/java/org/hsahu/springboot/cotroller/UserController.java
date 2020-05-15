package org.hsahu.springboot.cotroller;

import org.hsahu.springboot.dao.UserDaoService;
import org.hsahu.springboot.dto.User;
import org.hsahu.springboot.exception.UserNotFoundException;
import org.hsahu.springboot.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping
    public List<User> getAllUsers() {
        return userDaoService.findAllUsers();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody  User user) {
        User savedUser  =  userDaoService.createUser(user);
        URI createdUriLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        // location will be set in headers
        return ResponseEntity.created(createdUriLocation).build();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer userId) throws UserNotFoundException {
        Assert.notNull(userId, "User Id to retrieve can not be null and must be integer.");
        return userDaoService.findUserById(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Integer userId) throws UserNotFoundException {
        Assert.notNull(userId, "User Id to delete can not be null and must be integer.");
        userDaoService.deleteUserById(userId);
    }
}
