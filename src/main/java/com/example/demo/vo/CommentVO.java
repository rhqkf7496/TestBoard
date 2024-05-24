package com.example.demo.vo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "comment")
public class CommentVO {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String comment;

	@Column(nullable = false)
	private String reg_id;

	private LocalDateTime reg_dtm;

	@PrePersist
	protected void onCreate() {
		reg_dtm = LocalDateTime.now(); // 생성일을 현재 날짜 및 시간으로 설정
	}

	@ManyToOne
	@JoinColumn(name = "board_id")
	private BoardVO board;

}
