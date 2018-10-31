package com.jhj.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jhj.action.ActionFoward;
import com.jhj.board.BoardDTO;
import com.jhj.file.FileDAO;
import com.jhj.file.FileDTO;
import com.jhj.page.MakePager;
import com.jhj.page.Pager;
import com.jhj.page.RowNum;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class QnaService {
	private QnaDAO qnaDAO;

	public QnaService() {
		qnaDAO = new QnaDAO();
	}

	public ActionFoward selectList(HttpServletRequest request, HttpServletResponse response) {

		ActionFoward actionFoward = new ActionFoward();
		int curPage = 1;
		try {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		} catch (Exception e) {
		}
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");

		MakePager makePager = new MakePager(curPage, search, kind);
		RowNum rowNum = makePager.makeRow();

		List<BoardDTO> ar = null;
		Pager pager = null;
		try {
			ar = qnaDAO.selectList(rowNum);
			int totalCount = qnaDAO.getCount(rowNum.getSearch());
			pager = makePager.makePage(totalCount);
		} catch (Exception e) {
			actionFoward.setPath("../index.jsp");
			e.printStackTrace();
		}
		request.setAttribute("list", ar);
		request.setAttribute("pager", pager);
		actionFoward.setPath("../WEB-INF/qna/qnaList.jsp");
		actionFoward.setCheck(true);

		return actionFoward;
	}

	public ActionFoward selectOne(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDTO boardDTO = null;
		ActionFoward actionFoward = new ActionFoward();
		actionFoward.setCheck(false);
		actionFoward.setPath("./qnaList.do");
		try {
			boardDTO = qnaDAO.selectOne(num);
			FileDAO fileDAO = new FileDAO();
			List<FileDTO> ar = fileDAO.selectList(num, "N");
			request.setAttribute("dto", boardDTO);
			request.setAttribute("list", ar);
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/qna/qnaSelectOne.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (boardDTO == null) {
			actionFoward.setCheck(false);
			actionFoward.setPath("./qnaList.do");
		}
		return actionFoward;
	}

	public ActionFoward insert(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getServletContext().getRealPath("upload");
		int max = 1024 * 1024 * 10;
		MultipartRequest multi;
		ActionFoward actionFoward = new ActionFoward();
		try {
			multi = new MultipartRequest(request, path, max, "UTF-8", new DefaultFileRenamePolicy());

			// path 경로에 파일 업로드가 끝났습니다
			// 파일의 정보
			QnaDTO qnaDTO = new QnaDTO();
			qnaDTO.setTitle(multi.getParameter("title"));
			qnaDTO.setWriter(multi.getParameter("writer"));
			qnaDTO.setContents(multi.getParameter("contents"));
			FileDTO f1 = new FileDTO();
			String fname = multi.getFilesystemName("f1"); // 파라미터의 이름
			String oname = multi.getOriginalFileName("f1");
			f1.setFname(fname);
			f1.setOname(oname);

			FileDTO f2 = new FileDTO();
			fname = multi.getFilesystemName("f2"); // 파라미터의 이름
			oname = multi.getOriginalFileName("f2");
			f2.setFname(fname);
			f2.setOname(oname);

			/*
			 * File f = multi.getFile("f1"); Enumeration e = multi.getFileNames();
			 */ // 파라미터 이름들

			QnaDAO dao = new QnaDAO();
			int result = dao.insert(qnaDTO);



			String str = "등록 완료";
			actionFoward.setCheck(true);
			actionFoward.setPath("../common/result.jsp");
			if (result ==0) {
				actionFoward.setCheck(false);
				actionFoward.setPath("./qnaWriterForm.do");
			}

			// map과 같은 형태로 request 속성 추가
			request.setAttribute("message", str);
			request.setAttribute("path", "../WEB-INF/qna/qnaList.jsp");

		} catch (Exception e) {
			actionFoward.setCheck(false);
			actionFoward.setPath("./qnaWriterForm.do");
			e.printStackTrace();
		}

		return actionFoward;
	}

	public ActionFoward delete(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDTO boardDTO = null;
		ActionFoward actionFoward = new ActionFoward();
		actionFoward.setCheck(false);
		actionFoward.setPath("./qnaList.do");
		try {
			boardDTO = qnaDAO.selectOne(num);
			FileDAO fileDAO = new FileDAO();
			List<FileDTO> ar = fileDAO.selectList(num, "N");
			request.setAttribute("dto", boardDTO);
			request.setAttribute("list", ar);
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/qna/qnaSelectOne.jsp");
		} catch (Exception e) {
			actionFoward.setCheck(false);
			actionFoward.setPath("./qnaList.do");
			e.printStackTrace();
		}

		if (boardDTO == null) {
			actionFoward.setCheck(false);
			actionFoward.setPath("./qnaList.do");
		}
		return actionFoward;
	}
}