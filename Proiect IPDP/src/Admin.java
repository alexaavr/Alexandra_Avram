public class Admin extends Person{

    int CNP;
    char Job;

    public Admin(int ID, char firstName, char lastName, int CNP, char job) {
        super(ID, firstName, lastName);
        this.CNP = CNP;
        Job = job;
    }

    public int getCNP() {
        return CNP;
    }

    public void setCNP(int CNP) {
        this.CNP = CNP;
    }

    public char getJob() {
        return Job;
    }

    public void setJob(char job) {
        Job = job;
    }

    public void addUser(){

    }

    public void deleteUser(){

    }

    public void modifyUser_rights(){

    }

    public void addItem(){

    }

    public void deleteItem(){

    }

    public void modifyItem(){

    }

}
