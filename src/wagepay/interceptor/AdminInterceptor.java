package wagepay.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class AdminInterceptor implements Interceptor{

	@Override
	public void intercept(ActionInvocation ai) {
		// TODO Auto-generated method stub
		Controller c = ai.getController();
		String username = c.getSessionAttr("Admin");
		if (username == null) {
			c.setAttr("altertype", "error")//类型值可以为 error success 或不设置该值
			.setAttr("alterinfo", "您不是管理员，请管理员帐号登录!").render("/WEB-INF/pages/alerts.html");
			c.getResponse().setHeader("Refresh","1;URL=/loginpg");
		}else{
			ai.invoke();
		}
	}
	

}
