package com.boardService.controller;

import com.boardService.models.Board;
import com.boardService.repository.BoardRepository;
import com.boardService.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController extends BaseController<BoardService, Board, String, BoardRepository> {


}
