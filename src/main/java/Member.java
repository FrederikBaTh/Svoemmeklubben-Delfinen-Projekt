import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Member {

    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private int phonenumber;
    private String adress;
    private int memberNumber;
    private String passiveOrActive;
    private String motionist;
    private String competitive;

    //constructor

    public Member(String name, String dateOfBirth, String gender, int phonenumber, String adress, int memberNumber, String passiveOrActive, String motionist, String competitive) {
        this.name = name;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.adress = adress;
        this.memberNumber = memberNumber;
        this.passiveOrActive = passiveOrActive;
        this.motionist = motionist;
        this.competitive = competitive;
    }

    public Member() {

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
                "Motionist:" + " " + motionist + "\n" +
                "konkurrencesvømmer:" + " " + competitive;
    }
}

