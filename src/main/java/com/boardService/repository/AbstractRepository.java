package com.boardService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

public interface AbstractRepository<T, ID extends Serializable>  {

//    @Query(nativeQuery = true, value = "SELECT * FROM " + tableName)
//    ID findMaxId(@Param("tableName") String tableName);
}
