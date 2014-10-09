Use Cases
=========
####	Use Case Number: 1
	ID: 				UC-1
	Title:   			BrowseQuestions
	Description: 		The user will be able to browse questions. the veiw will consist of 
						just the question string, (likely) the number of answers, upvotes, 
						date made. exact view determined by the questionBrowse Item. The 
						browse should be sorted in a manner tested below. I will just test 
						if all the items are added.
	Primary Actor:   	AppUser
	Precondition:	 	AppUser has network connection to view non-favorite/saved questions 
						if not display nothing in list
	Postcondition:	 	On success, AppUser views questions made by Authors
	Main Success 
	Scenario: 
						1. 	The user clicks on the Questions or returns to the Question screen. 
						2. 	The list view displays all of the questions, each having their stats 
						and options
	Extensions: 		2a. displays questions but does not have correct stats(number of votes, 
							number of answerse, and if it has a photo).
								-2a1. displays or has the incorrect values for stats
								-2a2. displays 0 or null for all stats
						2b. displays no questions.
	Frequency of Use: 	likely used everytime app runs, and multiple cycles.
	Status: 			pending testcase, and implementation
	Responsibility:		Devon for test case
	Prioirty: 			P1- very Important

	
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
	Precondition:	AppUser has selected a specific question or answer and is currently viewing it
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

####  Use Case Number: 8
	ID:					UC-08
	Title: 				AttachPictureAnswer
	Description:		Author to attach a photo to an answer
	Primary Actor:		Author
	Precondition:		Author must be in the process of making an answer
	Postcondition:		Authors and AppUsers can view the picture that is attached to the 
  			          	the answer
    Main Sucess
	Scenario:
						1. AppUser selects the "yes" option when prompted "Do you want to add picture".
						2. App displays a list of image options either from the gallery/camera.
						3. AppUser adds a picture <65kB. 
	Extensions:			2a. Image directory is empty; AppUser selects a void picture 
							2a1. Picture added exceeds the speficifcations; >64kB. 
	Frequency of Use: 	Likely used somewhat often by AppUsers who add pictures to their answers.
	Status:				Testcases in production
	Responsibility:		Anthony for Test cases
	Priority:			P3 - Low


####	Use Case Number: 9
	ID:					UC-09
	Title: 				SmallPicturesOnly
	Description:		Have any picture attached to question or answer be no larger than 64kb.
	Primary Actor:		SysAdmin
	Precondition:		Author must submit question or answer
	Postcondition:		The question or answer is accepted if the photo is <65kb and rejected if it is >64kb
	Main Success 
	Scenario:
						1. AppUser selects the "yes" option when prompted "Do you want to add picture".
						2. App displays a list of image options either from the gallery/camera.
						3. AppUser adds a picture <65kB. 
	Extensions:			2a. Image directory is empty; AppUser selects a void picture option
							2a1. Picture added is >64kB. 
	Frequency of Use: 	Likely used somewhat often by AppUsers who add pictures to their answers.
	Status:				Testcases in production
	Responsibility:		Anthony for Test cases
	Priority:			P3 - Low

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
	Goal:			To upvote other users questions
	Precondition:	AppUser must have selected the question and be currently viewing it
	Postcondition:	AppUsers and Authors can see how many upvotes the question has recieved 
					when they are viewing it
					
####	Use Case Number: 13
	ID:					UC-13
	Title: 				ViewMostUpvotedQuestion
	Description:		AppUser accesses the app and selects the option to view questions. 
						He or she then clicks on the sortby dropdown and 
						selects the sort by upvotes option. The user then views the questions 
						in the order of most upvotes to least upvotes.
	Primary Actor:		AppUser
	Precondition:		AppUser must be currently able to browse questions (internet 
						connection)
	Postcondition:		AppUser views questions in descending order of number of upvotes
	Main Success 
	Scenario:
						1. AppUser selects the upvote option from the sortby menu.
						2. App displays the list of Questions with the most upvoted at the top.
						3. AppUser can browse questions in decreasing number of upvotes.
	Extensions:			2a. No questions are currently available
							2a1. App displays error message saying no questions are currently
								available, try connecting to the internet.
	Frequency of Use: 	Likely used somewhat often by AppUsers who like to browse popular 
						questions
	Status:				Testcases in production
	Responsibility:		Wyatt for test cases
	Priority:			P3 - Low
	
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
	Use Case Name: 	SearchQuestionsAndAnswers
	Participants:	AppUser, Elastisearch
	Goal:			To find specific questions or answers using keywords
	Precondition:	AppUser must have keyword(s) for question or answers of interest and internet connection
	Postcondition:	AppUser views questions and answers in descending relevency to the keyword returned by
					Elastisearch
					
####	Case Number: 	17
	Use Case Name: 	RememberQuestionsAsked
	Participants:	Author
	Goal:			To have device remember which questions were asked by the Author
	Precondition:	Author must have asked a question
	Postcondition:	Author can browse and view all questions she/he has asked and any 
					corresponding answers

####	Case Number: 	18
	Use Case Name: 	ViewOffline
	Participants:	AppUser
	Goal:			To be able to view questions and corresponding answers offline
	Precondition:	AppUser must have viewed a question and any of its answers or indicated
					it should be viewable offline
	Postcondition:	AppUser can view previously viewed questions and answers or selected 
					questions and answers offline

####	Case Number: 	19
	Use Case Name: 	SetFavorites
	Participants:	AppUser
	Goal:			To indicate a specific question as a favorite and view it offline
	Precondition:	AppUser be currently viewing the desired question in order to favorite it
	Postcondition:	AppUser can browse and select favorited questions on and offline via
					ViewOffline
	
####	Case Number: 	20
	Use Case Name: 	AuthorOffline
	Participants:	Author
	Goal:			For author to write replies, questions, and answers offline
	Precondition:	Author must be viewing a question that can be viewed offline via
					ViewOffline
	Postcondition:	Author can write replies, questions, and answers to questions and 
					corresponding answers offline
	
	
####	Case Number: 	21
	Use Case Name: 	PushOfflineContent
	Participants:	Author
	Goal:			For author to push content created in use case AuthorOffline
	Precondition:	Author must be connected to the internet and already done an 
					AuthorOffline
	Postcondition:	Other AppUsers and Authors can view the questions, answers, and replies
					and interact with them accordingly
	
####	Case Number: 	22
	Use Case Name: 	ViewMostFresh
	Participants:	AppUser
	Goal:			For AppUser to view the most recent comments first when viewing
					a selected question
	Precondition:	AppUser must have selected a question for viewing
	Postcondition:	AppUser can views comments in descending order of date and time published
	
####	Case Number: 	23
	Use Case Name: 	SetUsername
	Participants:	Author
	Goal:			For author to have choice of available usernames
	Precondition:	Author must be connected to the internet
	Postcondition:	Author can create questions, answers, and replies using the specified
					username
