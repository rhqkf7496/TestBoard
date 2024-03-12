package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.repository.BoardRepository;
import com.example.demo.vo.BoardVO;
import com.example.demo.vo.CommentVO;

@Service
public class BoardService {

	@Autowired
	BoardRepository boardRepository;
	
	//글 작성 처리
	public void write(BoardVO board, MultipartFile file) throws Exception {
		
		if (file != null && !file.isEmpty()) {
			String projectPath =  System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
			UUID uuid = UUID.randomUUID();
			String fileName = uuid + "_" + file.getOriginalFilename();
			File saveFile = new File(projectPath, fileName);
			//System.out.println("file: " + file);
			file.transferTo(saveFile);
			
			board.setFilename(fileName);
			board.setFilepath("/files/" + fileName);
		}
			
			boardRepository.save(board);
			
	}
	
	//게시물 리스트 처리
	public Page<BoardVO> boardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
		
	}
	
	public Page<BoardVO> boardSearchList(String searchKeyword, Pageable pageable) {
		return boardRepository.findByTitleContaining(searchKeyword, pageable);
		
	}
	
	//특정 게시글 불러오기
	public BoardVO boardView(Integer id) {
		return boardRepository.findById(id).get();
		
	}
	
	//특정 게시글 삭제
	public void boardDelete(Integer id) {
		boardRepository.deleteById(id);
	}
	
	public BoardVO findById(Integer id) {
	    return boardRepository.findById(id).orElse(null);
	}
	
	
}
