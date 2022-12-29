package com.ezen.myProject.service;

import com.ezen.myProject.domain.UserVO;


public interface UserService {

	boolean signUp(UserVO user);

	UserVO getUser(String id, String pw);

}
