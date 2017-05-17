package ua.nure.tur.entities;

import java.util.Date;

public class State {

    private int id;
    private int userId;
    private double x;
    private double y;
    private double temperature;
    private int breathing;
    private int hearth;
    private Date time;

    public State() {}
    public State(int id, int userId, double x, double y, double temperature, int breathing, int hearth, Date time) {
        this.id = id;
        this.userId = userId;
        this.x = x;
        this.y = y;
        this.temperature = temperature;
        this.breathing = breathing;
        this.hearth = hearth;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getBreathing() {
        return breathing;
    }

    public void setBreathing(int breathing) {
        this.breathing = breathing;
    }

    public int getHearth() {
        return hearth;
    }

    public void setHearth(int hearth) {
        this.hearth = hearth;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
