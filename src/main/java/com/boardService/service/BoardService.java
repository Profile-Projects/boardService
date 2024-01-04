package com.boardService.service;

import com.boardService.models.Board;
import com.boardService.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService extends BaseService<BoardRepository, Board, String> {

    @Autowired
    private BoardRepository boardRepository;

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
        final String sid = getNextSid();
        newBoard.setSid(sid);
        return super.add(newBoard);
    }


    private void findNextSid() {
        final String maxSid = (String) boardRepository.findMaxId(this.tableName);
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
