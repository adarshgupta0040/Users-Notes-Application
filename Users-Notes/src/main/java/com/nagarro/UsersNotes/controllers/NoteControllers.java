package com.nagarro.UsersNotes.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nagarro.UsersNotes.models.Notes;
import com.nagarro.UsersNotes.models.Users;
import com.nagarro.UsersNotes.repository.NoteRepository;
import com.nagarro.UsersNotes.repository.UserRepository;
import com.nagarro.UsersNotes.services.NoteService;

@RestController
@CrossOrigin("http://localhost:4200")
public class NoteControllers {
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NoteService noteService;
    
	// for testing
	@GetMapping("/")
	public String test() {
		return "Welcome to User_Notes Application ! This is NoteController";
	}
	
	
	@PostMapping("/addNote")
	public ResponseEntity<String> addNote(@RequestBody Notes note) {

	    noteRepository.save(note);

	    return new ResponseEntity<>("Note added successfully", HttpStatus.CREATED);
	}
	
	
	@GetMapping("/recent")
	public List<Notes> getRecentNotes(@RequestParam Long userId) {
		System.out.print("hi");
		System.out.print(userId);
//		List<Notes> recentNotes = null;
        List<Notes> recentNotes = noteService.getRecentNotesByUserId(userId);
        return recentNotes;
    }
	
	@PostMapping("/delete")
//  public ResponseEntity<List<Notes>> getRecentNotes(@RequestParam Long userId) {
	public void deleteNotes(@RequestBody Notes note ) {
       noteService.deleteNoteId(note.getId(),note.getUserId());

  }
	


	
	

}
