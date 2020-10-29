package com.neelav.springbootcassandra.controller;

import com.neelav.springbootcassandra.models.User;
import com.neelav.springbootcassandra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void saveUser()
    {
        List<User> users = new ArrayList<>();
        users.add( new User(5,"Neelav","Ranchi",24));
        users.add( new User(10,"Leo Messi","Rosario",32));
        users.add( new User(8,"Andres Iniesta","Barcelona",26));
        users.add( new User(11,"Neymar","Santos",28));

        userRepository.saveAll(users);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @GetMapping("/ageGreaterThan/{age}")
    public List<User> getUsersAbove25(@PathVariable Integer age)
    {
        Optional<List<User>> userListOptional = userRepository.findByAgeGreaterThan(age);

        if (userListOptional.isPresent())
           return userListOptional.get();

        else
            throw new NullPointerException();
    }


}
