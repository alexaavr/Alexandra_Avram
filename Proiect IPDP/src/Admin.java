public class Admin extends Person{

    public int CNP;
    public String Job;

    public Admin(int ID, String firstName, String lastName, int CNP, String job) {
        super(ID, firstName, lastName);
        this.CNP = CNP;
        Job = job;
    }
}
