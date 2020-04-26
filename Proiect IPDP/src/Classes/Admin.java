package Classes;

public class Admin extends Person {

    public int Login_serial;
    public String adminID;
    public String password;
    private String mail_adress;

    public Admin(String firstName, String lastName, int age, int login_serial, String adminID, String password, String mail_adress) {
        super(firstName, lastName, age);
        Login_serial = login_serial;
        this.adminID = adminID;
        this.password = password;
        this.mail_adress = mail_adress;
    }

    public Admin() {

    }

    public String getMail_adress() {
        return mail_adress;
    }

    public void setMail_adress(String mail_adress) {
        this.mail_adress = mail_adress;
    }

}
