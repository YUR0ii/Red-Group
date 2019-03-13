import java.awt.Font;
import java.util.ArrayList;
//reee cant push

public class Task {
	private String name=null;
	private String priority;
	private scheduledPriority[] scheduledPriorities = new scheduledPriority[3]; // look at the constructor for this, make 3 new Priorities in brackets
	private ArrayList<Event> eventList = new ArrayList<Event>();
	private String comment = "";
	private boolean complete;
	private boolean changed=false;
	private Font font;

	Task(String toBeName) {
		name = toBeName;
		priority = "inactive";
		complete = false;
		font=new Font(Font.SERIF,Font.ITALIC,14);
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

	public void updateScheduledPriorities(scheduledPriority[] scheduledPriorities) {
		this.scheduledPriorities = scheduledPriorities;
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

	public void addComment(String comment) {
		//adds a comment
		this.comment = comment;
		Event event=new commentEvent(comment,1);
		addEventToHistory(event);
	}
	
	public void removeComment(int index) {
		//removes the comment
		Event event=new commentEvent(comment,0); // I don't know how this works. Might need fixing. -xiaodeng
		addEventToHistory(event);
		comment = "";
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

	public void setComplete(boolean complete) {
		//sets the completion status of an event
		this.complete = complete;
	}
	
	public void setChanged(boolean bool) {
		changed=bool;
	}
}