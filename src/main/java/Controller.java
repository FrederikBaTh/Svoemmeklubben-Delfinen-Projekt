import java.time.LocalDate;
import java.util.List;

//
public class Controller {


    Member member = new Member();

    Database database = new Database();


    public void printMedlemmerStamoplysninger() {
        database.printMedlemmerStamoplysninger();
    }

    public void registrerMedlem(String name, String dateOfBirth, String gender, int phonenumber, String adress, int memberNumber, String passiveOrActive, String memberType, String motionist, String competitive) {
        database.registrerMedlem(name, dateOfBirth, gender, phonenumber, adress, memberNumber, passiveOrActive, memberType, motionist, competitive);
    }

    public void exit() {
        database.scannerExit();
    }

    public void sortMembersByAge(List<Member> members) {
        database.sortMembersByAge(members);
    }

    public int calculateAge(LocalDate date){
        return member.calculateAge(date);
    }

    public List<Member> getMembers() {
        return database.getMeembers();

    }

    public void someMethod() {
        List<Member> members = getMembers();


    }
}