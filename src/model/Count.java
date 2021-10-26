package model;

public class Count {

    private String name;

    private Integer value;

    private Integer count;

    private Boolean status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Count(String name, Integer value, Integer count, Boolean status) {
        this.name = name;
        this.value = value;
        this.count = count;
        this.status = status;
    }

    @Override
    public String toString() {
        return "[name = " + name + ", value =" + value + ", count =" + count + ", status = " +status +" ]";
    }
}
