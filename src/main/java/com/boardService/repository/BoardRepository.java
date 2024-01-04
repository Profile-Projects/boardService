package com.boardService.repository;

import com.boardService.models.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {
    @Query(nativeQuery = true,
            value = "select max(sid) from board")
    String findMaxId(final String tableName);
}
