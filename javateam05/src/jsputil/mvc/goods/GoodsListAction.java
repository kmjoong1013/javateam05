package jsputil.mvc.goods;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsputil.mvc.CommandAction;

public class GoodsListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		GoodsDAO goodsDao = GoodsDAO.getInstance();
		List<GoodsVO> goodsList = goodsDao.getGoodsList();
		
		request.setAttribute("goodsList", goodsList);

		return "/goods/goodsList.jsp";
	}

}
