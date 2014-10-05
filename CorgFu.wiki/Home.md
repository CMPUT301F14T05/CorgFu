Welcome to the CorgFu wiki!
===========================

Use Cases:
----------
	Use Case Number: 1
	Use Case Name:   BrowseQuestions
	Participants:    AppUser
	Goal:            To browse questions
	Precondition:	 AppUser has network connection to view non-favorite questions
	Postcondition:	 On success, AppUser views questions made by Authors
	
	Use Case Number: 2
	Use Case Name: 	 ViewQuestionAndAnswers
	Participants:	 AppUser
	Goal:			 To view a single question and its corresponding answers
	Precondition:	 AppUser has found question of interest and selected it
	Postcondition:	 AppUser can view the question and corresponding answers
	
	Case Number: 	3
	Use Case Name: 	ViewReplies
	Participants:	AppUser
	Goal:		 	To view replies to a question or answer
	Precondition:	AppUser has selected a specific question and is currently viewing it
	Postcondition:	AppUser can view replies to selected question or answer
	
	Case Number: 	4
	Use Case Name: 	MakeQuestion
	Participants:	Author 
	Goal:			For Author to create a question that other users can reply to 
					and answer 
	Precondition:	Author must have a login (Could allow for Anon posting?)
	Postcondition:	AppUsers can view the question and Authors can view, reply, and answer 
					the question when viewing it
	
	Case Number: 	5
	Use Case Name: 	MakeAnswer
	Participants:	Author
	Goal:			For Author to create an answer to an existing question
	Precondition:	Author must have selected the question for viewing
	Postcondition:	AppUsers can view the answer and Authors can view, and reply to the 
					answer when viewing the question
	
	Case Number: 	6
	Use Case Name: 	ReplyToQuestionAndAnswer
	Participants:	Author
	Goal:			For Author to reply to question or answer to clarify meaning
	Precondition:	Author must have selected question for viewing
	Postcondition:	Authors and AppUsers can view the reply to the question or answer
	
	Case Number: 	7
	Use Case Name: 	AttachPictureQuestion
	Participants:	Author
	Goal:			To attach a picture to a question.
	Precondition:	Author must be in the process of making a question
	Postcondition:	Authors and AppUsers can view the picture attached to the question
	
	Case Number: 	8
	Use Case Name: 	AttachPictureAnswer
	Participants:	Author
	Goal:			For Author to attach a photo to an answer
	Precondition:	Author must be in the process of making an answer
	Postcondition:	Authors and AppUsers can view the picture that is attached to the 
					the answer
	
	Case Number: 	9
	Use Case Name: 	SmallPicturesOnly
	Participants:	SysAdmin
	Goal:			Have any picture attached to question or answer be no 
					larger than 64kb.
	Precondition:	Author must submit question or answer
	Postcondition:	The question or answer is accepted if the photo is <65kb and 
					rejected if it is >64kb
	
	Case Number: 	10
	Use Case Name: 	SortQuestionsByPicture
	Participants:	AppUser
	Goal:			For AppUser to view Questions sorted based on whether 
					they have pictures or not
	Precondition:	AppUser must be have a network connection
	Postcondition:	AppUser views questions grouped by whether or not they have a 
					picture attached to them
	
	Case Number: 	11
	Use Case Name: 	SortQuestionsDate
	Participants:	AppUser
	Goal:			For AppUser to view Questions based on date
	Precondition:	AppUser must have internet connection and be able to browse questions
	Postcondition:	AppUser views questions in decending order of recency
	
	Case Number: 	12
	Use Case Name: 	UpVote
	Participants:	AppUser
	Goal:			To upvote other users questions (not thier own)
	Precondition:	AppUser must have selected the question and be currently viewing it
	Postcondition:	AppUsers and Authors can see how many upvotes the question has recieved 
					when they are viewing it
	
	
	
