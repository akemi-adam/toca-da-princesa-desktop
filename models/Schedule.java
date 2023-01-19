package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import enums.Table;
import helpers.ValidationHelper;

public class Schedule extends Model
{
    private Integer animal_id;

    private Integer employee_id;

    private Integer vet_id;

    private String entryDate;

    private String exitDate;

    public Schedule(Integer animal_id, Integer employee_id, Integer vet_id, String entryDate, String exitDate)
    {
        this.animal_id = animal_id;
        this.employee_id = employee_id;
        this.vet_id = vet_id;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
    }

    public Integer getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(Integer animal_id) {
        this.animal_id = animal_id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Integer getVet_id() {
        return vet_id;
    }

    public void setVet_id(Integer vet_id) {
        this.vet_id = vet_id;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getExitDate() {
        return exitDate;
    }

    public void setExitDate(String exitDate) {
        this.exitDate = exitDate;
    }

    public static void create(String collumns, String values)
    {
        try {
            insert(Table.SCHEDULE, collumns, values);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static ArrayList<Model> getModels(ResultSet result) throws SQLException
    {
        ArrayList<Model> schudeles = new ArrayList<Model>();

        while (result.next())
        {
            schudeles.add(
                new Schedule(result.getInt("animal_id"), result.getInt("employee_id"), result.getInt("vet_id"), result.getString("entry_date"), result.getString("exit_date"))
            );
        }

        return schudeles;
    }

    public static ArrayList<Model> all()
    {
        try {
            
            ResultSet result = query(Table.SCHEDULE, "*");

            return getModels(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void destroy() { }

    @Override
    public void edit(String updateSql)
    {
        try {
            update(Table.SCHEDULE, updateSql, String.format("animal_id = %s and employee_id = %s", this.animal_id, this.employee_id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString()
    {
        Vet vet = Vet.find(this.getVet_id());

        Animal animal = Animal.find(this.getAnimal_id());

        return String.format(
            "Uma consulta com o profissional %s, visando atender a princesa %s, foi marcada para às %s %s",
            vet.getName(),
            animal.getName(),
            ValidationHelper.getDateFormat(this.entryDate),
            (this.exitDate == null ? "e ainda não foi concluída." : "e foi concluída às " + ValidationHelper.getDateFormat(this.exitDate))
        );
    }

}
