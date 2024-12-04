package fit_zone.entity;

public class Client {
    private int id;
    private String name;
    private String surName;
    private int memberNumber;

    public Client() {
    }

    public Client(int id) {
        this.id = id;
    }

    public Client(String name, String surName, int memberNumber) {
        this.name = name;
        this.surName = surName;
        this.memberNumber = memberNumber;
    }

    public Client(String name, int id, String surName, int memberNumber) {
        this.name = name;
        this.id = id;
        this.surName = surName;
        this.memberNumber = memberNumber;
    }

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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }

}
