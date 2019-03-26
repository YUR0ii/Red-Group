import java.util.ArrayList;
import java.util.Date;

public class priorityEvent extends Event{
	
	private String oldLevel;
	private String newLevel;
	private Date oldDate;
	private Date newDate;
	
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
	public ArrayList<String> createSentence() {
		// TODO Auto-generated method stub
		
		String day = Event.convertDay(date.getDay());
		String month = Event.convertMonth(date.getMonth());
		sentence.clear();
		if(oldDate==null) {
			firstFiller="The priority level changed from ";
			secondFiller=" to ";
			sentence.add(firstFiller);
			sentence.add(oldLevel);
			sentence.add(secondFiller);
			sentence.add(newLevel);
		}else {
			firstFiller="The upgrade date for ";
			secondFiller=" changed from ";
			sentence.add(firstFiller);
			sentence.add(oldDate.toString());
			sentence.add(secondFiller);
			sentence.add(newDate.toString());
			sentence.add(" to ");
		}
		sentence.add(on);
		sentence.add(Event.getDateString(date));
		for(String s: sentence) {
		}
		return sentence;
	}
	
	public int getStringType(int index) {
		String temp=sentence.get(index);
		if(temp.equals(oldLevel)) {
			return 1;
		}else if(temp.equals(newLevel)) {
			return 1;
		}
		if(temp.equals(oldDate)) {
			return 2;
		}else if(temp.equals(newDate)) {
			return 2;
		}
		return 0;
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Priority";
	}
	@Override
	public String getOldComment() {
		// TODO Auto-generated method stub
		
//		System.out.println("Ya cant get comments from priority events dum dum");
		return null;
	}

	@Override
	public String getNewComment() {
		// TODO Auto-generated method stub
//		System.out.println("Ya cant get comments from priority events dum dum");
		return null;
	}

	@Override
	public void setComment(String newcomment) {
		// TODO Auto-generated method stub
		
	}

}
