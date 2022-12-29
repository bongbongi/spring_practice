package com.ezen.myProject.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class BoardDTO {
	// DTO : 파일을 트랜스퍼 할 수 있는 하나의 묶음
	// 하나의 게시글에 파일 여러개 첨부
	private BoardVO bvo;
	private List<fileVO> fList;
}
