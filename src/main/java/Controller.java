import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//
public class Controller {


    Member member = new Member();
    CompetitiveMember competitiveMember = new CompetitiveMember();

    Database database = new Database();

    public void registrerMedlem(String name, String dateOfBirth, String gender, int phonenumber, String adress, int memberNumber, String passiveOrActive, String memberType, String motionist) {
        database.registrerMedlem(name, dateOfBirth, gender, phonenumber, adress, memberNumber, passiveOrActive, memberType, motionist);
    }

    public void sortMembersByAge(ArrayList<Member> members) {
        database.sortMembersByAge(members);
    }

    public int calculateAge(LocalDate date){
        return member.calculateAge(date);
    }

    public ArrayList<Member> getMembers() {
        return database.getMeembers();
    }

    public int calculateYearlyIncome(){
        return database.calculateYearlyIncome();
    }

    public int generateMemberNumber(){
        return member.generateMemberNumber();
    }

    public void registrerTræningsResultat(int memberNumber, Duration svimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline) {
        database.registrerTræningTid(memberNumber, svimTime, dateOfSwim, swimmingDiscipline);
    }

    public void registrerEventResultat(int memberNumber, Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline, String eventName, String eventPlacement) {
        database.registrerEventTid(memberNumber, swimTime, dateOfSwim, swimmingDiscipline, eventName, eventPlacement);
    }

    public boolean memberNumberUsed(int input) {
        return false;
    }

    public List<Member> getCompetitiveMembersUnder18(){
        return database.getCompetitiveMembersUnder18();
    }
    public List<Member> getCompetitiveMembersOver18(){
        return database.getCompetitiveMembersOver18();
    }


    public boolean memberExists(int memberNumber){
        return database.memberExists(memberNumber);
    }

    //_______________________
    public boolean hasPaidAnnualMembership(Member member) {
        return member.hasPaidAnnualMembership();
    }
    public void renewMembership() {
        // Display list of members
        ArrayList<Member> members = getMembers();  // Assume you have a method to get the list of members

        System.out.println("List of Members:");
        for (Member member : members) {
            System.out.println("Member Number: " + member.getMemberNumber() + ", Name: " + member.getName() + ", Membership Status: " + (member.hasPaidAnnualMembership() ? "Paid" : "Not Paid"));
        }

        // Prompt user for member selection
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Member Number to renew membership: ");
        int selectedMemberNumber = scanner.nextInt();

        Member selectedMember = getMemberByNumber(selectedMemberNumber);  // Implement this method to retrieve a member by number
        if (selectedMember != null) {
            if (hasPaidAnnualMembership(selectedMember)) {
                System.out.println("Membership is already paid for this member.");
            } else {
                // Perform the necessary actions to renew membership
                renewMembershipForSelectedMember(selectedMember);
                System.out.println("Membership renewed successfully!");
            }
        } else {
            System.out.println("Member not found.");
        }
    }

    private Member getMemberByNumber(int memberNumber) {
        for (Member member : getMembers()) {
            if (member.getMemberNumber() == memberNumber) {
                return member;
            }
        }
        return null;  // Member not found
    }

    // Method to renew membership for the selected member
    private void renewMembershipForSelectedMember(Member selectedMember) {
        // Implement logic to renew membership, e.g., update membership status
        // You may need to modify this based on your actual data structure and logic
        selectedMember.setPaidAnnualMembership(true);
    }
    //_______________________

    public ArrayList<CompetitiveMember> getCompetitiveMembers() {
        return database.getCompMeembers();
    }

    public void redigerResultaterTræning(List<Integer> getUsedMemberNumbers){
        database.redigerResultaterTræning(getUsedMemberNumbers);
    }



}