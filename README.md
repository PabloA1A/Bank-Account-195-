# Bank Account (*195)

## Develop a program that models a bank account that has the following attributes, which must be access protected:

Balance, of type float.
Number of deposits with zero initial value, of type int.
Number of withdrawals with zero initial value, of type int.
Annual rate (percentage), of type float.
Monthly commission with zero initial value, of type float.

## The Account class has a constructor that initializes the attributes balance and annual rate with values passed as parameters. The Account class has the following methods:
Deposit an amount of money in the account by updating its balance.
Withdraw an amount of money in the account by updating its balance. The value to withdraw must not exceed the balance.
Calculate the monthly interest of the account and update the corresponding balance.
Monthly statement: updates the balance by subtracting the monthly fee and calculating the corresponding monthly interest (invokes the previous method).
Print: returns the values of the attributes.

### The Account class has two child classes:

- Savings account: has an attribute to determine if the savings account is active (boolean type). If the balance is less than $10000, the account is inactive, otherwise it is considered active. The following methods are redefined:

Consign: money can be consigned if the account is active. You must invoke the inherited method.
Withdraw: it is possible to withdraw money if the account is active. You must invoke the inherited method.
Monthly statement: if the number of withdrawals is greater than 4, for each additional withdrawal, $1000 is charged as a monthly fee. When the statement is generated, it is determined whether the account is active or not with the balance.
A new printing method that returns the account balance, the monthly fee and the number of transactions made (sum of the number of deposits and withdrawals).


- Current account: has an overdraft attribute, which is initialized to zero. The following methods are redefined:

Withdraw: money is withdrawn from the account by updating its balance. It is possible to withdraw money in excess of the balance. The money owed remains as overdraft.
Deposit: invokes the inherited method. If there is an overdraft, the amount deposited reduces the overdraft.
Monthly statement: invokes the inherited method.
A new print method that returns the account balance, the monthly fee, the number of transactions performed (sum of amount of deposits and withdrawals) and the overdraft value.

## Requirements:

UML class diagram
Mandatory unit tests (minimum coverage 70%).

## Deliverables:

Github repository
Screenshot of the class diagram or public link to the file on diagrams.net
Screenshot of the testing section of VSCode showing that test coverage has been completed

### AUTHOR:
```sh
Pablo Abad
```

## My diagram with https://app.diagrams.net/:
![My diagram](<Diagrama UML Bank Account (*195).png>)

## Screenshot of the coverage test:
![Coverage test](<Captura de pantalla 2024-08-20 a las 8.44.07.png>)