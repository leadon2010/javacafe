<%@page import="jdbc.GoodsDO"%>
<%@page import="members.OrderSets"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="members.OrdersDAO"%>
<%@page import="members.OrdersDO"%>
<%@page import="members.CartsDO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="members.MembersDO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="crtdo" class="members.CartsDO" />
<jsp:useBean id="crtdao" class="members.CartDAO" />
<jsp:setProperty name="crtdo" property="*" />
<jsp:useBean id="usrdo" class="members.MembersDO" />
<jsp:useBean id="usrdao" class="members.MembersDAO" />
<jsp:setProperty name="usrdo" property="*" />
<jsp:useBean id="orsdo" class="members.OrdersDO" />
<jsp:setProperty name="orsdo" property="*" />
<jsp:useBean id="orsdao" class="members.OrdersDAO" />
<jsp:useBean id="gdsdo" class="jdbc.GoodsDO" />
<jsp:setProperty name="gdsdo" property="*" />
<jsp:useBean id="gdsdao" class="jdbc.GoodsDAO" />
<%
	String action = request.getParameter("action");
	MembersDO userno = (MembersDO) session.getAttribute("userno");

	if (userno == null && action != null && !action.equals("login") && !action.equals("insert")) {
		//로그인을 안한 경우, action값이 없는 경우(?), 
		response.sendRedirect("login.jsp");

	} else {
		if (action == null) {
			//action 이 없는 경우
			System.out.println("action===" + action);
			response.sendRedirect("index.jsp");

		} else if (action.equals("list")) {
			//회원전체 조회
			System.out.println("action===" + action);
			ArrayList<MembersDO> usrlist = usrdao.selectAll();
			request.setAttribute("usrdo", usrlist);
			pageContext.forward("userAll.jsp");

		} else if (action.equals("find")) {
			//관리자가 회원조회를 해서 주문정보, 회원등급 변경, 할 경우에 조회하는 화면
			System.out.println("action===" + action);

		} else if (action.equals("cart")) {
			//상품을 장바구니에 담으면 장바구니 조회화면으로 간다.
			System.out.println("action===" + action);
			if (crtdao.insertProc(crtdo)) {
				ArrayList<CartsDO> clist = crtdao.selectAll(crtdo.getUser_no());
				request.setAttribute("clist", clist);
				pageContext.forward("cartList.jsp");
			} else {
				out.println("cart error action.");
			}
		} else if (action.equals("cartlistUser")) {
			System.out.println("action===" + action);
			ArrayList<CartsDO> clist = crtdao.selectAll(usrdo.getUser_no());
			request.setAttribute("clist", clist);
			pageContext.forward("cartList.jsp");

		} else if (action.equals("cartlist")) {
			//쇼핑을 하다가 장바구니를 조회하고 싶을 때
			System.out.println("action===" + action);
			ArrayList<CartsDO> clist = crtdao.selectAll(userno.getUser_no());
			request.setAttribute("clist", clist);
			pageContext.forward("cartList.jsp");

		} else if (action.equals("cart2orderUser")) {
			System.out.println("action===" + action);
			OrdersDO od = new OrdersDO();
			GoodsDO gdso = new GoodsDO();
			OrdersDAO oda = new OrdersDAO();
			od.setUser_no(userno.getUser_no());
			gdso.setProd_no(request.getParameter("prod_no"));
			System.out.println("prod_price:::" + request.getParameter("sales_price"));
			Integer prod_price = (Integer.parseInt(request.getParameter("sales_price")));

			gdso.setProd_price(prod_price);
			Integer onhand_qty = (Integer.parseInt(request.getParameter("order_qty")));
			gdso.setOnhand_qty(onhand_qty);

			String oeh = oda.createOrderHeader(od.getUser_no(), gdso);
			List<HashMap<String, Object>> c1list = oda.selectOrder(od.getUser_no(), oeh);
			request.setAttribute("c1list", c1list);
			List<HashMap<String, Object>> c2list = oda.selectAll(oeh);
			request.setAttribute("c2list", c2list);
			pageContext.forward("cartOrders.jsp");

		} else if (action.equals("cart2order")) {
			//장바구니에 있는 리스트를 주문의 헤더정보와 라인정보로 넘겨준다.
			//사용자번호로 관련 주문헤더 정보를 만들고 장바구니번호로 상품과 수량, 금액을 조회해서 주문상세정보 생성.
			System.out.println("action===" + action);
			OrdersDO od = new OrdersDO();
			OrdersDAO oda = new OrdersDAO();
			od.setUser_no(userno.getUser_no());
			od.setCart_no(request.getParameterValues("detail_no"));
			String oeh = oda.createOrderHeader(od.getUser_no(), od.getCart_no());
			List<HashMap<String, Object>> c1list = oda.selectOrder(od.getUser_no(), "");
			request.setAttribute("c1list", c1list);
			List<HashMap<String, Object>> c2list = oda.selectAll(oeh);
			request.setAttribute("c2list", c2list);
			pageContext.forward("cartOrders.jsp");

		} else if (action.equals("orderproc")) {
			System.out.println("action===" + action);
			String ret = orsdao.orderComplete(orsdo);
			out.print("orderProc" + ret);
			if (ret.equals("success")) {
				out.println("<script>alert(정상적으로 처리되었습니다)</script>");
				response.sendRedirect("../goods/GoodsServlet?action=goodsList");
			} else {
				out.println("<script>alert(no정상적으로 처리되었습니다)</script>");
			}

		} else if (action.equals("orderlistUser")) {
			System.out.println("action===" + action);
			OrdersDAO oda = new OrdersDAO();
			ArrayList<OrderSets> oslist = oda.selectOrdersDetails(usrdo.getUser_no());
			request.setAttribute("oslist", oslist);
			pageContext.forward("orderList.jsp");

		} else if (action.equals("orderlist")) {
			System.out.println("action===" + action);
			OrdersDAO oda = new OrdersDAO();
			//List<HashMap<String, Object>> c1list = oda.selectOrder(userno.getUser_no());
			ArrayList<OrderSets> oslist = oda.selectOrdersDetails(userno.getUser_no());
			request.setAttribute("oslist", oslist);
			pageContext.forward("orderList.jsp");

		} else if (action.equals("search")) {
			//관리자가 회원정보 확인 및 등급변경을 한다.
			System.out.println("action===" + action);
			MembersDO data = usrdao.selectOne(usrdo.getUser_no());
			request.setAttribute("usrdo", data);
			pageContext.forward("userSearch.jsp");

		} else if (action.equals("home")) {
			//사용자 홈 화면으로 간다.
			System.out.println("action===" + action);
			MembersDO newusr = usrdao.selectOne(usrdo.getUser_no());
			if (newusr.getUser_no() != null) {
				request.setAttribute("usrdo", newusr);
				pageContext.forward("home.jsp");
			} else {
				response.sendRedirect("home.jsp");
			}

		} else if (action.equals("mypage")) {
			System.out.println("action===" + action);
			MembersDO newusr = usrdao.selectOne(userno.getUser_no());
			if (newusr.getUser_no() != null) {
				request.setAttribute("usrdo", newusr);
				pageContext.forward("home.jsp");
			} else {
				response.sendRedirect("home.jsp");
			}

		} else if (action.equals("login")) {
			//회원이 로그인 할 경우에 등급정보를 가지고 와서 세션에 담아둔다.
			//로그인 실패일 경우 메세지를 보여주고 로그인화면을 다시 열어준다.
			System.out.println("action===" + action);
			MembersDO data = null;
			data = usrdao.login(usrdo.getUser_no(), usrdo.getPassword());
			if (data != null) {
				//session setting
				session.setAttribute("userno", data);
				//MembersDO abc = (MembersDO) session.getAttribute("userno");
				//response.sendRedirect("memberControl.jsp?action=home&user_no=" + usrdo.getUser_no());
				response.sendRedirect("../goods/GoodsServlet?action=goodsList");
			} else {
				out.print("<script>alert('사용자ID와 비밀번호를 확인하세요.')</script>");
				response.sendRedirect("main.jsp");
			}

		} else if (action.equals("insert")) {
			//회원가입 후 정보확인 및 수정하도록 사용자홈화면으로 간다.
			System.out.println("action===" + action);
			if (usrdao.insert(usrdo)) {
				response.sendRedirect("memberControl.jsp?action=home&user_no=" + usrdo.getUser_no());
			} else {
				out.println("login.jsp");
			}

		} else if (action.equals("updateGoods")) {
			System.out.println("action===" + action);
			if (gdsdao.update(gdsdo)) {
				request.setAttribute("datas", gdsdao.selectAll(""));
				pageContext.forward("../goods/adminGoodsList.jsp");

			} else {
				System.out.println("error");
			}

		} else if (action.equals("select")) {
			System.out.println("action===" + action);
			ArrayList<MembersDO> usrlist = usrdao.selectAll();
			request.setAttribute("usrdo", usrlist);
			pageContext.forward("userAll.jsp");

		} else if (action.equals("updateUserInfo")) {
			System.out.println("action===" + action);
			if (usrdao.update(usrdo)) {
				response.sendRedirect("memberControl.jsp?action=home&user_no=" + usrdo.getUser_no());

			} else {
				out.println("<script>alert('회원정보변경에러')</script>");

			}

		} else if (action.equals("logout")) {
			//세션을 초기화 한다.
			System.out.println("action===" + action);
			session.invalidate();
			response.sendRedirect("../index.jsp");

		} else {
			System.out.println("action=== else");
			out.println("error action.");

		}
	}
%>