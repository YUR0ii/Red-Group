import java.awt.Font;
import java.util.ArrayList;


public class Task {
	private String name=null;
	private String priority;
	private scheduledPriority[] scheduledPriorities = new scheduledPriority[3]; // look at the constructor for this, make 3 new Priorities in brackets
	private ArrayList<Event> eventList = new ArrayList<Event>();
	private ArrayList<String> commentList=new ArrayList<String>();
	private boolean complete;
	
	private boolean changed=false;
	private Font font;

	Task(String toBeName) {
		name = toBeName;
		priority = "urgent";
		complete = false;
		font=new Font(Font.SERIF,Font.BOLD,14);
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

	public String getComment(int index) {
		//returns a specific comment
		return commentList.get(index);
	}

	public void setComment(String comment,int index) {
		//changes a specific comment
		Event event=new commentEvent(commentList.get(index),comment);
		addEventToHistory(event);
		commentList.set(index,comment);
	}

	public void addComment(String comment) {
		//adds a comment
		commentList.add(comment);
		Event event=new commentEvent(comment,1);
		addEventToHistory(event);
	}
	
	public void removeComment(int index) {
		//removes a specific comment
		Event event=new commentEvent(commentList.get(index),0);
		addEventToHistory(event);
		commentList.remove(index);
	}
	
	public ArrayList<String> getAllComments() {
		//returns the array list of comments
		return commentList;
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