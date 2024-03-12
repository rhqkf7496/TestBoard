package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.BoardVO;

@Repository
public interface BoardRepository extends JpaRepository<BoardVO, Integer>{
	
	
	Page<BoardVO> findByTitleContaining(String searchKeyword, Pageable pageable);
}
