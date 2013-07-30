package wagepay.model;

import static wagepay.util.Md5Util.*;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

public class Admin extends Model<Admin>{
	
	private String LOGIN_SQL="SELECT * FROM admin WHERE adminname=? and adminpassword=?";
	public static final Admin dao = new Admin();
	
	public boolean login(String usename, String passwd) {
		System.out.println(getMD5(passwd.getBytes()));
		if (Db.findFirst(LOGIN_SQL, usename, getMD5(passwd.getBytes())) == null) {
			return false;
		} else {
			return true;
		}
	}
}
