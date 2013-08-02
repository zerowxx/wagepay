package wagepay.controller;

import java.io.File;
import java.util.List;

import wagepay.interceptor.*;
import wagepay.model.ClassHour;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Record;

public class AdminController extends Controller {
	/**
	 * 管理员主页
	 * 
	 */
	@Before(AdminInterceptor.class)
	public void index() {
		render("upload.html");
	}

	/**
	 * 查看课时确认情况
	 * 
	 */
	@Before(AdminInterceptor.class)
	public void classhourConfirmed() {
		List<Record> confirmed = ClassHour.dao.getConfirmedNumber();
		setAttr("confirmedNumber", confirmed);
		render("classhourconfirm.html");
	}
	/**
	 * 文件下载页面
	 * 
	 * @author wxx
	 */
	public void downloadpg(){
		render("download.html");
	}
	
	/**
	 * 下载文件
	 * 
	 * @author wxx
	 */
	public void download() {

		String filepath = PathKit.getWebRootPath() + "/download/";
		//参数需通过数据库取，kettle在导出文件时，把文件名存数据库
		File file = new File(filepath + "12345.xls");

		if (file.exists()) {
			renderFile(file);
			return; // 关键在这里，要有 return; 否则 index()方法会被执行
		}
		index();
	}

}
