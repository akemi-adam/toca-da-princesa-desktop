package models;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Database;

import enums.Table;

import interfaces.Factory;

public abstract class Model implements Factory, Serializable
{
    protected static Connection connection = Database.getConnection();

    private int id;

    /**
     * Constructor of Model class
     */
    public Model() {};

    /**
     * Super constructor
     * 
     * @param id | Database id
     */
    public Model(int id)
    {
        this.id = id;
    }

    /**
     * Returns the id
     * 
     * @return int
     */
    public int getId()
    {
        return id;
    }

    /**
     * Defines the id
     * 
     * @param id | Database id
     * 
     * @return void
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Insert data into the table
     * 
     * @param table | Table name
     * @param collumns | Table collumns
     * @param values | Insert values
     * @throws SQLException
     * 
     * @return void
     */
    protected static void insert(Table table, String collumns, String values) throws SQLException
    {
        Statement stmt = connection.createStatement();

        stmt.executeUpdate(
            String.format("insert into %s (%s) values (%s)", table.getTable(), collumns, values)
        );
    }

    /**
     * Makes a query to the database
     * 
     * @param table | Table name
     * @param collumns | Table collumns
     * @throws SQLException
     * 
     * @return ResultSet
     */
    protected static ResultSet query(Table table, String collumns) throws SQLException
    {
        Statement stmt = connection.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE
        );
        
        return stmt.executeQuery(
            String.format("select %s from %s", collumns, table.getTable())
        );
    }

    /**
     * Makes a query to the database with conditions
     * 
     * @param table | Table name
     * @param collumns | Table collumns
     * @param condition | Query conditions
     * @throws SQLException
     * 
     * @return ResultSet
     */
    protected static ResultSet query(Table table, String collumns, String condition) throws SQLException
    {
        Statement stmt = connection.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE
        );
 
        return stmt.executeQuery(
            String.format("select %s from %s where %s", collumns, table.getTable(), condition)
        );
    }

    protected static ResultSet orderBy(Table table, String collumns, String order) throws SQLException
    {
        Statement stmt = connection.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE
        );
        
        return stmt.executeQuery(
            String.format("select %s from %s order by %s", collumns, table.getTable(), order)
        );
    }

    /**
     * Updates data in the database
     * 
     * @param table | Table name
     * @param updateSql | Columns that will be updated and their respective values
     * @param condition | Update conditions
     * @throws SQLException
     */
    protected static void update(Table table, String updateSql, String condition) throws SQLException
    {
        Statement stmt = connection.createStatement();

        stmt.executeUpdate(
            String.format("update %s set %s where %s", table.getTable(), updateSql, condition)
        );
    }

    /**
     * Delete a row from table
     * 
     * @param table | Table name
     * @param condition | Delete condition
     * @throws SQLException
     * 
     * @return void
     */
    protected static void delete(Table table, String condition) throws SQLException
    {
        Statement stmt = connection.createStatement();

        stmt.executeUpdate(
            String.format("delete from %s where %s", table.getTable(), condition)
        );
    }
}
