package com.nagarro.UsersNotes.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nagarro.UsersNotes.repository.NoteRepository;

//import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableScheduling
@EnableAsync
public class NoteCleanupScheduler {
	
	@Autowired
    private NoteRepository noteRepository;

	@Scheduled(cron = "0 0 * * * *") // Runs every hour
    public void deleteOldNotes() {
    	System.out.print("hello");
        // Delete all notes older than a certain threshold for each user
        LocalDateTime thresholdTime = LocalDateTime.now().minusHours(1); // 1 hour ago
        List<Long> distinctUserIds = noteRepository.findDistinctUserIds(); // Implement this method in your repository

        for (Long userId : distinctUserIds) {
        	System.out.print(userId);
            noteRepository.deleteOldNotesForUser(userId);
        }
    }

}
