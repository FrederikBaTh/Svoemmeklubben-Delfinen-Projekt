import java.time.Duration;
import java.time.LocalDate;
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
    private boolean paidAnnualMembership;
    private List<Integer> usedMemberNumbers = new ArrayList<>();


    //TODO Konstruktører
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

    public Member() {
    }


    //___________
    public void setPaidAnnualMembership(boolean paidAnnualMembership) {
        this.paidAnnualMembership = paidAnnualMembership;
    }

    public boolean hasPaidAnnualmembership() {
        return paidAnnualMembership;
    }

    public void setAnnualMembershipPaymentStatus(boolean paid) {
        this.paidAnnualMembership = paid;
    }
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


    public int calculateYearlySubscriptionFee() {
        int baseFee = 0;

        if ("aktivt".equalsIgnoreCase(passiveOrActive)) {
            if ("ungdomssvømmer u18".equalsIgnoreCase(memberType)) {
                baseFee = 1000;
            } else if ("senior".equalsIgnoreCase(memberType) || "ungdomssvømmer o18".equalsIgnoreCase(memberType)) {
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


// TODO Getters
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


    //TODO Setters

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }

    public boolean hasPaidAnnualMembership() {
        return paidAnnualMembership;
    }

    public void editName(String newName) {
        this.name = newName;
    }

    public void editDateOfBirth(String newDateOfBirth) {
        this.dateOfBirth = LocalDate.parse(newDateOfBirth, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
//TODO Edits
    public void editGender(String newGender) {
        this.gender = newGender;
    }

    public void editPhonenumber(int newPhonenumber) {
        this.phonenumber = newPhonenumber;
    }
    public void editAdress(String newAdress) {
        this.adress = newAdress;
    }

    public void editPassiveOrActive(String newPassiveOrActive) {
        this.passiveOrActive = newPassiveOrActive;
    }
    public void editMotionist(String newMotionist) {
        this.motionist = newMotionist;
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
