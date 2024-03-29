package com.boardService.service;

import com.boardService.models.Board;
import com.boardService.models.Users;
import com.boardService.repository.BoardRepository;
import com.boardService.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class BoardService extends BaseService<BoardRepository, Board, String> {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UsersRepository usersRepository;

    protected Integer nextSid;

    protected String prefix = "BD";

    protected String tableName;
//    public BoardService() {
//        super("BD", "board");
//        findNextSid();
//    }

    @Override
    public Board add(final Board board) {
        findNextSid();
        final Board newBoard = Board.from(board);
        final Optional<Users> userO = usersRepository.findById(newBoard.getOwnerSid());
        if (userO.isEmpty()) return null;
        final String sid = getNextSid();
        newBoard.setSid(sid);
        return super.add(newBoard);
    }


    private void findNextSid() {
        final String maxSid = (String) boardRepository.findMaxId();
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
