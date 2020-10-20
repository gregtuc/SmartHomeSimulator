package models;

public class Person {
    private String name;
    private String location;

    public Person(){
        this.location = "";
    }
    public Person(String name, String location){
        this.name = name;
        this.location = location;
    }
}
