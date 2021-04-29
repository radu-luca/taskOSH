# Technical Interview challenge
 
### How to run my code :
1. Make sure you have a database named database.db at the root of this folder, as in the example above. The database must contain the table `test_table` which can be created by running the attached script `script.sql`.
If you have a local database at another address, run the script `script.sql` to create the required table and in the `Main.class` class change line 12 as follows:  &nbsp; `connection = DriverManager.getConnection("jdbc:sqlite:PATH");`, where `PATH` is your local path to database.

2. Open `pom.xml` and reload all maven projects.

3. Open `Main.class` and run method `main`.

4. Check the change log in logs.txt.

### How I solve the challenge :
First I used the maven framework to access the [sqlite Driver](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc), and to work efficiently with CSV files I used [OpenCSV](https://mvnrepository.com/artifact/com.opencsv/opencsv) .


After I got a connection to the database, I set auto commit on off because I will commit manually after all insert to increase the speed. Also I used only one prepared statement fro insert to increase the performance. 


I used the DAO pattern for a object-oriented model of the data managed. 


With the help of OpenCSV the process of reading and writing is much easy. In the process of reading, I read line by line and I receive a list of strings, one string per coloumn so if the length of list is different of 10 (number of coloumns in table), then it's a line I'm writing in `bat-data<>.csv`( in folder logs). Before inserting in `bad-data.csv` I check if exists a element who contains a comma, if exist then I double-quoted this element. Throughout this process I have a contour of successful and failed insertions in database and at the end I write in logs.txt the statistics.
