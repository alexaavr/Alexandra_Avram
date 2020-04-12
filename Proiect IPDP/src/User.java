public class User extends Person{

    public String username;
    public String password;
    private String mail_adress;

    public User(String firstName, String lastName, int age, String username, String password, String mail_adress) {
        super(firstName, lastName, age);
        this.username = username;
        this.password = password;
        this.mail_adress = mail_adress;
    }

    public User() {
        super();
    }
}
