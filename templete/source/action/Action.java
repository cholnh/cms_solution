package /*Project-Package-Action*/;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.manual.douzone.handler.vo.MemberBean;
import /*Project-Package-Name*/.common.controller.Action;
import /*Project-Package-DAO*/./*Subject-DAO-Name*/;
import /*Project-Package-Bean*/./*Subject-Bean-Name*/;

public class /*Subject-Action-Name*/ implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		MemberBean member = (MemberBean) session.getAttribute("MemberBean");
		
		/*Subject-DAO-Name*/ /*Subject-DAO-Alias*/ = new /*Subject-DAO-Name*/();
		ArrayList</*Subject-Bean-Name*/> /*Subject-Name-lowercase*/List = /*Subject-DAO-Alias*/.getList();
		
		if(member != null)
		{
			request.setAttribute("/*Subject-Name-lowercase*/List", /*Subject-Name-lowercase*/List);
			
			return "RequestDispatcher:jsp//*Subject-Name-lowercase*/.jsp";
		}
		
		return "RequestDispatcher:jsp/404.jsp";
		
	}

}
