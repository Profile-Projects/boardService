package com.boardService.repository;

import com.boardService.models.BoardInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardInvitationRepository extends JpaRepository<BoardInvitation, String> {
    @Query(nativeQuery = true,
            value = "select max(sid) from board_invitation")
    String findMaxId();

    Optional<BoardInvitation> findByEmail(final String email);
}
