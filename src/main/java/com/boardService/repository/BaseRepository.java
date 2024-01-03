package com.boardService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

public interface BaseRepository<T, ID> extends JpaRepository<T, ID>  {

    @Query(nativeQuery = true,
            value = "select max(sid) from ?1")
    ID findMaxId(final String tableName);
}
