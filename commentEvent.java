
public class commentEvent extends Event{
	//i added differentiation between adding, removing, and changing a comment
	private String oldComment = null;
	private String newComment = null;
	
	commentEvent(String oldcomment, String newcomment){
		//makes an event when a comment is changed
		oldComment= oldcomment;
		newComment = newcomment;
		sentence=createSentence();
	}
	commentEvent(String newcomment, int identifier){
		//makes an event when a comment is added or removed
		newComment = newcomment;
		sentence=createSentence(identifier);
	}
	
	@Override
	public String createSentence() {
		// TODO Auto-generated method stub
		//makes the string for a change event
		
		return "The comment " + oldComment + "was changed to "+  newComment + " on " + Event.getDateString(date);
	}
	public String createSentence(int identifier) {
		// TODO Auto-generated method stub
		//makes the string for a remove event
		
		return "The comment "+newComment+" was deleted on " + Event.getDateString(date);		
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

	public void editComment() {
		//creates the comment page when a comment is double clicked
		CommentPage page=new CommentPage(newComment);
	}
}
