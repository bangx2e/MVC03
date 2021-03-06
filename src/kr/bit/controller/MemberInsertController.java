package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

@WebServlet("/memberInsert.do")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Request에서 넘어오는 데이터의 인코딩을 UTF-8로 설정한다
		request.setCharacterEncoding("UTF-8");
		// 1. 파라메터 수집 (VO)
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
//	System.out.println(id);
//		MemberVO mem = new MemberVO(id, pass, name, age, email, phone);
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPass(pass);
		vo.setName(name);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhone(phone);
//		System.out.println(mem.toString()); //출력 확인
		//Model 연동부분
		MemberDAO dao = new MemberDAO();
		int cnt=dao.memberInsert(vo);
		PrintWriter out = response.getWriter();
		if(cnt>0) {
			//가입 성공 -> 회원 리스트 페이지로 Redirect 해준다
			response.sendRedirect("/MVC03/memberList.do");
			out.println("insert success");
		}else {
			//가입 실패 -> 예외처리
			throw new ServletException("not insert");
		}
		
	}

}
