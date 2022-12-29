package com.ezen.myProject.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezen.myProject.domain.UserVO;
import com.ezen.myProject.repogitory.UserDAO;

import lombok.extern.slf4j.Slf4j;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Inject
	private UserDAO userDAO;
	@Inject
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public boolean signUp(UserVO user) {
		logger.info(">>> signup check2");
		//아이디가 중복되면 회원가입 실패
		//아이디와 일치하는 정보를 db에서 가져옴
		UserVO tmpUser = userDAO.getUser(user.getId());
		//tmpUser가 null이 아니라면(이미 가입된 회원) -> 아이디 중복 -> 회원가입 실패
		//null이면 회원가입시켜주기
		if(tmpUser != null) {
			return false;
		}
		//아이디가 중복되지 않았으면 회원가입
		//아이디 유효성 검사 -> 맞으면 체크, 아니면 실패
		//우선 아이디가 입력이 되었는지만 체크하기
		if(user.getId()==null || user.getId().length()==0) {
			return false;
		}
		//비밀번호 유효성 검사 : 비밀번호가 입력되었는지 체크
		if(user.getPw()==null || user.getPw().length()==0) {
			return false;
		}
		
		//회원가입
		//비밀번호를 암호화 과정
		String pw = user.getPw();
		//encode라는 객체를 이용하면 암호화가 됨
		//matches 를 이용하여 (원래비번, 암호화된 비번) 일치하는지 체크
		String encodePw = passwordEncoder.encode(pw); //암호화된 패스워드
		//회원비밀번호를 암호화된 비밀번호로 수정
		user.setPw(encodePw);
		//회원가입 -> insert
		int isOk = userDAO.insertUser(user);
		return isOk>0? true:false;
	}

	@Override
	public UserVO getUser(String id, String pw) {
		UserVO user = userDAO.getUser(id); //앞에서 했던 메서드 호출
		//가져온 User 객체의 비밀번호와 입력받은 비밀번호가 같은지 확인
		//user가 없을 때
		if(user == null) {return null;}
		
		//matches(입력받은 비번, 암호화된(db에 저장된) 비번 ) : matches가 알아서 확인해줌
		if(passwordEncoder.matches(pw, user.getPw())) {
			return user;
		}else {
			return null;
		}
	}
}