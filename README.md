# Panda Assistant: Level-3

Panda is a command-line personal assistant under development.
New feature added is the ability to mark and unmark tasks to track whether a task has been completed or not.



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
- Add tasks into a list
- Display the list
- Mark a task as done
- Unmark a task
- Terminate program on command

### Display the Tasklist
To display the list stored in the program, user may input `list` to display the task list.
Note that leading and trailing spaces in the program are ignored.

### Add tasks into Tasklist
After the program has started, any user input (other than `bye` and `list`) are considered tasks by Panda.
They will be added into the task list.

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

#### Example Run

```text
list                    ← User Input
____________________________________________________________
~~~ Empty List ~~~
____________________________________________________________
mark                    ← User Input
____________________________________________________________
added: mark
____________________________________________________________
unmark                  ← User Input
____________________________________________________________
added: unmark
____________________________________________________________
unmark 1 3 5             ← User Input
____________________________________________________________
added: unmark 1 3 5
____________________________________________________________
list                    ← User Input
____________________________________________________________
Here are the tasks in your list:
1.[ ] mark
2.[ ] unmark
3.[ ] unmark 1 3 5
____________________________________________________________
mark 2                  ← User Input
____________________________________________________________
Nice! I've marked this task as done:
  [X] unmark
____________________________________________________________
list                    ← User Input
____________________________________________________________
Here are the tasks in your list:
1.[ ] mark
2.[X] unmark
3.[ ] unmark 1 3 5
____________________________________________________________
unmark 2                ← User Input
____________________________________________________________
OK, I've marked this task as not done yet:
  [ ] unmark
____________________________________________________________
list                    ← User Input
____________________________________________________________
Here are the tasks in your list:
1.[ ] mark
2.[ ] unmark
3.[ ] unmark 1 3 5
____________________________________________________________
```

### Terminate Program on Command
To terminate the program, input `bye`. The program should print:

```
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