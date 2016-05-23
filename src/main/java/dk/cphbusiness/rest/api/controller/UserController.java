package dk.cphbusiness.rest.api.controller;

import dk.cphbusiness.rest.api.model.User;
import dk.cphbusiness.rest.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/users/{role}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findByRole(@PathVariable String role) {
        List<User> users = userRepository.findByRoleIgnoreCase(role);
        if (CollectionUtils.isEmpty(users)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userName}", method = RequestMethod.GET)
    public ResponseEntity<User> findByUserName(@PathVariable String userName) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/new", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getUserName());

        if (userRepository.exists(user.getUserName())) {
            System.out.println("A User with name " + user.getUserName() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        userRepository.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{userName}").buildAndExpand(user.getUserName()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
