import java.util.Date;

public class priorityEvent extends Event{
	
	String oldLevel;
	String newLevel;
	Date oldDate;
	Date newDate;
	
	//takes levels as strings and sets the internal level variables
	priorityEvent(String OldLevel, String NewLevel){
		super();
		oldLevel = OldLevel;
		newLevel = NewLevel;
	}
	
	//takes the old and new date of a check box
	priorityEvent(String level, Date oldCheckDate, Date newCheckDate){
		super();
		newLevel=level;
		oldDate=oldCheckDate;
		newDate=newCheckDate;
	}
	//takes scheduledPriority instances and converts them into level Strings
	priorityEvent(scheduledPriority oldPriority, scheduledPriority newPriority){
		oldLevel = oldPriority.getLevel();
		newLevel = newPriority.getLevel();
	}
	
	public String getOldLevel(){
		return oldLevel;
	}
	
	public String getNewLevel() {
		return newLevel;
	}
	
	public Date getOldDate() {
		return oldDate;
	}
	
	public Date getNewDate() {
		return newDate;
	}
	
	@Override
	//Still working here
	public String createSentence() {
		// TODO Auto-generated method stub
		
		String day = Event.convertDay(date.getDate());
		String month = Event.convertMonth(date.getMonth());
		String output;
		if(oldDate==null) {
			output = "The priority level changed from " + oldLevel +" to " + newLevel + " on " + day + " " + (date.getMonth()+1) +"/" + date.getDate()+ "/" + (1900+date.getYear()); 
		}else {
			output = "The upgrade date for " + newLevel + " changed from " + oldDate.toString() +" to " + newDate.toString() + " on " + day + " " + (date.getMonth()+1) +"/" + date.getDate()+ "/" + (1900+date.getYear()); 
		}		
				
		return output;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Priority";
	}
	@Override
	public String getOldComment() {
		// TODO Auto-generated method stub
		
		System.out.println("Ya cant get comments from priority events dum dum");
		return null;
	}

	@Override
	public String getNewComment() {
		// TODO Auto-generated method stub
		System.out.println("Ya cant get comments from priority events dum dum");
		return null;
	}

}
