package wagepay.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

@SuppressWarnings("serial")
public class GraduationDesign_ClassHour extends Model<GraduationDesign_ClassHour>{
	
	private String GRADUATIONDESIGN_CLASSHOUR="select * from classhour_graduationdesign_s where 教学工号=? and 学年  in"+
			"(select ParameterValue from Parameter where ParameterName='Academic_Year')";
	public static final GraduationDesign_ClassHour dao =new GraduationDesign_ClassHour();
	
	public List<Record> getGraduationDesign_ClassHour(int teachingId) {
		return Db.find(GRADUATIONDESIGN_CLASSHOUR,teachingId);
	}
}
