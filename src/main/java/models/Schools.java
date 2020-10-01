package models;

public class Schools {

    private int id;
    private String schoolname;
    private String schooladdress;
    private String schoolwebsite;
    private String schoolemail;
    private String schoolphone;

    public Schools(String schoolname, String schooladdress, String schoolwebsite, String schoolemail, String schoolphone) {
        this.schoolname = schoolname;
        this.schooladdress = schooladdress;
        this.schoolwebsite = schoolwebsite;
        this.schoolemail = schoolemail;
        this.schoolphone = schoolphone;
    }

    public Schools(String schoolname, String schooladdress, String schoolphone) {
        this.schoolname = schoolname;
        this.schooladdress = schooladdress;
        this.schoolwebsite = "no website";
        this.schoolemail = "no email";
        this.schoolphone = schoolphone;

    }

    public String getSchoolName() {
        return schoolname;
    }

    public void setSchoolName(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getSchoolAddress() {
        return schooladdress;
    }

    public void setSchoolAddress(String schooladdress) {
        this.schooladdress = schooladdress;
    }

    public String getSchoolWebsite() {
        return schoolwebsite;
    }

    public void setSchoolWebsite(String schoolwebsite) {
        this.schoolwebsite = schoolwebsite;
    }

    public String getSchoolEmail() {
        return schoolemail;
    }

    public void setSchoolEmail(String schoolemail) {
        this.schoolemail = schoolemail;
    }

    public String getSchoolPhoneNumber() {
        return schoolphone;
    }

    public void setSchoolPhoneNumber(String schoolphone) {
        this.schoolphone = schoolphone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
