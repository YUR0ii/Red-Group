import java.util.*;

public class Task {
	private String name;
	private String priority;
	private scheduledPriority[] updates=new scheduledPriority[3];//look at the constructor for this, make 3 new Priorities in brackets
	private ArrayList<Event> events=new ArrayList<Event>();
	Task(String toBeName){
		name=toBeName;
		priority="inactive";
	}
	public String getName() {
		return name;
	}
	public String getPriorityLevel() {
		return priority;
	}
	public ArrayList getEvents() {
		return events;
	}
}

