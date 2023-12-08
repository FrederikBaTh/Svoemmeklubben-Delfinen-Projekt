import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Database {

    private ArrayList<Member> meembers = new ArrayList<>();

    private FileHandler fileHandler = new FileHandler("MedlemsListe.csv");
    private FileHandler fileHandler1 = new FileHandler("TræningsTid.csv");

    private ArrayList<CompetitiveMember> compMeembersEvent = new ArrayList<>();

    public ArrayList<CompetitiveMember> getCompMeembersEvent() {
        return compMeembersEvent;

    }
    private ArrayList<CompetitiveMember> compMeembersTræning = new ArrayList<>();

    public ArrayList<CompetitiveMember> getCompMeembersTræning() {
        return compMeembersTræning;

    }

    private Member member;

    public Database() {
        if (meembers.isEmpty()) {
            meembers = fileHandler.loadedMembers();
        }
        if (compMeembersEvent.isEmpty()) {
            compMeembersEvent = fileHandler.loadedCompetitiveMember("KonkurrenceTid.csv");
        }
        if (compMeembersTræning.isEmpty()) {
            compMeembersTræning = fileHandler1.loadedTræningsResultater( "TræningsTid.csv");
        }
    }



    public void sortTrainingMembersBySwimTime() {
        compMeembersTræning.sort(Comparator.comparing(CompetitiveMember::getSwimTime));
    }
    public void sortEventMembersBySwimTime() {
        compMeembersEvent.sort(Comparator.comparing(CompetitiveMember ::getSwimTime ));
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // fjerne print fra metoden
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

            //Member member = new Member(memberNumber, svimTime, dateOfSwim, swimmingDiscipline);
            CompetitiveMember competitiveMember = new CompetitiveMember(memberNumber, svimTime, dateOfSwim, swimmingDiscipline);
            compMeembersTræning.add(competitiveMember);
            fileHandler.saveListOfTræningsTidToFile("TræningsTid.csv", compMeembersTræning);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //____________

    //_____________

    public void registrerEventTid(int memberNumber, Duration svimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline, String eventName, String eventPlacement) {
        try {
            CompetitiveMember competitiveMember = new CompetitiveMember(memberNumber, svimTime, dateOfSwim, swimmingDiscipline, eventName, eventPlacement);
            compMeembersEvent.add(competitiveMember);
            fileHandler.saveListOfKokurrenceTidToFile("KonkurrenceTid.csv", compMeembersEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean memberExists(int memberNumber) {
        for (Member member : meembers) {
            if (member.getMemberNumber() == memberNumber) {
                return true;
            }
        }
        return false;
    }

    private void renewMembershipForSelectedMember(Member selectedMember) {
        // Implement logic to renew membership, e.g., update membership status
        // You may need to modify this based on your actual data structure and logic
        selectedMember.setPaidAnnualMembership(true);
    }

    public void renewMembership() {
        ArrayList<Member> members = getMeembers();

        System.out.println("List of Members:");
        for (Member member : members) {
            System.out.println("Member Number: " + member.getMemberNumber() + ", Name: " + member.getName() + ", Membership Status: " + (member.hasPaidAnnualMembership() ? "Paid" : "Not Paid"));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Member Number to renew membership: ");
        int selectedMemberNumber = scanner.nextInt();

        Member selectedMember = getMemberByNumber(selectedMemberNumber);
        if (selectedMember != null) {
            if (member.hasPaidAnnualMembership()) {
                System.out.println("Membership is already paid for this member.");
            } else {
                renewMembershipForSelectedMember(selectedMember);
                System.out.println("Membership renewed successfully!");
            }
        } else {
            System.out.println("Member not found.");
        }
    }

    public Member getMemberByNumber(int memberNumber) {
        for (Member member : getMeembers()) {
            if (member.getMemberNumber() == memberNumber) {
                return member;
            }
        }
        return null;
    }

    public CompetitiveMember getMemberByMemberNumberTræning(int memberNumber) {
        for (CompetitiveMember member : getCompMeembersTræning()) {
            if (member.getMemberNumber() == memberNumber) {
                return member;
            }
        }
        return null; // Return null if the member is not found
    }
    public CompetitiveMember getMemberByMemberNumberEvent (int memberNumber) {
        for (CompetitiveMember member : getCompMeembersEvent()) {
            if (member.getMemberNumber() == memberNumber) {
                return member;
            }
        }
        return null; // Return null if the member is not found
    }


    public void updateMember(Member updatedMember) {
        for (int i = 0; i < meembers.size(); i++) {
            Member existingMember = meembers.get(i);
            if (existingMember.getMemberNumber() == updatedMember.getMemberNumber()) {
                meembers.set(i, updatedMember);
                fileHandler.saveListOfMembersToFile("MedlemsListe.csv", meembers);
                break;
            }
        }
    }
    public void updateKonkurrence(CompetitiveMember updatedMember) {
        for (int i = 0; i < compMeembersEvent.size(); i++) {
            CompetitiveMember existingMember = compMeembersEvent.get(i);
            if (existingMember.getMemberNumber() == updatedMember.getMemberNumber()) {
                compMeembersEvent.set(i, updatedMember);
                fileHandler.saveListOfKokurrenceTidToFile("KonkurrenceTid.csv", compMeembersEvent);
                break;
            }
        }
    }
    public void updateTræning(CompetitiveMember updatedMember) {
        for (int i = 0; i < compMeembersTræning.size(); i++) {
            CompetitiveMember existingMember = compMeembersTræning.get(i);
            if (existingMember.getMemberNumber() == updatedMember.getMemberNumber()) {
                compMeembersTræning.set(i, updatedMember);
                fileHandler.saveListOfTræningsTidToFile("TræningsTid.csv", compMeembersTræning);
                break;
            }
        }
    }
    public List<CompetitiveMember> getTop5SwimTimes(SwimmingDiscipline swimmingDiscipline) {
        List<CompetitiveMember> competitiveMembers = new ArrayList<>();

        switch (swimmingDiscipline) {
            case BUTTERFLY:
                competitiveMembers.addAll(getCompMeembersTræning());
                competitiveMembers.addAll(getCompMeembersEvent());
                break;
            case BACKSTROKE:
                competitiveMembers.addAll(getCompMeembersTræning());
                competitiveMembers.addAll(getCompMeembersEvent());
                break;
            case BREASTSTROKE:
                competitiveMembers.addAll(getCompMeembersTræning());
                competitiveMembers.addAll(getCompMeembersEvent());
                break;
            case FREESTYLE:
                competitiveMembers.addAll(getCompMeembersTræning());
                competitiveMembers.addAll(getCompMeembersEvent());
                break;
        }


        // Sort the list based on swim times
        competitiveMembers.sort(Comparator.comparing(CompetitiveMember::getSwimTime));

        // Reverse the list to get the slowest times first
        Collections.reverse(competitiveMembers);

        return competitiveMembers;
    }



}


}
