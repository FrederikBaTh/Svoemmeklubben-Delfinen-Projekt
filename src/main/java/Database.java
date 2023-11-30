import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Database {

    private ArrayList<Member> meembers = new ArrayList<>();

    private ArrayList<CompetitiveMember> trææning = new ArrayList<>();


    private FileHandler fileHandler = new FileHandler("MedlemsListe.csv");

    public Database() {
        if (meembers.isEmpty()) {
            meembers = fileHandler.loadedMembers();
        }
       // if (trææning.isEmpty()){
       //     trææning = fileHandler.loadedCompetitiveMember();
       // }
    }


    public void registrerMedlem(String name, String dateOfBirth, String gender, int phonenumber, String address, int memberNumber, String passiveOrActive, String memberType, String motionist, String competitive) {
        try {
            // meembers = FileHandler.loadedMembers("MedlemsListe.csv");
            Member member = new Member(name, dateOfBirth, gender, phonenumber, address, memberNumber, passiveOrActive, memberType, motionist, competitive);
            meembers.add(member);
            fileHandler.saveListOfMembersToFile("MedlemsListe.csv", meembers);
            System.out.println("Member successfully registered.");
        } catch (Exception e) {
            System.out.println("Error registering member: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /*public void registrerTræningTid(LocalTime svimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline) {
        try {
            CompetitiveMember træning = new CompetitiveMember(svimTime, dateOfSwim, swimmingDiscipline);
            trææning.add(træning);
            fileHandler.saveListOfTræningsTidToFile("TræningsTid.csv", trææning);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
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
