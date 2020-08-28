/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.mmj.service1.resources;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.mmj.service1.domain.model.entity.ErrorProfile;
import com.project.mmj.service1.domain.model.entity.User;
import com.project.mmj.service1.domain.repository.ErrorRepository;
import com.project.mmj.service1.domain.repository.UserRepository;
import com.project.mmj.service1.domain.valueobject.Alert;
import com.project.mmj.service1.domain.valueobject.View;
import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Neumann
 */
@RestController
@RequestMapping("/api")
public class WebController {

    UserRepository userRepository;

    ErrorRepository errorRepository;

    @Autowired
    public WebController(UserRepository userRepository, ErrorRepository errorRepository) {
        this.userRepository = userRepository;
        this.errorRepository = errorRepository;
    }

    @PostMapping("/profiles/set")
    @JsonView(View.Foo.class)
    public ResponseEntity<?> add(@RequestBody User user) {
        User userFor;
        try {
            if (userRepository.findByEmail(user.getEmail()).size()>0) {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            } else {
                userFor = userRepository.save(new User(user.getName(), user.getEmail(), user.getAge()));
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return userFor != null ? ResponseEntity.status(HttpStatus.CREATED).body(userFor)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Alert("Запись не найдена"));

    }

    @GetMapping("/profiles/last")
    public ResponseEntity<?> findLast() {
        return new ResponseEntity<>(userRepository.findFirstByOrderByDateDesc(), HttpStatus.OK);
    }

    @GetMapping("/profiles")
    @JsonView(View.FooBar.class)
    public ResponseEntity<Collection<User>> getAll() {
        Collection<User> users;
        try {
            users = (Collection<User>) userRepository.findAll();
        } catch (Exception ex) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return users.size() > 0 ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping("/profiles/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") long id) {
        Optional<User> objToUse;
        try {
            objToUse = userRepository.findById(id);
        } catch (DataAccessException ex) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return objToUse.isPresent() ? new ResponseEntity<>(objToUse.get(), HttpStatus.OK)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Alert("Запись не найдена"));

    }

    @RequestMapping(value = "/profiles/get", method = RequestMethod.POST)
    public ResponseEntity<?> get(@RequestBody Map<String, Object> payload) {
        Collection<User> userFor;
        try {
            userFor = userRepository.findByEmail((String) payload.get("email"));

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return userFor.size() > 0 ? new ResponseEntity<>(userFor, HttpStatus.OK)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRepository.save(new ErrorProfile("Профиль не найден")));
    }

    @RequestMapping("/save")
    public String process() {
        // save a single Customer
        userRepository.save(new User("Jack", "test@somemail.com", 29));
        userRepository.save(new User("Adam", "my@yahoo.jp", 34));
        userRepository.save(new User("Kim", "123@gmail.com", 22));
        return "Done";
    }

    @RequestMapping(value = "/error/last", method = RequestMethod.GET)
    public ResponseEntity<?> getLastError() {
        Collection<ErrorProfile> error;
        error = (Collection<ErrorProfile>) errorRepository.findFirstByOrderByDateDesc();

        return error.size() > 0 ? new ResponseEntity<>(error, HttpStatus.OK)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ошибки не найдены");
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public ResponseEntity<?> getExit() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:8010/exit-success"));
        return new ResponseEntity<>("Приложение закрыто", headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @RequestMapping(value = "/exit-success", method = RequestMethod.GET)
    public ResponseEntity<?> getExitRedirect() {
        return new ResponseEntity<>("Приложение закрыто", HttpStatus.OK);
    }
}
