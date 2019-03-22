import java.io.Serializable;
import java.util.*;


public class Task implements Serializable{
	private String name=null;
	private String priority;
	private scheduledPriority[] scheduledPriorities = new scheduledPriority[3];
	private ArrayList<Event> eventList = new ArrayList<Event>();
	private String comment="new comment";
	private boolean complete;

	Task(String toBeName) {
		name = toBeName;
		priority = "urgent";
		complete = false;
		scheduledPriority urgent=new scheduledPriority("urgent");
		scheduledPriority current=new scheduledPriority("current");
		scheduledPriority eventual=new scheduledPriority("eventual");
		scheduledPriorities[0]=urgent;
		scheduledPriorities[1]=current;
		scheduledPriorities[2]=eventual;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPriorityLevel() {
		//returns priority level
		return priority;
	}

	public void setPriorityLevel(String priority) {
		//changes the priority level
		addEventToHistory(new priorityEvent(this.priority,priority));
		this.priority = priority;
	}

	public scheduledPriority[] getScheduledPriorities() {
		return scheduledPriorities;
	}

	public scheduledPriority getScheduledPriority(int level) {
		return scheduledPriorities[level];
	}
	
	public void updateScheduledPriorities(scheduledPriority[] scheduledPriorities) {
		this.scheduledPriorities = scheduledPriorities;
	}

	public void updatePriorityDate(Date date, int level) {
		scheduledPriorities[level].setDate(date);
	}
	
	public String getDateString() {
		String ds=Event.getDateString(Calendar.getInstance().getTime());
		Loop: for(scheduledPriority p: scheduledPriorities) {
			if(p.getActive()) {
				ds=Event.getDateString(p.getDate());
				break Loop;
			}
		}
		return ds;
	}
	
	public ArrayList<Event> getEvents() {
		//returns the event list of the task
		return eventList;
	}

	public void addEventToHistory(Event event) {
		//adds an event to the event list
		eventList.add(event);
	}

	public String getComment() {
		//returns a specific comment
		return comment;
	}

	public void setComment(String newComment) {
		//changes a specific comment
		Event event=new commentEvent(comment,newComment);
		addEventToHistory(event);
		comment=newComment;
	}
	
	public void removeComment(int index) {
		//removes a specific comment
		Event event=new commentEvent(comment,0);
		addEventToHistory(event);
		comment="new comment";
	}
	
	public void delete() {
		// deletes the task
	}
	
	public void edit() {
		//opens the edit action page
		EditAction edit=new EditAction(this);
		edit.createAndShowGUI();
	}
	
	public boolean getComplete() {
		//returns the completion status of a task
		return complete;
	}
	
	public void setComplete(boolean newComplete) {
		complete=newComplete;
	}
}