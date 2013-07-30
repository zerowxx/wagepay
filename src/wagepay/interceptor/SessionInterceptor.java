package wagepay.interceptor;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class SessionInterceptor implements Interceptor {
	
	/**
	 * 拦截方法
	 * 
	 * @param ai JFinal动作调度器 
	 * @return void
	 * @author 
	 */
	public void intercept(ActionInvocation ai) {
		Controller c = ai.getController();
		String username = c.getSessionAttr("UserName");
		if (username == null) {
			c.setAttr("altertype", "error")//类型值可以为 error success 或不设置该值
			.setAttr("alterinfo", "您还没有登录，请您先登录!").render("/WEB-INF/pages/alerts.html");
			c.getResponse().setHeader("Refresh","1;URL=/loginpg");
		}else{
			ai.invoke();
		}
	}
}
