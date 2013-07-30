package wagepay.controller;

import java.util.List;

import wagepay.interceptor.SessionInterceptor;
import wagepay.model.AdultEducation_ClassHour;
import wagepay.model.Graduate_ClassHour;
import wagepay.model.UnderGraduate_ClassHour;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

public class ClassHourController extends Controller{
	
	
	/**
	 * 本科课时详细
	 * 
	 * @return void
	 * @author wxx
	 */
	@Before(SessionInterceptor.class)
	public void undergraduate(){
		int teachingId = this.getSessionAttr("TeachingId");
		List<Record> undergraduate_classhour=UnderGraduate_ClassHour.dao.getUnderGraduate_ClassHour(teachingId);
		setAttr("undergraduate_classhourlist",undergraduate_classhour);
		render("undergraduate.html");
	}
	/**
	 * 研究生课时详细
	 * 
	 * @return void
	 * @author wxx
	 */
	@Before(SessionInterceptor.class)
	public void graduate(){
		int teachingId = this.getSessionAttr("TeachingId");
		List<Record> graduate_classhour=Graduate_ClassHour.dao.getGraduate_ClassHour(teachingId);
		setAttr("graduate_classhourlist",graduate_classhour);
		render("graduate.html");
	}
	
	public void adulteducation(){
		int teachingId = this.getSessionAttr("TeachingId");
		List<Record> adulteducation_classhour=AdultEducation_ClassHour.dao.getAdultEducation_ClassHour(teachingId);
		setAttr("adulteducation_classhourlist",adulteducation_classhour);
		render("adulteducation.html");
	}

}
