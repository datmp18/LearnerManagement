package model;

import java.util.List;

public class Subject {

    private String id;
    private String name;
    private int credits;

    public static final Subject LAP_TRINH_JAVA = new Subject("CTK3", "Lap trinh Java", 2);

    public static final Subject CO_SO_DU_LIEU = new Subject("CTK1", "Co so du lieu", 1);

    public static final Subject CAU_TRUC_DU_LIEU = new Subject("CTK2", "Cau truc du lieu", 3);

    public static final Subject MANG_MAY_TINH = new Subject("CTK4", "Mang may tinh", 2);

    public static final Subject HE_DIEU_HANH = new Subject("CTK5", "He dieu hanh", 2);

    public static List<Subject> values() {
        return List.of(
                LAP_TRINH_JAVA,
                CO_SO_DU_LIEU,
                CAU_TRUC_DU_LIEU,
                MANG_MAY_TINH,
                HE_DIEU_HANH);
    }

    public Subject(String id, String name, int credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

}
