package com.ezen.myProject.repogitory;

import org.springframework.stereotype.Repository;

import com.ezen.myProject.domain.UserVO;

@Repository
public interface UserDAO {

	UserVO getUser(String id);

	int insertUser(UserVO user);



}
