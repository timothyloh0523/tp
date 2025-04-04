# User Guide

## Introduction

Coinflip will allow children to simulate a gambling environment without using actual money like some games on the App
Store. This will be done by allowing them to bet in-game currency on a coin flip, and educate them about the dangers of
gambling.

## Quick Start

1. Ensure you have Java ```17``` or above installed in your Computer.
2. Download the latest .jar file from [here](https://github.com/AY2425S2-CS2113-F13-1/tp/releases).
3. Copy the file to the folder you want to use as the *home folder* for your coinflip programme.
4. Open a command terminal, ```cd``` into the folder you put the jar file in, and use the ```java -jar tp.jar```
   command to run the programme.
5. Type in a command and press Enter to execute it.  
   Some example commands you can try:
    - ```help```: Shows a list of executable commands
    - ```check balance```: Checks remaining balance
    - ```check bet```: Checks betting amount
    - ```exit```: Terminates programme

## Features

### Viewing available commands: `help`

Shows available functions

Format: `help`

Example of usage:

`help`

### Viewing balance: `check balance`

Shows balance available to be used for betting

Format: `check balance`

Example of usage:

`check balance`

### Viewing statistics for previous flips: `check history`

Shows statistics for all previous coinflips

Format: `check history`

Example of usage:

`check history`

### Viewing bet amount: `check bet`

Shows bet amount that will be used for your coin flips.

Format: `check bet`

Example of usage:

`check bet`

### Changing betting amount: `change`

Sets betting amount to be used in subsequent flips.

Format: `change <number representing new bet amount>`

Example of usage:

`change 10`

### Playing coinflip: `flip`

Wager your bet amount on the outcome of a coin flip (heads or tails).

If you bet on the right outcome, you will win your bet amount. Otherwise, you'll lose it!

Format: `flip <heads>/<tails>`

Example of usage:

`flip heads` or `flip tails`

### Terminating program: `exit`

Exits the program

Format: `exit`

Example of usage:

`exit`

## FAQ

**Q**: Will my data be automatically saved when I end the program?

**A**: Yes, it will be saved in a save file.

**Q**: How do I transfer my data to another computer?

**A**: When you start Coinflip for the first time, there will be a `data` folder
in the same directory as Coinflip's .jar file.
Transfer this folder to the same directory as the .jar on the other computer,
and your save data will be transferred.

**Q**: Can I edit the save file?

**A**: Yes, the user data is stored in a .csv file in the `data` folder.
However, if our program detects that the save file is corrupted,
it will ignore the save file and start as if a new game has been started.

## Command Summary

| Action                  | Command                   |
|-------------------------|---------------------------|
| View available commands | `help`                    |
| View coin balance       | `check balance`           |
| View flip statistics    | `check history`           |
| View bet amount         | `check bet`               |
| Change bet amount       | `change <new bet amount>` |
| Perform a coin flip     | `flip <heads>/<tails>`    |
| Exit program            | `exit`                    |