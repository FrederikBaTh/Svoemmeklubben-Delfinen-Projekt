import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Database {

    public ArrayList<Member> meembers = new ArrayList<>();
    private Scanner keyboard = new Scanner(System.in);

    public Database() {
        if (meembers.isEmpty()) {
            meembers = FileHandler.loadedMembers("MedlemsListe.csv");
        }
    }


    public void registrerMedlem(String name, String dateOfBirth, String gender, int phonenumber, String address, int memberNumber, String passiveOrActive, String motionist, String competitive) {
        try {
            meembers = FileHandler.loadedMembers("MedlemsListe.csv");
            Member member = new Member(name, dateOfBirth, gender, phonenumber, address, memberNumber, passiveOrActive, motionist, competitive);
            meembers.add(member);
            FileHandler.saveListOfMembersToFile("MedlemsListe.csv", meembers);
            System.out.println("Member successfully registered.");
        } catch (Exception e) {
            System.out.println("Error registering member: " + e.getMessage());
            e.printStackTrace();  // Print the stack trace for debugging
        }
    }


    public void printMedlemmerStamoplysninger() {
        meembers = FileHandler.loadedMembers("MedlemsListe.csv");
        if (meembers.isEmpty()){
            System.out.println("Ingen medlemmer oprettet");
        }

        for (Member member : meembers) {
            System.out.println(member);
            System.out.println();
        }

    }

    public ArrayList<Member> getMeembers() {
        return meembers;
    }
    public void sortMembersByAge(List<Member> meembers) {
        meembers.sort(Comparator.comparingInt(Member::calculateAge));
    }

    /*public  updateMembership() {
        LocalDate today = LocalDate.now();
        //Period ageDifference

    }


}
