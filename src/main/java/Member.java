import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Member {

    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private int phonenumber;
    private String adress;
    private int memberNumber;
    private String passiveOrActive; // TODO Overvej at ændre til boolean
    private String memberType;
    private String motionist;
    private LocalDate membershipExpiryDate;
    private boolean paidAnnualMembership;

    private List<Integer> usedMemberNumbers = new ArrayList<>();

    private Database database;



    /*public boolean isCompetitiveUnder18() {
        return "aktivt".equalsIgnoreCase(passiveOrActive) && "ungdomssvømmer u18".equalsIgnoreCase(memberType) && calculateAgeList() < 18;
    }

    public List<Member> getCompetitiveMembersUnder18() {
        List<Member> competitiveMembersUnder18 = new ArrayList<>();

        for (Member member : database.getMeembers()) {
            if (member.isCompetitiveUnder18()) {
                competitiveMembersUnder18.add(member);
            }
        }

        return competitiveMembersUnder18;
    }
*/


    //constructor

    public Member(String name, String dateOfBirth, String gender, int phonenumber, String adress, int memberNumber, String passiveOrActive, String memberType, String motionist) {
        this.name = name;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.adress = adress;
        this.memberNumber = memberNumber;
        this.passiveOrActive = passiveOrActive;
        this.memberType = memberType;
        this.motionist = motionist;

    }

    public Member(int memberNumber, Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline, String eventName, String eventPlacement) {
    }

    public Member(int memberNumber) {
    }

    public Member() {
    }

    public Member(int memberNumber, java.time.Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline) {
    }

    //TODO Lav en Save metode til denne her
    //public Member(String name, newStatus) {

   // }

    //___________

    //_____________


    public int generateMemberNumber() {
        Random random = new Random();
        int newMemberNumber;


        do {
            newMemberNumber = random.nextInt(999999);
        } while (memberNumberUsed(newMemberNumber));

        usedMemberNumbers.add(newMemberNumber);
        return newMemberNumber;
    }

    public boolean memberNumberUsed(int number) {
        for (int usedNumber : usedMemberNumbers) {
            if (usedNumber == number) {
                return true;
            }
        }
        return false;
    }

    public int calculateAge(LocalDate date) {
        this.dateOfBirth = date;
        LocalDate today = LocalDate.now();
        //System.out.println(today.getYear() - dateOfBirth.getYear());
        return today.getYear() - dateOfBirth.getYear();
    }

    public int calculateAgeList() {
        LocalDate today = LocalDate.now();
        return today.getYear() - dateOfBirth.getYear();
    }

    /*public String getFormattedDateOfBirth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dateOfBirth.format(formatter);

    }*/

    public int calculateYearlySubscriptionFee() {
        int baseFee = 0;

        if ("aktivt".equalsIgnoreCase(passiveOrActive)) {
            if ("ungdomssvømmer u18".equalsIgnoreCase(memberType)) {
                baseFee = 1000;
            } else if ("seniorsvømmer".equalsIgnoreCase(memberType) || "ungdomssvømmer o18".equalsIgnoreCase(memberType)) {
                baseFee = 1600;
                if (calculateAgeList() > 60) {
                    baseFee = (int) (baseFee * 0.75);
                }
            }
        } else if ("passivt".equalsIgnoreCase(passiveOrActive)) {
            baseFee = 500;
        }

        return baseFee;
    }


    // getters

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public String getAdress() {
        return adress;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public String getPassiveOrActive() {
        return passiveOrActive;
    }

    public String getMemberType() {
        return memberType;
    }

    public String getMotionist() {
        return motionist;
    }

    public List<Integer> getUsedMemberNumbers() {
        return usedMemberNumbers;
    }

    //setters

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }

    public void setPassiveOrActive(String passiveOrActive) {
        this.passiveOrActive = passiveOrActive;
    }

    public void setMemberType(String Membertype) {
        this.memberType = Membertype;
    }

    public void setMotionist(String motionist) {
        this.motionist = motionist;
    }

    public boolean hasPaidAnnualMembership() {
        return paidAnnualMembership;
    }

    @Override
    public String toString() {
        return "\n" +
                "navn:" + " " + name + "\n" +
                "Fødselsår:" + " " + dateOfBirth + "\n" +
                "køn:" + " " + gender + "\n" +
                "telefon:" + " " + phonenumber + "\n" +
                "Adresse:" + " " + adress + "\n" +
                "Medlemsnummer:" + " " + memberNumber + "\n" +
                "Medlemsstatus:" + " " + passiveOrActive + "\n" +
                "Medlemstype:" + " " + memberType + "\n" +
                "Motionist:" + " " + motionist + "\n";
    }
}


