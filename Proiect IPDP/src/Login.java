import org.bson.Document;

public class Login {

    public boolean verifyLogin(Document tempLogin, String un, String ps){
        if(tempLogin.containsValue(un) && tempLogin.containsValue(ps)) { return true; }
        else {return false;}
    }
}
