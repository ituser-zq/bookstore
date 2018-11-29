package cn.jxau.bookstore.cart.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;
import cn.jxau.bookstore.book.domain.Book;
import cn.jxau.bookstore.book.service.BookService;
import cn.jxau.bookstore.cart.domain.Cart;
import cn.jxau.bookstore.cart.domain.CartItem;

public class CartServlet extends BaseServlet {
	/**
	 * 购物车添加条目
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 得到车
		 * 2. 得到条目（得到图书和数量）
		 * 3. 把条目添加到车中
		 */
		/*
		 * 得到车
		 */
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		/*
		 * 表单传递的只有bid和数量
		 * 2. 得到条目
		 *   > 得到图书和数量
		 *   > 先得到图书的bid，然后我们需要通过bid查询数据库得到Book
		 *   > 数量表单中有
		 */
		String bid = request.getParameter("bid");
		Book book = new BookService().load(bid);
		int count = Integer.parseInt(request.getParameter("count"));
		CartItem cartItem = new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		
		/*
		 * 把条目添加到购物车
		 */
		cart.add(cartItem);
		return "f:/jsps/cart/list.jsp";
	}

	/**
	 * 购物车清空条目
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		cart.clear();
		return "f:/jsps/cart/list.jsp";
	}

	/**
	 * 购物车删除条目
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		String bid = request.getParameter("bid");
		cart.delect(bid);
		return "f:/jsps/cart/list.jsp";
	}
}
