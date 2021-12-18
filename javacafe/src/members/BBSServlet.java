package members;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bbs.BBS;
import bbs.BBSDAO;
import common.Paging;

/**
 * Servlet implementation class EmpServlet
 */
@WebServlet("/members/BBSServlet")
public class BBSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BBSServlet() {
		super();

	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 응답페이지 인코딩
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		// 내장 객체
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

//		System.out.println("<===========================================");
//		Map<String, String[]> map = request.getParameterMap();
//		Set<String> set = map.keySet();
//		for (String str : set) {
//			System.out.println(str + ", " + map.get(str)[0]);
//		}
//		System.out.println("--------------------------------------------");
//		Enumeration<String> en = request.getParameterNames();
//		while (en.hasMoreElements()) {
//			String elem = en.nextElement();
//			System.out.println("element: " + elem);
//			System.out.println(request.getParameterValues(elem)[0]);
//		}
//		BufferedReader br = request.getReader();
//		String line = null;
//		while ((line = br.readLine()) != null) {
//			System.out.println(line);
//		}
//		br.close();
//		System.out.println("===========================================>");

		BBSDAO bbsDAO = new BBSDAO();
		BBS bbs = new BBS();

		// 파라미터를 DO에 매핑
		try {
			BeanUtils.copyProperties(bbs, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		String action = request.getParameter("action");
		if (action == null) {
			// throw new Exception("action null");
			BufferedReader br = request.getReader();
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				sb.append(line);
			}
			br.close();
			line = sb.toString();

			System.out.println("=========================");
			String[] params = line.split("name");
			Map<String, String> pMap = new HashMap<String, String>();
			for (String param : params) {
				if (param.indexOf("=") >= 0) {
					System.out.println(param);
					String key = param.substring(2, param.lastIndexOf("\""));
					String val = param.substring(param.lastIndexOf("\"") + 1, param.indexOf("-"));
					System.out.println(key + ", " + val);
					pMap.put(key, val);
				}
			}
			System.out.println("=========================");

			if (!pMap.isEmpty()) {
				// 등록처리
				bbs.setContents(pMap.get("contents"));
				bbs.setPassword_yn(pMap.get("password_yn"));
				bbs.setProd_no(pMap.get("prod_no"));
				bbs.setTitle(pMap.get("title"));
				bbs.setUser_no(pMap.get("user_no"));

				if (bbsDAO.insert(bbs)) {
					// 목록으로 페이지 이동
					// response.sendRedirect("../members/BBSServlet?action=list&prod_no=" +
					// bbs.getProd_no());
					Gson gson = new GsonBuilder().create();
					out.print(gson.toJson(bbs).toString());

				} else {
					out.print("<script>");
					out.print("alert(등록 실패);");
					out.print("history.go(-1);");
					out.print("<script>");
				}

			} else {

				out.print("action이 null입니다.");
			}

		} else if (action.equals("list")) {

			// 페이징처리
			Paging paging = new Paging();
			paging.setPageUnit(10);
			// 현재 페이지번호
			String page = request.getParameter("page");
			int p = 1;
			if (page != null)
				p = Integer.parseInt(page);
			paging.setPage(p);

			// 파라미터 값 가져오기

			String id = request.getParameter("user_no");
			String subject = request.getParameter("title");
			String content = request.getParameter("contents");
			String ref = request.getParameter("ref");
			String ref_lev = request.getParameter("ref_lev");
			String re_step = request.getParameter("re_step");

			String prod_no = request.getParameter("prod_no");

			// 전체 건수
			int total = bbsDAO.count(prod_no);//

			paging.setTotalRecord(total);

			// 데이터조회 (DAO)
			List<BBS> datas = bbsDAO.selectPage(paging.getFirst(), paging.getLast(), prod_no);
			request.setAttribute("datas", datas);
			request.setAttribute("paging", paging);
			//
			request.getRequestDispatcher("../members/list.jsp").forward(request, response);

		} else if (action.equals("insert")) {

			// 등록처리
			System.out.println(bbs);
			if (bbsDAO.insert(bbs)) {
				// 목록으로 페이지 이동
				// response.sendRedirect("../members/BBSServlet?action=list&prod_no=" +
				// bbs.getProd_no());
				Gson gson = new GsonBuilder().create();
				out.print(gson.toJson(bbs).toString());

			} else {
				out.print("<script>");
				out.print("alert(등록 실패);");
				out.print("history.go(-1);");
				out.print("<script>");
			}

		} else if (action.equals("selectOne")) {

			bbs = bbsDAO.selectOne(bbs.getBbsnum());
			// 조회수 업데이트
			bbsDAO.ReadcountUpdate(bbs.getBbsnum());
			request.setAttribute("bbs", bbs);
			request.getRequestDispatcher("../members/view.jsp").forward(request, response);

		} else if (action.equals("edit")) {

			// 수정할 데이터 한건 조회
			bbs = bbsDAO.selectOne(bbs.getBbsnum());
			request.setAttribute("bbs", bbs);
			// 수정폼으로 포워드
			request.getRequestDispatcher("../members/modify.jsp").forward(request, response);

		} else if (action.equals("modify")) {
			// 수정 처리
			if (bbsDAO.update(bbs)) {

				// 목록으로 페이지 이동
				response.sendRedirect("../members/BBSServlet?action=list");
			} else {
				out.print("<script>");
				out.print("alert(수정 실패);");
				out.print("history.go(-1);");
				out.print("<script>");
			}

		} else if (action.equals("delete")) {
			// 삭제 처리
			if (bbsDAO.delete(bbs.getBbsnum())) {

				// 목록으로 페이지 이동
				response.sendRedirect("../members/BBSServlet?action=list");
			}

		} else if (action.equals("insertReply")) {
			// 답글중 가장 최근 답글이 위로 올라가게 처리한다.
			// 그러기 위해 답글의 순서인 seq를 1증가시킨다.
			bbs.setRef(bbs.getRef());
			bbs.setRe_step(bbs.getRe_step());
			bbsDAO.insertReply(bbs);

			response.sendRedirect("../members/BBSServlet?action=list");
		}

		else {
			out.print("잘못 된 action 입니다.");
		}
	}

}
