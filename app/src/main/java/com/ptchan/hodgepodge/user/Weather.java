package com.ptchan.hodgepodge.user;

/**
 * Created by chentao on 16-8-27.
 */
public class Weather {
    private int id;
    private String name;
    private String temp;
    private String pm;

    public Weather(int id, String name, String temp, String pm) {
        this.id = id;
        this.name = name;
        this.temp = temp;
        this.pm = pm;
    }

    public Weather() {
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

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }
}
