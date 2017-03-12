package com.tyqtest.xmldata;

/**
 * Created by 谭雅清 on 2017/3/12.
 */

public class ContactEntity {
    private String name;

    private String number;

    public ContactEntity(String name, String number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "ContactEntity{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
