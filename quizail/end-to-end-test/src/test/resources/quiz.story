Narrative: US001 Quiz Management

Scenario: US001 Quiz Management - Add Quiz
Given persisted quiz with title: Quiz1 and description: description1 and date: 11/10/2010 and time: 30
When quiz is added
Then there should be quiz with title: Quiz1 and description: description1 and date: 11/10/2010 and time: 30 in the system
Then teardown - remove added quiz

Scenario: US001 Quiz Management - Delete Quiz
Given persisted quiz with title: Quiz2 and description: description2 and date: 12/10/2010 and time: 20
When this quiz is deleted
Then this quiz should be removed from system

Scenario: US001 Quiz Managementt - Find All Quizzes
Given persisted quiz with title: Quiz3 and description: description3 and date: 13/10/2010 and time: 30
Given persisted quiz with title: Quiz4 and description: description4 and date: 14/10/2010 and time: 40
When find all quizzes
Then number of quizzes should be 2

Scenario: US001 Quiz Management - Find Quiz
Given persisted quiz with title: Quiz5 and description: description5 and date: 15/10/2010 and time: 50
When find this quiz
Then there should be quiz with title: Quiz5 and description: description5 and date: 15/10/2010 and time: 50 in the system
Then teardown - remove added quiz

Scenario: US001 Quiz Management - Edit Quiz
Given persisted quiz with title: Quiz6 and description: description6 and date: 16/10/2010 and time: 60
When this quiz is edited with values title: Quiz7 and description: description7 and date: 17/10/2010 and time: 70
Then there should be quiz with title: Quiz7 and description: description7 and date: 17/10/2010 and time: 70 in the system


