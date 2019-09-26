# ME Bank Coding Challenge

## Running from the jar
java -jar libs\challenge-1.0-SNAPSHOT.jar

## Running through gradle
gradlew run --console=plain


## Scenario As a user I enter the following input :
    accountId: ACC334455
    from: 20/10/2018 12:00:00
    to: 20/10/2018 19:00:00

## Acceptance Criteria  :
	The output should be:
	Relative balance for the period is: -$25.00
	Number of transactions included is: 1 


Given this CSV input :
```csv
transactionId, fromAccountId, toAccountId, createdAt, amount, transactionType, relatedTransaction
TX10001, ACC334455, ACC778899, 20/10/2018 12:47:55, 25.00, PAYMENT
TX10002, ACC334455, ACC998877, 20/10/2018 17:33:43, 10.50, PAYMENT
TX10003, ACC998877, ACC778899, 20/10/2018 18:00:00, 5.00, PAYMENT
TX10004, ACC334455, ACC998877, 20/10/2018 19:45:00, 10.50, REVERSAL, TX10002
TX10005, ACC334455, ACC778899, 21/10/2018 09:30:00, 7.25, PAYMENT
```


## Assumptions
1. Account relative balance means the end result of processing ALL transactions for the time period entered, not just the accountId in question.
2. Reversals are processed first, which removes TX10002 from the list.  it is never processed.
3. It is possible to have two unrelated transactions act as third party to an account relative balance, ie. The time frame must  process all accounts, regardless of the user selected account.
4. The Number of transactions included reported is adjusted to list the number of transactions matching either the *to* or *from* account selected by the user.

## Technical note
It may be slightly over complicating processing, however I believe for this fairly simple system to perform with relative consistency, it
needs to process all payments, regardless of the accounts, in specified date range period, for the reflected account to have balance integrity.

That is unreleated accounts to what the user has selected, can and will given multiple payments, have direct result on the selected account.


Impacted account balances 3, transactions processed 2, reported number of transactions 1  :

    [Account{accountId='ACC334455', balance=-25.00}, 
     Account{accountId='ACC778899', balance=30.00}, 
     Account{accountId='ACC998877', balance=-5.00}]

The **Acceptance Criteria** indicates the output should say **Number of transactions included is: 1**.
I am deducing that this number is the number of transactions impacting ACC334455 in the scenerio provided, out of possibly many
more transactions processed?


