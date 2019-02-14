package jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class EmpServlet DB에 저장되어있는것 보기
 */
@WebServlet("/event/EventServlet")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 인코딩
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		// 내장 객체
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		EventOffpriceDAO eventDAO = new EventOffpriceDAO();
		EventOffpriceDO eventDO = new EventOffpriceDO();

		try {
			BeanUtils.copyProperties(eventDO, request.getParameterMap()); // 앞단 화면의 뷰에서 여기에 담는다 / 지역변수는 eventDO;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// action에서 따라서 처리
		String action = request.getParameter("action");
		if (action == null) {
			out.println("action이 null입니다.");

		}  else if (action.equals("adminEventList")) {  // 이벤트 목록 페이지
			out.println("action이" + action);
			if (eventDO.getProd_category() == null) {
				eventDO.setProd_category("");
			}
			request.setAttribute("datas", eventDAO.selectAll(EventOffpriceDO.getEvent_name())); // 액션에서 객체를 request에 담을때.
			// request.setAttribute("객체명",객체);
			request.getRequestDispatcher("adminEventContent.jsp").forward(request, response);

		} else if (action.equals("adminEventContent")) { // 이벤트 내용 페이지
			
			
		} else if (action.equals("adminEventRegisterForm")) { // 등록폼(관리자)
			// 카테고리 목록 조회
			CategoryDAO categoryDAO = new CategoryDAO();
			ArrayList<CategoryDO> cate = categoryDAO.selectAll();
			request.setAttribute("cate", cate);
			request.getRequestDispatcher("adminEventRegister.jsp").forward(request, response); // 페이지명x

		} else if (action.equals("adminEventRegister")) { // 등록(관리자)
			//System.out.println("hhhh" + goodsDO.getProd_name());
			EventOffpriceDAO abc = new EventOffpriceDAO();
			abc.insert(eventDO);
			response.sendRedirect("EventServlet?action=adminEventList");

		} else if (action.equals("adminEventCorrectForm")) { // 수정폼(관리자)
			// 수정할 상품 한 건 조회
			EventOffpriceDO event = eventDAO.selectOne(EventOffpriceDO.getEvent_name());
			request.setAttribute("event", event);
			request.getRequestDispatcher("adminEventCorrect.jsp").forward(request, response); // 페이지명x

		} else if (action.equals("adminEventCorrect")) { // 수정(관리자)
			EventOffpriceDAO up = new EventOffpriceDAO();
			up.update(eventDO);
			response.sendRedirect("GoodsServlet?action=goodsList"); // 수정한 다음, 상품목록으로 돌아가기.

		} else if (action.equals("adminEventDelete")) { // 삭제(관리자)
														// 페이지명x
		} else {
			out.println("없는 action 입니다.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
