package com.example.uleulue;

import java.security.PrivateKey;

public class wardenDhundo {

    private String eemail,hhostelname,nname,pphonenumber,ssharing;

    public wardenDhundo()
    {}
    public wardenDhundo(String eemail,String hhostelname,String nname,String pphonenumber,String ssharing)
    {
       this.eemail=eemail;
       this.hhostelname=hhostelname;
       this.nname=nname;
       this.pphonenumber=pphonenumber;
       this.ssharing=ssharing;
    }

    public String getEemail() {
        return eemail;
    }

    public String getHhostelname() {
        return hhostelname;
    }

    public String getNname() {
        return nname;
    }

    public String getPphonenumber() {
        return pphonenumber;
    }

    public String getSsharing() {
        return ssharing;
    }

}
