package wagepay.controller;


import java.io.File;
import java.util.List;

import wagepay.model.Admin;
import wagepay.model.AdultEducation_ClassHour;
import wagepay.model.ClassHour;
import wagepay.model.Graduate_ClassHour;
import wagepay.model.GraduationDesign_ClassHour;
import wagepay.model.Parameter;
import wagepay.model.UnderGraduate_ClassHour;
import wagepay.model.User;
import wagepay.validate.LoginValidate;
import wagepay.config.MyConfig;
import wagepay.interceptor.AdminInterceptor;
import wagepay.interceptor.SessionInterceptor;


import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;

public class CommonController extends Controller {
	
	public void index() {
		String username = this.getSessionAttr("UserName");
		if(username!=null)
			render("index.html");
		else
			render("login.html");
	}
	/**
	 * 用户主页
	 */
	@Before(SessionInterceptor.class)
	public void home(){
		String username = this.getSessionAttr("UserName");
		int teachingId=User.dao.getTeachingID(username);
		getRequest().getSession().setAttribute("TeachingId",teachingId);
		//System.out.println(teachingId);
		
		try{
		Record classhour=ClassHour.dao.getClassHour(teachingId);
		List<Record> undergraduate_classhour=UnderGraduate_ClassHour.dao.getUnderGraduate_ClassHour(teachingId);
		List<Record> graduate_classhour=Graduate_ClassHour.dao.getGraduate_ClassHour(teachingId);
		List<Record> graduationdesign_classhour=GraduationDesign_ClassHour.dao.getGraduationDesign_ClassHour(teachingId);
		List<Record> adulteducation_classhour=AdultEducation_ClassHour.dao.getAdultEducation_ClassHour(teachingId);
		
		//当前学期没有课时用户登录，空指针异常！！！！！
		//boolean confirmflag=ClassHour.dao.getConfirmFlag(teachingId);
		//System.out.println(confirmflag);
		if(classhour!=null) {
			boolean confirmflag=ClassHour.dao.getConfirmFlag(teachingId);
			setAttr("flag",confirmflag);
		}
		
		setAttr("classhour", classhour);
		setAttr("undergraduate_classhourlist",undergraduate_classhour);
		setAttr("graduate_classhourlist",graduate_classhour);
		setAttr("graduationdesign_classhourlist",graduationdesign_classhour);
		setAttr("adulteducation_classhourlist",adulteducation_classhour);
		
		render("index.html");
		}catch(Exception e){
			e.printStackTrace();
			renderError404();
		}
	}
	/**
	 * 测试mssql数据库连接
	 */
	public void test(){
		wagepay.config.MyConfig config=new MyConfig();
		
		
		List<Record> user=User.dao.getIdFromSql(config.c3p0Plugin2);
		
		setAttr("user",user);
		render("test.html");
	}
	
	/**
	 * 登录页面
	 * 
	 * @return void
	 * @author 
	 */
	public void loginpg() {		
		render("login.html");				
	}
	
	/**
	 * 登录验证
	 * 
	 * @return void
	 * @author 
	 */
	@Before(LoginValidate.class)
	public void login() {
			String UserName = getPara("UserName");
			String Password = getPara("Password");
			String IsAdmin =getPara("User");
			if(IsAdmin.equals("adminuser")){
				System.out.println(IsAdmin);
				
				boolean success=Admin.dao.login(UserName, Password);
				if(!success){
					keepPara("UserName").setAttr("Error", "用户名或密码错误或用户类型错误！");
					forwardAction("/loginpg");
				}else{
					getRequest().getSession().setAttribute("Admin", UserName);
					setAttr("altertype", "success");//类型值可以为 error success 或不设置该值
					setAttr("alterinfo", "登录成功!").render("alerts.html");
					getResponse().setHeader("Refresh","2;URL=/admin");
				}
			}else{
				boolean success = User.dao.login(UserName, Password);			
				if(!success){
					keepPara("UserName").setAttr("Error", "用户名或密码错误或用户类型错误！");
					forwardAction("/loginpg");
				}else{
					getRequest().getSession().setAttribute("UserName",UserName);
					//String expert = Db.findFirst("select Expert from UserInfo where UserName =?",UserName).getBoolean("Expert").toString();
					//System.out.println("expert:"+expert);
					//getRequest().getSession().setAttribute("Expert",expert);
					setAttr("altertype", "success");//类型值可以为 error success 或不设置该值
					setAttr("alterinfo", "登录成功!").render("alerts.html");
					getResponse().setHeader("Refresh","2;URL=/home");
				}
			}

	}
	/**
	 * 用户退出
	 * 
	 * @return void
	 * @author 
	 */
	public void exit() {
		getRequest().getSession().invalidate();
		setAttr("altertype", "success");//类型值可以为 error success 或不设置该值
		setAttr("alterinfo", "您已成功退出!").render("alerts.html");
		getResponse().setHeader("Refresh","2;URL=/loginpg");
	}
	/**
	 * 确认课时操作
	 * 
	 * @return void
	 * @author
	 */
	@Before(SessionInterceptor.class)
	public void confirm(){
		int teachingId=getParaToInt("teachingid");
		int success=ClassHour.dao.setConfirmFlag(teachingId);
		if(success!=1){
			renderError404();
		}else{
			setAttr("altertype", "success");//类型值可以为 error success 或不设置该值
			setAttr("alterinfo", "确认成功!").render("alerts.html");
			getResponse().setHeader("Refresh","2;URL=/home");
		}
	}
	
	//管理员级别拦截器！！！
	@Before(AdminInterceptor.class)
	public void uploadpg(){
		render("upload.html");
	}
	
	/**
	 * 文件上传
	 * 
	 * @author wxx
	 */
	public void upload(){
		String filepath=PathKit.getWebRootPath() +"/upload/";
		try{
			deleteFolder(filepath);
			
			UploadFile file = getFile("file", PathKit.getWebRootPath()+ "/upload");
			
			String data="data.xls";
			String originfilename=file.getOriginalFileName();
			if(!(originfilename.length()>0)){
				setAttr("FIleError","请选择上传文件！").render("upload.html");
			}else if(!originfilename.equals(data)){
				setAttr("FileError", "请上传模版文件(data.xls)!").render("upload.html");
				deleteFolder(filepath);
			}
			
			//判断学期，是否应该同当前日期判断输入是否正确？
			String year=getPara("year");
			if(!(year.length()>0)){
				setAttr("YearError","学年不能为空！").render("upload.html");
			}
		}catch (Exception e) {
			setAttr("altertype", "error");//类型值可以为 error success 或不设置该值
			setAttr("alterinfo", "文件上传失败!").render("alerts.html");
			getResponse().setHeader("Refresh","2;URL=/uploadpg");
			System.out.println("上传出错~~~");
		}
		//处理参数“学年”
		String year=getPara("year");
		int success=Parameter.dao.updateYear(year);
		if(success!=1){
			renderError404();
		}else{
			setAttr("altertype", "success");//类型值可以为 error success 或不设置该值
			setAttr("alterinfo", "上传成功！!").render("alerts.html");
			getResponse().setHeader("Refresh","2;URL=/uploadpg");
		}

	}
	/**
	 * 删除目录和目录下文件
	 * 
	 * @param filepath 文件路径
	 */
	public void deleteFolder(String filepath){
		File f = new File(filepath);
		if(f.exists()&&f.isDirectory()){
			if(f.listFiles().length==0){
				f.delete();
			}else{
				File delFile[]=f.listFiles();
				int i=f.listFiles().length;
				for(int j=0;j<i;j++){
					if(delFile[j].isDirectory()){
						deleteFolder(delFile[j].getAbsolutePath());
					}
					delFile[j].delete();
				}
			}
		}
	}


}
