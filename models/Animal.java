package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import enums.Table;

public class Animal extends Model
{
    private String name;

    private int sick;

    private String owner;

    private int age;

    public Animal() {}

    public Animal(String name, int sick, String owner, int age, int id)
    {
        this.name = name;

        this.sick = sick;

        this.owner = owner;

        this.age = age;

        setId(id);
    }

    /**
     * Returns the name
     * 
     * @return String
     */
    public String getName()
    {
        return name;
    }

    /**
     * Defines the name
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
     * Returns the sick
     * 
     * @return int
     */
    public int getSick()
    {
        return sick;
    }

    /**
     * Defines the sick
     * 
     * @param sick
     * 
     * @return void
     */
    public void setSick(int sick)
    {
        this.sick = sick;
    }

    /**
     * Returns the owner
     * 
     * @return String
     */
    public String getOwner()
    {
        return owner;
    }

    /**
     * Defines the owner
     * 
     * @param owner
     * 
     * @return void
     */
    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    /**
     * Returns the age
     * 
     * @return String
     */
    public int getAge()
    {
        return this.age;
    }
    
    /**
     * Defines the age
     * 
     * @param age
     * 
     * @return void
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    public static ArrayList<Model> all()
    {
        try {

            ArrayList<Model> animals = new ArrayList<Model>();

            ResultSet result = query(Table.ANIMAL, "*");
    
            while (result.next())
            {
                animals.add(
                    new Animal(
                        result.getString("name"),
                        result.getInt("sick"),
                        result.getString("owner"),
                        result.getInt("age"),
                        result.getInt("id")
                    )
                );
            }

            return animals;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Animal find(int id)
    {
        try {
            
            ResultSet result = query(Table.ANIMAL, "*", "id = " + id);

            result.first();

            return new Animal(
                result.getString("name"),
                result.getInt("sick"),
                result.getString("owner"),
                result.getInt("age"),
                result.getInt("id")
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void destroy()
    {
        try {
            delete(Table.ANIMAL, "id = " + getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(String updateSql)
    {
        try {
            update(Table.ANIMAL, updateSql, "id = " + getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
