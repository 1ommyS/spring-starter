package com.example.demo.application.user;

import com.example.demo.domain.entity.User;
import com.example.demo.infrastructure.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(Integer id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User create(User userFromCommand) {
        User user = repository.save(userFromCommand);

        return user;
    }

    public User update(Integer id, User userFromCommand) {
        var user = getById(id);

        if (!user.getName().equals(userFromCommand.getName())) user.setName(userFromCommand.getName());

        User saved = repository.save(user);

        return saved;
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
