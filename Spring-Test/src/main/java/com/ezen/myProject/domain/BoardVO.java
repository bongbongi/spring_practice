package com.ezen.myProject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 값을 다 받는 생성자
@Setter
@Getter
public class BoardVO {
	private int bno;
	private String title;
	private String content;
	private String writer;
	private String isDel;
	private String registerDate;
	private int read_count;
	
	
}
