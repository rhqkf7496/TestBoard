package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.vo.CommentVO;

@Service
public class CommentService {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	BoardRepository boardRepository;

	public int write(CommentVO comment) {
		try {
			commentRepository.save(comment);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	public void commentDelete(Integer id) {
		commentRepository.deleteById(id);
	}

	public ArrayList<CommentVO> get_comment(int id) {
		try {
			return commentRepository.FindCommentByBoardId(id);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
