
public class priorityEvent extends Event{
	
	String oldLevel;
	String newLevel;
	
	//takes levels as strings and sets the internal level variables
	priorityEvent(String OldLevel, String NewLevel){
		oldLevel = OldLevel;
		newLevel = NewLevel;
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

	@Override
	//Still working here
	public String createSentence() {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		
		String day = Event.convertDay(date.getDate());
		String month = Event.convertMonth(date.getMonth());
		
		String output = "The priority level changed from " + oldLevel +" to " + newLevel + " on " + day + " " + (date.getMonth()+1) +"/" + date.getDate()+ "/" + (1900+date.getYear()); 
				
		return output;
=======
//		String output = ""
>>>>>>> branch 'master' of https://github.com/YUR0ii/Red-Group
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
