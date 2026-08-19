# Panda Assistant: Level-5

Panda is a command-line personal assistant under development.
This version has made significant improvements to the error handling.

                                                            _______               
                _________   _...._                  _..._   \  ___ `'.            
                \        |.'      '-.             .'     '.  ' |--.\  \           
                 \        .'```'.    '.          .   .-.   . | |    \  '          
                  \      |       \     \   __    |  '   '  | | |     |  '    __   
                   |     |        |    |.:--.'.  |  |   |  | | |     |  | .:--.'. 
                   |      \      /    ./ |   \ | |  |   |  | | |     ' .'/ |   \ |
                   |     |\`'-.-'   .' `" __ | | |  |   |  | | |___.' /' `" __ | |
                   |     | '-....-'`    .'.''| | |  |   |  |/_______.'/   .'.''| |
                  .'     '.            / /   | |_|  |   |  |\_______|/   / /   | |_
                '-----------'          \ \._,\ '/|  |   |  |             \ \._,\ '/
                                        `--'  `" '--'   '--'              `--'  `"s
## Setting up of Panda

### Prerequisites
- JDK 25
- IntelliJ IDEA

1. Run `git clone https://github.com/kungfuxiongmao/ip.git`

### To run the program:
1. Run `main` in `./src/main/java/Panda.java`

## Current Features
Panda is able to:
- Greet users
- Add tasks into a list (Tasks are split into **Todo**, **Deadline** and **Event** types)
- Display the list
- Mark a task as done
- Unmark a task
- Terminate program on command

### Display the Tasklist
To display the list stored in the program, user may input `list` to display the task list.
Note that leading and trailing spaces in the program are ignored.


### Add tasks into Tasklist
#### Support for multiple types of tasks

Panda supports three types of tasks: Todo, Deadline and Event

- **Todo**: Todo tasks are tasks without a specific deadline or time period.
They can be used as a gentle reminder.


```
    todo <description>
```



- **Deadline**: Deadline tasks are tasks with a specific deadline.
  The /by flag is used to specify the deadline.

```
    deadline task-description /by task-deadline
```
- **Event**: An event is a task with a specific start and end time.
The `/from` and `/to` flag are used to specify the time period.

```
    event task-description /from start-datetime /to end-datetime
```


### Mark tasks as done
Tasks are added into the task list as undone. 
Upon completion, users may mark tasks as completed by running the command `mark <int>`, where `<int>` is to be replaced
with the task index of the task in the list.

If the task number is missing, not an integer, or followed by extra values, the application throws an
exception, which is handled by the global `ExceptionHandler`. Panda displays the expected command format.

### Unmark tasks
Given a scenario where a user wrongly marks a task as done, or realises that he/she has actually not
completed the task, user may unmark the task using the command `unmark <int>`, where `<int>` is to be replaced
with the task index of the task in the list.

If the task number is missing, not an integer, or followed by extra values, the application throws an 
exception, which is handled by the global `ExceptionHandler`. Panda displays the expected command format.

### Input Validation with the Parser

Panda first identifies the command keyword, then sends the remaining text to that command's parser. The command parser
checks that the arguments follow the required format before creating a command. Unknown commands and malformed arguments
cause an exception to be thrown.

### Exception Handling

Panda checks command input before executing it and displays helpful messages when it cannot continue with a command.

#### Customised Exceptions
Exceptions thrown by the application are instances of ApplicationException. Each exception stores a user-facing 
error message that describes the error based on the application's current state. This provides a consistent mechanism 
for handling exceptions and standardises the presentation of error messages throughout the application.

#### Handling ApplicationException

After valid input creates a command, application logic can still reject it. For example, `TaskList` checks whether a task
number exists and whether a task can be marked or unmarked. These errors are thrown as application exceptions and passed
to the global `ExceptionHandler`, which displays the exception's message and keeps Panda running.

For example:

```text
unknown command                           ← User Input
____________________________________________________________
OOPS! Panda does not know the "unknown command" command yet. :<
____________________________________________________________
todo read book                            ← User Input
____________________________________________________________
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
____________________________________________________________
mark                                     ← User Input
____________________________________________________________
OOPS! Panda needs the mark command written like this: "mark TASK_NUMBER"
____________________________________________________________
mark 0                                   ← User Input
____________________________________________________________
OOPS! I think you made a mistake, task number cannot be 0
____________________________________________________________
mark 1                                   ← User Input
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] read book
____________________________________________________________
mark 1                                   ← User Input
____________________________________________________________
OOPS! Panda has already marked this task as done:
  [T][X] read book
No extra tick needed. :>
____________________________________________________________
unmark 1                                 ← User Input
____________________________________________________________
OK, I've marked this task as not done yet:
  [T][ ] read book
____________________________________________________________
unmark 1                                 ← User Input
____________________________________________________________
OOPS! Panda has already marked this task as not done:
  [T][ ] read book
No extra un-tick needed. :>
____________________________________________________________
```

### Terminate Program on Command
To terminate the program, input `bye`. The program should print:

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### Example Run

```text
____________________________________________________________ 
                                            _______                 
_________   _...._                  _..._   \  ___ `'.              
\        |.'      '-.             .'     '.  ' |--.\  \             
 \        .'```'.    '.          .   .-.   . | |    \  '            
  \      |       \     \   __    |  '   '  | | |     |  '    __     
   |     |        |    |.:--.'.  |  |   |  | | |     |  | .:--.'.   
   |      \      /    ./ |   \ | |  |   |  | | |     ' .'/ |   \ |  
   |     |\`'-.-'   .' `" __ | | |  |   |  | | |___.' /' `" __ | |  
   |     | '-....-'`    .'.''| | |  |   |  |/_______.'/   .'.''| |  
  .'     '.            / /   | |_|  |   |  |\_______|/   / /   | |_ 
'-----------'          \ \._,\ '/|  |   |  |             \ \._,\ '/ 
                        `--'  `" '--'   '--'              `--'  `"  

Hello! I'm Panda. 
What can I do for you? 
____________________________________________________________ 
list                                        ← User Input
____________________________________________________________ 
~~~ Empty List ~~~ 
____________________________________________________________ 
todo Borrow book                            ← User Input
____________________________________________________________ 
Got it. I've added this task: 
  [T][ ] Borrow book 
Now you have 1 tasks in the list. 
____________________________________________________________ 
Read book                                   ← User Input
____________________________________________________________ 
Got it. I've added this task: 
  [T][ ] Read book 
Now you have 2 tasks in the list. 
____________________________________________________________ 
deadline Return book /by Thursday           ← User Input
____________________________________________________________ 
Got it. I've added this task: 
  [D][ ] Return book (by: Thursday) 
Now you have 3 tasks in the list. 
____________________________________________________________ 
event Meeting /from Wednesday 12pm /to 2pm  ← User Input
____________________________________________________________ 
Got it. I've added this task: 
  [E][ ] Meeting (from: Wednesday 12pm to: 2pm) 
Now you have 4 tasks in the list. 
____________________________________________________________ 
list                                        ← User Input
____________________________________________________________ 
Here are the tasks in your list: 
1.[T][ ] Borrow book 
2.[T][ ] Read book 
3.[D][ ] Return book (by: Thursday) 
4.[E][ ] Meeting (from: Wednesday 12pm to: 2pm) 
____________________________________________________________ 
mark 2                                      ← User Input
____________________________________________________________ 
Nice! I've marked this task as done: 
  [T][X] Read book 
____________________________________________________________ 
list                                        ← User Input
____________________________________________________________ 
Here are the tasks in your list: 
1.[T][ ] Borrow book 
2.[T][X] Read book 
3.[D][ ] Return book (by: Thursday) 
4.[E][ ] Meeting (from: Wednesday 12pm to: 2pm) 
____________________________________________________________ 
unmark 2                                    ← User Input
____________________________________________________________ 
OK, I've marked this task as not done yet: 
  [T][ ] Read book 
____________________________________________________________ 
list                                        ← User Input
____________________________________________________________ 
Here are the tasks in your list: 
1.[T][ ] Borrow book 
2.[T][ ] Read book 
3.[D][ ] Return book (by: Thursday) 
4.[E][ ] Meeting (from: Wednesday 12pm to: 2pm) 
____________________________________________________________ 
bye                                         ← User Input
____________________________________________________________ 
Bye. Hope to see you again soon! 
____________________________________________________________
```


## AI Declaration
- AI have been used in the development to AI-4:
- 'Think' and compare: 
Think of how you would do the task manually. Get AI to do it. Compare the solution you 'imagined' with the one AI produced.

#### Usual Workflow
- I will think of how to implement the code, explain how I want the classes, methods and event flows to be like and get AI to implement. 
- I will then make minor adjustments to finalise how I want the code to be like.
