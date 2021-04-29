# Technical Interview challenge
 
### How to run my code :
1. Make sure you have a database named database.db at the root of this folder, as in the example above. The database must contain the table `test_table` which can be created by running the attached script `script.sql`.
If you have a local database at another address, run the script `script.sql` to create the required table and in the ``Main.class`` class change line 12 as follows:

`connection = DriverManager.getConnection("jdbc:sqlite:PATH");`, where PATH is your local path to database including the name of the  database.
