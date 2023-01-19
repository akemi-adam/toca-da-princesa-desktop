package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import enums.Table;

public class Vet extends Model
{
    private String name;

    private String specialty;

    /**
     * Returns the name property
     * 
     * @return String
     */
    public String getName()
    {
        return name;
    }

    /**
     * Defines the name property
     * 
     * @param name
     * 
     * @return void
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the specialty property
     * 
     * @return String
     */
    public String getSpecialty()
    {
        return specialty;
    }

    /**
     * Defines the specialty property
     * 
     * @param specialty
     * 
     * @return void
     */
    public void setSpecialty(String specialty)
    {
        this.specialty = specialty;
    }

    /**
     * Class constructor
     * 
     * @param name
     * @param specialize
     * @param id
     */
    public Vet(String name, String specialty, int id)
    {
        super(id);
        this.name = name;
        this.specialty = specialty;
    }

    /**
     * Creates a new model in the database
     * 
     * @param collumns
     * @param values
     */
    public static void create(String collumns, String values)
    {
        try {
            insert(Table.VET, collumns, values);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes as parameter the result of a query and returns the rows of this query as objects in an ArrayList
     * 
     * @param result
     * @throws SQLException
     * 
     * @return ArrayList<Vet>
     */
    protected static ArrayList<Model> getModels(ResultSet result) throws SQLException
    {
        ArrayList<Model> vets = new ArrayList<Model>();

        while (result.next())
        {
            vets.add(
                new Vet(
                    result.getString("name"), result.getString("specialty"), result.getInt("id")
                )
            );
        }

        return vets;
    }

    /**
     * Returns all rows of table
     * 
     * @return
     */
    public static ArrayList<Model> all()
    {
        try {
            
            ResultSet result = query(Table.VET, "*");

            return getModels(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Returns rows based on conditions
     * 
     * @param condition
     * 
     * @return ArrayList<Vet>
     */
    public static ArrayList<Model> where(String condition)
    {
        try {
            
            ResultSet result = query(Table.VET, "*", condition);

            return getModels(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Find a specific row
     * 
     * @param id
     * @return Vet
     */
    public static Vet find(int id)
    {
        try {
            
            ResultSet result = query(Table.VET, "*", "id = " + id);

            result.first();

            return new Vet(
                result.getString("name"), result.getString("specialty"), result.getInt("id")
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Deletes data from the database based on a condition
     * 
     * @param condition
     * 
     * @return void
     */
    public static void delete(String condition)
    {
        try {
            delete(Table.VET, condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates data from the database based on a condition
     * 
     * @param updateSql
     * @param condition
     * 
     * @return void
     */
    public static void update(String updateSql, String condition)
    {
        try {
            update(Table.VET, updateSql, condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy()
    {
        delete("id = " + getId());
    }

    @Override
    public void edit(String updateSql)
    {
        update(updateSql, "id = " + getId());
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
