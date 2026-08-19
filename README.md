# Panda Assistant: Level-4

Panda is a command-line personal assistant under development.
This version provides support for different types of tasks, namely `Todo`, `Deadline` and `Event`.


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
They can be used as a gentle reminder. [Default]


```
    todo <description>
```

**Todo**s are the default task type and users may drop the ``todo`` keyword when wanting to add a
Todo task.

```
    <description>
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

If there are no values or more than one values after the `mark` command, or the value passed is not an integer, 
it will be treated as a task to add into the list instead.

### Unmark tasks
Given a scenario where a user wrongly marks a task as done, or realises that he/she has actually not
completed the task, user may unmark the task using the command `unmark <int>`, where `<int>` is to be replaced
with the task index of the task in the list.

If there are no values or more than one values after the `unmark` command, or the value passed is not an integer,
it will be treated as a task to add into the list instead.

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