import java.util.*;


public class scheduledPriority {
	
	String level;
	
<<<<<<< HEAD
	Date date = new Date();
	
	scheduledPriority(String Level){
		level=Level;
	}
=======
>>>>>>> branch 'master' of https://github.com/YUR0ii/Red-Group
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
		Level = level;
	}
	
}
