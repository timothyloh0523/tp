# Loh Yan Xun, Timothy - Project Portfolio Page

## Overview

Coinflip is a Command-Line Interface (CLI) application with the intended target audience being children who are not of
age to gamble, aiming to educate them on the dangers of gambling. This is done by allowing the user to gamble virtual
currency on coin flips, whilst simultaneously tracking and giving feedback on their statistics.

### Summary of Contributions

**<ins>Main Code</ins>** 

Link to code on tP dashboard: [Click here](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=timothyloh0523&breakdown=true)

**<ins>Contributions to Main Code</ins>**

* Implemented code and exceptions for `CheckCommand`, corresponding to the following user inputs:
  * `check bet` - for user to check their bet amount
  * `check balance` - for user to check their existing credit balance
  * `check history` - for user to check their statistics
* Implemented streaks into `UserData` and `FlipCommand` classes
  * Added data validity checks for streaks in `Storage` class
* Created abstractions for the following classes:
  * `Parser`
  * `Command`
  * 5 subclasses of `Command` (`ChangeCommand`, `CheckCommand`, `ExitCommand`, `FlipCommand`, `HelpCommand`)

**<ins>Contributions to Test Code</ins>**
* Added all test cases for the `CheckCommand` class

**<ins>Review/Mentoring Contributions</ins>**
* Guided teammates on version control steps
* Guided teammates on adding PlantUML diagrams into Developer Guide
* Assisted in changing UML sequence diagrams format from [mermaid](https://mermaid.js.org/) format to plantUML

**<ins>Contributions to Developer Guide</ins>**
* Added UML sequence diagram for `CheckCommand`
