package wagepay.model;


import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

@SuppressWarnings("serial")
public class ClassHour extends Model<ClassHour> {
	private String CLASSHOUR="select 教学工号,本科总课时,研究生总课时,成教总课时,毕设总课时,合计总课时  from classhour_summarize_s " +
			"where 教学工号=? and 学年 in (select ParameterValue from Parameter where ParameterName='Academic_Year')";
	
	
	public static final ClassHour dao = new ClassHour();
	
	/**
	 * 获取个人课时汇总信息
	 * 
	 * @param teachingId
	 * @return record
	 */
	public Record getClassHour(int teachingId) {
		//return Db.find(CLASSHOUR,teachingId);
		return Db.findFirst(CLASSHOUR, teachingId);
	}
	
	/**
	 * 确认超课时，确认标记置1
	 * 
	 * @param teachingId
	 * @return int
	 */
	public int setConfirmFlag(int teachingId){
		return  Db.update("UPDATE classhour_summarize_s SET 确认标记 = 1 WHERE 教学工号 = ?", teachingId);
	}
	
	/**
	 * 获取确认标记值
	 * 
	 * @param teachingId
	 * @return boolean
	 */
	public boolean getConfirmFlag(int teachingId){
		return Db.queryBoolean("SELECT 确认标记   FROM classhour_summarize_s WHERE 教学工号=? ", teachingId);
	}

}
