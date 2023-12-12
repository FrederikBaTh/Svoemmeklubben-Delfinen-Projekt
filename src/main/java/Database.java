import java.time.Duration; 
import java.time.LocalDate;
import java.util.*;

public class Database {

    private ArrayList<Member> membersList = new ArrayList<>();
    private FileHandler fileHandler = new FileHandler("MedlemsListe.csv");
    private FileHandler fileHandler1 = new FileHandler("TræningsTid.csv");

    private FileHandler fileHandler2 = new FileHandler("KontingentOversigt.csv");
    private ArrayList<CompetitiveMember> compMeembersEvent = new ArrayList<>();
    private ArrayList<CompetitiveMember> compMeembersTræning = new ArrayList<>();
    private ArrayList<MembershipStatus> statusPayment = new ArrayList<>();
    private Member member;

    public Database() {
        if (membersList.isEmpty()) {
            membersList = fileHandler.loadedMembers();
        }
        if (compMeembersEvent.isEmpty()) {
            compMeembersEvent = fileHandler.loadedCompetitiveMember("KonkurrenceTid.csv");
        }
        if (compMeembersTræning.isEmpty()) {
            compMeembersTræning = fileHandler1.loadedTrainingResults( "TræningsTid.csv");
        }
        if (statusPayment.isEmpty()){
            statusPayment = fileHandler2.loadListOfPaidOrNot("KontingentOversigt.csv");
        }
    }


    public void registerPaidOrNot(int memberNumber, boolean paid) {
        try {
            MembershipStatus membershipStatus = new MembershipStatus(memberNumber, paid);
            statusPayment.add(membershipStatus);
            fileHandler.saveListOfPaidOrNot("KontingentOversigt.csv", statusPayment);

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

        for (Member member : membersList) {
            yearlyIncome += member.calculateYearlySubscriptionFee();
        }

        return yearlyIncome;
    }


    public void registerMember(String name, String dateOfBirth, String gender, int phonenumber, String address, int memberNumber, String passiveOrActive, String memberType, String motionist) {
        try {
            Member member = new Member(name, dateOfBirth, gender, phonenumber, address, memberNumber, passiveOrActive, memberType, motionist);
            membersList.add(member);
            fileHandler.saveListOfMembersToFile("MedlemsListe.csv", membersList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Member> getMembersList() {
        return membersList;
    }

    public void sortMembersByAge(List<Member> meembers) {
        meembers.sort(Comparator.comparingInt(Member::calculateAgeList));
    }
    public boolean memberExists(int memberNumber) {
        for (Member member : membersList) {
            if (member.getMemberNumber() == memberNumber) {
                return true;
            }
        }
        return false;
    }
    public Member getMemberByNumber(int memberNumber) {
        for (Member member : getMembersList()) {
            if (member.getMemberNumber() == memberNumber) {
                return member;
            }
        }
        return null;
    }
    public void updateMember(Member updatedMember) {
        for (int i = 0; i < membersList.size(); i++) {
            Member existingMember = membersList.get(i);
            if (existingMember.getMemberNumber() == updatedMember.getMemberNumber()) {
                membersList.set(i, updatedMember);
                fileHandler.saveListOfMembersToFile("MedlemsListe.csv", membersList);
                break;
            }
        }
    }
    public CompetitiveMember getMemberByMemberNumberEvent (int memberNumber) {
        for (CompetitiveMember member : getCompMembersForEvent()) {
            if (member.getMemberNumber() == memberNumber) {
                return member;
            }
        }
        return null;
    }
    //TODO EVENT

    public ArrayList<CompetitiveMember> getCompMembersForEvent() {
        return compMeembersEvent;
    }

    public void sortTrainingMembersBySwimTime() {
        compMeembersTræning.sort(Comparator.comparing(CompetitiveMember::getSwimTime));
    }

    public boolean isCompetitiveUnder18(Member member) {
        return "aktivt".equalsIgnoreCase(member.getPassiveOrActive()) && "ungdomssvømmer u18".equalsIgnoreCase(member.getMemberType()) && member.calculateAgeList() < 18;
    }

    public boolean isCompetitiveOver18(Member member) {
        return "aktivt".equalsIgnoreCase(member.getPassiveOrActive()) && "ungdomssvømmer o18".equalsIgnoreCase(member.getMemberType()) && member.calculateAgeList() >= 18;
    }
    public List<Member> getCompetitiveMembersUnder18() {
        List<Member> competitiveMembersUnder18 = new ArrayList<>();

        for (Member member : membersList) {
            if (isCompetitiveUnder18(member)) {
                competitiveMembersUnder18.add(member);
            }
        }

        return competitiveMembersUnder18;
    }

    public List<Member> getCompetitiveMembersOver18() {
        List<Member> competitiveMembersOver18 = new ArrayList<>();

        for (Member member : membersList) {
            if (isCompetitiveOver18(member)) {
                competitiveMembersOver18.add(member);
            }
        }

        return competitiveMembersOver18;
    }
    public ArrayList<CompetitiveMember> getCompMeembers() {
        return compMeembersEvent;
    }
    public void registerEventTime(int memberNumber, Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline, String eventName, String eventPlacement) {
        try {
            CompetitiveMember competitiveMember = new CompetitiveMember(memberNumber, swimTime, dateOfSwim, swimmingDiscipline, eventName, eventPlacement);
            compMeembersEvent.add(competitiveMember);
            fileHandler.saveListOfKokurrenceTidToFile("KonkurrenceTid.csv", compMeembersEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateContest(CompetitiveMember updatedMember) {
        for (int i = 0; i < compMeembersEvent.size(); i++) {
            CompetitiveMember existingMember = compMeembersEvent.get(i);
            if (existingMember.getMemberNumber() == updatedMember.getMemberNumber()) {
                compMeembersEvent.set(i, updatedMember);
                fileHandler.saveListOfKokurrenceTidToFile("KonkurrenceTid.csv", compMeembersEvent);
                break;
            }
        }
    }

public ArrayList<CompetitiveMember> getCompMembersTraining() {
    return compMeembersTræning;
}
public void registerTrainingTime(int memberNumber, Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline) {
    try {

        CompetitiveMember competitiveMember = new CompetitiveMember(memberNumber, swimTime, dateOfSwim, swimmingDiscipline);
        compMeembersTræning.add(competitiveMember);
        fileHandler.saveListOfTrainingtimeToFile("TræningsTid.csv", compMeembersTræning);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
public CompetitiveMember getMemberByMemberNumberTraining(int memberNumber) {
    for (CompetitiveMember member : getCompMembersTraining()) {
        if (member.getMemberNumber() == memberNumber) {
            return member;
        }
    }
    return null;
}
    public void updateTraining(CompetitiveMember updatedMember) {
        for (int i = 0; i < compMeembersTræning.size(); i++) {
            CompetitiveMember existingMember = compMeembersTræning.get(i);
            if (existingMember.getMemberNumber() == updatedMember.getMemberNumber()) {
                compMeembersTræning.set(i, updatedMember);
                fileHandler.saveListOfTrainingtimeToFile("TræningsTid.csv", compMeembersTræning);
                break;
            }
        }
    }

    public List<CompetitiveMember> getTop5SwimTimes(SwimmingDiscipline swimmingDiscipline) {
        List<CompetitiveMember> competitiveMembers = new ArrayList<>();

        switch (swimmingDiscipline) {
            case BUTTERFLY:
                competitiveMembers.addAll(getCompMembersTraining());
                competitiveMembers.addAll(getCompMembersForEvent());
                break;
            case BACKSTROKE:
                competitiveMembers.addAll(getCompMembersTraining());
                competitiveMembers.addAll(getCompMembersForEvent());
                break;
            case BREASTSTROKE:
                competitiveMembers.addAll(getCompMembersTraining());
                competitiveMembers.addAll(getCompMembersForEvent());
                break;
            case FREESTYLE:
                competitiveMembers.addAll(getCompMembersTraining());
                competitiveMembers.addAll(getCompMembersForEvent());
                break;
        }

        competitiveMembers.sort(Comparator.comparing(CompetitiveMember::getSwimTime));

        return competitiveMembers;
    }



}
