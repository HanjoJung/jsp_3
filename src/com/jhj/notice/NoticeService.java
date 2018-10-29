package com.jhj.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jhj.action.ActionFoward;
import com.jhj.board.BoardDTO;
import com.jhj.file.FileDAO;
import com.jhj.file.FileDTO;

public class NoticeService {
	private NoticeDAO noticeDAO; 
	
	public NoticeService() {
		noticeDAO = new NoticeDAO();
	}
	
	public void selectList() {
		
	}
	
	public ActionFoward selectOne(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDTO boardDTO = null;
		ActionFoward actionFoward = new ActionFoward();
		actionFoward.setCheck(false);
		actionFoward.setPath("./noticeList.do");
		try {
			boardDTO = noticeDAO.selectOne(num);
			FileDAO fileDAO = new FileDAO();
			List<FileDTO> ar = fileDAO.selectList(num, "N");
			request.setAttribute("dto", boardDTO);
			request.setAttribute("files", ar);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (boardDTO != null) {
			actionFoward.setPath("./noticeList.do");
			actionFoward.setCheck(true);
			request.setAttribute("dto", boardDTO);
		}
		return actionFoward;
	}

}
