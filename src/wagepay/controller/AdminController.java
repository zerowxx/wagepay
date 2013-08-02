package wagepay.controller;

import java.util.List;

import wagepay.interceptor.*;
import wagepay.model.ClassHour;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

public class AdminController extends Controller{
	@Before(AdminInterceptor.class)
	public void index(){
		render("upload.html");
	}
	//@Before(AdminInterceptor.class)
	public void classhourConfirmed(){
		List<Record> confirmed=ClassHour.dao.getConfirmedNumber();
		setAttr("confirmedNumber",confirmed);
		render("classhourconfirm.html");
	}

}
