package com.ezen.myProject.repogitory;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ezen.myProject.domain.BoardVO;
import com.ezen.myProject.domain.PagingVO;
import com.ezen.myProject.handler.PagingHandler;

@Repository
public interface BoardDAO {

	int insertBoard(BoardVO bvo);

	List<BoardVO> selectBoardList();

	BoardVO getDetail(int bno);

	int readCountUp(int bno);

	int updateBoard(BoardVO bvo);

	int getdelete(int bno);

	List<BoardVO> selectBoardListPaging(PagingVO pvo);

	int totalCount();

	List<BoardVO> pageList(PagingHandler ph);

	int searchTotalCount(PagingVO pgvo);

	int selectOneBno();


}
