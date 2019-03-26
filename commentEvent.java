import java.util.ArrayList;

public class commentEvent extends Event{
	//i added differentiation between adding, removing, and changing a comment
	private String oldComment = null;
	private String newComment = null;
	
	commentEvent(String oldcomment, String newcomment){
		//makes an event when a comment is changed
		oldComment= oldcomment;
		newComment = newcomment;
		int x = 0;
		sentence=createSentence(x);
	}
	commentEvent(String newcomment){
		newComment = newcomment;
		sentence = createSentence();
	}
	commentEvent(String newcomment, int identifier){
		//makes an event when a comment is added or removed
		newComment = newcomment;
		sentence=createSentence(identifier);
	}
	
	@Override
	public ArrayList<String> createSentence() {
		// TODO Auto-generated method stub
		//makes the string for a change event
		firstFiller= "The commment was changed to ";
		sentence.add(firstFiller);
		sentence.add(newComment);
		sentence.add(on);
		sentence.add(Event.getDateString(date));
		return sentence;
	}
	public ArrayList<String> createSentence(int identifier) {
		// TODO Auto-generated method stub
		//makes the string for a remove event
		firstFiller= "The commment ";
		secondFiller= " was deleted"+on;
		sentence.add(firstFiller);
		sentence.add(oldComment);
		sentence.add(secondFiller);
		sentence.add(Event.getDateString(date));
		return sentence;		
	}

	public int getStringType(int index) {
		String temp=sentence.get(index);
		if(temp.equals(oldComment)) {
			return 1;
		}else if(temp.equals(newComment)) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Comment";
	}

	@Override
	public String getOldComment() {
		// TODO Auto-generated method stub
		return oldComment;
	}

	@Override
	public String getNewComment() {
		// TODO Auto-generated method stub
		return newComment;
	}

	public void editComment(HistoryPage h) {
		//creates the comment page when a comment is double clicked
		CommentPage page=new CommentPage(newComment,getDate(),h);
	}
	
	public void setComment(String newcomment) {
		newComment = newcomment;
		createSentence();
	}
}
