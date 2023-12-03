import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import java.io.FileOutputStream;
import java.io.IOException;
public class FileHandler {
    private String fileName;


    public FileHandler(String fileName) {
        this.fileName = fileName;
    }
    public ArrayList<Member> loadedMembers() {
        ArrayList<Member> loadedMembers = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
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

                Member member = new Member(name, dateOfBirth, gender, phonenumber, adress, memberNumber, passiveOrActive, memberType, motionist);
                loadedMembers.add(member);

                if (fileScanner.hasNext()) {
                    fileScanner.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Fejl: " + e.getMessage());
        } catch (NumberFormatException | NoSuchElementException e) {
        }
        return loadedMembers;
    }


    /*public void saveListOfMembersToFile(String fileName, ArrayList<Member> members) {
        try (PrintStream output = new PrintStream(fileName)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for (Member member : members) {
                String memberInfo = member.getName() + ":" +
                        member.getDateOfBirth().format(formatter) + ":" +
                        member.getGender() + ":" +
                        member.getPhonenumber() + ":" +
                        member.getAdress() + ":" +
                        member.getMemberNumber() + ":" +
                        member.getPassiveOrActive() + ":" +
                        member.getMemberType() + ":" +
                        member.getMotionist() + ":" +

                output.println(memberInfo);
                output.println();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Fejl: " + e.getMessage());
        }
        catch (NumberFormatException | NoSuchElementException e) {
        System.err.println("Fejl ved indlæsning af medlemmer: " + e.getMessage());
        }
    }*/

    public void saveListOfMembersToFile(String fileName, ArrayList<Member> members) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName, true)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for (Member member : members) {
                String memberInfo = member.getName() + ":" +
                        member.getDateOfBirth().format(formatter) + ":" +
                        member.getGender() + ":" +
                        member.getPhonenumber() + ":" +
                        member.getAdress() + ":" +
                        member.getMemberNumber() + ":" +
                        member.getPassiveOrActive() + ":" +
                        member.getMemberType() + ":" +
                        member.getMotionist();

                // Append a newline character after each member's information
                memberInfo += System.lineSeparator();
                fileOutputStream.write(memberInfo.getBytes());
            }
        } catch (IOException e) {
            System.err.println("Fejl: " + e.getMessage());
        }
    }


        /*
        public void toFile() {
            try {
                PrintStream writeToFile = new PrintStream(new File("MedlemsListe.csv"));
                for (int i = 0; i < this.members.toArray().length; i++) {
                    writeToFile.println(this.members.get(i).toCsvString());
                    System.out.println("toFileMethod " + this.members.get(i).toCsvString());
                }
           }
            catch (FileNotFoundException e) {
                System.out.println(e);
            }
        } */
}
