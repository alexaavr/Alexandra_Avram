public class Person {
    int ID;
    char firstName;
    char lastName;

    public Person(int ID, char firstName, char lastName) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public char getFirstName() {
        return firstName;
    }

    public void setFirstName(char firstName) {
        this.firstName = firstName;
    }

    public char getLastName() {
        return lastName;
    }

    public void setLastName(char lastName) {
        this.lastName = lastName;
    }

    public void verify_stock(){

    }

}
