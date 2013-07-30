package wagepay.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Parameter extends Model<Parameter>{
	
	private String UPDATE_YEAR="UPDATE parameter SET ParameterValue=? where ParameterName='Academic_Year'";
	public static final Parameter dao = new Parameter();
	
	public int updateYear(String parameter){
		return Db.update(UPDATE_YEAR, parameter);
		
	}

}
