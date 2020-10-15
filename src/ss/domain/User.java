package ss.domain;

public class User {
    private int id;
    private int uid;
    private String uname; //maxSize=16
    private int uage;
    private String ugender; //maxSize=5
    private String uadress; //maxSize=10
    private String uclass;  //maxSize=15

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public int getUid() {
        return uid;
    }

    public User setUid(int uid) {
        this.uid = uid;
        return this;
    }

    public String getUname() {
        return uname;
    }

    public User setUname(String uname) {
        this.uname = uname;
        return this;
    }

    public int getUage() {
        return uage;
    }

    public User setUage(int uage) {
        this.uage = uage;
        return this;
    }

    public String getUgender() {
        return ugender;
    }

    public User setUgender(String ugender) {
        this.ugender = ugender;
        return this;
    }

    public String getUadress() {
        return uadress;
    }

    public User setUadress(String uadress) {
        this.uadress = uadress;
        return this;
    }

    public String getUclass() {
        return uclass;
    }

    public User setUclass(String uclass) {
        this.uclass = uclass;
        return this;
    }
}
