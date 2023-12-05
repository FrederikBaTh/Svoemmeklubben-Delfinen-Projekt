import java.time.Duration;
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


    public boolean isCompetitiveUnder18(Member member) {
        return "aktivt".equalsIgnoreCase(member.getPassiveOrActive()) && "ungdomssvømmer u18".equalsIgnoreCase(member.getMemberType()) && member.calculateAgeList() < 18;
    }

    public boolean isCompetitiveOver18(Member member) {
        return "aktivt".equalsIgnoreCase(member.getPassiveOrActive()) && "ungdomssvømmer o18".equalsIgnoreCase(member.getMemberType()) && member.calculateAgeList() >= 18;
    }

    public List<Member> getCompetitiveMembersUnder18() {
        List<Member> competitiveMembersUnder18 = new ArrayList<>();

        for (Member member : meembers) {
            if (isCompetitiveUnder18(member)) {
                competitiveMembersUnder18.add(member);
            }
        }

        return competitiveMembersUnder18;
    }

    public List<Member> getCompetitiveMembersOver18() {
        List<Member> competitiveMembersOver18 = new ArrayList<>();

        for (Member member : meembers) {
            if (isCompetitiveOver18(member)) {
                competitiveMembersOver18.add(member);
            }
        }

        return competitiveMembersOver18;
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

    /*public void registrerEventTid(int memberNumber, Duration svimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline, String eventName, String eventPlacement) {
        try {

            Member member = new Member(memberNumber, svimTime, dateOfSwim, swimmingDiscipline);
            compMeembersTræning.add((CompetitiveMember) member);
            fileHandler.saveListOfTræningsTidToFile("TræningsTid.csv", compMeembersTræning);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

        public boolean memberExists ( int memberNumber){
            for (Member member : meembers) {
                if (member.getMemberNumber() == memberNumber) {
                    return true;
                }
            }
            return false;
        }


    }
