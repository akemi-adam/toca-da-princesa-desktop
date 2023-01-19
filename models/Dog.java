package models;

import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;

import enums.Table;

public class Dog extends Animal
{
    private int pedigree;

    private int animalId;

    /**
     * Class constructor
     * 
     * @param id
     * @param name
     * @param sick
     * @param owner
     * @param pedigree
     * @param animalId
     */
    public Dog(int id, int pedigree, int animalId)
    {
        try {

            ResultSet result = query(Table.ANIMAL, "*", "id = " + animalId);

            result.first();

            this.setAnimalId(animalId);

            this.setName(result.getString("name"));

            this.setOwner(result.getString("owner"));

            this.setSick(result.getInt("sick"));

            this.setAge(result.getInt("age"));

            this.pedigree = pedigree;

            this.animalId = animalId;

            this.setId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the pedigree
     * 
     * @return int
     */
    public int getPedigree()
    {
        return pedigree;
    }

    /**
     * Defines the pedigree
     * 
     * @param pedigree
     */
    public void setPedigree(int pedigree)
    {
        this.pedigree = pedigree;
    }

    /**
     * Returns the animal id
     * 
     * @return int
     */
    public int getAnimalId()
    {
        return animalId;
    }

    /**
     * Defines the animal id
     * 
     * @param animalId
     * @return void
     */
    public void setAnimalId(int animalId)
    {
        this.animalId = animalId;
    }

    protected static ArrayList<Model> getModels(ResultSet result) throws SQLException
    {
        ArrayList<Model> dogs = new ArrayList<Model>();

        while (result.next())
        {
            dogs.add(
                new Dog(
                    result.getInt("id"),
                    result.getInt("pedrigree"),
                    result.getInt("animal_id")
                )
            );
        }

        return dogs;
    }

    /**
     * Creates a new model in the database
     * 
     * @param collumns
     * @param values
     * @return void
     */
    public static void create(String animalCollumns, String animalValues, String collumns, String values)
    {
        try {

            insert(Table.ANIMAL, animalCollumns, animalValues);

            ResultSet result = Animal.orderBy(Table.ANIMAL, "*", "animals.id desc");

            result.first();

            insert(Table.DOG, collumns + ", animal_id", values + ", " + result.getInt("id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Model> all()
    {
        try {
            
            ResultSet result = query(Table.DOG, "*");

            return getModels(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Model> where(String condition)
    {
        try {
            
            ResultSet result = query(Table.DOG, "*", condition);

            return getModels(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Dog find(int id)
    {
        try {

            ResultSet result = query(Table.DOG, "*", "id = " + id);

            result.first();

            return new Dog(
                result.getInt("id"),
                result.getInt("pedrigree"),
                result.getInt("animal_id")
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

            delete(Table.DOG, "id = " + getId());

            delete(Table.ANIMAL, "id = " + getAnimalId());

            delete(Table.SCHEDULE, "animal_id = " + getAnimalId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(String updateSql)
    {
        try {
            update(Table.DOG, updateSql, "id = " + getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
