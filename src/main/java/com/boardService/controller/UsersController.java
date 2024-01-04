package com.boardService.controller;

import com.boardService.models.Users;
import com.boardService.repository.UsersRepository;
import com.boardService.service.UsersService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController extends BaseController<UsersService, Users, String, UsersRepository> {

    @Autowired
    private UsersService usersService;

    @Override
    @PostMapping
    public ResponseEntity<Users> add(@RequestBody Users user) {
        final Users newUser = usersService.add(user);
        if (newUser == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }
}
