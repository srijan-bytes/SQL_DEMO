package com.example.sqldatabasedemo;
public class CustomerModel {
    private int id;
    private String name;
    private boolean isActive;
    private int age;

    //constructors
    public CustomerModel(int id, String name, boolean isActive, int age) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.age = age;
    }

    public CustomerModel() {
    }
    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    //to_String

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                ", age=" + age +
                '}';
    }
}