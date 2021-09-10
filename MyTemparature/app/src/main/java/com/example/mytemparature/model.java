package com.example.mytemparature;

public class model {
    String name,email,temperature,phone,time;

    public model() {
    }

    public model(String name, String email, String temperature, String phone, String time) {
        this.name = name;
        this.email = email;
        this.temperature = temperature;
        this.phone = phone;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
