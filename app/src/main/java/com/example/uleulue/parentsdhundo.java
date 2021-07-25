package com.example.uleulue;

import java.security.PrivateKey;

public class parentsdhundo {
    private String fullname;
    private String email;
    private String parentsname;
    private String phonenumber;
    private String studentname;
    private String usn,eemail,hhostelname,nname,pphonenumber,ssharing;

       public parentsdhundo()
       {}
       public parentsdhundo(String email,String parentsname,String phonenumber,String studentname,String usn,String fullname)
       {
           this.email=email;
           this.parentsname = parentsname;
           this.phonenumber = phonenumber;
           this.studentname = studentname;
           this.usn = usn;
           this.fullname = fullname;

       }

    public String getEmail() {
        return email;
    }

    public String getParentsname() {
        return parentsname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getStudentname() {
        return studentname;
    }

    public String getUsn() {
        return usn;
    }
    public String getFullname(){return fullname;}
}
