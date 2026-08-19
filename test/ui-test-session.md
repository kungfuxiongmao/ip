# UI Test Session

## PASS: Termination function

Aim: Verify that Panda starts, accepts `bye`, displays a farewell, and exits normally.

Command:
~~~sh
javac -d out/ui-test $(find src/main/java -name '*.java') && java -cp out/ui-test Panda
~~~

Console input:
~~~text
bye
~~~

Console output:
~~~text
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
~~~

Exit code: 0

## PASS: Add to-do, event, and deadline tasks

Aim: Verify that the parser creates to-do, deadline, and event tasks from valid commands.

Command:
~~~sh
javac -d out/ui-test $(find src/main/java -name '*.java') && java -cp out/ui-test Panda
~~~

Console input:
~~~text
todo borrow book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
list
bye
~~~

Console output:
~~~text
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
~~~

Exit code: 0

## PASS: Mark and unmark an event

Aim: Verify that an event can be marked as done, unmarked, and displayed as unmarked afterwards.

Command:
~~~sh
javac -d out/ui-test $(find src/main/java -name '*.java') && java -cp out/ui-test Panda
~~~

Console input:
~~~text
event project meeting /from Mon 2pm /to 4pm
mark 1
unmark 1
list
bye
~~~

Console output:
~~~text
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
~~~

Exit code: 0

## PASS: Handle parser, task-list, and task-state errors

Aim: Verify that the global exception handler displays messages for unknown commands, invalid mark usage, invalid task indexes, and repeated task-state changes.

Command:
~~~sh
javac -d out/ui-test $(find src/main/java -name '*.java') && java -cp out/ui-test Panda
~~~

Console input:
~~~text
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
~~~

Console output:
~~~text
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
~~~

Exit code: 0

## PASS: Reject malformed command values

Aim: Verify that blank and unknown input, plus every supported command with malformed arguments, displays its informative usage error and allows Panda to continue.

Command:
~~~sh
javac -d out/ui-test $(find src/main/java -name '*.java') && java -cp out/ui-test Panda
~~~

Console input:
~~~text
   
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
bye
~~~

Console output:
~~~text
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
Bye. Hope to see you again soon!
____________________________________________________________
~~~

Exit code: 0
