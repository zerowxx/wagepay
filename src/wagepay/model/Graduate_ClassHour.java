package wagepay.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

@SuppressWarnings("serial")
public class Graduate_ClassHour extends Model<Graduate_ClassHour>{
	private String GRADUATE_CLASSHOUR="select 课程名称,周数,周课时,基本课时,班系数增课时,硕士增课时,博士增课时,英语原版讲增课时,"
			+"合计课时,硕导课时,博导课时,总计课时,备注,最终总课时 from classhour_graduate_s where 教学工号=? and 学年 in "
			+"(select ParameterValue from Parameter where ParameterName='Academic_Year')";
	public static final Graduate_ClassHour dao =new Graduate_ClassHour();
	
	public List<Record> getGraduate_ClassHour(int teachingId) {
		return Db.find(GRADUATE_CLASSHOUR,teachingId);
	}

}
