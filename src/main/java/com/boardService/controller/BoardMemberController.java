package com.boardService.controller;

import com.boardService.models.BoardMember;
import com.boardService.repository.BoardMemberRepository;
import com.boardService.service.BoardMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boardmember")
public class BoardMemberController extends BaseController<BoardMemberService, BoardMember, String, BoardMemberRepository>{

    @Autowired
    private BoardMemberService boardMemberService;

    @Override
    @PostMapping
    public ResponseEntity<BoardMember> add(@RequestBody BoardMember boardMember) {
        final BoardMember newBoardMember = boardMemberService.add(boardMember);
        if (newBoardMember == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(newBoardMember);
    }
}
