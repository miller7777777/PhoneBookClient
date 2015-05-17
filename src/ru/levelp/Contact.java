package ru.levelp;

/**
 * Created by vladimir on 14.04.15.
 */
/*
Модификаторы доступа:
public - к полю или методу объекта открыт доступ извне
private - запрещает получить доступ к полю или методу из другого класса
protected
 */
public class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public void print() {
        System.out.println(name + " " + phone + " " + email);
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
