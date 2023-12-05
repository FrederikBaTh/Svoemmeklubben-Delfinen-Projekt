import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Database {
    Member member = new Member();
    private ArrayList<Member> meembers = new ArrayList<>();

    private ArrayList<CompetitiveMember> compMembersUnder18 = new ArrayList<>();
    private ArrayList<CompetitiveMember> compMembersOver18 = new ArrayList<>();


    private ArrayList<CompetitiveMember> compMeembersEvent = new ArrayList<>();

    private ArrayList<CompetitiveMember> compMeembersTræning = new ArrayList<>();

    private FileHandler fileHandler = new FileHandler("MedlemsListe.csv");

    public Database() {
        if (meembers.isEmpty()) {
            meembers = fileHandler.loadedMembers();
        }
    }



    public void printCompMembersUnder18() {
        System.out.println("Competitive Members Under 18:");
        printCompetitiveMembers(compMembersUnder18);
    }

    // Print competitive members over 18
    public void printCompMembersOver18() {
        printCompetitiveMembers(compMembersOver18);
    }

    private void printCompetitiveMembers(List<CompetitiveMember> members) {
        for (CompetitiveMember member : members) {
            System.out.println(member);
        }
    }
    public void setCompetitiveMember(String name, LocalDate dateOfBirth, String gender, int phonenumber, String address, int memberNumber, String passiveOrActive, String memberType, String motionist) {
        CompetitiveMember compMember = new CompetitiveMember(name, dateOfBirth, gender, phonenumber, address, memberNumber, passiveOrActive, memberType, motionist);

        boolean isOver18 = compMember.calculateAge(dateOfBirth) > 18;

        if (isOver18) {
            compMembersOver18.add(compMember);
        } else {
            compMembersUnder18.add(compMember);
        }

        // Save to file or perform other actions as needed
    }










    public void registrerMedlem(String name, String dateOfBirth, String gender, int phonenumber, String address, int memberNumber, String passiveOrActive, String memberType, String motionist) {
        try {
            Member member = new Member(name, dateOfBirth, gender, phonenumber, address, memberNumber, passiveOrActive, memberType, motionist);
            meembers.add(member);
            fileHandler.saveListOfMembersToFile("MedlemsListe.csv", meembers);
            // lave en metode i filehandle til at gemme medlemsnr i en anden CSV file.
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

    public ArrayList<CompetitiveMember> getCompMeembers() {
        return compMeembersEvent;
    }

    public void registrerTræningTid(int memberNumber, Duration svimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline) {
        try {

            Member member = new Member(memberNumber, svimTime, dateOfSwim, swimmingDiscipline);
            compMeembersTræning.add((CompetitiveMember) member);
            fileHandler.saveListOfTræningsTidToFile("TræningsTid.csv", compMeembersTræning);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //____________
    public void processArrears() {
        for (Member member : meembers) {
            if (member.isInArrears()) {
                // Implement logic to handle arrears, e.g., send a notification or take appropriate action
                System.out.println("Member in arrears: " + member.getName());
            }
        }
    }
    public void performMembershipRenewal() {

    }
    //_____________

    public void registrerEventTid(int memberNumber, Duration svimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline, String eventName, String eventPlacement, Duration eventSwimTime) {
        try {

            Member member = new Member(memberNumber, svimTime, dateOfSwim, swimmingDiscipline);
            compMeembersTræning.add((CompetitiveMember) member);
            fileHandler.saveListOfTræningsTidToFile("TræningsTid.csv", compMeembersTræning);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}