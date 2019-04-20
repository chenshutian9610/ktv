package test;

/**
 * @author er_dong_chen
 * @date 2019/3/17
 */
public class User2 {
    private long id;
    private String name;

    public User2() {
    }

    public User2(long id, String name) {
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
