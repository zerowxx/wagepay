package wagepay.model;


import static wagepay.util.Md5Util.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.c3p0.C3p0Plugin;
@SuppressWarnings("serial")
public class User extends Model<User>{
	
	private String LOGIN_SQL = "SELECT * FROM User WHERE UserName=? AND UserPassword=?";         //定义SQL语句
	private String GET_TEACHINGID="SELECT 教学工号 FROM T_NO WHERE 姓名=? ";
	public static final User dao = new User();
	
	
	/**
	 * 测试mssql数据库连接
	 * @param C3p0Plugin2
	 * @return
	 */
	public List<Record> getIdFromSql(C3p0Plugin C3p0Plugin2){
		return Db.find(C3p0Plugin2.getDataSource(), "select * from User");
	}
	
	/**
	 * 查询根据用户名教学工号
	 * 
	 * @param name 用户名
	 * @return int TeachingID
	 */
	public int getTeachingID(String name){
		return Db.queryInt(GET_TEACHINGID, name);
	}
	/**
	 * 用户登录
	 * 
	 * @param username 用户名
	 * @param passwd 密码
	 * @return boolean true为可用
	 */
	public boolean login(String usename, String passwd) {
		System.out.println(getMD5(passwd.getBytes()));
		if (Db.findFirst(LOGIN_SQL, usename, getMD5(passwd.getBytes())) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	

}
