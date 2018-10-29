package com.jhj.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jhj.board.BoardDAO;
import com.jhj.board.BoardDTO;
import com.jhj.page.RowNum;
import com.jhj.util.DBConnector;
import com.oreilly.servlet.MultipartRequest;

public class NoticeDAO implements BoardDAO {

	@Override
	public int getCount(String kind, String search) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select count(num) from notice where " + kind + " like ?";

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%" + search + "%");
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

		String sql = "select * from " + "(select rownum R, N.* from " + "(select * from notice " + "where "
				+ rowNum.getSearch().getKind() + " like ? order by num desc) N) " + "where R between ? and ?";

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%" + rowNum.getSearch().getSearch() + "%");
		st.setInt(2, rowNum.getStartRow());
		st.setInt(3, rowNum.getLastRow());
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			NoticeDTO dto = new NoticeDTO();
			dto.setNum(rs.getInt("num"));
			dto.setTitle(rs.getString("title"));
			dto.setContents(rs.getString("contents"));
			dto.setWriter(rs.getString("writer"));
			dto.setReg_date(rs.getDate("reg_date"));
			dto.setHit(rs.getInt("hit"));
			ar.add(dto);
		}

		DBConnector.disConnect(rs, st, con);
		return ar;
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select * from notice where num = ?";

		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		ResultSet rs = st.executeQuery();
		NoticeDTO dto = new NoticeDTO();
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

	// seq
	public int getNum() throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select notice_seq.nextval from dual";

		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		rs.next();
		int num = rs.getInt(1);
		
		DBConnector.disConnect(rs, st, con);
		return num;
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "insert into notice values(?,?,?,?,sysdate,0)";

		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, boardDTO.getNum());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		st.setString(4, boardDTO.getWriter());
		int result = st.executeUpdate();

		DBConnector.disConnect(st, con);

		return result;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "update notice set title=?, contents=?, writer=?, hit=? where num=?";

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
		String sql = "delete from notice where num=?";

		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		int result = st.executeUpdate();

		DBConnector.disConnect(st, con);

		return result;

	}
}
