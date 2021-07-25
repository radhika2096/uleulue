package com.example.uleulue;

public class requestdhundo {

    private String address, entryDate, entryTime, exitDate, exitTime, name;

    public requestdhundo() {
    }

    public requestdhundo(String address, String entryDate, String entryTime, String exitDate,String name, String exitTime) {
        this.address = address;
        this.entryDate = entryDate;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.exitDate = exitDate;
        this.name= name;
    }

    public String getAddress() {
        return address;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public String getExitDate() {
        return exitDate;
    }

    public String getName() {return name; }
}


