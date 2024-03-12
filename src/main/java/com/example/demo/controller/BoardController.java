package com.example.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.BoardService;
import com.example.demo.service.CommentService;
import com.example.demo.vo.BoardVO;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@Autowired
	CommentService commentService;

	@GetMapping("/")
	public String index() {
		return "index";

	}

	@GetMapping("/board/writeForm")
	public String boardWriteForm() {
		return "BoardWriteForm";

	}

	@PostMapping("/board/write")
	public String boardWrite(BoardVO board, Model model, MultipartFile file) throws Exception {
		
		boardService.write(board, file);
		
		model.addAttribute("message", "글 작성이 완료되었습니다.");
		model.addAttribute("searchUrl", "/board/list");
		
		return "message";
	}

	@GetMapping("/board/list")
	public String boardList(Model model,
							@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.DESC) Pageable pageable,
							String searchKeyword) {
		
		Page<BoardVO> list = null;
		
		if(searchKeyword == null) {
			list = boardService.boardList(pageable);
		}else {
			list = boardService.boardSearchList(searchKeyword, pageable);
		}
			
		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, list.getTotalPages());
		
		model.addAttribute("list", list);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "BoardList";

	}
	
	@GetMapping("/board/view")
	public String boardView(Model model, Integer id) {
		
		model.addAttribute("board", boardService.boardView(id));
		model.addAttribute("comment", commentService.get_comment(id));
		return "BoardView";
		
	}
	
	@GetMapping("/board/delete")
	public String boardDelete(Integer id) {
		
		boardService.boardDelete(id);
		return "redirect:/board/list";
		
	}
	
	@GetMapping("/board/modify/{id}")
	public String boardModify(@PathVariable("id") Integer id, Model model) {
		
		model.addAttribute("board", boardService.boardView(id));
		return "BoardModify";
	}
	
	@PostMapping("/board/update/{id}")
	public String boardUpdate(@PathVariable("id") Integer id, BoardVO board, MultipartFile file) throws Exception {
		
		BoardVO boardTemp = boardService.boardView(id);
		boardTemp.setReg_id(board.getReg_id());
		boardTemp.setTitle(board.getTitle());
		boardTemp.setContent(board.getContent());
		boardTemp.setFilename(board.getFilename());
		boardTemp.setFilepath(board.getFilepath());
		
		boardService.write(boardTemp, file);
		
		return "redirect:/board/list";
	}
	
	
	@GetMapping("/summernote")
	public String summernote() {
		return "summernote";

	}
	
}
