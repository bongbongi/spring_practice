package com.ezen.myProject.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.myProject.domain.UserVO;
import com.ezen.myProject.service.UserService;

@RequestMapping("/member/*")
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private UserService userService;

	@GetMapping("/")
	public ModelAndView home(ModelAndView mv) {
		mv.setViewName("/home"); //destPage와 같음
		return mv;
	}
	@GetMapping("/signup")
	public ModelAndView signUpGet(ModelAndView mv) {
		mv.setViewName("/user/signup");
		return mv;
	}
	@PostMapping("/signup")
	public ModelAndView signUpPost(ModelAndView mv, UserVO user) {
		logger.info(user.toString());
		
		//비밀번호 암호화 설정 완료 후
		boolean isUp = userService.signUp(user); //서비스 구현 라인
		if(isUp) {  //true라면
			mv.setViewName("/user/login");
		}else {
			mv.setViewName("/user/signup");
			mv.addObject("msg","0"); //메세지 받아서 스크립트가 처리
		}
		return mv;
	}
	@GetMapping("/login")
	public ModelAndView login(ModelAndView mv) {
		mv.setViewName("/user/login");
		return mv;
	}
	@PostMapping("/login")
	public ModelAndView loginPost(ModelAndView mv,String id, String pw, HttpServletRequest req) {
		//id, pw를 받아서 일치하는 회원이 있으면 회원정보를 가져옴, 없으면 null
		logger.info(">>> id : "+id+">>> pw : "+pw);
		UserVO isUser = userService.getUser(id, pw);
		
		//isUser가 null이 아니라면 세션 연결
		if(isUser !=null) {
			HttpSession session = req.getSession();
			session.setAttribute("ses", isUser);
			
			mv.setViewName("/home");
			mv.addObject("msg", "1");
		}else {
			mv.setViewName("/user/login"); //로그인 실패시 로그인창 머물기
			mv.addObject("msg", "0");
		}
		return mv;
	}
	@GetMapping("/logout")
	public ModelAndView logoutGet(ModelAndView mv, HttpServletRequest req) {
		mv.addObject("msg","0");
		
		req.getSession().removeAttribute("ses");  //삭제후
		req.getSession().invalidate(); //로그아웃
		
		/* mv.setViewName("/home"); */ //방법1
		mv.setViewName("redirect:/");  //방법2
		return mv;
	}
}
