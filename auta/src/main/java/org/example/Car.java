package org.example;

import java.util.ArrayList;
class Car{
    public String uuid;
    public String model;
    public int rok;
    public ArrayList<Airbag> airbags;
    public String color;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Car{" +
                "uuid='" + uuid + '\'' +
                ", model='" + model + '\'' +
                ", rok=" + rok +
                ", airbags=" + airbags +
                ", color='" + color + '\'' +
                '}';
    }
}