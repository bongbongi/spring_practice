package com.ezen.myProject.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagingVO {
	
	private int pageNo; //현재 화면에 출력된 페이지네이션
	private int qty; // 한 페이지당 보여줄 게시글 수
	
	private String type;
	private String keyword;
	
	public PagingVO() {
		this(1,10);
	}
	public PagingVO(int pageNo, int qty) {
		this.pageNo=pageNo;
		this.qty=qty;
	}
	public int getPageStart() {//시작번호값 구하는 메서드
		return (this.pageNo-1)*qty; //DB에서 값을 limit, 첫 시작값이 0임.
	}
	public String[] getTypeToArray() {
		return this.type == null? new String[] {} : this.type.split(""); //값이 없으면 배열, 있으면 값을 자르기
	}
}
