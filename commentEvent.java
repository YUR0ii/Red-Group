
public class commentEvent extends Event{
	//i added differentiation between adding, removing, and changing a comment
	private String oldComment = null;
	private String newComment = null;
	private final int add=1;
	
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
		String day = Event.convertDay(date.getDate());
		String month = Event.convertMonth(date.getMonth());
		
		return "The comment was changed to "+  newComment + " on " + day + " " + (date.getMonth()+1) +"/" + date.getDate()+ "/" + (1900+date.getYear());
	}
	public String createSentence(int identifier) {
		// TODO Auto-generated method stub
		//makes the string for an add or remove event
		String day = Event.convertDay(date.getDate());
		String month = Event.convertMonth(date.getMonth());
		
		if(identifier==add) {
			return "The comment "+newComment+" was added on " + day + " " + (date.getMonth()+1) +"/" + date.getDate()+ "/" + (1900+date.getYear());
		}else{
			return "The comment "+newComment+" was deleted on " + day + " " + (date.getMonth()+1) +"/" + date.getDate()+ "/" + (1900+date.getYear());
		}
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
