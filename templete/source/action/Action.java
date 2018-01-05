package [##_Project-Package-Action_##];

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.manual.douzone.handler.vo.MemberBean;
import [##_Project-Package-Name_##].common.controller.Action;
import [##_Project-Package-DAO_##].[##_Subject-DAO-Name_##];
import [##_Project-Package-Bean_##].[##_Subject-Bean-Name_##];

public class [##_Subject-Action-Name_##] implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		MemberBean member = (MemberBean) session.getAttribute("MemberBean");
		
		[##_Subject-DAO-Name_##] [##_Subject-DAO-Alias_##] = new [##_Subject-DAO-Name_##]();
		ArrayList<[##_Subject-Bean-Name_##]> [##_Subject-Name-lowercase_##]List = [##_Subject-DAO-Alias_##].getList();
		
		if(member != null)
		{
			request.setAttribute("[##_Subject-Name-lowercase_##]List", [##_Subject-Name-lowercase_##]List);
			
			return "RequestDispatcher:jsp/[##_Subject-Name-lowercase_##].jsp";
		}
		
		return "RequestDispatcher:jsp/404.jsp";
		
	}

}
