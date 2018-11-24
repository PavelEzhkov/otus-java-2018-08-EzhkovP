package DataSet;

public abstract class DataSet {
    private long id;

    public DataSet() {
    }

    public DataSet(final long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
