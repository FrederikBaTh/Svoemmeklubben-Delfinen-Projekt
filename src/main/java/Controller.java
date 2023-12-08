import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//
public class Controller {

    Member member = new Member();

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




    public int calculateYearlyIncome(){
        return database.calculateYearlyIncome();
    }

    public int generateMemberNumber(){
        return member.generateMemberNumber();
    }

    public void registrerTræningsResultat(int memberNumber, Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline) {
        database.registrerTræningTid(memberNumber, swimTime, dateOfSwim, swimmingDiscipline);
    }
    public void registrerEventResultat(int memberNumber, Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline, String eventName, String eventPlacement) {
        database.registrerEventTid(memberNumber, swimTime, dateOfSwim, swimmingDiscipline, eventName, eventPlacement);
    }

//TODO Getters
    public ArrayList<Member> getMembers() {
    return database.getMeembers();
}
    public List<Member> getCompetitiveMembersUnder18(){
        return database.getCompetitiveMembersUnder18();
    }
    public List<Member> getCompetitiveMembersOver18(){
        return database.getCompetitiveMembersOver18();
    }
    public ArrayList<CompetitiveMember> getCompMeembersTræning(){
        return database.getCompMeembersTræning();
    }
    public Member getMemberByMemberNumber(int memberNumber){
        return database.getMemberByNumber(memberNumber);
    }

    public ArrayList<CompetitiveMember> getCompMeembersEvent(){
        return database.getCompMeembersEvent();
    }
    public boolean memberExists(int memberNumber){
        return database.memberExists(memberNumber);
    }
    public CompetitiveMember getCompetitiveMemberByMemberNumbertræning(int memberNumber){
        return database.getMemberByMemberNumberTræning(memberNumber);
    }
    CompetitiveMember getCompetitiveMemberByMemberNumberEvent(int memberNumber){
        return database.getMemberByMemberNumberEvent(memberNumber);
    }
    public List<CompetitiveMember> getTop5SwimTimes(SwimmingDiscipline style){
        return database.getTop5SwimTimes(style);
    }
    //_______________________
    public boolean hasPaidAnnualMembership(Member member) {
        return member.hasPaidAnnualMembership();
    }

    public void registretPaidOrNot(int memberNumber, boolean paid) {
        database.registrerPaidOrNot(memberNumber, paid);
    }

    //_______________________



    public void sortTrainingMembersBySwimTime(){
        database.sortTrainingMembersBySwimTime();
    }




    //TODO Update
    public void updateMember(Member updatedMember) {
        database.updateMember(updatedMember);
    }

    public void updateKonkurrenceTid(CompetitiveMember updatedMember) {
        database.updateKonkurrence(updatedMember);
    }
    public void updateTræningsTid(CompetitiveMember updatedMember) {
        database.updateTræning(updatedMember);
    }


}
