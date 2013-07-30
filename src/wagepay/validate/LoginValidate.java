package wagepay.validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidate extends Validator {
	/**
	 * 对用户名和密码进行验证
	 * 
	 * @param c 控制器
	 * @return void
	 */
	@Override
	protected void validate(Controller c) {
		
		//validateRegex("UserName", "^[A-Za-z0-9]{3,12}$", "UserNameError", "用户名格式错误");
		validateRegex("Password", "^[A-Za-z0-9]{6,16}$", "PassWordError", "密码格式错误");
		
		validateRequiredString("UserName", "UserNameError", "请输入用户名");
		validateRequiredString("Password", "PassWordError", "请输入密码");
	}
	/**
	 *出现错误后跳转到登录页面
	 * 
	 * @param c 控制器
	 * @return void
	 */
	@Override
	protected void handleError(Controller c) {
		c.keepPara("UserName","Password").forwardAction("/loginpg");		
	}

}
