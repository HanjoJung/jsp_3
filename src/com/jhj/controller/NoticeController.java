package com.jhj.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jhj.action.ActionFoward;
import com.jhj.board.BoardDTO;
import com.jhj.file.FileDAO;
import com.jhj.file.FileDTO;
import com.jhj.notice.NoticeDAO;
import com.jhj.notice.NoticeService;
import com.jhj.page.MakePager;
import com.jhj.page.Pager;
import com.jhj.page.RowNum;

/**
 * Servlet implementation class NoticeController
 */
@WebServlet("/NoticeController")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String info = request.getPathInfo();
		NoticeDAO noticeDAO = new NoticeDAO();
		String path = "";
		boolean check = false;
		NoticeService noticeService = new NoticeService();
		
		ActionFoward actionFoward = null;

		if (info.equals("/noticeList.do")) {
			int curPage = 1;
			try {
				curPage = Integer.parseInt(request.getParameter("curPage"));
			} catch (Exception e) {
			}
			String kind = request.getParameter("kind");
			if (kind == null || kind.length() == 0) {
				kind = "title";
			}
			String search = request.getParameter("search");
			if (search == null) {
				search = "";
			}

			MakePager makePager = new MakePager(curPage, search, kind);
			RowNum rowNum = makePager.makeRow();

			List<BoardDTO> ar = null;
			Pager pager = null;
			try {
				ar = noticeDAO.selectList(rowNum);
				int totalCount = noticeDAO.getCount(kind, search);
				pager = makePager.makePage(totalCount);
			} catch (Exception e) {
				path = "../index.jsp";
				e.printStackTrace();
			}
			request.setAttribute("list", ar);
			request.setAttribute("pager", pager);
			path = "../WEB-INF/notice/noticeList.jsp";
			check = true;

		} else if (info.equals("/noticeSelectOne.do")) {
			actionFoward = noticeService.selectOne(request);
		} else if (info.equals("/noticeSelectOne.do")) {
		} else {

		}
		if (actionFoward.isCheck()) {
			RequestDispatcher view = request.getRequestDispatcher(actionFoward.getPath());
			view.forward(request, response);
		} else {
			response.sendRedirect(actionFoward.getPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
