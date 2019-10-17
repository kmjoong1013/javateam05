package jsputil.mvc.goods;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsputil.mvc.CommandAction;

public class GoodsInsAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		request.setCharacterEncoding("UTF-8");
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String color = request.getParameter("color");
		
		GoodsVO goods = new GoodsVO();
		goods.setCode(code);
		goods.setName(name);
		int intPrice = Integer.parseInt(price);
		goods.setPrice(intPrice);
		goods.setColor(color);
		
		GoodsDAO goodsDao = GoodsDAO.getInstance();
		goodsDao.goodsIns(goods);
		response.sendRedirect("goodsList.do");
	 
		return "";
	}

}
