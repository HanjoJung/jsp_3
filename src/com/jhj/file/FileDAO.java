package com.jhj.file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jhj.util.DBConnector;

public class FileDAO {

	public List<FileDTO> selectList(int num, String kind) throws Exception {

		Connection con = DBConnector.getConnect();
		String sql = "select * from upload where num=? and kind = ?";
		List<FileDTO> ar = new ArrayList<>();

		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		st.setString(2, kind);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			FileDTO dto = new FileDTO();
			dto.setFnum(rs.getInt("fnum"));
			dto.setFname(rs.getString("fname"));
			dto.setOname(rs.getString("oname"));
			dto.setNum(rs.getInt("num"));
			dto.setKind(rs.getString("kind"));
			ar.add(dto);
		}

		DBConnector.disConnect(st, con);
		return ar;
	}
	
	public int insert(FileDTO fileDTO) throws Exception {

		Connection con = DBConnector.getConnect();
		String sql = "insert into upload values(file_seq.nextval,?,?,?,?)";

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, fileDTO.getFname());
		st.setString(2, fileDTO.getOname());
		st.setInt(3, fileDTO.getNum());
		st.setString(4, fileDTO.getKind());
		int result = st.executeUpdate();

		DBConnector.disConnect(st, con);

		return result;
	}
}
