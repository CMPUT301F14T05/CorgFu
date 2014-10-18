package ca.ualberta.cs.corgFu;

import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;
import android.graphics.Bitmap;

public class Picture {
	
	// via an intent in VIEW 
	// attachImage method modifies Model attributes "hasPic" 
	// hasPic = True means Image successfully attached; vice versa if otherwise
	public void attachImageQuest(Question Quest, Bitmap Image){
	  /* 1. fetches picture from camera directory 
			
		 2. fetches picture from another directory
		
		 3. prescribe a default image if no images are supplied/option aborted */
		
		// Finally, attach Image meets requirements 
		if (smallPicture(Image)){
			Quest.setImage(Image);
		}
	}

	public void attachImageAns(Answer Ans, Bitmap Image){
		  /* 1. fetches picture from camera directory 
		
			 2. fetches picture from another directory
			
			 3. prescribe a default image if no images are supplied/option aborted */
			
			// Finally, attach Image meets requirements 
			if (smallPicture(Image)){
				Ans.setImage(Image);
			}
		}
	
	public Boolean smallPicture(Bitmap Image){
		// attached picture meets requirements
		if (byteSizeOf(Image) < 65){
			return true;
		}
		// attached picture fails requirements 
		return false;
	}
	
	public int byteSizeOf(Bitmap bitmap) {
		// default 
		return 0;
	}
}