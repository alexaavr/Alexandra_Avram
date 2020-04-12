public class Login {

    public String username;
    public String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean verifyLogin(Login this, Login log){
        boolean found = false;
        String tUsername = "Alexa";
        String tPassword = "alexa";
        if(this.username.equals(tUsername)){
            if(this.password.equals(tPassword)){
                found = true;
            }
        }

        return found;
    }
}
