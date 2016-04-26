package dk.cphbusiness.rest.api.controller;

import dk.cphbusiness.rest.api.model.User;
import dk.cphbusiness.rest.api.service.UserService;
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


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users/{role}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findByRole(@PathVariable String role) {
        List<User> users = userService.findByRole(role);
        if (CollectionUtils.isEmpty(users)) {
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userName}", method = RequestMethod.GET)
    public ResponseEntity<User> findByUserName(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/new", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getUserName());

        if (userService.exist(user)) {
            System.out.println("A User with name " + user.getUserName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.saveUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{userName}").buildAndExpand(user.getUserName()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
