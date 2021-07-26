package com.example.uleulue;

public class acceptedrequestdhundo {

    private String address;
    private String entryDate;
    private String entryTime;
    private String exitDate;
    private String exitTime;
    private String name;
    private String status;
    private String status2;
    private String useridparents;
    private String useridstudents;

    public acceptedrequestdhundo()
    {}
    public acceptedrequestdhundo(String address,String entryDate,String entryTime,String exitDate,String exitTime,String name,String status,String status2,String useridparents,String useridstudents)
    {
        this.address=address;
        this.entryDate = entryDate;
        this.entryTime = entryTime;
        this.exitDate= exitDate;
        this.exitTime=exitTime;

        this.name = name;
        this.status = status;
        this.status2 = status2;
        this.useridparents = useridparents;
        this.useridstudents= useridstudents;



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

    public String getExitDate() {
        return exitDate;
    }

    public String getExitTime() {
        return exitTime;
    }
    public String getName(){return name;}
    public String getStatus() {
        return status;
    }

    public String getStatus2() {
        return status2;
    }

    public String getUseridparents() {
        return useridparents;
    }

    public String getUseridstudents() {
        return useridstudents;
    }

}

