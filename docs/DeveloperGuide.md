# Developer Guide

## Acknowledgements

Third-party Libraries Used for Test Cases:

* Mockito: Mocking framework to test save file loading/storing

## Design & implementation

`Coinflip` (consisting of class `Coinflip`) is in charge of the app launch and shut down.

* Upon app launch, the program attempts to load key user data from a local save file
* If save file cannot be accessed or does not exist, the key user data will be assigned default values instead
* Following this, the program enters a loop which will wait for the user to key in a line of input,
  before acting on the user input accordingly
* When the correct command is given, the program will shut down

To achieve this basic flow of events, most of the program's functionalities are done by the following components:

* `Parser`: Parses user input and executes relevant commands
* `Command` and its sub-classes: Handles logic and output for each command
* `Storage`: Creates, writes to and reads from save file

These components share usage of some other common utility classes:

* `CoinflipException`: Exception class for all Coinflip related errors
* `CoinFlipFileException`: Exception class specifically for save-file related errors
* `CoinflipLogger`: Handles logging of program events for developers
* `Printer`: Handles printing of output for the user
* `UserData`: Abstraction for the user's data (number of coins, bet amounts, etc.)

## Product scope

### Target user profile

Children who are not of age to gamble

### Value proposition

The app will allow children to simulate a gambling environment without using actual money
like some games on the App Store. This will be done by allowing them to bet in-game currency
on a coin flip, and educate them about the dangers of gambling.

## User Stories

| Version | As a ...   | I want to ...                                       | So that I can ...                                                       |
|---------|------------|-----------------------------------------------------|-------------------------------------------------------------------------|
| v1.0    | new player | start the game with a fixed amount of virtual money | experience the consequences of gambling                                 |
| v1.0    | new player | view all the commands I can use                     | use the programme as intended                                           |
| v1.0    | new player | view my current virtual money balance               | know how much I can bet on my next coinflip                             |
| v1.0    | new player | choose different betting amounts                    | see how risky bets affect my virtual balance                            |
| v1.0    | new player | randomly win or lose money when gambling            | experience the unpredictability of gambling                             |
| v1.0    | user       | continue my coin balance from my previous session   | the wins or losses from my previous bets are permanent to me            |
| v2.0    | user       | view my previous coinflip results                   | see the long-term effect of my playing                                  |
| v2.0    | user       | save my data between sessions                       | continue experiencing the effects of my actions over a long time period |
| v2.0    | user       | have a loading animation when I flip coins          | see the parallels to real-world gambling                                |
| v2.1    | user       | unlock achievements upon achieving certain actions  | feel rewarded for using the program                                     |
| v2.1    | user       | view my achievements                                | reflect on my experience playing the same                               |

## Non-Functional Requirements

* Should work on any _mainstream_ OS with Java 17 installed.
* A user with decent typing speed for normal text should be able to complete most tasks faster through typing out
  commands, compared to using the mouse to navigate a GUI application.

## Glossary

* *balance* - Number of coins possessed by the user
* *win* - When a user successfully predicts the outcome of a coin flip
* *lose* - When a user does not successfully predicts the outcome of a coin flip
* *win streak* - Number of consecutive successful coin flips performed by the user
* *lose streak* - Number of consecutive unsuccessful coin flips performed by the user

## Instructions for Manual Testing

Viewing available commands

* Format: `help`
* Features: Shows available functions

Viewing balance

* Format: `check balance`
* Feature: Shows balance available to be used for betting

Viewing statistics of previous flips

* Format: `check history`
* Feature: Shows statistics for all previous coinflips

Viewing bet amount

* Format: `check bet`
* Feature: Shows bet amount that will be used if a bet were to be made.

Changing betting amount

* Format: `change <number representing new bet amount>`
* Feature: Gets new betting amount from user to be used in next bet.
* Example of usage:
  `change 10`

Playing coinflip

* Format: `flip <heads>/<tails>`
* Feature: Flips a coin and either gains or loses the bet amount
* Example of usage:
  `flip heads`
  `flip tails`

Terminating program

* Format: `exit`
* Features: Exits the program

## Class Diagrams

Here is the class diagram for Coinflip:

## Object Diagrams

## Sequence Diagrams

Viewing available commands (help function)

![](./diagrams/help.svg)

Viewing balance (check balance command)

![](./diagrams/checkbalance.svg)

Playing coinflip (flip command)

![](./diagrams/flip.svg)

Changing betting amount (change command)

![](./diagrams/change.svg)

Terminating program (exit command)

![](./diagrams/exit.svg)