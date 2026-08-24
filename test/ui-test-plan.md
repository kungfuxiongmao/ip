# UI Test Plan

Run this plan with:

```sh
python3 skills/test-ui/scripts/run_ui_tests.py
```

## Bugs found during exploratory testing

The following cases currently fail. Keep them as regression tests until the
corresponding defects are fixed.

These failures were reproduced with Java 25.0.3.

### Bug: Large task numbers crash Panda

**Severity:** High — a single valid-looking command terminates the application.

All three commands accept an arbitrary sequence of digits, but values greater
than `Integer.MAX_VALUE` cause an unhandled `NumberFormatException` instead of
the normal usage error. The application exits before it can process any later
commands.

### Command

```sh
javac -d out/ui-test $(find src/main/java -name '*.java') && java -cp out/ui-test Panda
```

### Inputs

Run Panda separately for each task-number command because every session crashes
on its first command:

```text
mark 2147483648
bye
```

```text
unmark 2147483648
bye
```

```text
delete 2147483648
bye
```

### Expected behaviour after the fix

Each oversized number should display the same usage error as another invalid
task number, keep Panda running, and allow `bye` to exit normally. No Java
stack trace should be printed.

### Actual behaviour

The first input terminates Panda with:

```text
Exception in thread "main" java.lang.NumberFormatException: For input string: "2147483648"
```

### Bug: End of input causes an uncaught exception

**Severity:** Medium — running Panda with redirected or closed standard input
ends with an error rather than a clean exit.

### Command

```sh
javac -d out/ui-test $(find src/main/java -name '*.java') && printf 'list\n' | java -cp out/ui-test Panda
```

### Expected behaviour after the fix

After displaying the list, Panda should exit cleanly when no more input is
available, without a stack trace.

### Actual behaviour

Panda displays the empty list, then terminates with:

```text
Exception in thread "main" java.util.NoSuchElementException: No line found
```

### Bug: Event parser accepts malformed or reversed markers

**Severity:** Medium — malformed input creates an event with misleading start
and end values.

### Command

```sh
javac -d out/ui-test $(find src/main/java -name '*.java') && java -cp out/ui-test Panda
```

### Inputs

```text
event meeting /from 4pm /from 5pm
event meeting /to 5pm /from 4pm
list
bye
```

### Expected behaviour after the fix

Both event commands should be rejected with the documented usage error:
`event DESCRIPTION /from START /to END`. The list should remain empty.

### Actual behaviour

Both malformed commands are accepted. The first creates an event despite having
no `/to` marker, and the second reverses the entered values:

```text
[E][ ] meeting (from: 4pm to: 5pm)
[E][ ] meeting (from: 5pm to: 4pm)
```

### Bug: Task descriptions can inject terminal control sequences

**Severity:** Medium — crafted task text can clear or reposition a user's
terminal and make application output misleading.

Panda stores and echoes raw control characters in task descriptions. This test
uses the ANSI “clear screen” and “move cursor home” sequences.

### Command

```sh
javac -d out/ui-test $(find src/main/java -name '*.java') && printf 'todo safe\033[2J\033[Hforged\nlist\nbye\n' | java -cp out/ui-test Panda
```

### Expected behaviour after the fix

Panda should reject control characters or display an escaped, harmless form of
them. Task text must not be able to control the terminal.

### Actual behaviour

Panda accepts the task and writes the escape bytes back to the terminal in both
the add confirmation and task list. In a compatible terminal, `ESC[2J` clears
the screen and `ESC[H` moves the cursor to the top-left corner.

### Bug: An invisible description is accepted as a task

**Severity:** Low — a task can appear to have no description even though the
parser's ordinary empty-description check passes.

### Command

The octal bytes below encode a zero-width space (`U+200B`):

```sh
javac -d out/ui-test $(find src/main/java -name '*.java') && printf 'todo \342\200\213\nlist\nbye\n' | java -cp out/ui-test Panda
```

### Expected behaviour after the fix

Panda should reject a description that contains no visible text with the normal
`todo DESCRIPTION` usage error, and the task list should remain empty.

### Actual behaviour

Panda reports that the task was added and lists a visually blank task:

```text
1.[T][ ] [a zero-width space is present here but cannot be seen]
```

### Bug: A one-item list uses a plural task count

**Severity:** Low — add and delete confirmations contain a grammatical error.

### Command

```sh
javac -d out/ui-test $(find src/main/java -name '*.java') && java -cp out/ui-test Panda
```

### Inputs

```text
todo first
todo second
delete 1
bye
```

### Expected behaviour after the fix

Both confirmations produced when the list size is one should say:

```text
Now you have 1 task in the list.
```

### Actual behaviour

The first add and the deletion back to one item both say:

```text
Now you have 1 tasks in the list.
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
