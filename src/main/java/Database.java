import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Database {

    public ArrayList<Member> meembers = new ArrayList<>();

    public Database() {
        if (meembers.isEmpty()) {
            meembers = FileHandler.loadedMembers("MedlemsListe.csv");
        }
    }


    public void registrerMedlem(String name, String dateOfBirth, String gender, int phonenumber, String address, int memberNumber, String passiveOrActive, String memberType,String motionist, String competitive) {
        try {
           // meembers = FileHandler.loadedMembers("MedlemsListe.csv");
            Member member = new Member(name, dateOfBirth, gender, phonenumber, address, memberNumber, passiveOrActive, memberType, motionist, competitive);
            meembers.add(member);
            FileHandler.saveListOfMembersToFile("MedlemsListe.csv", meembers);
            System.out.println("Member successfully registered.");
        } catch (Exception e) {
            System.out.println("Error registering member: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public ArrayList<Member> getMeembers() {
        return meembers;
    }
    public void sortMembersByAge(List<Member> meembers) {
        meembers.sort(Comparator.comparingInt(Member::calculateAgeList));
    }

    /*public  updateMembership() {
        LocalDate today = LocalDate.now();
        return today.getYear();*/
        //Period ageDifference






}
