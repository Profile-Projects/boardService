package com.boardService.repository;

import com.boardService.models.BoardMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardMemberRepository extends JpaRepository<BoardMember, String> {
    @Query(nativeQuery = true, value = "select max(sid) from board_member")
    String findMaxId();
}
