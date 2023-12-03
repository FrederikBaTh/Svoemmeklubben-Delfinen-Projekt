import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Database {

    private ArrayList<Member> meembers = new ArrayList<>();
    private FileHandler fileHandler = new FileHandler("MedlemsListe.csv");

    public Database() {
        if (meembers.isEmpty()) {
            meembers = fileHandler.loadedMembers();
        }
    }

    private ArrayList<CompetitiveMember> compMeembersEvent = new ArrayList<>();

    private ArrayList<CompetitiveMember> compMeembersTræning = new ArrayList<>();

    public void registrerMedlem(String name, String dateOfBirth, String gender, int phonenumber, String address, int memberNumber, String passiveOrActive, String memberType, String motionist) {
        try {
            Member member = new Member(name, dateOfBirth, gender, phonenumber, address, memberNumber, passiveOrActive, memberType, motionist);
            meembers.add(member);
            fileHandler.saveListOfMembersToFile("MedlemsListe.csv", meembers);
            System.out.println("Member successfully registered.");
        } catch (Exception e) {
            System.out.println("Error registering member: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public int calculateYearlyIncome() {
        int yearlyIncome = 0;

        for (Member member : meembers) {
            yearlyIncome += member.calculateYearlySubscriptionFee();
        }

        return yearlyIncome;
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


    public ArrayList<CompetitiveMember> getCompMeembers() {
        return compMeembersEvent;
    }

    public void registrerTræningTid(LocalTime svimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline) {
        try {
            CompetitiveMember træning = new CompetitiveMember(svimTime, dateOfSwim, swimmingDiscipline);
            compMeembersTræning.add(træning);
            fileHandler.saveListOfTræningsTidToFile("TræningsTid.csv", compMeembersTræning);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}