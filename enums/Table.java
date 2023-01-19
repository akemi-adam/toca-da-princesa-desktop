package enums;

public enum Table
{
    ANIMAL("animals"),
    VET("vets"),
    SCHEDULE("schedules"),
    EMPLOYEE("employees"),
    DOG("dogs"),
    CAT("cats");

    private String table;

    private Table(String table)
    {
        this.table = table;
    }

    public String getTable()
    {
        return this.table;
    }
}
