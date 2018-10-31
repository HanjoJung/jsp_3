package com.jhj.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jhj.action.ActionFoward;
import com.jhj.board.BoardService;
 
public class MemberService implements BoardService {
	private MembarDAO membarDAO;

	public MemberService() {
		membarDAO = new MembarDAO();
	}

	public ActionFoward login(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		MemberDTO memberDTO = new MemberDTO();
		try {
			memberDTO = membarDAO.selectOne(memberDTO);
			if (memberDTO != null) {
				request.setAttribute("message", "로그인 성공");
				request.setAttribute("path", "../WEB-INF/view/common/result.jsp");
			} else {
				request.setAttribute("message", "로그인 실패");
				request.setAttribute("path", "../WEB-INF/view/common/result.jsp");
			}
		} catch (Exception e) {
			request.setAttribute("message", "로그인 실패");
			request.setAttribute("path", "../WEB-INF/view/common/result.jsp");
			e.printStackTrace();
		}

		request.setAttribute("board", "member");
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/member/memberJoin.jsp");
		return actionFoward;
	}

	public ActionFoward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();

		if (method.equals("POST")) {

		} else {

		}
		request.setAttribute("board", "member");
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/member/memberJoin.jsp");
		return actionFoward;
	}

	@Override
	public ActionFoward update(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		request.setAttribute("board", "member");
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/member/memberJoin.jsp");
		return actionFoward;
	}

	public ActionFoward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		request.setAttribute("board", "member");
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/member/memberJoin.jsp");
		return actionFoward;
	}

	@Override
	public ActionFoward selectList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionFoward selectOne(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
}