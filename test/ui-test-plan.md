# UI Test Plan

Run this plan with:

```sh
python3 skills/test-ui/scripts/run_ui_tests.py
```

## Test case: Termination function

### Aim

Verify that Panda starts, accepts `bye`, displays a farewell, and exits normally.

### Command

```sh
javac -d out/ui-test $(find src/main/java -name '*.java') && java -cp out/ui-test Panda
```

### Inputs

```text
bye
```

### Expected output

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
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

## Test case: Add to-do, event, and deadline tasks

### Aim

Verify that the add-task command parsers create to-do, deadline, and event tasks from valid commands.

### Command

```sh
javac -d out/ui-test $(find src/main/java -name '*.java') && java -cp out/ui-test Panda
```

### Inputs

```text
todo borrow book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
list
bye
```

### Expected output

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
____________________________________________________________
Got it. I've added this task:
  [T][ ] borrow book
Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [D][ ] return book (by: Sunday)
Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: Sunday)
3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

## Test case: Mark and unmark an event

### Aim

Verify task-list logic: an event can be marked as done, unmarked, and displayed as unmarked afterwards.

### Command

```sh
javac -d out/ui-test $(find src/main/java -name '*.java') && java -cp out/ui-test Panda
```

### Inputs

```text
event project meeting /from Mon 2pm /to 4pm
mark 1
unmark 1
list
bye
```

### Expected output

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
____________________________________________________________
Got it. I've added this task:
  [E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [E][X] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
____________________________________________________________
OK, I've marked this task as not done yet:
  [E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

## Test case: Handle parser, task-list, and task-state errors

### Aim

Verify that the global exception handler displays messages for unknown commands, invalid mark usage, invalid task indexes, and repeated task-state changes.

### Command

```sh
javac -d out/ui-test $(find src/main/java -name '*.java') && java -cp out/ui-test Panda
```

### Inputs

```text
unknown command
todo read book
mark
mark abc
mark 1 2
mark 2
unmark 2
mark 1
mark 1
unmark 1
unmark 1
bye
```

### Expected output

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
____________________________________________________________
OOPS! Panda does not know the "unknown command" command yet. :<
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the mark command written like this: "mark TASK_NUMBER" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the mark command written like this: "mark TASK_NUMBER" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the mark command written like this: "mark TASK_NUMBER" 
____________________________________________________________
____________________________________________________________
OOPS! Panda cannot find task number 2; there are only 1 task(s) in the list. 
____________________________________________________________
____________________________________________________________
OOPS! Panda cannot find task number 2; there are only 1 task(s) in the list. 
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] read book
____________________________________________________________
____________________________________________________________
OOPS! Panda has already marked this task as done:
  [T][X] read book
No extra tick needed. :>
____________________________________________________________
____________________________________________________________
OK, I've marked this task as not done yet:
  [T][ ] read book
____________________________________________________________
____________________________________________________________
OOPS! Panda has already marked this task as not done:
  [T][ ] read book
No extra un-tick needed. :>
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

## Test case: Reject malformed command values

### Aim

Verify that every command parser rejects blank, unknown, and malformed command values with an informative usage error while Panda continues running.

### Command

```sh
javac -d out/ui-test $(find src/main/java -name '*.java') && java -cp out/ui-test Panda
```

### Inputs

```text
   
unknown command
bye later
list all
todo
deadline return book
deadline /by Sunday
deadline return book /by
event meeting
event meeting /from Monday
event /from Monday /to Tuesday
event meeting /from Monday /to
mark
mark one
mark 1 2
unmark
unmark one
unmark 1 2
delete
delete one
delete 1 2
delete 2
delete 0
bye
```

### Expected output

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
____________________________________________________________
OOPS! Panda needs a command before it can help. :>
____________________________________________________________
____________________________________________________________
OOPS! Panda does not know the "unknown command" command yet. :<
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the bye command written like this: "bye" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the list command written like this: "list" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the todo command written like this: "todo DESCRIPTION" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the deadline command written like this: "deadline DESCRIPTION /by DATE" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the deadline command written like this: "deadline DESCRIPTION /by DATE" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the deadline command written like this: "deadline DESCRIPTION /by DATE" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the event command written like this: "event DESCRIPTION /from START /to END" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the event command written like this: "event DESCRIPTION /from START /to END" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the event command written like this: "event DESCRIPTION /from START /to END" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the event command written like this: "event DESCRIPTION /from START /to END" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the mark command written like this: "mark TASK_NUMBER" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the mark command written like this: "mark TASK_NUMBER" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the mark command written like this: "mark TASK_NUMBER" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the unmark command written like this: "unmark TASK_NUMBER" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the unmark command written like this: "unmark TASK_NUMBER" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the unmark command written like this: "unmark TASK_NUMBER" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the delete command written like this: "delete TASK_NUMBER" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the delete command written like this: "delete TASK_NUMBER" 
____________________________________________________________
____________________________________________________________
OOPS! Panda needs the delete command written like this: "delete TASK_NUMBER" 
____________________________________________________________
____________________________________________________________
OOPS! Panda cannot find task number 2; there are only 0 task(s) in the list. 
____________________________________________________________
____________________________________________________________
OOPS! I think you made a mistake, task number cannot be 0
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

## Test case: Delete a task

### Aim

Verify that delete removes the requested task, preserves the remaining task order, and reports the updated task count.

### Command

```sh
javac -d out/ui-test $(find src/main/java -name '*.java') && java -cp out/ui-test Panda
```

### Inputs

```text
todo read book
deadline return book /by June 6th
event project meeting /from Aug 6th 2pm /to 4pm
todo join sports club
todo borrow book
mark 1
mark 2
mark 4
list
delete 3
list
bye
```

### Expected output

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
____________________________________________________________
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [D][ ] return book (by: June 6th)
Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [T][ ] join sports club
Now you have 4 tasks in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [T][ ] borrow book
Now you have 5 tasks in the list.
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] read book
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [D][X] return book (by: June 6th)
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] join sports club
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][X] read book
2.[D][X] return book (by: June 6th)
3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
4.[T][X] join sports club
5.[T][ ] borrow book
____________________________________________________________
____________________________________________________________
Noted. I've removed this task:
  [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
Now you have 4 tasks in the list.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][X] read book
2.[D][X] return book (by: June 6th)
3.[T][X] join sports club
4.[T][ ] borrow book
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

## Test case: Load and save persisted tasks

### Aim

Verify that Panda loads all task types and marked states, then overwrites the save file with the updated task list on termination.

### Setup

```sh
mkdir -p data
cp test/fixtures/preloaded-tasks.txt data/tasks.txt
```

### Command

```sh
javac -d out/ui-test $(find src/main/java -name '*.java') && java -cp out/ui-test Panda && diff -u test/fixtures/expected-saved-tasks.txt data/tasks.txt
```

### Inputs

```text
list
todo new task
bye
```

### Expected output

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
____________________________________________________________
Here are the tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: June 6th)
3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
4.[T][X] join sports club
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [T][ ] new task
Now you have 5 tasks in the list.
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

## Test case: Recover from a corrupted save file

### Aim

Verify that an invalid saved-task format initializes an empty task list instead of terminating Panda.

### Setup

```sh
mkdir -p data
cp test/fixtures/corrupted-tasks.txt data/tasks.txt
```

### Command

```sh
javac -d out/ui-test $(find src/main/java -name '*.java') && java -cp out/ui-test Panda
```

### Inputs

```text
list
bye
```

### Expected output

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
____________________________________________________________
The saved file is broken... I can only restart your task list.
____________________________________________________________
____________________________________________________________
~~~ Empty List ~~~
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```
