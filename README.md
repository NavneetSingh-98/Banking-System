# Banking System (Java OOP Console Project)

A console-based banking application that demonstrates core OOP concepts.

## OOP concepts used
| Concept | Where |
|---|---|
| Encapsulation | Private fields + getters in `Customer`, `Account` |
| Abstraction | `abstract class Account` (abstract `canWithdraw`, `getAccountType`) |
| Inheritance | `SavingsAccount`, `CurrentAccount` extend `Account` |
| Polymorphism | Each account type overrides withdrawal rules; `Bank` works with `Account` references |
| Interface | `InterestBearing` implemented by `SavingsAccount` |
| Composition | `Bank` has customers and accounts; `Account` has `Transaction`s |
| Custom exception | `InsufficientFundsException` |
| Enum | `TransactionType` |

## Features
Add customers, open Savings/Current accounts, deposit, withdraw, transfer, statement, monthly interest.
Savings keeps a minimum balance of 500; Current allows an overdraft up to 10,000.

## Run
```bash
cd BankingSystem
mkdir out
javac -d out $(find src -name "*.java")      # Windows: javac -d out src\com\bank\*.java src\com\bank\model\*.java src\com\bank\exception\*.java src\com\bank\service\*.java
java -cp out com.bank.Main
```
