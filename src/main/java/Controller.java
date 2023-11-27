import java.util.List;

//
public class Controller {

    Database database = new Database();


    public void printMedlemmerStamoplysninger() {
        database.printMedlemmerStamoplysninger();
    }

    public void registrerMedlem(String name, String dateOfBirth, String gender, int phonenumber, String adress, int memberNumber, String passiveOrActive, String motionist, String competitive) {
        database.registrerMedlem(name, dateOfBirth, gender, phonenumber, adress, memberNumber, passiveOrActive, motionist, competitive);
    }

    public void sortMembersByAge(List<Member> members) {
        database.sortMembersByAge((List<Member>) member);
    }


    public List<Member> getMembers() {
        return database.getMeembers();

    }

    public void someMethod() {
        List<Member> members = getMembers();


    }
}