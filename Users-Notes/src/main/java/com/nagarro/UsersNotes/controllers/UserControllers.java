package com.nagarro.UsersNotes.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.nagarro.UsersNotes.models.Users;
import com.nagarro.UsersNotes.repository.UserRepository;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserControllers {
	
	@Autowired
    private UserRepository userRepository;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Users user) {
		// Check if the user with the given email already exists
		if (userRepository.findByEmail(user.getEmail()) != null) {
			return new ResponseEntity<>("User with this email already exists", HttpStatus.BAD_REQUEST);
		}

		// Encrypt the user's password before saving it
		String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
		user.setPassword(hashedPassword);

		// Save the user to the database
		userRepository.save(user);

		return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
	}
	
	
	@PostMapping("/login")
    public ResponseEntity<Map<String,Object>> loginUser(@RequestBody Users loginUser) {
        // Find the user by email
        Users user = userRepository.findByEmail(loginUser.getEmail());

        // Check if the user exists
        if (user == null) {
            return new ResponseEntity<>(Collections.singletonMap("message","User not found"), HttpStatus.NOT_FOUND);
        }

        // Check if the provided password matches the hashed password in the database
        if (BCrypt.checkpw(loginUser.getPassword(), user.getPassword())) {
        	Map<String,Object> response = new HashMap<>();
        	response.put("message", "Login successful");
        	response.put("id", user.getId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("message","Invalid email or password"), HttpStatus.UNAUTHORIZED);
        }
    }

}
