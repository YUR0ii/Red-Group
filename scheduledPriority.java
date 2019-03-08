import java.util.*;


public class scheduledPriority {
	
	String level;
	
	Date date = new Date();
	
	scheduledPriority(String Level){
		level=Level;
	}
	//Initially sets the level and date
	scheduledPriority(String Level, Date Date){
		level = Level;
		date = Date;
		
	}
	
	public String getLevel(){
		return level;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setLevel(String Level) {
		level = Level;
	}
	
	public void setDate(Date Date) {
		date = Date;
	}
	
	public void setDateLevel(Date Date,String Level){
		date = Date;
		level = Level;
	}
	
}
