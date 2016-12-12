package pl.pb.r.sm_sqlite.database;

/**
 * Created by Radosław Naruszewicz on 2016-11-30.
 */

public class Model {
    private long id;
    private String name;
    private double value;

    public Model() {
    }

    public Model(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if (!(obj instanceof Model)) {
            return false;
        }
        Model other = ((Model) obj);
        if(this.id != other.id){
            return false;
        }
        return true;
    }
}
