# Loh Yan Xun, Timothy - Project Portfolio Page

## Overview

Coinflip is a Command-Line Interface (CLI) application with the intended target audience being children who are not of
age to gamble, aiming to educate them on the dangers of gambling. This is done by allowing the user to gamble virtual
currency on coin flips, whilst simultaneously tracking and giving feedback on their statistics.

### Summary of Contributions

**<ins>Main Code</ins>**
* Added code for CheckCommand to interpret 3 user commands:
  * check bet
  * check balance
  * check history
* Implemented streaks into UserData and FlipCommand
* Created abstractions for the following classes:
  * Command
  * 5 Subclasses of Command (ChangeCommand, CheckCommand, ExitCommand, FlipCommand, HelpCommand)
  * Parser

**<ins>Developer Guide</ins>**
* Added UML sequence diagram for CheckCommand class
* Assisted in changing UML sequence diagrams format from mermaid to plantUML

**<ins>Test Code</ins>**
* Added all test cases for the CheckCommand class