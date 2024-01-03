package com.boardService.service;

import com.boardService.models.Board;
import com.boardService.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
public class BoardService extends BaseService<BoardRepository, Board, String> {

    public BoardService() {
        super("BD", "board");
    }

    @Override
    public Board add(final Board board) {
        final Board newBoard = Board.from(board);
        final String sid = super.getNextSid();
        newBoard.setSid(sid);
        return super.add(newBoard);
    }
}
