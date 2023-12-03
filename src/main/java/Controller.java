import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//
public class Controller {


    Member member = new Member();

    Database database = new Database();

    //TODO brug til noget?
    public ArrayList<Member> printMedlemmerStamoplysninger() {
       return database.getMeembers();
    }

    public void registrerMedlem(String name, String dateOfBirth, String gender, int phonenumber, String adress, int memberNumber, String passiveOrActive, String memberType, String motionist) {
        database.registrerMedlem(name, dateOfBirth, gender, phonenumber, adress, memberNumber, passiveOrActive, memberType, motionist);
    }


    public void sortMembersByAge(ArrayList<Member> members) {
        database.sortMembersByAge(members);
    }

    public int calculateAge(LocalDate date){
        return member.calculateAge(date);
    }

    public ArrayList<Member> getMembers() {
        return database.getMeembers();

    }

    public int calculateYearlyIncome(){
        return database.calculateYearlyIncome();
    }


    public int generateMemberNumber(){

        return member.generateMemberNumber();
    }


    //TODO brug til noget?
    public void someMethod() {
        List<Member> members = getMembers();


    }
}