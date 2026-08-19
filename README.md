# Panda Assistant: Level-5

Panda is a command-line personal assistant under development.
This version now supports deletion of tasks. 

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
## Setting up of Panda

### Prerequisites

- JDK 25
- IntelliJ IDEA (optional)

1. Clone the repository: `git clone https://github.com/kungfuxiongmao/ip.git`

### Run the program

Compile and run Panda from the project root:

```sh
javac -d out $(find src/main/java -name '*.java') && java -cp out Panda
```


## Current Features

Panda is able to:

- Greet users
- Add tasks to a list (**Todo**, **Deadline**, and **Event**)
- Display the task list
- Mark and unmark a task
- Delete a task
- Terminate the program on command

### Display the task list
Enter `list` to display the task list. Leading and trailing whitespace in a command is ignored.


### Add tasks to the task list
#### Support for multiple types of tasks

Panda supports three types of tasks: Todo, Deadline, and Event.

- **Todo**: A task without a specific deadline or time period; it can be used as a gentle reminder.


```
    todo <description>
```



- **Deadline**: A task with a specific deadline. Use `/by` to specify the deadline.

```
    deadline task-description /by task-deadline
```
- **Event**: A task with a specific start and end time. Use `/from` and `/to` to specify the time period.

```
    event task-description /from start-datetime /to end-datetime
```


### Mark tasks as done

Tasks are added to the task list as undone. Upon completion, mark a task as completed with `mark TASK_NUMBER`, 
where `TASK_NUMBER` is the one-based number displayed by `list`.

If the task number is missing, not an integer, or followed by extra values, Panda displays the expected command format.

### Unmark tasks
If a task was marked accidentally, unmark it with `unmark TASK_NUMBER`, 
where `TASK_NUMBER` is the one-based number displayed by `list`.

If the task number is missing, not an integer, or followed by extra values, Panda displays the expected command format.

### Delete tasks

Remove a task with `delete TASK_NUMBER`, where `TASK_NUMBER` is the one-based number displayed by `list`. 
Panda confirms the task that was removed, reports the new task count, and renumbers the remaining tasks. 
Panda rejects missing, non-numeric, or out-of-range task numbers.

### Input Validation with the Parser

Panda first identifies the command keyword, then sends the remaining text to that command's parser. The command parser
checks that the arguments follow the required format before creating a command. 
Unknown commands and malformed arguments cause an exception to be thrown.

### Exception Handling

Panda checks command input before executing it and displays helpful messages when it cannot continue with a command.

#### Customised Exceptions
Exceptions thrown by the application are instances of ApplicationException. Each exception stores a user-facing 
error message that describes the error based on the application's current state. This provides a consistent mechanism 
for handling exceptions and standardises the presentation of error messages throughout the application.

#### Handling ApplicationException

After valid input creates a command, application logic can still reject it. For example, `TaskList` checks whether a task
number exists and whether a task can be marked, unmarked, or deleted. 
These errors are thrown as application exceptions and passed to the global `ExceptionHandler`, 
which displays the exception's message and keeps Panda running.

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
todo borrow book                            ← User Input
____________________________________________________________ 
Got it. I've added this task: 
  [T][ ] borrow book 
Now you have 1 tasks in the list. 
____________________________________________________________ 
todo read book                              ← User Input
____________________________________________________________ 
Got it. I've added this task: 
  [T][ ] read book 
Now you have 2 tasks in the list. 
____________________________________________________________ 
deadline return book /by Thursday           ← User Input
____________________________________________________________ 
Got it. I've added this task: 
  [D][ ] return book (by: Thursday) 
Now you have 3 tasks in the list. 
____________________________________________________________ 
event meeting /from Wednesday 12pm /to 2pm  ← User Input
____________________________________________________________ 
Got it. I've added this task: 
  [E][ ] meeting (from: Wednesday 12pm to: 2pm) 
Now you have 4 tasks in the list. 
____________________________________________________________ 
list                                        ← User Input
____________________________________________________________ 
Here are the tasks in your list: 
1.[T][ ] borrow book 
2.[T][ ] read book 
3.[D][ ] return book (by: Thursday) 
4.[E][ ] meeting (from: Wednesday 12pm to: 2pm) 
____________________________________________________________ 
mark 2                                      ← User Input
____________________________________________________________ 
Nice! I've marked this task as done: 
  [T][X] read book 
____________________________________________________________ 
list                                        ← User Input
____________________________________________________________ 
Here are the tasks in your list: 
1.[T][ ] borrow book 
2.[T][X] read book 
3.[D][ ] return book (by: Thursday) 
4.[E][ ] meeting (from: Wednesday 12pm to: 2pm) 
____________________________________________________________ 
unmark 2                                    ← User Input
____________________________________________________________ 
OK, I've marked this task as not done yet: 
  [T][ ] read book 
____________________________________________________________ 
list                                        ← User Input
____________________________________________________________ 
Here are the tasks in your list: 
1.[T][ ] borrow book 
2.[T][ ] read book 
3.[D][ ] return book (by: Thursday) 
4.[E][ ] meeting (from: Wednesday 12pm to: 2pm) 
____________________________________________________________ 
delete 3                                    ← User Input
____________________________________________________________ 
Noted. I've removed this task:
  [D][ ] return book (by: Thursday)
Now you have 3 tasks in the list.
____________________________________________________________ 
list                                        ← User Input
____________________________________________________________ 
Here are the tasks in your list:
1.[T][ ] borrow book
2.[T][ ] read book
3.[E][ ] meeting (from: Wednesday 12pm to: 2pm)
____________________________________________________________ 
bye                                         ← User Input
____________________________________________________________ 
Bye. Hope to see you again soon! 
____________________________________________________________
```


## AI Declaration
- AI (Codex) have been used in the development of this project up to level AI-4:
  - 'Think' and compare: 
  Think of how you would do the task manually. Get AI to do it. 
  Compare the solution you 'imagined' with the one AI produced.
  - Definitely, after AI has completed the tasks, I will modify the code (if necessary) to how I envision it to be.
- Some portions of the code only use level AI-3:
  - Hand-code to start, get AI to finish: You hand-code a minimal version, just a proof-of-concept. 
  Get AI to strengthen it to a full-fledged version e.g., handle edge cases, add tests.
  - This is generally so when developing new structure to the repository.
  - This is to ensure that I retain control over the core components to build structure for AI to expand on.
