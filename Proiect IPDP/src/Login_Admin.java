import org.bson.Document;

public class Login_Admin {

    Admin a = new Admin();
    public boolean verifyLogin(Document tempLogin){
        if(tempLogin.containsValue(a.adminID) && tempLogin.containsValue(a.password)) { return true; }
        else {return false;}
    }
}
