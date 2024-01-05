package com.boardService.service;

import com.boardService.models.Board;
import com.boardService.models.BoardInvitation;
import com.boardService.repository.BoardInvitationRepository;
import com.boardService.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardInvitationService extends BaseService<BoardInvitationRepository, BoardInvitation, String> {

    @Autowired
    private BoardInvitationRepository boardInvitationRepository;

    @Autowired
    private BoardRepository boardRepository;

    protected Integer nextSid;

    protected String prefix = "BI";


    @Override
    public BoardInvitation add(final BoardInvitation boardInvitation) {
        findNextSid();
        final BoardInvitation newBoardInvitation = BoardInvitation.from(boardInvitation);
        final Optional<Board> boardO = boardRepository.findById(newBoardInvitation.getBoardSid());
        if (boardO.isEmpty()) return null;

        final String sid = getNextSid();
        newBoardInvitation.setSid(sid);
        return super.add(newBoardInvitation);
    }

    public Optional<BoardInvitation> findByEmail(final String email) {
        return boardInvitationRepository.findByEmail(email);
    }

    public BoardInvitation markComplete(final BoardInvitation boardInvitation) {
        final BoardInvitation updated = BoardInvitation.from(boardInvitation);
        updated.setMarkComplete(true);
        return super.update(updated, updated.getSid());
    }


    private void findNextSid() {
        final String maxSid = (String) boardInvitationRepository.findMaxId();
        if (maxSid == null) {
            this.nextSid = 1;
        } else {
            this.nextSid = Integer.parseInt(maxSid.substring(2)) + 1;
        }
        System.out.printf("Next Sid is %d", nextSid);
    }

    protected String getNextSid() {
        final String nextSidStr = this.prefix + String.format("%06d", this.nextSid);
        this.nextSid += 1;
        return nextSidStr;
    }
}
