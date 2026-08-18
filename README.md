# Panda Assistant: Level-1

Panda is a command-line personal assistant under development.
New feature added at this level is being able to echo the user's commands.



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
- Echo user's input
- Terminate program on command

### Echoing user Input
After the program has started, any user input (other than `bye`) will be echoed by Panda.

#### Example Run

```text
Value  <-- User Input
____________________________________________________________
Value
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