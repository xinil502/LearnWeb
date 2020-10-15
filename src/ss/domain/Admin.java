package ss.domain;

public class Admin {
    private int aid;
    private String auser;      //maxSize=16
    private String apassword;  //maxSize=16

    @Override
    public String toString() {
        return "Admin{" +
                "aid=" + aid +
                ", auser='" + auser + '\'' +
                ", apassword='" + apassword + '\'' +
                '}';
    }

    public int getAid() {
        return aid;
    }

    public Admin setAid(int aid) {
        this.aid = aid;
        return this;
    }

    public String getAuser() {
        return auser;
    }

    public Admin setAuser(String auser) {
        this.auser = auser;
        return this;
    }

    public String getApassword() {
        return apassword;
    }

    public Admin setApassword(String apassword) {
        this.apassword = apassword;
        return this;
    }

}
