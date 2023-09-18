package com.nagarro.UsersNotes.repository;

//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.nagarro.UsersNotes.models.Users;
//
//public interface UserRepository extends JpaRepository<Users, Long> {
//    // You can add custom query methods here if needed
//}


import org.springframework.data.jpa.repository.JpaRepository;


import com.nagarro.UsersNotes.models.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    // You can add custom query methods here if needed
	Users findByEmail(String email);
	
}