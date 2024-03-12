package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.vo.CommentVO;

public interface CommentRepository extends JpaRepository<CommentVO, Integer> {

	@Query(value = "SELECT * FROM comment WHERE board_id = ?", nativeQuery = true)
	public ArrayList<CommentVO> FindCommentByBoardId(int id);
	
}
