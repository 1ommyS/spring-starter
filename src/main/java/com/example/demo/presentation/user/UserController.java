package com.example.demo.presentation.user;

import com.example.demo.application.user.UserService;
import com.example.demo.domain.entity.User;
import com.example.demo.presentation.user.dto.commands.CreateUserCommand;
import com.example.demo.presentation.user.dto.commands.UpdateUserCommand;
import com.example.demo.presentation.user.dto.queries.UserQuery;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
class UserController {
    private UserService service;
    private ModelMapper modelMapper;


    /* REST API
    1) получить все данные: GET: name_of_entity
    2) получить 1 запись: GET name_of_entity/id
    3) создать 1 запись: POST name_of_entity + RequestBody
    4) Обновить 1 запись: PUT name_of_entity/id + RequestBody
    5) Удалить запись: DELETE name_of_entity/id
     */

    // GET - получить данные, post - создать, put - обновить, delete - удалить
    @GetMapping
    public List<UserQuery> getAll() {
        return service.getAll().stream()
                .map(user -> modelMapper.map(user, UserQuery.class))
                .toList();
    }

    @GetMapping("/{id}")
    public UserQuery getById(@PathVariable Integer id) {
        return modelMapper.map(service.getById(id), UserQuery.class);
    }

    @PostMapping
    public UserQuery create(@RequestBody CreateUserCommand command) {
        User userFromCommand = modelMapper.map(command, User.class);

        User user = service.create(userFromCommand);

        UserQuery userQueryResponse = modelMapper.map(user, UserQuery.class);

        return userQueryResponse;
    }
    @PutMapping("/{id}")
    public UserQuery update(@PathVariable Integer id, @RequestBody UpdateUserCommand command) {
        User userFromCommand = modelMapper.map(command, User.class);

        User user = service.update(id, userFromCommand);

        UserQuery userQueryResponse = modelMapper.map(user, UserQuery.class);

        return userQueryResponse;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    /*@GetMapping
    public User getById(@RequestParam("id") Integer id) {
        return service.getById(id);
    }*/
}