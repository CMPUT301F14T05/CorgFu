##2014/11/26
####Comments
	- We need to document some refactoring. Use J Deoderant and write why or why
	didn't refactor the deadlines
	- Should run our code on the lab machines to
	- Work on the UI
####Devon & Ahmed
	- Authored offline is mostly done. Every activity now has to set the context.
	working for the Main Activity. works based on booleans for checking if it has
	been added only
####Alex O. & Anthony
	- Attach picture to question is done. Did some refactoring with the answer
	controller and used 
	- Working on answering a question and adding it to the view and making it 
	
####Wyatt & Alex M.
	- Alex worked on the GeoLocation and completed it and now it shows 
	- Needs to reflect changes in the UML
	- 
##2014/11/19
####Comments
	- Presentation is next week. We will be trying to sell the app to our audience and convince them to use our app instead of the competitor's.
####Devon & Ahmed
	- Did: Saving, cache, and favourites have been implemented and the controllers 
	that weren't needed have been deleted.
	- Do: Working on pushing offline content (UC-20) and updating of testcases &
	 UML
####Alex O. & Anthony	
	- Add picture is working now using an underlying blank activity.
	- Answers appear, but aren't consistent at the moment,
	- Will be working on some picture bugs and finishing adding answers.
####Alex M. &  Wyatt
	- Finished implementing Elastic search. Retrieving questions and updating them
	seems to be working at the moment.
	- May be bug with exiting a view before elastic search has finished, causing
	the question not to be updated. Will look into that this week.
	- Alex has started and will be working on the new Use Case of Geolocation.
	- Wyatt will most likely be testing and refactoring where possible.
##2014/11/12
####Comments
    - Add link to Wiki to the UML
    - No links between the views and controllers
    - Working consistingly during all weeks
    - Use milestones in the issue tracker!
####Anthony & Alex O.
    - Working on pictures
    - Still a problem with going from the external photo app back to the activity
    - Answers are done, just working on displaying the answer in the view question and answer.
    - Finish answers this week and the connected stuff (upvote, add picture, and reply)
####Devon & Ahmed
    - Had to restart the saving stuff.
    - Hoping to have saving done by next week
####Wyatt & Alex M.
    - I forgot to add our notes and now I don't remember. Worked on elastic search
    I believe
##2014/11/05
####Comments
	- Seems to be about 50% complete. 
	- Make sure you do JavaDoc for your code!!!
####Anthony & Alex O.
	- Worked on attach picture. Still working on attach picture
	- Blockers: Issues with testing on emulator because it does not have all the
	gallery options. Not able to catch the right path for the picture right now.
	Using 
####Devon & Ahmed
	- Got sorting done, fixed user profile bugs, and working on favorite stuff.
	- finished test cases
	- Blocker: Favorites are tough
	- Finish release planning
####Wyatt & Alex M.
	- Finished making the make question using the singleton so that questions 
	are persistent across the app
	- UI test cases for asking a question
	- Made the UI for browsing questions and has been tested
	- Made UI for opening a question. Currently upvotes, and question text can
	be viewed

##2014/10/29
#####To Do
	
####Anthony
	- Worked on the attach picture and made a mock view to test methods and such
	- Will be finishing the implementations this week with Alex O.
	- No blockers
####Devon & Ahmed
	- Implemented the username page. 
	- Keep going through the user profiles use cases.
	- Git hub issues, and android experience.
####Alex M.
	- Worked on adding questions and the UI and they will show up in the browse 
	questions view
	- Will continue working on question use cases
####Alex O.
	- Same as Anthony. Finished use case with small pictures only and upvotes
	- Same as Anthony. Finish the make answer use case and view replies as well
	- Not much blocking, waited on Wyatt and Alex to finish the questions part.
####Wyatt
	- Worked on implementing the Browse Questions use case and made the view for 
	this use case
	- Will continue working on implementing the question model
	- No blockers
	
##2014/10/22
####Part2 Comments
	- General everything was ok
	- what happens if when you make a question, you lose connection?
		- make an extension to for the make question use case
	- hard to differentiate between buttons and text views in the storyboard
	- MVC - there were some problems with one of the controllers
	- Release plan was good
	- Glossary - needs to mention all of the terms in the application and all
	related services
	- Keep updating the UML
####Alex O.
	- Finished the UML and added some things to the glossary (links and external
	sources). Links in the readme
	- Will be working with Anthony on the answer group to begin implementing 
	use cases.
####Alex M.
	- Offered help and helped divide use case with Wyatt
	- will be working on questions this week
####Ahmed
	- Added the title to the release plan.
	- Partnered with Devon and will be working on the User Profile for next week.
	- Midterms were blocking him
####Devon
	- Finished up use cases 
	- will be working on implementing the setting of username
####Anthony
	- Finished working on the picture related test cases and used bogopic gen to 
	have fake pics for the test cases
	- This week he will be working on Answers with Alex O.
####Wyatt
	- Finished last test case for UC-22 and worked with Alex M to split the
	implementation of use cases
	- Working on getting the make question use case operational for next weeks 
	meeting


##2014/10/15
####ToDo
	- update the readme to be prettier
	- take out garbage files from the root file.
	- only upload things that Diego can open (.pdf, etc.)
	- update the status of the UseCase
####Alex O.
	Did 		- Working on the UML
	Doing		- Making the UML more MVC 
	Blocking	- Not sure about how to show MVC in the UML diagram.
####Alex M.
	Did 		- Tweaked the storyboard
	Doing		- Working on implementing use cases
	Blocking	- Nothing
####Ahmed
	Did 		- Release planning. 5 page document.
	Doing		- Update the title of the app. Help with anything that we are stuck on.
				release planning and implementing use cases next week.
	Blocking	- Midterms 
####Devon
	Did 		- Finished used cases and implemented most of the testcases. One
				currently passes, but that will be changed.
	Doing		- Finishing the last testcase and editing the passing test.
				Test empty question.
	Blocking	- Everyone working at once and some uncertainty with decisions.
				Also many midterms this week.
####Wyatt	
	Did 		- Finished the use cases and related test cases.
	Doing		- Working on implementing the use cases 
	Blocking	- Midterms and other assignments
##2014/10/8
####Alex O.
	Did 		- Added the main classes that were obvious to the UML. 
	Doing 		- Organizing classes to be more MVCish.
	Blocking 	- Lack of info on specific classes and how they are implemented.
####Alex M.
	Did 		- Finished storyboard based on the user stories.
	Doing		- Tweaking the storyboard for anything new that comes up.
	Blocking	- Was uncertain about user login stuff.
	
####Ahmed
	Did 		- Reviewed requirements for release planning and mapped out MVC 
				design to initiate testing.
	Doing		- The rest of the release planning.
	Blocking	- Not having a full understanding of what needs to be done first.
####Devon
	Did 		- Made first testcase for #UC-01 and helped map out MVC design.
	Doing		- Six more testcases for the rest of his use cases UC-02 - UC-06
				and UC-12.
	Blocking	- Not sure what to add because the UML is not complete.
####Wyatt
	Did 		- Created all the empty test cases for the use cases and helped map
				out MVC design.
	Doing		- UC-13 - UC-22 test cases and updating the use cases to new format.
	Blocking	- Unsure of how our code is going to look, so deciding on test cases
				is tough.
