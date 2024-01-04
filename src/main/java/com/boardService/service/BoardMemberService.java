package com.boardService.service;

import com.boardService.models.Board;
import com.boardService.models.BoardMember;
import com.boardService.models.Users;
import com.boardService.repository.BoardMemberRepository;
import com.boardService.repository.BoardRepository;
import com.boardService.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardMemberService extends BaseService<BoardMemberRepository, BoardMember, String> {

    @Autowired
    private BoardMemberRepository boardMemberRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BoardRepository boardRepository;

    protected Integer nextSid;

    protected String prefix = "BM";

    @Override
    public BoardMember add(final BoardMember boardMember) {
        findNextSid();
        final BoardMember newBoardMember = BoardMember.from(boardMember);
        final Optional<Users> userO = usersRepository.findById(newBoardMember.getUserSid());
        final Optional<Board> boardO = boardRepository.findById(newBoardMember.getBoardSid());
        if (userO.isEmpty() || boardO.isEmpty()) return null;
        final String sid = getNextSid();
        newBoardMember.setSid(sid);
        return super.add(newBoardMember);
    }


    private void findNextSid() {
        final String maxSid = (String) boardMemberRepository.findMaxId();
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
