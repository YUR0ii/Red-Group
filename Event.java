import java.util.*;


public abstract class Event {
	
	private Date date;
	private String sentence;
	
	Event(){
		//Should never be called here
		System.out.println("You called the event superclass constructor");
		createSentence();
	}
	
	//Creates the sentence to be stores until retrieved
	//priority and comment events use given priorities and comments
	//respectively to create these sentences
	public abstract String createSentence();
	
	//another way to get the sentence
	public String getSentence() {
		return sentence;
	}
	
	public Date getDate() {
		return date;
	}
	
	//Returns the type of event it is
	//Either the String "Comment" or "Priority"
	//Corresponding to the type of event of course
	public abstract String getType();
	
	
	//Will return the new and old comment if
	//it contains a commentEvent. If not, then returns null.
	//please check what type of event it is using getType
	//before calling either of these two methods
	public abstract String getOldComment();
	public abstract String getNewComment();
	
}
