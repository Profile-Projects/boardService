package com.boardService.controller;

import com.boardService.models.Board;
import com.boardService.repository.BoardRepository;
import com.boardService.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController extends BaseController<BoardService, Board, String, BoardRepository> {

    @Autowired
    private BoardService boardService;

    @Override
    @PostMapping
    public ResponseEntity<Board> add(@RequestBody Board board) {
        final Board newBoard = (Board) boardService.add(board);
        if (newBoard == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(newBoard);
    }

}
