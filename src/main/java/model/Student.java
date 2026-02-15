package model;

public class Student {

    private String id;
    private String name;
    private int year;
    private String address;
    private String phone;
    private String email;
    private String className;

    public Student(String id, String name, int year,
                   String address, String phone,
                   String email, String className) {

        this.id = id;
        this.name = name;
        this.year = year;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
  
}
