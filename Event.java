import java.util.*;


public abstract class Event {
	
	protected Date date;
	protected String sentence;

	Event() {
		// Should never be called here
		System.out.println("You called the event superclass constructor");
		createSentence();
	}

	// Creates the sentence to be stores until retrieved
	// priority and comment events use given priorities and comments
	// respectively to create these sentences
	public abstract String createSentence();

	// another way to get the sentence
	public String getSentence() {
		return sentence;
	}

	public Date getDate() {
		return date;
	}

	// Returns the type of event it is
	// Either the String "Comment" or "Priority"
	// Corresponding to the type of event of course
	public abstract String getType();

	// Will return the new and old comment if
	// it contains a commentEvent. If not, then returns null.
	// please check what type of event it is using getType
	// before calling either of these two methods
	public abstract String getOldComment();
	public abstract String getNewComment();
	
	protected static String convertMonth(int num) {
		String month;
		if(num == 0) {
			month = "January";
		}
		else if(num ==1) {
			month = "February";
		}
		else if(num ==2) {
			month = "March";
		}
		else if(num ==3) {
			month = "April";
		}
		else if(num ==4) {
			month = "May";
		}
		else if(num ==5) {
			month = "June";
		}
		else if(num ==6) {
			month = "July";
		}
		else if(num ==7) {
			month = "August";
		}
		else if(num ==8) {
			month = "September";
		}
		else if(num ==9) {
			month = "October";
		}
		else if(num ==10) {
			month = "November";
		}
		else{
			month = "December";
		}
		return month;
	}
	
	protected static String convertDay(int num) {
		String day;
		if(num == 0) {
			day = "Sunday";
		}
		else if(num ==1) {
			day = "Monday";
		}
		else if(num ==2) {
			day = "Tuesday";
		}
		else if(num ==3) {
			day = "Wednesday";
		}
		else if(num ==4) {
			day = "Thursday";
		}
		else if(num ==5) {
			day = "Friday";
		}
		else{
			day = "Saturday";
		}
		return day;
	}
	

}
