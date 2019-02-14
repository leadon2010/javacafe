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
@WebServlet("/goods/GoodsServlet")
public class GoodsServlet extends HttpServlet {
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

		GoodsDAO goodsDAO = new GoodsDAO();
		GoodsDO goodsDO = new GoodsDO();

		try {
			BeanUtils.copyProperties(goodsDO, request.getParameterMap()); // 앞단 화면의 뷰에서 여기에 담는다
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// action에서 따라서 처리
		String action = request.getParameter("action");
		if (action == null) {
			out.println("action이 null입니다.");

		} else if (action.equals("adminGoodsForm")) { // 상세보기 페이지
			System.out.println("action===" + action);
			GoodsDO gds = goodsDAO.selectOne(goodsDO.getProd_no());
			request.setAttribute("goods", gds); // 액션에서 객체를 request에 담을때. request.setAttribute("객체명",객체);
			request.getRequestDispatcher("../goods/adminGoodsForm.jsp").forward(request, response);

		} else if (action.equals("goodsForm")) { // 상세보기 페이지
			System.out.println("action===" + action);
			GoodsDO gds = goodsDAO.selectOne(goodsDO.getProd_no());
			request.setAttribute("goods", gds); // 액션에서 객체를 request에 담을때. request.setAttribute("객체명",객체);
			request.getRequestDispatcher("../goods/goodsForm.jsp").forward(request, response);

		} else if (action.equals("adminGoodsList")) {
			out.println("action이" + action);
			if (goodsDO.getProd_category() == null) {
				goodsDO.setProd_category("");
			}
			request.setAttribute("datas", goodsDAO.selectAll(goodsDO.getProd_category())); // 액션에서 객체를 request에 담을때.
			// request.setAttribute("객체명",객체);
			request.getRequestDispatcher("adminGoodsList.jsp").forward(request, response);

		} else if (action.equals("goodsList")) {
			System.out.println("action===" + action);
			if (goodsDO.getProd_category() == null) {
				goodsDO.setProd_category("");
			}
			request.setAttribute("datas", goodsDAO.selectAll(goodsDO.getProd_category())); // 액션에서 객체를 request에 담을때.
			// request.setAttribute("객체명",객체);
			request.getRequestDispatcher("goodsList.jsp").forward(request, response);

		} else if (action.equals("goodsConfirm")) {// 주문확인
			System.out.println("action===" + action);
			request.getRequestDispatcher("goodsConfirm.jsp").forward(request, response);

		} else if (action.equals("goodsOrder")) { // 주문확인버튼
			System.out.println("action===" + action); // 주문확인버튼은 페이지명x

		} else if (action.equals("cartCheck")) { // 장바구니확인
			System.out.println("action===" + action);
			request.getRequestDispatcher("cart.jsp").forward(request, response);

		} else if (action.equals("cartOrder")) { // 장바구니담기
			System.out.println("action===" + action); // 장바구니담기는 페이지명x

		} else if (action.equals("adminGoodsRegisterForm")) { // 상품등록폼(관리자)
			System.out.println("action===" + action);
			// 카테고리 목록 조회
			CategoryDAO categoryDAO = new CategoryDAO();
			ArrayList<CategoryDO> cate = categoryDAO.selectAll();
			request.setAttribute("cate", cate);
			request.getRequestDispatcher("adminGoodsRegister.jsp").forward(request, response); // 페이지명x

		} else if (action.equals("adminGoodsRegister")) { // 상품등록(관리자)
			System.out.println("action===" + action);
			// System.out.println("hhhh" + goodsDO.getProd_name());
			goodsDAO.insert(goodsDO);
			response.sendRedirect("GoodsServlet?action=goodsList");

		} else if (action.equals("adminGoodsCorrectForm")) { // 상품수정폼(관리자)
			System.out.println("action===" + action);
			// 수정할 상품 한 건 조회
			GoodsDO gds = goodsDAO.selectOne(goodsDO.getProd_no());
			request.setAttribute("goods", gds);

			request.getRequestDispatcher("adminGoodsCorrect.jsp").forward(request, response); // 페이지명x

		} else if (action.equals("adminGoodsCorrect")) { // 상품수정(관리자)
			System.out.println("action===" + action);
			goodsDAO.update(goodsDO);
			response.sendRedirect("GoodsServlet?action=goodsList"); // 수정한 다음, 상품목록으로 돌아가기.

		} else if (action.equals("adminGoodsDelete")) { // 상품삭제(관리자)
			System.out.println("action===" + action); // 페이지명x
			
		} else {
			out.println("없는 action 입니다.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
