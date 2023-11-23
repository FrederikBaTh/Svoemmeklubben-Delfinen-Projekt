import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {

    public ArrayList<Member> meembers = new ArrayList<>(150);
    private Scanner keyboard =new Scanner(System.in);
    public Database() {
        meembers = FileHandler.loadedMembers("MedlemsListe.csv");
    }

    public void registrerMedlem(String name, int dateOfBirth, String gender, int phonenumber, String adress, int memberNumber, String passiveOrActive, String motionist, String competitive) {
        meembers = FileHandler.loadedMembers("MedlemsListe.csv");
        Member member = new Member(name, dateOfBirth, gender, phonenumber, adress, memberNumber, passiveOrActive, motionist, competitive);
        meembers.add(member);
        FileHandler.saveListOfMembersToFile("MedlemsListe.csv", meembers);
    }





    public void printMedlemmerStamoplysninger(){
        meembers = FileHandler.loadedMembers("MedlemsListe.csv");
        if (meembers.isEmpty()){
            return;

        }

    }


    public ArrayList<Member> getMeembers() {
        return meembers;
    }
}
