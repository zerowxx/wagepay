package wagepay.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

@SuppressWarnings("serial")
public class UnderGraduate_ClassHour extends Model<UnderGraduate_ClassHour> {
	private String CLASSHOUR_UNDERGRADUATE="select * from classhour_undergraduate_s where 教学工号=? and 学年  in"+
			"(select ParameterValue from Parameter where ParameterName='Academic_Year')";
	public static final UnderGraduate_ClassHour dao = new UnderGraduate_ClassHour();

	
	public List<Record> getUnderGraduate_ClassHour(int teachingId) {
		return Db.find(CLASSHOUR_UNDERGRADUATE,teachingId);
	}
	
}
