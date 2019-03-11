
public class commentEvent extends Event{
	
	String oldComment = null;
	String newComment = null;
	
	commentEvent(String oldcomment, String newcomment){
		oldComment= oldcomment;
		newComment = newcomment;
	}
	commentEvent( String newcomment){
		newComment = newcomment;
	}
	
	@Override
	public String createSentence() {
		// TODO Auto-generated method stub
		
		String day = Event.convertDay(date.getDate());
		String month = Event.convertMonth(date.getMonth());
		
		
		return "The comment was changed to "+  newComment + " on " + day + " " + (date.getMonth()+1) +"/" + date.getDate()+ "/" + (1900+date.getYear());
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

}
