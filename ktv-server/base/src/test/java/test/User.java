package test;

import java.io.Serializable;

/**
 * @author er_dong_chen
 * @date 2019/3/17
 */
public class User implements Serializable {
    long id;
    String name;
    public User() {
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
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
}
