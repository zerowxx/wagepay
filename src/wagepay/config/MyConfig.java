package wagepay.config;


import javax.sql.DataSource;

import wagepay.controller.AdminController;
import wagepay.controller.ClassHourController;
import wagepay.controller.CommonController;
import wagepay.model.AdultEducation_ClassHour;
import wagepay.model.ClassHour;
import wagepay.model.Graduate_ClassHour;
import wagepay.model.GraduationDesign_ClassHour;
import wagepay.model.UnderGraduate_ClassHour;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;


/**
 * API引导式配置
 */
public class MyConfig extends JFinalConfig {
	public C3p0Plugin c3p0Plugin2;
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		loadPropertyFile("conf.properties");				// 加载少量必要配置，随后可用getProperty(...)获取值
		me.setDevMode(getPropertyToBoolean("devMode", false));
		me.setFreeMarkerTemplateUpdateDelay(1000);
		me.setBaseViewPath("/WEB-INF/pages");
		me.setError404View("/WEB-INF/pages/404.html");
		me.setError500View("/WEB-INF/pages/500.html");
		me.setMaxPostSize(10*1024*1024);
		me.setUploadedFileSaveDirectory("/upload");
		//me.setViewType(ViewType.JSP); 							// 设置视图类型为Jsp，否则默认为FreeMarker
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/", CommonController.class);
		me.add("/classhour", ClassHourController.class, "classhour");
		me.add("/admin", AdminController.class,"admin");
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件  !!!!如何配置合适！！！！
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
		
		DataSource d=c3p0Plugin.getDataSource();
		//c3p0Plugin2= new C3p0Plugin(getProperty("jdbcUrl2"),getProperty("user2"),getProperty("password2"),getProperty("driver"));
		me.add(c3p0Plugin);
		//me.add(c3p0Plugin2);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		
		arp.setDialect(new AnsiSqlDialect());
		//arp.setShowSql(true);
		arp.setContainerFactory(new CaseInsensitiveContainerFactory(true));
		me.add(arp);
		arp.addMapping("classhour_summarize_s", ClassHour.class);
		arp.addMapping("classhour_undergraduate_s", UnderGraduate_ClassHour.class);
		arp.addMapping("classhour_graduate_s", Graduate_ClassHour.class);
		arp.addMapping("classhour_graduationdesign_s", GraduationDesign_ClassHour.class);
		arp.addMapping("classhour_adulteducation_s",AdultEducation_ClassHour.class);
		
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		me.add(new SessionInViewInterceptor());
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		
	}
	
	/**
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 5);
	}
}

