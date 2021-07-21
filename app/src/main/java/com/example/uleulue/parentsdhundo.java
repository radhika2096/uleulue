package com.example.uleulue;

public class parentsdhundo {
    private String email;
    private String parentsname;
    private String phonenumber;
    private String studentname;
    private String usn;
       public parentsdhundo()
       {}
       public parentsdhundo(String email,String parentsname,String phonenumber,String studentname,String usn)
       {
           this.email=email;
           this.parentsname = parentsname;
           this.phonenumber = phonenumber;
           this.studentname = studentname;
           this.usn = usn;
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
}
