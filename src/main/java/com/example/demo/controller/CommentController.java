package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.CommentRepository;
import com.example.demo.service.BoardService;
import com.example.demo.service.CommentService;
import com.example.demo.vo.CommentVO;

@RestController
public class CommentController {

	@Autowired
	CommentService commentService;

	@Autowired
	BoardService boardService;

	@Autowired
	CommentRepository commentRepository;
	
	@PostMapping("/board/comment")
	public int commentWrite(@RequestBody CommentVO comment) throws Exception {
		return commentService.write(comment);
	}
	
	@GetMapping("/board/comment/delete")
	public void commentDelete(Integer id, HttpServletResponse response) {

		CommentVO vo = commentRepository.findById(id).get();
		
		commentService.commentDelete(id);
		
		try {
			response.sendRedirect("/board/view?id=" + vo.getBoard().getId());
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/*
	 * @GetMapping("/board/get_comment") public ArrayList<CommentVO>
	 * get_comment(@RequestParam int id) throws Exception { return
	 * commentService.get_comment(id); }
	 */
}
