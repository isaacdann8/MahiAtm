# MahiAtm


# Overview

This project is an application that simulates cash dispension from an ATM. On initialisation, the user can input an amount of twentys and fiftys for the ATM to operate with. The user can then choose from 3 options. Withdraw funds, add cash to the machine or shutdown the atm.

# How to Run Code

To run the ATM, clone the repository
    `git clone https://github.com/isaacdann8/MahiAtm.git`

To compile run the script
    `compile.bat`

To run the unit tests
    `java TestAtm/Test1.java`

To run the ATM Client, which is the main command line based UI to use the functionality of the ATM
    `java AtmClient/AtmClient.java`

# Notes and Algorithms

The overall design was to build an interface class which defined all the methods the atm needs. I then implemented a base class which included all of the common functionality (such as withdrawals and adding notes)of the ATM, but I left the authentication abstract, so in the future, different ATM clients could implement their own authentication. I only implemented one full derived class called AtmNoAuth, which lets anyone withdraw money by default.

I Also implemented a Factory class that follows the Factory pattern which makes it easy to create new ATM objects.

In terms of Testing in order to reduce dependencies I implemented my own unit tests within these I tested all the outlines within the project description.

The withdrawal algorithm uses modular arithmetic to gather the maximum amount of 50s and minimum amount of 20s for a requested number for withdraw from the user.
This Algorithm takes the request amount mod 50:

`remainder = request mod 50`

Call this number the remainder, then takes the result mod 20:

`remainder2 = remainder mod 20`

If this is zero, we then get:

`(request - remainder) / 50 = Number of Fifties`

`remainder / 20 = Number of Twenties`

Then we are done, Otherwise if remainder2 is not zero AND our request is greater than 50, we calculate:

`remainder3 = remainder + 50 mod 20`
If remainder3 is now zero we get:

`(request - remainder - 50) / 50 = Number of Fifties`

`remainder + 50 / 20 = Number of Twenties`

Otherwise we have an invalid Number that is not a linear combination of 20 and 50 and we cannot withdraw the request
 
The algorithm for balancing the number of twenties and fifties in the machine when making withdrawals and making sure no withdrawals use notes that aren't available, uses the fact that our original algorithm takes the max 50s and the minimum 20s. The algorithm operates as a loop until we are done.

First, the algorithm checks if the remaining number of Fifties and Twenties is greater than 0 after withdrawal AND that there are more than 2 Fifties in the withdrawal

If yes, we check if the remaining Fifties are less than the remaining Twenties - 5. If so we replace 2 Fifties with 5 Twenties in our withdrawal and start from step one. Otherwise the withdrawal is valid and the loop ends.

If the first check fails, the second if statement looks at if the remaining number of Fifties and Twenties is greater than 0 after withdrawal. If this passes, then we are ok with our withdrawal and the loops ends, as no swaps can be made if there are fewer than 2 Fifties in the withdrawal.

If the second check fails, the third if statement checks if the remaining number of fifties are less than zero after withdrawal, the remaining number of Twenties is greater than or equal to 5 and that the withdrawal has more than 2 Fifties. If this passes, then we replace 2 Fifties with 5 Twenties. (The opposite swap is never checked as our original algorithm gives max Fifties min twenties.

If this third check fails, our ATM throws an error as we do not have enough notes to complete the withdrawal.

# Thought Processes and Additional Features not Implemented

Whilst building the software, the most important thought processes to me was the two algorithms specified above that give the ATM the ability to complete withdrawals whilst maximising the amount of withdrawals it can complete given its stock. Another important thought process was building the software with abstract methods, such as the authentication, making it easy to develop these later if need be. The last thought process was making the ATM client as useful as possible, showing the balance of the stock where it made sense to such as before adding notes and new balances after adding notes, as well as adding a "Low Stock" check which displays a message to the user when the total balance of the stock drops below the limit, which was set at 1000.
Given more time I would wish to develop an Authentication method to limit what user has access to different methods. Another good feature would be adding more types of notes and coins to the machine, however this would need the algorithms to be heavily adjusted and would be a fairly time consuming change.