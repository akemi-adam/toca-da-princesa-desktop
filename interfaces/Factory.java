package interfaces;

public interface Factory
{
    /**
     * Deletes the respective model from the database
     * 
     * @return void
     */
    public abstract void destroy();

    /**
     * Updates the respective database model
     * 
     * @param updateSql
     * 
     * @return void
     */
    public abstract void edit(String updateSql);
}
