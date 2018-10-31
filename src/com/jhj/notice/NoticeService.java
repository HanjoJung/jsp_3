package com.jhj.notice;

import java.io.File;
import java.io.IOException;
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

public class NoticeService implements BoardService {
	private NoticeDAO noticeDAO;

	public NoticeService() {
		noticeDAO = new NoticeDAO();
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
			ar = noticeDAO.selectList(rowNum);
			int totalCount = noticeDAO.getCount(rowNum.getSearch());
			pager = makePager.makePage(totalCount);
		} catch (Exception e) {
			actionFoward.setPath("../index.jsp");
			e.printStackTrace();
		}
		request.setAttribute("list", ar);
		request.setAttribute("pager", pager);
		request.setAttribute("board", "notice");
		actionFoward.setPath("../WEB-INF/view/board/boardList.jsp");
		actionFoward.setCheck(true);

		return actionFoward;
	}

	public ActionFoward selectOne(HttpServletRequest request, HttpServletResponse response) {
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
			request.setAttribute("list", ar);
			request.setAttribute("board", "notice");
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/board/boardSelectOne.jsp");
		} catch (Exception e) {
			actionFoward.setCheck(false);
			actionFoward.setPath("./boardList.do");
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
			String path = "./noticeList.do";
			actionFoward.setCheck(false);
			actionFoward.setPath("../view/board/boardWrite.do");
			// 파일의 최대 크기 byte 단위
			int max = 1024 * 1024 * 10;
			// 파일의 저장공간
			String save = request.getServletContext().getRealPath("upload");
			// file 객체 생성
			File file = new File(save);
			// 디렉토리 여부 확인
			if (!file.exists()) {// 디렉토리가 없으면
				// 디렉토리 생성
				file.mkdirs();
			}
			MultipartRequest multi;
			try {
				multi = new MultipartRequest(request, save, max, "UTF-8", new DefaultFileRenamePolicy());

				// path 경로에 파일 업로드가 끝났습니다
				// 파일의 정보
				NoticeDTO noticeDTO = new NoticeDTO(); // DTO 생성
				noticeDTO.setTitle(multi.getParameter("title"));
				noticeDTO.setWriter(multi.getParameter("writer"));
				noticeDTO.setContents(multi.getParameter("contents"));
				noticeDTO.setNum(noticeDAO.getNum());
				int result = noticeDAO.insert(noticeDTO);

				if (result > 0) {
					FileDAO fileDAO = new FileDAO();
					// 파일의 파라미터명을 모은 것
					Enumeration<Object> e = multi.getFileNames();
					while (e.hasMoreElements()) {
						String p = (String) e.nextElement();
						if (multi.getOriginalFileName(p) != null) {
							FileDTO fileDTO = new FileDTO();
							fileDTO.setKind("N");
							fileDTO.setNum(noticeDTO.getNum());
							fileDTO.setFname(multi.getFilesystemName(p));
							fileDTO.setOname(multi.getOriginalFileName(p));
							fileDAO.insert(fileDTO);
						}
					}
					message = "성공";
					request.setAttribute("message", message);
					request.setAttribute("path", path);
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
				} else {
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
				}

			} catch (Exception e) {
				request.setAttribute("message", message);
				request.setAttribute("path", path);
				e.printStackTrace();
			}
			// map과 같은 형태로 request 속성 추가
			request.setAttribute("message", message);
			request.setAttribute("path", path);
		} else {
			request.setAttribute("board", "notice");
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/board/boardWrite.jsp");
		}

		return actionFoward;
	}

	@Override
	public ActionFoward update(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();

		if (method.equals("POST")) {
			int max = 1024 * 1024 * 10;
			String path = request.getServletContext().getRealPath("upload");
			String message = "실패";
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				MultipartRequest multi = new MultipartRequest(request, path, max, "UTF-8",
						new DefaultFileRenamePolicy());
				NoticeDTO noticeDTO = new NoticeDTO();
				int num = Integer.parseInt(multi.getParameter("num"));
				noticeDTO.setNum(num);
				noticeDTO.setTitle(multi.getParameter("title"));
				noticeDTO.setContents(multi.getParameter("contents"));
				noticeDTO.setHit(noticeDAO.hitUp(num));

				int result = noticeDAO.update(noticeDTO);
				if (result > 0) {

					FileDAO fileDAO = new FileDAO();
					// 파일의 파라미터명을 모은 것
					Enumeration<Object> e = multi.getFileNames();
					while (e.hasMoreElements()) {
						String key = e.nextElement().toString();
						FileDTO fileDTO = new FileDTO();
						fileDTO.setKind("N");
						fileDTO.setNum(noticeDTO.getNum());
						fileDTO.setFname(multi.getFilesystemName(key));
						fileDTO.setOname(multi.getOriginalFileName(key));
						fileDAO.insert(fileDTO);
					}
					message = "성공";
					request.setAttribute("message", message);
					request.setAttribute("path", "./noticeList.do");
				} else {
					request.setAttribute("message", message);
					request.setAttribute("path", "./noticeList.do");
				}
			} catch (Exception e) {
				request.setAttribute("message", message);
				request.setAttribute("path", "./noticeList.do");
				e.printStackTrace();
			}
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/common/result.jsp");

		} else {
			try {
				int num = Integer.parseInt(request.getParameter("num"));
				BoardDTO boardDTO = noticeDAO.selectOne(num);
				FileDAO fileDAO = new FileDAO();
				List<FileDTO> files = fileDAO.selectList(num, "N");
				request.setAttribute("board", "notice");
				request.setAttribute("dto", boardDTO);
				request.setAttribute("files", files);
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/board/boardUpdate.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return actionFoward;
	}

	public ActionFoward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			int result = noticeDAO.delete(num);
			if (result > 0) {
				FileDAO fileDAO = new FileDAO();
				fileDAO.delete(num);

				System.out.println(request.getServletContext().getRealPath("upload"));
				request.setAttribute("message", "성공");
				request.setAttribute("path", "./noticeList.do");
			} else {
				request.setAttribute("message", "실패");
				request.setAttribute("path", "./noticeList.do");
			}
		} catch (Exception e) {
			request.setAttribute("message", "실패");
			request.setAttribute("path", "./noticeList.do");
			e.printStackTrace();
		}
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/result.jsp");
		return actionFoward;
	}

}