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

## PASS: Add to-do, event, deadline, and fallback to-do tasks

Aim: Verify that the parser creates to-do, deadline, and event tasks, and that unmatched input defaults to a to-do task.

Command:
~~~sh
javac -d out/ui-test $(find src/main/java -name '*.java') && java -cp out/ui-test Panda
~~~

Console input:
~~~text
todo borrow book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
read book
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
Got it. I've added this task:
  [T][ ] read book
Now you have 4 tasks in the list.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: Sunday)
3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
4.[T][ ] read book
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

## PASS: Reject invalid and repeated task state changes

Aim: Verify that marking and unmarking an out-of-range task number reports an error, and that repeated marking or unmarking reports the existing task state.

Command:
~~~sh
javac -d out/ui-test $(find src/main/java -name '*.java') && java -cp out/ui-test Panda
~~~

Console input:
~~~text
todo read book
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
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
Invalid task number
____________________________________________________________
____________________________________________________________
Invalid task number
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] read book
____________________________________________________________
____________________________________________________________
[T][X] read book
This task has already been marked.
____________________________________________________________
____________________________________________________________
OK, I've marked this task as not done yet:
  [T][ ] read book
____________________________________________________________
____________________________________________________________
[T][ ] read book
This task has already been unmarked.
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
~~~

Exit code: 0
