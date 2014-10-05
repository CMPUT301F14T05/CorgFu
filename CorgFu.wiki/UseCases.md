Use Cases
=========
####	Use Case Number: 1
	Use Case Name:   BrowseQuestions
	Participants:    AppUser
	Goal:            To browse questions
	Precondition:	 AppUser has network connection to view non-favorite questions
	Postcondition:	 On success, AppUser views questions made by Authors
	
####	Use Case Number: 2
	Use Case Name: 	 ViewQuestionAndAnswers
	Participants:	 AppUser
	Goal:			 To view a single question and its corresponding answers
	Precondition:	 AppUser has found question of interest and selected it
	Postcondition:	 AppUser can view the question and corresponding answers
	
####	Case Number: 	3
	Use Case Name: 	ViewReplies
	Participants:	AppUser
	Goal:		 	To view replies to a question or answer
	Precondition:	AppUser has selected a specific question and is currently viewing it
	Postcondition:	AppUser can view replies to selected question or answer
	
####	Case Number: 	4
	Use Case Name: 	MakeQuestion
	Participants:	Author 
	Goal:			For Author to create a question that other users can reply to 
					and answer 
	Precondition:	Author must have a login (Could allow for Anon posting?)
	Postcondition:	AppUsers can view the question and Authors can view, reply, and answer 
					the question when viewing it
	
####	Case Number: 	5
	Use Case Name: 	MakeAnswer
	Participants:	Author
	Goal:			For Author to create an answer to an existing question
	Precondition:	Author must have selected the question for viewing
	Postcondition:	AppUsers can view the answer and Authors can view, and reply to the 
					answer when viewing the question
	
####	Case Number: 	6
	Use Case Name: 	ReplyToQuestionAndAnswer
	Participants:	Author
	Goal:			For Author to reply to question or answer to clarify meaning
	Precondition:	Author must have selected question for viewing
	Postcondition:	Authors and AppUsers can view the reply to the question or answer
	
####	Case Number: 	7
	Use Case Name: 	AttachPictureQuestion
	Participants:	Author
	Goal:			To attach a picture to a question.
	Precondition:	Author must be in the process of making a question
	Postcondition:	Authors and AppUsers can view the picture attached to the question
	
####	Case Number: 	8
	Use Case Name: 	AttachPictureAnswer
	Participants:	Author
	Goal:			For Author to attach a photo to an answer
	Precondition:	Author must be in the process of making an answer
	Postcondition:	Authors and AppUsers can view the picture that is attached to the 
					the answer
	
####	Case Number: 	9
	Use Case Name: 	SmallPicturesOnly
	Participants:	SysAdmin
	Goal:			Have any picture attached to question or answer be no 
					larger than 64kb.
	Precondition:	Author must submit question or answer
	Postcondition:	The question or answer is accepted if the photo is <65kb and 
					rejected if it is >64kb
	
####	Case Number: 	10
	Use Case Name: 	SortQuestionsByPicture
	Participants:	AppUser
	Goal:			For AppUser to view Questions sorted based on whether 
					they have pictures or not
	Precondition:	AppUser must be have a network connection
	Postcondition:	AppUser views questions grouped by whether or not they have a 
					picture attached to them
	
####	Case Number: 	11
	Use Case Name: 	SortQuestionsDate
	Participants:	AppUser
	Goal:			For AppUser to view Questions based on date
	Precondition:	AppUser must have internet connection and be able to browse questions
	Postcondition:	AppUser views questions in decending order of recency
	
####	Case Number: 	12
	Use Case Name: 	UpVote
	Participants:	AppUser
	Goal:			To upvote other users questions (not thier own)
	Precondition:	AppUser must have selected the question and be currently viewing it
	Postcondition:	AppUsers and Authors can see how many upvotes the question has recieved 
					when they are viewing it
					
####	Case Number: 	13
	Use Case Name: 	ViewMostUpvotedQuestion
	Participants:	AppUser
	Goal:			To view most upvoted question when browsing questions
	Precondition:	AppUser must be currently able to browse questions (internet connection)
	Postcondition:	AppUser views questions in descending order of number of upvotes
	
####	Case Number: 	14
	Use Case Name: 	ViewMostUpvotedAnswer
	Participants:	AppUser
	Goal:			To view most upvoted answer when viewing a questions
	Precondition:	AppUser must have already selected a question of interest
	Postcondition:	AppUser views answers in descending order of number of upvotes
	
####	Case Number: 	15
	Use Case Name: 	ViewNumberOfAnswers
	Participants:	AppUser
	Goal:			To view number of answers to a question while browsing questions
	Precondition:	AppUser must be currently browsing questions (internet connection)
	Postcondition:	AppUser views number of answers to questions while browsing questions
	
####	Case Number: 	16
	Use Case Name: 	SearchQuestions
	Participants:	AppUser, Elastisearch
	Goal:			To find specific questions using keywords
	Precondition:	AppUser must have keyword(s) for question of interest and internet connection
	Postcondition:	AppUser views questions in descending relevency to the keyword returned by
					Elastisearch
					
####	Case Number: 	17
	Use Case Name: 	SearchAnswers
	Participants:	AppUser, Elastisearch
	Goal:			To find specific answers using keywords
	Precondition:	AppUser must have keyword(s) for answer of interest and internet connection
	Postcondition:	AppUser views answers in descending relevency to the keyword returned by
					Elastisearch

####	Case Number: 	18
	Use Case Name: 	RememberQuestionsAsked
	Participants:	Author
	Goal:			To have device remember which questions were asked by the Author
	Precondition:	Author must have asked a question
	Postcondition:	Author can browse and view all questions she/he has asked and any 
					corresponding answers

####	Case Number: 	19
	Use Case Name: 	ViewOffline
	Participants:	AppUser
	Goal:			To be able to view questions and corresponding answers offline
	Precondition:	AppUser must have viewed a question and any of its answers or indicated
					it should be viewable offline
	Postcondition:	AppUser can view previously viewed questions and answers or selected 
					questions and answers offline

####	Case Number: 	20
	Use Case Name: 	SetFavorites
	Participants:	AppUser
	Goal:			To indicate a specific question as a favorite and view it offline
	Precondition:	AppUser be currently viewing the desired question in order to favorite it
	Postcondition:	AppUser can browse and select favorited questions on and offline via
					ViewOffline
	
####	Case Number: 	21
	Use Case Name: 	AuthorOffline
	Participants:	Author
	Goal:			For author to write replies, questions, and answers offline
	Precondition:	Author must be viewing a question that can be viewed offline via
					ViewOffline
	Postcondition:	Author can write replies, questions, and answers to questions and 
					corresponding answers offline
	
	
####	Case Number: 	22
	Use Case Name: 	PushOfflineContent
	Participants:	Author
	Goal:			For author to push content created in use case AuthorOffline
	Precondition:	Author must be connected to the internet and already done an 
					AuthorOffline
	Postcondition:	Other AppUsers and Authors can view the questions, answers, and replies
					and interact with them accordingly
	
####	Case Number: 	23
	Use Case Name: 	ViewMostFresh
	Participants:	AppUser
	Goal:			For AppUser to view the most recent comments first when viewing
					a selected question
	Precondition:	AppUser must have selected a question for viewing
	Postcondition:	AppUser can views comments in descending order of date and time published
	
####	Case Number: 	24
	Use Case Name: 	SetUsername
	Participants:	Author
	Goal:			For author to have choice of available usernames
	Precondition:	Author must be connected to the internet
	Postcondition:	Author can create questions, answers, and replies using the specified
					username
