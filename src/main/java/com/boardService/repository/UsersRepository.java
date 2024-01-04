package com.boardService.repository;

import com.boardService.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
    @Query("select max(sid) from users")
    String findMaxId();
}
