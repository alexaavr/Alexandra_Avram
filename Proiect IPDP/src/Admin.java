public class Admin extends Person{

    public String adminID;
    public String password;
    private String mail_adress;

    public Admin(String firstName, String lastName, int age, String adminID, String password, String mail_adress) {
        super(firstName, lastName, age);
        this.adminID = adminID;
        this.password = password;
        this.mail_adress = mail_adress;
    }

    public Admin() {

    }
}
