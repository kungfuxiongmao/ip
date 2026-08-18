# Panda Assistant: Level-2

Panda is a command-line personal assistant under development.
New feature added is being able to add items into a list and print the list, instead of echoing user inputs. 



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
- Terminate program on command

### Display the Tasklist
To display the list stored in the program, user may input `list` to display the task list.
Note that leading and trailing spaces in the program are ignored.

### Add items into Tasklist
After the program has started, any user input (other than `bye` and `list`) are considered tasks by Panda.
They will be added into the task list.

#### Example Run

```text
list                    ← User Input
____________________________________________________________
~~~ Empty List ~~~
____________________________________________________________
Buy food                ← User Input
____________________________________________________________
added: Buy food
____________________________________________________________
list                    ← User Input
____________________________________________________________
1. Buy food
____________________________________________________________
Do laundry              ← User Input
____________________________________________________________
added: Do laundry
____________________________________________________________
list                    ← User Input
____________________________________________________________
1. Buy food
2. Do laundry
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