# TheBankList
App shows richest user in data base and  the sum of all the accounts in the 
bank by pushing on appropriate button.

DB consists of two tables:
user
-----
userid INT
name VARCHAR(45)
surname VARCHAR(45)


account
--------
accountId INT
account INT
userid INT

Work with DB based on DAO and JDBC.
View developed on jsp.
Data delivers from DB to view with help Service and Servlet.