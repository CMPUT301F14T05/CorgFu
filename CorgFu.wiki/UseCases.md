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


####  Use Case Number: 10
	ID:					UC-10
	Title: 				SortQuestionsByPicture
	Description:		For AppUser to view Questions sorted based on whether they have pictures or not
	Primary Actor:		AppUser
	Precondition:		AppUser must be have a network connection
	Postcondition:	    AppUser views questions grouped by whether or not they have a picture attached to them
	Scenario:
						1. AppUser selects on the "sort by: tab "Questions with Pictures" option in the questions menu
						2. App displays a list of questions reflecting the question with the most pictures followed by the least. 
	Extensions:			2a. No questions are currently available
							2a1. App displays error message saying no questions are currently
                                 available, try connecting to the internet. 
	Frequency of Use: 	Likely used somewhat often by AppUsers who like to browse questions with pictures
	Status:				Testcases in production
	Responsibility:		(Need some clarification)Test cases
	Priority:			P3 - Low

####  Use Case Number: 11
	ID:					UC-11
	Title: 				SortQuestionsDate
	Description:		For AppUser to view Questions based on date
	Primary Actor:		AppUser
	Precondition:		AppUser must be currently able to browse questions (internet connection)
	Postcondition:		AppUser views questions in decending order of recency
	Main Success 
	Scenario:
						1. AppUser selects on the "sort by: tab "Most Recent Question" option in the questions menu
						2. App displays a list of questions reflecting the most recent question followed by the oldest. 
	Extensions:			2a. No questions are currently available
							2a1. App displays error message saying no questions are currently
								available, try connecting to the internet.
	Frequency of Use: 	Likely used somewhat often by AppUsers who like to browse the most recent/oldest questions
	Status:				Testcases in production
	Responsibility:		Anthony for test cases
	Priority:			P3 - Low

####	Case Number: 	12
	Use Case Name: 	UpVote
	Participants:	AppUser
	Goal:			To upvote other users questions
	Precondition:	AppUser must have selected the question and be currently viewing it
	Postcondition:	AppUsers and Authors can see how many upvotes the question has recieved 
					when they are viewing it
					
####	ID: UC-13
	Title: 				ViewMostUpvotedQA
	Description:		AppUser accesses the app and selects the option to browse questions. 
						He or she then selects the sort by upvotes option and the AppUser then
						views the questions and answers in the order of most upvotes to least 
						upvotes.
	Primary Actor:		AppUser
	Precondition:		AppUser must be currently able to browse questions (internet 
						connection)
	Postcondition:		AppUser views questions in descending order of number of upvotes
	Main Success 
	Scenario:			1. AppUser selects the upvote option from the sortby menu.
						2. System displays the list of Questions with the most upvoted at the top.
						3. AppUser can browse questions and answers in decreasing number of upvotes.
	Extensions:			2a. No questions are currently available:
							2a1. App displays error message saying no questions are currently
								available, try connecting to the internet.
	Frequency of Use: 	Likely used somewhat often by AppUsers who like to browse popular 
	                    questions
	Status:				Testcases in production
	Responsibility:		Wyatt for test cases
	Priority:			P3 - Low

####	ID: UC-14
	Title: 	            ViewNumberOfAnswers
	Description:        AppUser wants to view the number of answers to questions while they are.
						browsing questions
	Primary Actor:		AppUser	
	Precondition:		AppUser must be currently browsing questions.
	Postcondition:		AppUser views number of answers to questions while browsing questions.
	Main Success
	Scenario:			1. AppUser chooses to browse questions.
						2. System displays questions in the default order of freshest first with
						the number of answers shown by each question.
						3. The AppUser can continue as desired.
	Extensions:			2a. No Questions are available:
							2a1. System displays error message indicating no questions are
							currently available.
	Frequency of Use:	Very frequent, every time an AppUser browses questions.
	Status:				Empty testcase
	Responsibility:		Wyatt
	Priotity:			P2 - Medium
	
####	ID: UC-15
	Title: 				SearchQuestionsAndAnswers
	Description:		AppUser wants to search questions and answers using keywords to find 
						questions and answers of interest.
	Primary Actor:		AppUser, Elasticsearch
	Precondition:		AppUser must have keyword(s) for questions or answers of interest and internet 						connection.
	Postcondition:		AppUser views questions and answers returned by Elastisearch in descending 
						relevency to the keyword.
	Main Success
	Scenario:			1. AppUser accesses the system and chooses to browse questions.
						2. System displays all the questions that have been asked with the freshest
						first.
						3. AppUser chooses to search quesstions and answers using a keyword.
						4. System sends keyword to Elastisearch.
						5. Elasticsearch returns relevant results to system.
						6. System displays the relevant results to the user.
	Extensions:			2a. No Questions are available:
							2a1. System will display error message indication no questions are 
							currently available.
							2a2. User can still enter a keyword, but system will display error
							indicating that no results were found.
						5a. Elasticsearch returns no results:
							5a1. System displays error message indicating that no results were 
							found.
	Frequency of Use:	Very frequent, AppUsers will often find questions this way.
	Status:				Empty testcase
	Responsibility:		Wyatt
	Priority:			P2 - Medium
					
####	ID: UC-16
	Title: 				RememberQuestionsAsked
	Desciption:			Author wants the device to remember the questions were asked by the Author
	Primary Actor:		Author
	Precondition:		Author must be able to make a question
	Postcondition:		Author can browse and view all questions she/he has asked and any 
						corresponding answers
	Main Success
	Scenario:			1. Author makes a questions.
						2. System stores the question on the Authors device.
						3. System adds the question to the available questions so other 
						AppUsers can browse, answer, and reply to the question.
	Extenstions:		3a. System is not connected to the internet.
							3a1. System will display message that the message will be uploaded
							when the device has internet access again.
							3a2. System pushes question once it gains internet acess
	Frequency of Use:	Very frequent. Whenever an author makes a question.
	Status:				Empty testcase
	Responsibility:		Wyatt
	Priority:			P3-Low
	

####	ID: UC-17
	Title: 				ViewOffline
	Description:		AppUser wants to view questions and corresponding answers offline
	Primary Actor:		AppUser
	Precondition:		AppUser must have viewed a question and any of its answers or indicated
						it should be viewable offline
	Postcondition:		AppUser can view previously viewed questions and answers or selected 
						questions and answers offline
	Main Succuss
	Scenario:			1. AppUser selects a question for viewing from any of the available
						methods (browse, search, etc.).
						2. System displays the question with the corresponding answers and
						replies to the question.
						3. System stores the question and corresponding answers and replies
						on the AppUser's device.
						4. AppUser can view this question and its answers offline.
	Extenstions:		None so far
	Frequency of Use:	Very frequent. Used whenever an AppUser views a question.
	Status:				Empty testcase
	Responsibility:		Wyatt
	Priority:			P3-Low

####	Case Number: 	18
	Use Case Name: 	SetFavorites
	Participants:	AppUser
	Goal:			To indicate a specific question as a favorite and view it offline
	Precondition:	AppUser be currently viewing the desired question in order to favorite it
	Postcondition:	AppUser can browse and select favorited questions on and offline via
					ViewOffline
	
####	Case Number: 	19
	Use Case Name: 	AuthorOffline
	Participants:	Author
	Goal:			For author to write replies, questions, and answers offline
	Precondition:	Author must be viewing a question that can be viewed offline via
					ViewOffline
	Postcondition:	Author can write replies, questions, and answers to questions and 
					corresponding answers offline
	
	
####	Case Number: 	20
	Use Case Name: 	PushOfflineContent
	Participants:	Author
	Goal:			For author to push content created in use case AuthorOffline
	Precondition:	Author must be connected to the internet and already done an 
					AuthorOffline
	Postcondition:	Other AppUsers and Authors can view the questions, answers, and replies
					and interact with them accordingly
	
####	Case Number: 	21
	Use Case Name: 	ViewMostFresh
	Participants:	AppUser
	Goal:			For AppUser to view the most recent comments first when viewing
					a selected question
	Precondition:	AppUser must have selected a question for viewing
	Postcondition:	AppUser can views comments in descending order of date and time published
	
####	Case Number: 	22
	Use Case Name: 	SetUsername
	Participants:	Author
	Goal:			For author to have choice of available usernames
	Precondition:	Author must be connected to the internet
	Postcondition:	Author can create questions, answers, and replies using the specified
					username
