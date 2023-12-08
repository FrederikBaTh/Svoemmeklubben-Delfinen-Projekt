import java.time.Duration; 
import java.time.LocalDate;
import java.util.*;

public class Database {

    private ArrayList<Member> meembers = new ArrayList<>();
    private FileHandler fileHandler = new FileHandler("MedlemsListe.csv");
    private FileHandler fileHandler1 = new FileHandler("TræningsTid.csv");

    private FileHandler fileHandler2 = new FileHandler("KontingentOversigt.csv");
    private ArrayList<CompetitiveMember> compMeembersEvent = new ArrayList<>();
    private ArrayList<CompetitiveMember> compMeembersTræning = new ArrayList<>();
    private ArrayList<MembershipStatus> statusPayment = new ArrayList<>();
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
        if (statusPayment.isEmpty()){
            statusPayment = fileHandler2.loadListOfPaidOrNot("KontingentOversigt.csv");
        }
    }

   /* public void registrerPaidOrNot(int memberNumber, boolean paid) {
        try {
            MembershipStatus membershipStatus = new MembershipStatus(memberNumber, paid);
            statusPayment.add(membershipStatus);
            fileHandler.saveListOfPaidOrNot("KontingentOversigt.csv", statusPayment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void registrerPaidOrNot(int memberNumber, boolean paid) {
        try {
            MembershipStatus membershipStatus = new MembershipStatus(memberNumber, paid);
            statusPayment.add(membershipStatus);
            fileHandler.saveListOfPaidOrNot("KontingentOversigt.csv", statusPayment);

            // Update the payment status for the member in the members list
            Member member = getMemberByNumber(memberNumber);
            if (member != null) {
                member.setAnnualMembershipPaymentStatus(paid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //_____

    public int calculateYearlyIncome() {
        int yearlyIncome = 0;

        for (Member member : meembers) {
            yearlyIncome += member.calculateYearlySubscriptionFee();
        }

        return yearlyIncome;
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






    //TODO MEDLEM


    public void registrerMedlem(String name, String dateOfBirth, String gender, int phonenumber, String address, int memberNumber, String passiveOrActive, String memberType, String motionist) {
        try {
            Member member = new Member(name, dateOfBirth, gender, phonenumber, address, memberNumber, passiveOrActive, memberType, motionist);
            meembers.add(member);
            fileHandler.saveListOfMembersToFile("MedlemsListe.csv", meembers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Member> getMeembers() {
        return meembers;
    }

    public void sortMembersByAge(List<Member> meembers) {
        meembers.sort(Comparator.comparingInt(Member::calculateAgeList));
    }
    public boolean memberExists(int memberNumber) {
        for (Member member : meembers) {
            if (member.getMemberNumber() == memberNumber) {
                return true;
            }
        }
        return false;
    }
    public Member getMemberByNumber(int memberNumber) {
        for (Member member : getMeembers()) {
            if (member.getMemberNumber() == memberNumber) {
                return member;
            }
        }
        return null;
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
    public CompetitiveMember getMemberByMemberNumberEvent (int memberNumber) {
        for (CompetitiveMember member : getCompMeembersEvent()) {
            if (member.getMemberNumber() == memberNumber) {
                return member;
            }
        }
        return null;
    }
    //TODO EVENT

    public ArrayList<CompetitiveMember> getCompMeembersEvent() {
        return compMeembersEvent;
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
    public ArrayList<CompetitiveMember> getCompMeembers() {
        return compMeembersEvent;
    }
    public void registrerEventTid(int memberNumber, Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline, String eventName, String eventPlacement) {
        try {
            CompetitiveMember competitiveMember = new CompetitiveMember(memberNumber, swimTime, dateOfSwim, swimmingDiscipline, eventName, eventPlacement);
            compMeembersEvent.add(competitiveMember);
            fileHandler.saveListOfKokurrenceTidToFile("KonkurrenceTid.csv", compMeembersEvent);
        } catch (Exception e) {
            e.printStackTrace();
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

//TODO TRÆNING
public ArrayList<CompetitiveMember> getCompMeembersTræning() {
    return compMeembersTræning;
}
public void registrerTræningTid(int memberNumber, Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline) {
    try {

        //Member member = new Member(memberNumber, svimTime, dateOfSwim, swimmingDiscipline);
        CompetitiveMember competitiveMember = new CompetitiveMember(memberNumber, swimTime, dateOfSwim, swimmingDiscipline);
        compMeembersTræning.add(competitiveMember);
        fileHandler.saveListOfTræningsTidToFile("TræningsTid.csv", compMeembersTræning);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
public CompetitiveMember getMemberByMemberNumberTræning(int memberNumber) {
    for (CompetitiveMember member : getCompMeembersTræning()) {
        if (member.getMemberNumber() == memberNumber) {
            return member;
        }
    }
    return null;
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


    //TODO BÅDE TRÆNING OG EVENT

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

        competitiveMembers.sort(Comparator.comparing(CompetitiveMember::getSwimTime));

        return competitiveMembers;
    }



}
