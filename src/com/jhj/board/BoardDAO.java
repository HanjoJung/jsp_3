package com.jhj.board;

import java.util.List;

public interface BoardDAO {

	// getCount
	public int getCount(String kind, String search) throws Exception;

	// selectList
	public List<BoardDTO> selectList(int startRow, int lastRow, String kind, String search) throws Exception;

	// selectOne
	public BoardDTO selectOne(int num) throws Exception;

	// insert
	public int insert(BoardDTO boardDTO) throws Exception;

	// update
	public int update(BoardDTO boardDTO) throws Exception;

	// delete
	public int delete(BoardDTO boardDTO) throws Exception;

}
