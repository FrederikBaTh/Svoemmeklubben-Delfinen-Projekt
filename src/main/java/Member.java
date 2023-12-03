import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

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
    private String competitive;

    //constructor

    public Member(String name, String dateOfBirth, String gender, int phonenumber, String adress, int memberNumber, String passiveOrActive, String memberType, String motionist, String competitive) {
        this.name = name;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.adress = adress;
        this.memberNumber = memberNumber;
        this.passiveOrActive = passiveOrActive;
        this.memberType = memberType;
        this.motionist = motionist;
        this.competitive = competitive;
    }

    public Member() {

    }

    public Member(String name, LocalDate dateOfBirth, String gender, int phonenumber, String adress, int memberNumber, String passiveOrActive, String memberType, String motionist, String competitive, Duration swimTime, LocalDate dateOfSwim, String swimmingDiscipline) {
    }

    public int generateMemberNumber() {

        Random random = new Random();
        return random.nextInt(999999);
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

    public String getFormattedDateOfBirth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dateOfBirth.format(formatter);

    }

    public int calculateYearlySubscriptionFee() {

        int baseFee = 0;

        if ("aktivt".equalsIgnoreCase(passiveOrActive)) {
            if ("ungdomssvømmer u18".equalsIgnoreCase(memberType)) {
                baseFee = 1000;
            } else if ("seniorsvømmer".equalsIgnoreCase(memberType)) {
                baseFee = 1600;
                if (calculateAgeList() > 60) {

                    baseFee = (int) (baseFee * 0.75);
                }
            }else if("ungdomssvømmer o18".equalsIgnoreCase(memberType)){
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

    public String getCompetitive() {
        return competitive;
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

    public void setCompetitive(String competitive) {
        this.competitive = competitive;
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
                "Motionist:" + " " + motionist + "\n" +
                "konkurrencesvømmer:" + " " + competitive;
    }
}

