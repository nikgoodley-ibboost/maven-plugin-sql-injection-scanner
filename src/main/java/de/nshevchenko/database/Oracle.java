package de.nshevchenko.database;


public class Oracle implements Database{

    //select count (id) from reviews where review_author='MadBob'
    // and
    // select count(id) from reviews where review_author='MadB'||'ob'
    // in oracle would produce the same result!
    //pipe is used for concatenation in oracle!

    //check from oracle 10, not before!
    public boolean checkIfThisDatabase(){
        return false;
    }

    public String getName(){
        return "Oracle";
    }
}
