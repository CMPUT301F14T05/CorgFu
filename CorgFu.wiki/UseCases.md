Use Cases
=========

####	ID:	UC-01
	Title:   			BrowseQuestions
	Description: 		The user will be able to browse questions. the veiw will consist of 
						just the question string, (likely) the number of answers, upvotes, 
						date made. exact view determined by the questionBrowse Item. The 
						browse should be sorted by date, relavancy, and pictures . I will just test 
						if all the items are added. and once that passes move on to testing if the 
            			are sorted properly.
	Primary Actor:   	AppUser
	Precondition:	 	AppUser has network connection to view non-favorite/saved questions 
						if not display nothing in list
	Postcondition:	 	On success, AppUser views questions made by Authors
	Main Success 
	Scenario: 
						1. 	The user clicks on the Questions or returns to the Question screen. 
						2. 	The list view displays all of the questions, each having their stats 
						and options
	Extensions: 		
            			2a.0 There is no question to display.
            			2a.1 Prompotes user to ask a question
  	Frequency of Use: 	likely used everytime app runs, and multiple cycles.
	Status: 			pending implementation, test case complete
	Responsibility:		Devon for test case
	Prioirty: 			P1-High

####	ID: UC-02
	Title:			  	ViewQuestionAndAnswers
	Description: 	  	The user has selected a question. The screen will display the question at
        		      	the top with a list of ansers below. The veiw will be a expanding list view.
		        		the drop down will show replies and pictures. Votes should also be displayed.
              
	Primary Actor:	 	AppUser
	Precondition:	 	AppUser has found question of interest and selected it
	Postcondition:	 	AppUser can view the question and corresponding answers. note- should be
			            added to offline list but this is tested below.
	MainSuccess
	Scenario: 
			            1. The user clicks a question.
			            2. Information about that question is displayed. 
            			3. user clicks on an answer.
          				4. answer information is displayed.
    Extensions:
						3a.1 no answers exsists for the question
  
	Frequency of Use: 	very often, close to every use.
	Status: 			pending implementation
	Responsibility:		pending implementation, test case complete
	Prioirty: 			P1-High

####	ID: UC-03
	Title: 				ViewReplies
	Primary Actor:		AppUser
	Description:		AppUser users selects a question or answer he wishes to view the repies for
	Precondition:		AppUser has selected a specific question or answer and is currently viewing it
	Postcondition:		AppUser can view replies to selected question or answer
	MainSuccess
	Scenario:
						1. user selects a question or answer view reply drop down
						2. shows replies
	
	Frequency of Use:	somewhat often
	Status: 			pending testcase, and implementation
	Responsibility:		pending implementation, test case complete
	Priority: 			P3-Low 		
####	ID:	UC-04
	Title: 				MakeQuestion
	Primary Actor:		Author 
	Description:		The user enters a question they wish to ask
	Precondition:		Author must have a login (Could allow for Anon posting?)
	Postcondition:		AppUsers can view the question and Authors can view, reply, and answer 
						the question when viewing it
	
	MainSuccess
	Scenario: 
						1. The user enter their question 
						2. selects to not to  add photo not(if no stop)
	Extenions: 
	Frequency of Use: 	somewhat often
	Status: 			pending implementation, test case complete
	Responsibility: 	Devon 
	Priority:			P1-High
####	ID: UC-05
	Title: 				MakeAnswer
	Primary Actor:		Author
	Description:		For Author to create an answer to an existing question
	Precondition:		Author must have selected the question for viewing
	Postcondition:		AppUsers can view the answer and Authors can view, and reply to the 
						answer when viewing the question
	MainSuccess
	Scenario: 
						1. The user enter their answer 
						2. selects to not to  add photo not(if no stop)
	Extenions:
	Frequency of Use: 	somewhat often
	Status: 			pending implementation, test case complete
	Responsibility: 	Devon 
	Priority:			P1-High
####	ID: UC-06
	Title: 				ReplyToQuestionAndAnswer
	Primary Actor:		Author/app user
	Description:		The app user selects a question or answer view reply. Than enters
						thier reply.
	Precondition:		App user must have selected question for viewing
	Postcondition:		Authors and AppUsers can view the reply to the question or answer
	MainSuccess
	Scenario:
						1. user selects a question/answer view reply button
						2. user submits a reply
						3. their reply appears at the top as the most recent
	Frequency of Use: 	infrequent
	Status: 			pending implementation, test case complete
	Resposibility:		Devon 
	Priority: 			P3-Low
####	ID: UC-07
	Title: 				AttachPictureQuestion
	Primary Actor:		Author
	Description:		Author selects to attach a picture to Question.
	Precondition:		Author must be in the process of making a Question.
	Postcondition:		Authors and AppUsers can view the picture attached to the Question
	MainSuccess
	Scenario:
						1. User selects to attach a picture
						2. Displays choice of taking a photo or selecting from gallery
						3. AppUser adds a photo <=64kB
	Extensions:			2a. Image directory is empty; AppUser selects a void picture
							2a1. Picture is larger than 64Kb.
	Frequency of Use: 	Somewhat often
	Status: 			Testcase completed.
    Responsibility:     Anthony
	Priority: 			p2-Medium
####	ID:	UC-08
	Title: 				AttachPictureAnswer
	Description:		Author to attach a photo to an answer
	Primary Actor:		Author
	Precondition:		Author must be in the process of making an answer
	Postcondition:		Authors and AppUsers can view the picture that is attached to the 
  			          	the answer
    Main Sucess
	Scenario:
						1. User selects to attach a picture for Question.
						2. App displays a list of image options either from the gallery/camera.
						3. AppUser adds a picture <65kB. 
	Extensions:			2a. Image directory is empty; AppUser selects a void picture 
							2a1. Picture added exceeds the speficifcations; >64kB. 
	Frequency of Use: 	Likely used somewhat often by AppUsers who add pictures to their answers.
	Status:				Testcases completed.
	Responsibility:		Anthony for Test cases
	Priority:			P3-Low

####	ID:	UC-09
	Title: 				SmallPicturesOnly
	Description:		Have any picture attached to question or answer be no larger than 64kb.
	Primary Actor:		SysAdmin
	Precondition:		Author must submit question or answer
	Postcondition:		The question or answer is accepted if the photo is <65kb and rejected if it is >64kb
	Main Success 
	Scenario:
						1. User selects to add a picture for Question/Answer.
						2. App displays a list of image options either from the gallery/camera.
						3. User adds a picture <65kB. 
	Extensions:			2a. Image directory is empty; AppUser selects a void picture option
							2a1. Picture added is >64kB. 
	Frequency of Use: 	Likely used somewhat often by AppUsers who add pictures to their answers.
	Status:				Testcases completed.
	Responsibility:		Anthony for Test cases
	Priority:			P2 - Medium


####	ID:	UC-10
	Title: 				SortQuestionsByPicture
	Description:		For AppUser to view Questions sorted based on whether they have pictures or not
	Primary Actor:		AppUser
	Precondition:		AppUser must be have a network connection
	Postcondition:	    AppUser views questions grouped by whether or not they have a picture attached to them
	Scenario:
						1. User selects to sort Questions based on Pictures. 
						2. App displays a list of questions sorted by Questions with Pictures followed by Date. 
	Extensions:			2a. No questions are currently available
							2a1. App displays error message saying no questions are currently
                                 available, try connecting to the internet. 
	Frequency of Use: 	Likely used somewhat often by AppUsers who like to browse questions with pictures.
	Status:				Testcases in production
	Responsibility:		Anthony
	Priority:			P2 - Medium

####	ID:	UC-11
	Title: 				SortQuestionsDate
	Description:		For AppUser to view Questions based on date
	Primary Actor:		AppUser
	Precondition:		AppUser must be currently able to browse questions (internet connection).
	Postcondition:		AppUser views questions in decending order of recency.
	Main Success 
	Scenario:
						1. AppUser selects to sort Questions by Pictures option
						2. App displays a list of questions reflecting the most recent question followed by the oldest. 
	Extensions:			2a. No questions are currently available
							2a1. App displays error message saying no questions are currently
								available, try connecting to the internet.
	Frequency of Use: 	Likely used somewhat often by AppUsers who like to browse the most recent/oldest questions.
	Status:				Testcases completed
	Responsibility:		Anthony for test cases
	Priority:			P3 - Low

####	ID:	UC-12
	Title:		 		UpVote
	Primary Actor:		AppUser
	Description:		User indicates that they wish to up vote a question or answer. Can vote more than once 
	Precondition:		AppUser must have selected the question and be currently viewing it
	Postcondition:		Question or answers upvote count has up dated after being clicked.
	MainSuccess
	Scenario:
						1. User Selects a question to view
						2. displays the question
						3. User selects to upvote a question or answer
						4. upvote count displays new upvote amount
	Frequency of Use:	likely used often by most users
	Status:				pending implementation, test case complete
	Responsibility: 	Devon
	Priority: 			P2-Medium
					
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
	Status:				Testcase complete
	Responsibility:		Wyatt for test cases
	Priority:			P3-Low

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
	Status:				Testcase complete
	Responsibility:		Wyatt
	Priotity:			P2-Medium
	
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
	Status:				Testcase complete
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
	Extensions:		3a. System is not connected to the internet.
							3a1. System will display message that the message will be uploaded
							when the device has internet access again.
							3a2. System pushes question once it gains internet acess
	Frequency of Use:	Very frequent. Whenever an author makes a question.
	Status:				Testcase complete
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
	Extensions:			None so far
	Frequency of Use:	Very frequent. Used whenever an AppUser views a question.
	Status:				Testcase complete
	Responsibility:		Wyatt
	Priority:			P3-Low

####	ID: UC-18
	Title: 				SetFavorites
	Description:		AppUser wants to indicate a specific question as a favorite and view it 						offline.
	Primary Actor:		AppUser		
	Precondition:		AppUser be currently viewing the desired question in order to favorite it
	Postcondition:		AppUser can browse and select favorited questions on and offline via
						ViewOffline
	Main Success
	Scenario:			1. AppUser selects a question for viewing from any of the available
						methods.
						2. System displays the question along with its corresponding answers and
						replies to the questions and answers.
						3. AppUser adds the question to their favorites.
						4. System adds the question to the AppUser's favorites and makes it
						available offline.
	Frequency of Use:	Infrequent to medium frequency depending on the AppUser
	Status:				Testcase complete
	Responsibility:		Wyatt
	Priority:			P3-Low
	
####	ID: UC-19
	Title: 				AuthorOffline
	Description:		Author wants to write replies, questions, and answers offline
	Primary Actor:		Author
	Precondition:		Author must be viewing a question that can be viewed offline via
						ViewOffline
	Postcondition:		Author can write replies, questions, and answers to questions and 
						corresponding answers offline
	Main Success
	Scenario:			1. Author starts the system without internet connection and enters
						username and question to be asked.
						2. System notifies the Author that the question will not be posted until
						internet is available.
						3. System stores the question to be posted when internet is available
	Extensions:			1a. Author is viewing a question available offline
							1a1. Author answers the question or replies to the question or answer
							1a2. System notifies the Author that the answer or reply will not be 
							posted until internet is available.
							1a3. System stores answer or reply and posts it when internet is
							available.
	Frequency of Use:	Medium frequency
	Status:				Testcase complete
	Responsibility:		Wyatt
	Priority:			P3-Low
							
	
	
####	ID: UC-20
	Title: 				PushOfflineContent
	Description:		Author wants to push content created in use case AuthorOffline
	Primary Actor:		Author	
	Precondition:		Author must be connected to the internet and already done an 
						AuthorOffline
	Postcondition:		Other AppUsers and Authors can view the questions, answers, and replies
						and interact with them accordingly
	Main Success
	Scenario:			1. Author has already authored a question and gone throught the steps in 
						UC-19.
						2. Author starts the system with an internet connection
						3. System pushes offline content so it can be viewed by other AppUsers
						and Authors.
	Frequency of Use:	Medium frequency
	Status:				Testcase complete
	Responsibility:		Wyatt
	Priority:			P3-Low
	Extends:			UC-19
	
####	ID: UC-21
	Title: 				ViewMostFresh
	Description:		AppUser wants to view the most recent comment first when viewing
						a selected question
	Primary Actor:		AppUser
	Precondition:		AppUser must have selected a question for viewing
	Postcondition:		AppUser can view comments in descending order of date and time published
	Main Success
	Scenario:			1. AppUser selects a question through any available method.
						2. System displays the question and corresponding answers with the most
						recent answers visable to the AppUser
						3. AppUser chooses to view replies to a question or answer
						4. System displays replies with the most recent replies visable to the 
						AppUser
	Frequency of Use:	High frequency
	Status:				Testcase complete
	Responsibility:		Wyatt
	Priority:			P2-Medium 
	
####	ID: UC-22
	Title: 				SetUsername
	Description:		For author to have choice of a username
	Primary Actor:		Author
	Precondition:		Author must be able to open the system
	Postcondition:		Author can create questions, answers, and replies using the specified
						username
	Main Success
	Scenario:			1. Author starts the system.
						2. System starts.
						3. Author enters username and confirms the username.
						4. System uses the username to show other users who created the question,
						answer, or reply
	Frequency of Use: 	High frequency
	Status:				Testcase complete
	Responsibility:		Wyatt
	Priority:			P1-High
	
