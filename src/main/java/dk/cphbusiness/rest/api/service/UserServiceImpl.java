package dk.cphbusiness.rest.api.service;

import dk.cphbusiness.rest.api.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static List<User> users;

    static {
        users = populateDummyUsers();
    }

    @Override
    public List<User> findByRole(String role) {
        List<User> matched = new ArrayList<>();
        for (User user : users) {
            if (user.getRole().equalsIgnoreCase(role)) {
                matched.add(user);
            }
        }
        return matched;
    }

    @Override
    public User findByUserName(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        users.add(user);
    }

    @Override
    public void deleteUserByUserName(String userName) {
        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if (user.getUserName().equals(userName)) {
                iterator.remove();
            }
        }
    }

    @Override
    public boolean exist(User user) {
        return users.contains(user);
    }

    private static List<User> populateDummyUsers() {
        List<User> users = new ArrayList<User>();
        users.add(new User("John", "password", "user"));
        users.add(new User("Jill", "password", "user"));
        users.add(new User("James", "password", "user"));
        users.add(new User("Antony", "megaSecurePassword", "admin"));
        return users;
    }
}
