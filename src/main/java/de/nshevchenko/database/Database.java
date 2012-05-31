package de.nshevchenko.database;

/**
 * Created by IntelliJ IDEA.
 * User: nshevchenko
 * Date: 23.05.12
 * Time: 18:53
 * To change this template use File | Settings | File Templates.
 */
public interface Database {
    public boolean checkIfThisDatabase();
    
    public String getName();
}
