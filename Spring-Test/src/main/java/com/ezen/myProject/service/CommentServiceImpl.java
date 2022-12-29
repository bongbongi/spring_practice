package com.ezen.myProject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.myProject.domain.CommentVO;
import com.ezen.myProject.repogitory.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
	@Inject
	private CommentDAO cdao;

	@Override
	public int register(CommentVO cvo) {
		log.info("comment service check 2");
		return cdao.insertComment(cvo);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		log.info("comment list check 2");
		return cdao.list(bno);
	}

	@Override
	public int modify(CommentVO cvo) {
		log.info("comment update check 2");
		return cdao.update(cvo);
	}

	@Override
	public int delete(int cno) {
		log.info("comment delete check 2");
		return cdao.delete(cno);
	}
}
