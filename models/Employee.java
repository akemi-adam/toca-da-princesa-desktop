package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import enums.Table;

public class Employee extends Model
{
    private String name;

    private int age;

    private String cpf;

    private transient String password;

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
     * Returns the age property
     * 
     * @return String
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Defines the age property
     * 
     * @param age
     * 
     * @return void
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     * Returns the cpf property
     * 
     * @return String
     */
    public String getCpf()
    {
        return cpf;
    }

    /**
     * Defines the cpf property
     * 
     * @param cpf
     * 
     * @return void
     */
    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return this.password;
    }

    /**
     * Class constructor
     * 
     * @param id
     * @param name
     * @param age
     * @param cpf
     */
    public Employee(int id, String name, int age, String cpf, String password)
    {
        super(id);
        this.name = name;
        this.age = age;
        this.cpf = cpf;
        this.password = password;
    }
    
    /**
     * Creates a new model in the database
     * 
     * @param collumns
     * @param values
     */
    public static void create(String collumns, String values) throws SQLException
    {
        insert(Table.EMPLOYEE, collumns, values);
    }

    /**
     * Takes as parameter the result of a query and returns the rows of this query as objects in an ArrayList
     * 
     * @param result
     * @throws SQLException
     * 
     * @return ArrayList<Employee>
     */
    protected static ArrayList<Employee> getModels(ResultSet result) throws SQLException
    {
        ArrayList<Employee> employees = new ArrayList<Employee>();

        while (result.next())
        {
            employees.add(
                new Employee(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getInt("age"),
                    result.getString("cpf"),
                    result.getString("password")
                )
            );
        }

        return employees;
    }

    /**
     * Returns all rows of table
     * 
     * @return ArrayList<Employee>
     */
    public static ArrayList<Employee> all()
    {
        try {
            
            ResultSet result = query(Table.EMPLOYEE, "*");

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
    public static ArrayList<Employee> where(String condition)
    {
        try {
            
            ResultSet result = query(Table.EMPLOYEE, "*", condition);

            return getModels(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Find a employee by id
     * 
     * @param id
     * @return Employee
     */
    public static Employee find(int id)
    {
        try {

            ResultSet result = query(Table.EMPLOYEE, "*", "id = " + id);

            result.first();

            return new Employee(
                result.getInt("id"),
                result.getString("name"),
                result.getInt("age"),
                result.getString("cpf"),
                result.getString("password")
            );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void destroy()
    {
        try {
            delete(Table.EMPLOYEE, "id = " + getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(String updateSql)
    {
        try {
            update(Table.EMPLOYEE, updateSql, "id = " + getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }  
}
