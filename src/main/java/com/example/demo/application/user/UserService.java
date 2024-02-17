package com.example.demo.application.user;

import com.example.demo.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    public static List<User> users = new ArrayList <>();
    
    public UserService() {
        appendUsers();
    }
    
    private static void appendUsers() {
        users.add(User.builder()
                    .id(123)
                    .name("dasdas")
                    .build());
        users.add(User.builder()
                    .id(3123)
                    .name("dasdacccccc")
                    .build());
        users.add(User.builder()
                    .id(91239)
                    .name("czxxcz")
                    .build());
    }
    
    public List<User> getAll() {
        return users;
    }

    public User getById(Integer id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public User create(User userFromCommand) {
        userFromCommand.setId((int) (Math.random() * 1000));

        users.add(userFromCommand);

        return userFromCommand;
    }

    public User update(Integer id, User userFromCommand) {
        User userFromDb = users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("такого пользователя нет"));

        if (!userFromDb.getName().equals(
                userFromCommand.getName()
        )) userFromDb.setName(
                userFromCommand.getName()
        );


        return userFromDb;
    }

    public void delete(Integer id) {
        users.removeIf(u -> u.getId().intValue() == id.intValue());
    }
}
