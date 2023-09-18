package com.nagarro.UsersNotes.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email; // Change "username" to "email"

    private String password;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Notes> notes;

    // Constructors, getters, and setters
    public Users() {
        // Default constructor
    }
    
    public Users(String email, String password)
    {
        // Default constructor
    	this.email = email;
        this.password = password;
    }
    

    // Getters and setters for email (formerly username)
    public String getEmail() {
        return email;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public List<Notes> getNotes() {
//		return notes;
//	}
//
//	public void setNotes(List<Notes> notes) {
//		this.notes = notes;
//	}

	public void setEmail(String email) {
        this.email = email;
    }

    // Getters and setters for other fields (password, notes, etc.)
}