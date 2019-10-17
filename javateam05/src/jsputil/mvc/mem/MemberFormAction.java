package jsputil.mvc.mem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsputil.mvc.CommandAction;

public class MemberFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String mode = request.getParameter("mode");
		if(mode != null && mode.equals("zip")) {
			return "/mem/zip.jsp";
		}
		return "/mem/memberForm.jsp";
	}

}
