package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import enums.Table;

public class Cat extends Animal
{
    private int blueEye;

    private int animalId;

    /**
     * Class constructor
     * 
     * @param id
     * @param name
     * @param sick
     * @param owner
     * @param blueEye
     * @param animalId
     */
    public Cat(int id, int blueEye, int animalId)
    {
        try {

            ResultSet result = query(Table.ANIMAL, "*", "id = " + animalId);

            result.first();

            this.setAnimalId(animalId);

            this.setName(result.getString("name"));

            this.setOwner(result.getString("owner"));

            this.setSick(result.getInt("sick"));

            this.setAge(result.getInt("age"));

            this.blueEye = blueEye;

            this.animalId = animalId;

            this.setId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the blue eye
     * 
     * @return int
     */
    public int getBlueEye()
    {
        return blueEye;
    }

    /**
     * Defines the blue eye
     * 
     * @param blueEye
     * @return void
     */
    public void setBlueEye(int blueEye)
    {
        this.blueEye = blueEye;
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
        ArrayList<Model> cats = new ArrayList<Model>();

        while (result.next())
        {
            cats.add(
                new Cat(
                    result.getInt("id"),
                    result.getInt("blue_eye"),
                    result.getInt("animal_id")
                )
            );
        }

        return cats;
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

            insert(Table.CAT, collumns + ", animal_id", values + ", " + result.getInt("id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Model> all()
    {
        try {
            
            ResultSet result = query(Table.CAT, "*");

            return getModels(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Model> where(String condition)
    {
        try {
            
            ResultSet result = query(Table.CAT, "*", condition);

            return getModels(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Cat find(int id)
    {
        try {

            ResultSet result = query(Table.CAT, "*", "id = " + id);

            result.first();

            return new Cat(
                result.getInt("id"),
                result.getInt("blue_eye"),
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

            delete(Table.CAT, "id = " + getId());

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
            update(Table.CAT, updateSql, "id = " + getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Nome: " + super.getName() + "\nOlhos azuis: " + this.blueEye;
    }
}
