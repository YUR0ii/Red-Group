
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
//		String output = ""
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
