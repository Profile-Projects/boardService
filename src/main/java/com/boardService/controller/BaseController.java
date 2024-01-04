package com.boardService.controller;

import com.boardService.repository.AbstractRepository;
import com.boardService.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Optional;

public abstract class BaseController<S extends BaseService<R, T, I>, T, I extends Serializable, R extends JpaRepository<T, I>> {

    @Autowired
    protected S service;

    @PostMapping
    public ResponseEntity<T>add(@RequestBody T t) {
        final T entity = (T) service.add((t));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> get(@PathVariable("id") I i) {
        final Optional<T> entityO = service.get(i);
        if (!entityO.isPresent()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(entityO.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") I i) {
        service.delete(i);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable("id") I i,
                                    @RequestBody T t) {
        final T updated = (T) service.update(t, i);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }
}
