package com.nagarro.UsersNotes.services;

import java.util.List;

import com.nagarro.UsersNotes.models.Notes;

public interface NoteService {
	List<Notes> getRecentNotesByUserId(Long userId);
//    Notes createNote(Long userId, String content);
    void deleteNoteId(Long NoteId,Long UserId);
}