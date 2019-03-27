import java.io.Serializable;
import java.util.*;


public class scheduledPriority implements Serializable{
	String level;
	Date date;
	boolean active=false;
	
	scheduledPriority(String Level){
		level=Level;
		date=null;
	}
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
	
	public boolean getActive() {
		return active;
	}
	
	public void setActive(boolean boo) {
		active=boo;
	}
}