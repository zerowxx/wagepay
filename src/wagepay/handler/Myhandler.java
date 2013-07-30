package wagepay.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;
import com.jfinal.kit.HandlerKit;

public class Myhandler extends Handler{
	
	public void handle(String target,
			HttpServletRequest request,
			HttpServletResponse response,
			boolean[] isHandled) {
		if (target.indexOf(".") != -1)
			HandlerKit.renderError404("/pages/404.html", request, response, isHandled);
		else
			nextHandler.handle(target, request, response, isHandled);
  }
		
}
