package com.nagarro.UsersNotes.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.UsersNotes.models.Notes;
import com.nagarro.UsersNotes.models.Users;
import com.nagarro.UsersNotes.repository.NoteRepository;
import com.nagarro.UsersNotes.repository.UserRepository;
import com.nagarro.UsersNotes.services.NoteService;

@Service
public class NoteServiceImpl implements NoteService{

	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
    private UserRepository userRepository;

	
    
    
//    public Notes createNote(Long userId, String content) {
//        Users user = userRepository.findById(userId).orElse(null);
//        if (user != null) {
//            Notes note = new Notes(content, user);
//            return noteRepository.save(note);
//        }
//        return null;
//    }
    
    @Override
    public List<Notes> getRecentNotesByUserId(Long userId) {
    	
        // Implement logic to retrieve recent 10 notes for a user
        // and limit the result to the last 10 entries.
        List<Notes> recentNotes = noteRepository.findTop10ByUserIdOrderByCreatedAtDesc(userId);
        System.out.print(recentNotes);
        return recentNotes;
    }

    
    public void deleteNoteId(Long NoteId, Long UserId) {
        // Implement logic to delete old notes for a user
    	noteRepository.deleteByIdAndUserId(NoteId, UserId);
    }
    
    
    
}
