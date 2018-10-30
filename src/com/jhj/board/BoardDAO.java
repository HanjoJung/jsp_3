package com.jhj.board;

import java.util.List;

import com.jhj.page.RowNum;
import com.jhj.page.Search;

public interface BoardDAO {

	// getCount
	public int getCount(Search search) throws Exception;

	// selectList
	public List<BoardDTO> selectList(RowNum rowNum) throws Exception;

	// selectOne
	public BoardDTO selectOne(int num) throws Exception;

	// insert
	public int insert(BoardDTO boardDTO) throws Exception;

	// update
	public int update(BoardDTO boardDTO) throws Exception;

	// delete
	public int delete(int num) throws Exception;

}