package com.jhj.qna;

import java.io.File;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jhj.action.ActionFoward;
import com.jhj.board.BoardDTO;
import com.jhj.board.BoardService;
import com.jhj.file.FileDAO;
import com.jhj.file.FileDTO;
import com.jhj.page.MakePager;
import com.jhj.page.Pager;
import com.jhj.page.RowNum;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class QnaService implements BoardService {
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
		request.setAttribute("board", "qna");
		actionFoward.setPath("../WEB-INF/view/board/boardList.jsp");
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
			request.setAttribute("board", "qna");
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/board/boardSelectOne.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (boardDTO == null) {
			actionFoward.setCheck(false);
			actionFoward.setPath("./boardList.do");
		}
		return actionFoward;
	}

	public ActionFoward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();
		if (method.equals("POST")) {
			String message = "실패";
			String path = "./qnaList.do";
			actionFoward.setCheck(false);
			actionFoward.setPath("../view/board/boardWrite.do");
			int max = 1024 * 1024 * 10;
			String save = request.getServletContext().getRealPath("upload");
			File file = new File(save);
			if (!file.exists()) {
				file.mkdirs();
			}

			MultipartRequest multi;
			try {
				multi = new MultipartRequest(request, save, max, "UTF-8", new DefaultFileRenamePolicy());

				// path 경로에 파일 업로드가 끝났습니다
				// 파일의 정보
				QnaDTO qnaDTO = new QnaDTO();
				qnaDTO.setTitle(multi.getParameter("title"));
				qnaDTO.setWriter(multi.getParameter("writer"));
				qnaDTO.setContents(multi.getParameter("contents"));
				qnaDTO.setNum(qnaDAO.getNum());
				FileDAO fileDAO = new FileDAO();
				int result = qnaDAO.insert(qnaDTO);

				if (result > 0) {
					Enumeration<Object> e = multi.getFileNames();
					while (e.hasMoreElements()) {
						String p = e.nextElement().toString();
						FileDTO fileDTO = new FileDTO();
						fileDTO.setFname(multi.getFilesystemName(p));
						fileDTO.setOname(multi.getOriginalFileName(p));
						fileDTO.setKind("Q");
						fileDTO.setNum(qnaDTO.getNum());
						fileDAO.insert(fileDTO);
					}
					message = "성공";
					// map과 같은 형태로 request 속성 추가
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
				}else {
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// map과 같은 형태로 request 속성 추가
			request.setAttribute("message", message);
			request.setAttribute("path", path);
		}else {
			request.setAttribute("board", "qna");
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/board/boardWrite.jsp");
		}

		return actionFoward;
	}

	@Override
	public ActionFoward update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
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