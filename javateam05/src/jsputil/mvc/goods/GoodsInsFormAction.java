package jsputil.mvc.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsputil.mvc.CommandAction;

public class GoodsInsFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		 
		return "/goods/goodsInsForm.jsp";
	}

}
