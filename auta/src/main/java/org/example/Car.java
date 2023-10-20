package org.example;

import java.util.ArrayList;

class Car{
    private String model;
    private int rok;
    private ArrayList<Car> poduszki;
    private String kolor;

    public Car(String model, int rok, ArrayList<Car> poduszki, String kolor) {
        this.model = model;
        this.rok = rok;
        this.poduszki = poduszki;
        this.kolor = kolor;
    }
}