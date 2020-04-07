public class Login extends Person{
    int phone;
    char adress;
    char password;

    public Login(int ID, char firstName, char lastName, int phone, char adress, char password) {
        super(ID, firstName, lastName);
        this.phone = phone;
        this.adress = adress;
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public char getAdress() {
        return adress;
    }

    public void setAdress(char adress) {
        this.adress = adress;
    }

    public char getPassword() {
        return password;
    }

    public void setPassword(char password) {
        this.password = password;
    }

}
