package com.boardService.controller;

import com.boardService.converters.BoardInvitationToUser;
import com.boardService.models.Board;
import com.boardService.models.BoardInvitation;
import com.boardService.models.Users;
import com.boardService.repository.BoardInvitationRepository;
import com.boardService.requests.BoardInvitationMarkCompleteRequest;
import com.boardService.service.BoardInvitationService;
import com.boardService.service.BoardService;
import com.boardService.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/boardinvitation")
public class BoardInvitationController extends BaseController<BoardInvitationService, BoardInvitation, String, BoardInvitationRepository> {

    @Autowired
    private BoardInvitationService boardInvitationService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private UsersService usersService;

    @Override
    @PostMapping
    public ResponseEntity<BoardInvitation> add(@RequestBody BoardInvitation boardInvitation) {
        final BoardInvitation newBoardInvitation = boardInvitationService.add(boardInvitation);
        if (newBoardInvitation == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(newBoardInvitation);
    }

    @PostMapping("/markcomplete")
    public ResponseEntity<Users> markComplete(@RequestBody BoardInvitationMarkCompleteRequest markCompleteRequest) {
        final Optional<Board> boardO = boardService.get(markCompleteRequest.getBoardSid());
        final Optional<BoardInvitation> boardInvitationO = boardInvitationService.findByEmail(markCompleteRequest.getEmail());
        if (boardO.isEmpty() || boardInvitationO.isEmpty()) return ResponseEntity.notFound().build();
        final BoardInvitation updated = boardInvitationService.markComplete(boardInvitationO.get());

        final Users user= BoardInvitationToUser.convert(boardInvitationO.get(), markCompleteRequest.getName());
        final Users updateUser = usersService.add(user);
        return ResponseEntity.ok(updateUser);
    }
}
