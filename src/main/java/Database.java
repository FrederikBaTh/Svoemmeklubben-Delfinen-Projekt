import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {

    public ArrayList<Member> meembers = new ArrayList<>();
    private Scanner keyboard =new Scanner(System.in);
    public Database() {
        if (meembers.isEmpty()) {
            meembers = FileHandler.loadedMembers("MedlemsListe.csv");
        }
    }


    public void registrerMedlem(String name, String dateOfBirth, String gender, int phonenumber, String adress, int memberNumber, String passiveOrActive, String motionist, String competitive) {
        meembers = FileHandler.loadedMembers("MedlemsListe.csv");
        Member member = new Member(name, dateOfBirth, gender, phonenumber, adress, memberNumber, passiveOrActive, motionist, competitive);
        meembers.add(member);
        FileHandler.saveListOfMembersToFile("MedlemsListe.csv", meembers);
    }


    public void printMedlemmerStamoplysninger(){
        meembers = FileHandler.loadedMembers("MedlemsListe.csv");

        for (Member member : meembers) {
            System.out.println(member);
            System.out.println();
        }

    }

    public ArrayList<Member> getMeembers() {
        return meembers;
    }

    public void updateMembership(){
        LocalDate today = LocalDate.now();
        //Period ageDifference

    }





}
