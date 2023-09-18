package com.nagarro.UsersNotes.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nagarro.UsersNotes.models.Notes;

import jakarta.transaction.Transactional;

public interface NoteRepository extends JpaRepository<Notes, Long> {
    // You can define custom query methods here if needed.
	List<Notes> findTop10ByUserIdOrderByCreatedAtDesc(Long userId);
	
	@Transactional
	void deleteByIdAndUserId(Long NoteId,Long UserId);
	
	@Query("SELECT DISTINCT n.userId FROM Notes n")
	List<Long> findDistinctUserIds();
//	
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM notes " +
	               "WHERE user_id = ?1 AND id NOT IN (" +
	               "    SELECT id FROM (" +
	               "        SELECT id FROM notes " +
	               "        WHERE user_id = ?1 " +
	               "        ORDER BY created_at DESC " +
	               "        LIMIT 10" +
	               "    ) AS recent_notes" +
	               ")", nativeQuery = true)
	void deleteOldNotesForUser(Long userId);
}