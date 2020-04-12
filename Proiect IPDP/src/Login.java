import org.bson.Document;

public class Login {

    public String username;
    public String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Login() {
    }

    public boolean verifyLogin(Document tempLogin){
        if(tempLogin.containsValue(this.username) && tempLogin.containsValue(this.password)) { return true; }
        else {return false;}
    }
}
