package model;

import java.util.Timer;

public class Count {

    private String name;

    private Long updatedTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Count(String name, Long updatedTime) {
        this.name = name;
        this.updatedTime = updatedTime;
    }
}
