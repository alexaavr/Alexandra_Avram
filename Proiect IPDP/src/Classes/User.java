package Classes;

public class User extends Person {

    public String username;
    public String password;
    private String mail_adress;

    public User(String firstName, String lastName, int age, String username, String password, String mail_adress) {
        super(firstName, lastName, age);
        this.username = username;
        this.password = password;
        this.mail_adress = mail_adress;
    }

    public User(String username) {
        this.username = username;
    }

    public User() {
        super();
    }

    public String getMail_adress() {
        return mail_adress;
    }

    public void setMail_adress(String mail_adress) {
        this.mail_adress = mail_adress;
    }

    @Override
    public String toString() {
        return "User{ \n" +
                "Firstname = " + getFirstName() + "\n" +
                "Lastname = " + getLastName() + "\n" +
                "Username = " + username + '\n' +
                "Password = " + password + '\n' +
                "Mail address = " + mail_adress + '\n' +
                "Age = " + getAge() + "\n" +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
