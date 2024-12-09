package fit_zone.entity;

import java.util.Objects;

public class Client {
    private int id;
    private String name;
    private String surName;
    private int member;

    public Client() {
    }

    public Client(int id) {
        this.id = id;
    }

    public Client(String name, String surName, int memberNumber) {
        this.name = name;
        this.surName = surName;
        this.member = memberNumber;
    }

    public Client(String name, int id, String surName, int memberNumber) {
        this.name = name;
        this.id = id;
        this.surName = surName;
        this.member = memberNumber;
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

    public int getMember() {
        return member;
    }

    public void setMember(int member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Client[ " +
                "[id]=" + id +
                ", [name]=" + name +
                ", [surName]=" + surName +
                ", [member]=" + member;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && member == client.member && Objects.equals(name, client.name) && Objects.equals(surName, client.surName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surName, member);
    }
}
