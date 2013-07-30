package wagepay.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

@SuppressWarnings("serial")
public class AdultEducation_ClassHour extends Model<AdultEducation_ClassHour>{
	
	private String ADULTEDUCATION_CLASSHOUR="select * from classhour_adulteducation_s where 教学工号=? and 学年  in"+
			"(select ParameterValue from Parameter where ParameterName='Academic_Year')";
	
	public static final AdultEducation_ClassHour dao=new AdultEducation_ClassHour();
	
	public List<Record> getAdultEducation_ClassHour(int teachingId){
		return Db.find(ADULTEDUCATION_CLASSHOUR,teachingId);
	}
	

}
