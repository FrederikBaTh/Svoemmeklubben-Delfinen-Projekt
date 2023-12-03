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
                String line = fileScanner.nextLine();
                String[] memberInfo = line.split(":");

                String name = memberInfo[0];
                String dateOfBirth = memberInfo[1];
                String gender = memberInfo[2];
                int phonenumber = Integer.parseInt(memberInfo[3]);
                String address = memberInfo[4];
                int memberNumber = Integer.parseInt(memberInfo[5]);
                String passiveOrActive = memberInfo[6];
                String memberType = memberInfo[7];
                String motionist = memberInfo[8];


                Member member = new Member(name, dateOfBirth, gender, phonenumber, address, memberNumber, passiveOrActive, memberType, motionist);
                loadedMembers.add(member);
            }
        } catch (FileNotFoundException | NumberFormatException | NoSuchElementException e) {
            System.err.println("Fejl: " + e.getMessage());
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
