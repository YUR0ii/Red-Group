import java.util.Calendar;

public class priorityEvent extends Event{
	
	String oldLevel;
	String newLevel;
	
	//takes levels as strings and sets the internal level variables
	priorityEvent(String OldLevel, String NewLevel){
		oldLevel = OldLevel;
		newLevel = NewLevel;
		date=Calendar.getInstance().getTime();
		createSentence();
	}
	
	//takes scheduledPriority instances and converts them into level Strings
	priorityEvent(scheduledPriority oldPriority, scheduledPriority newPriority){
		oldLevel = oldPriority.getLevel();
		newLevel = newPriority.getLevel();
		
		createSentence();
	}
	
	public String getOldLevel(){
		return oldLevel;
	}
	
	public String getNewLevel() {
		return newLevel;
	}

	@SuppressWarnings("deprecation")
	@Override
	//Still working here
	public String createSentence() {
		// TODO Auto-generated method stub
		
		String day;
		int dayNum = date.getDay();
		if(dayNum == 0) {
			day = "Sunday";
		}
		else if(dayNum ==1) {
			day = "Monday";
		}
		else if(dayNum ==2) {
			day = "Tuesday";
		}
		else if(dayNum ==3) {
			day = "Wednesday";
		}
		else if(dayNum ==4) {
			day = "Thursday";
		}
		else if(dayNum ==5) {
			day = "Friday";
		}
		else{
			day = "Saturday";
		}
		
		String output = "The priority level changed from " + oldLevel +" to " + newLevel + " on " + day + " " + date.getMonth() +"/" + date.getDate()+ "/" + date.getYear(); 
				
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
		return null;
	}

	@Override
	public String getNewComment() {
		// TODO Auto-generated method stub
		return null;
	}

}
