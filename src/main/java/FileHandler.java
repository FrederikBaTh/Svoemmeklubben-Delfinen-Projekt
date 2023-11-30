import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private String fileName;


    public FileHandler(String fileName) {
        this.fileName = fileName;
    }
    public ArrayList<Member> loadedMembers() {
        ArrayList<Member> loadedMembers = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNext()) {
                String name = fileScanner.nextLine();
                String dateOfBirth = fileScanner.nextLine();
                String gender = fileScanner.nextLine();
                int phonenumber = Integer.parseInt(fileScanner.nextLine());
                String adress = fileScanner.nextLine();
                int memberNumber = Integer.parseInt(fileScanner.nextLine());
                String passiveOrActive = fileScanner.nextLine();
                String memberType = fileScanner.nextLine();
                String motionist = fileScanner.nextLine();
                String competitive = fileScanner.nextLine();



                Member members = new Member(name, dateOfBirth, gender, phonenumber, adress, memberNumber, passiveOrActive, memberType, motionist, competitive);
                loadedMembers.add(members);

                if (fileScanner.hasNext()) {
                    fileScanner.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Fejl: " + e.getMessage());
        }
        return loadedMembers;
    }

    public void saveListOfMembersToFile(String fileName, ArrayList<Member> members) {
        try (PrintStream output = new PrintStream(fileName)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for (Member member : members) {
                output.println(member.getName());
                output.println(member.getDateOfBirth().format(formatter));
                output.println(member.getGender());
                output.println(member.getPhonenumber());
                output.println(member.getAdress());
                output.println(member.getMemberNumber());
                output.println(member.getPassiveOrActive());
                output.println(member.getMemberType());
                output.println(member.getMotionist());
                output.println(member.getCompetitive());
                output.println();
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("Fejl: " + e.getMessage());
        }

    }
}
    /*public ArrayList<CompetitiveMember> loadedCompetitiveMember(){
        ArrayList<CompetitiveMember> loadedCompetitiveMember = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File(fileName))){
            while(fileScanner.hasNext()){
                String swimTimeString = fileScanner.nextLine();
                String dateOfSwimString =  fileScanner.nextLine();
                String  swimmingDisciplineString = fileScanner.nextLine();

                //Duration swimTime = Duration.parse(swimTimeString);
                LocalTime swimTime = LocalTime.parse(swimTimeString, DateTimeFormatter.ofPattern("HH:mm:ss"));



                LocalDate dateOfSwim = LocalDate.parse(dateOfSwimString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                SwimmingDiscipline swimmingDiscipline = SwimmingDiscipline.valueOf(swimmingDisciplineString);


                CompetitiveMember træningMember = new CompetitiveMember(swimTime, dateOfSwim, swimmingDiscipline);
                loadedCompetitiveMember.add(træningMember);

            }

        } catch (FileNotFoundException e) {
            System.err.println("Fejl: " + e.getMessage());
        }


        return loadedCompetitiveMember;
    }

    public void saveListOfTræningsTidToFile(String fileName, ArrayList<CompetitiveMember> compMember) {
        try (PrintStream output = new PrintStream(fileName)) {

            for (CompetitiveMember CompMember : compMember) {

                output.println(CompMember.getSwimTime());
                output.println(CompMember.getDateOfSwim());
                output.println(CompMember.getSwimmingDiscipline());
                output.println();
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("Fejl: " + e.getMessage());
        }

    }




}
/*

     */