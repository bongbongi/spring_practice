package com.ezen.myProject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor //기본 생성자
@AllArgsConstructor //모든 것을 담는 생성자
@Setter
@Getter
public class UserVO {
	private String id;
	private String pw;
	private int age;
	private String name;
	private String email;
	private String home;
}
