package com.example.demo.vo;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "board")
public class BoardVO {
	
	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String reg_id;
	private LocalDateTime reg_dtm;
	
	private String mod_id;
    private LocalDateTime mod_dtm;
    
    private String filename;
    private String filepath;
    
    @PrePersist
    protected void onCreate() {
        reg_dtm = LocalDateTime.now(); // 생성일을 현재 날짜 및 시간으로 설정
    }
    
    @PreUpdate
    protected void onUpdate() {
        mod_dtm = LocalDateTime.now(); // 수정일을 현재 날짜 및 시간으로 설정
    }
	
	/*
	 * @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade =
	 * CascadeType.REMOVE) private List<CommentVO> comment;
	 */
	
	
}
