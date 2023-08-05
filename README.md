# java-bulls-and-cows

# How to play:
This is a modified version of the famous Bulls and Cows game.

- User enters length of the code.
- User enters number of possible symbols. The number should be within the range 1-36 inclusive. Then the system will randomly pick each character from “0123456789abcdefghijklmnopqrstuvwxyz” (1-indexed). For example, if I enter “1”, then the system can choose “1” as the only possible symbol.
- In each turn, guess the code using the specified possible symbols.
- If a character is in the secret code with correct position, grade will be “1 bull”. If a character is in the secret code with wrong position, grade will be “1 cow”. Otherwise, grade will be “None”
- Keep guessing until your answer matches the secret code.

# How to run in local environment?
- Create a new Java project in your machine.
- Paste the Main.java file into your src folder
- Run as Main.

