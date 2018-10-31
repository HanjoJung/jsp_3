package com.jhj.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jhj.board.BoardDAO;
import com.jhj.board.BoardDTO;
import com.jhj.board.BoardReplyDTO;
import com.jhj.page.RowNum;
import com.jhj.page.Search;
import com.jhj.util.DBConnector;
import com.jhj.board.BoardReplyDAO;

public class QnaDAO implements BoardDAO, BoardReplyDAO {

	@Override
	public int reply(BoardReplyDTO boardReplyDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int replyUpdate(BoardReplyDTO boardReplyDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNum() throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select qna_seq.nextval from dual";

		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		rs.next();
		int num = rs.getInt(1);

		DBConnector.disConnect(rs, st, con);
		return num;
	}


	@Override
	public int getCount(Search search) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select count(num) from qna where " + search.getKind() + " like ?";

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%" + search.getSearch() + "%");
		ResultSet rs = st.executeQuery();
		rs.next();
		int result = rs.getInt(1);

		DBConnector.disConnect(rs, st, con);
		return result;
	}

	@Override
	public List<BoardDTO> selectList(RowNum rowNum) throws Exception {
		Connection con = DBConnector.getConnect();
		List<BoardDTO> ar = new ArrayList<>();

		String sql = "select * from " + "(select rownum R, N.* from " + "(select * from qna " + "where "
				+ rowNum.getSearch().getKind() + " like ? order by num desc) N) " + "where R between ? and ?";

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%" + rowNum.getSearch().getSearch() + "%");
		st.setInt(2, rowNum.getStartRow());
		st.setInt(3, rowNum.getLastRow());
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			QnaDTO dto = new QnaDTO();
			dto.setNum(rs.getInt("num"));
			dto.setTitle(rs.getString("title"));
			dto.setContents(rs.getString("contents"));
			dto.setWriter(rs.getString("writer"));
			dto.setReg_date(rs.getDate("reg_date"));
			dto.setHit(rs.getInt("hit"));
			dto.setRef(rs.getInt("ref"));
			dto.setDepth(rs.getInt("depth"));
			ar.add(dto);
		}

		DBConnector.disConnect(rs, st, con);
		return ar;
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select * from qna where num = ?";

		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		ResultSet rs = st.executeQuery();
		QnaDTO dto = new QnaDTO();
		if (rs.next()) {
			dto.setNum(rs.getInt("num"));
			dto.setTitle(rs.getString("title"));
			dto.setContents(rs.getString("contents"));
			dto.setWriter(rs.getString("writer"));
			dto.setReg_date(rs.getDate("reg_date"));
			dto.setHit(rs.getInt("hit"));
		}

		DBConnector.disConnect(rs, st, con);
		return dto;
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "insert into qna values(?,?,?,?,sysdate,0,?,0,0)";

		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, boardDTO.getNum());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		st.setString(4, boardDTO.getWriter());
		st.setInt(5, boardDTO.getNum());
		int result = st.executeUpdate();

		DBConnector.disConnect(st, con);

		return result;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "update qna set title=?, contents=?, writer=?, hit=? where num=?";

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, boardDTO.getTitle());
		st.setString(2, boardDTO.getContents());
		st.setString(3, boardDTO.getWriter());
		st.setInt(4, boardDTO.getHit());
		st.setInt(5, boardDTO.getNum());
		int result = st.executeUpdate();

		DBConnector.disConnect(st, con);

		return result;
	}

	@Override
	public int delete(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "delete from qna where num=?";

		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		int result = st.executeUpdate();

		DBConnector.disConnect(st, con);

		return result;

	}

}
