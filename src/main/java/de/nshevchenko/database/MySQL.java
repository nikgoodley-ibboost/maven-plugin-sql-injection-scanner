package de.nshevchenko.database;


public class MySQL implements Database{

    public boolean checkIfThisDatabase(){
        //changes in mysql 5.0 http://dev.mysql.com/doc/refman/5.0/en/news-5-0-x.html
        //changes in mysql 5.1 http://dev.mysql.com/doc/refman/5.1/en/mysql-nutshell.html
        //changes in mysql 5.5 http://dev.mysql.com/doc/refman/5.5/en/mysql-nutshell.html
        /* The new TO_SECONDS() function converts a date or datetime expression to a number of seconds since the year 0. This is a general-purpose function, but is useful for partitioning. You may use it in partitioning expressions, and partition pruning is supported for tables defined using such expressions. */

        //changes in 5.6 http://dev.mysql.com/doc/refman/5.6/en/mysql-nutshell.html
        /* in 5.6.4. SELECT MICROSECOND('2010-12-10 14:12:09.019473') possible */

        //information functions in mysql: http://dev.mysql.com/doc/refman/4.1/en/information-functions.html
        //check  " AND CONNECTION_ID()=CONNECTION_ID()"
        //Select {randomInt} FROM information_schema.TABLES LIMIT 0, 1
        //action=artikel&cat=21&id=58&artlang=de'AND ORD(MID((%s), %d, 1)) > %d AND 'OKSQO'='OKSQO


        return false;
    }
    
    public String getName(){
        return "MySql";
    }
}
