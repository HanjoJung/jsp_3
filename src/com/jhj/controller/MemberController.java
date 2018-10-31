package com.jhj.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jhj.action.ActionFoward;
import com.jhj.member.MemberService;
import com.jhj.qna.QnaService;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberController() {
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

		String commend = request.getPathInfo();
		MemberService memberService = new MemberService();

		ActionFoward actionFoward = null;

		if (commend.equals("/memberJoin.do")) {
			actionFoward = memberService.insert(request, response);
		} else if (commend.equals("/memberlogin.do")) {
			actionFoward = memberService.login(request, response);
		} else if (commend.equals("/memberSelectOne.do")) {
			actionFoward = memberService.selectOne(request, response);
		} else if (commend.equals("/memberUpdate.do")) {
			actionFoward = memberService.update(request, response);
		} else if (commend.equals("/memberDelete.do")) {
			actionFoward = memberService.delete(request, response);
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
