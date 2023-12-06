import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] memberInfo = line.split(",");

                if (memberInfo.length >= 2) {
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
                } else {
                }
            }
        } catch (FileNotFoundException | NumberFormatException | NoSuchElementException e) {
            System.err.println("Fejl: " + e.getMessage());
        }

        return loadedMembers;
    }

    public ArrayList<CompetitiveMember> loadedTræningsResultater(String fileName) {
        ArrayList<CompetitiveMember> loadedTræningsResultater = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] memberInfo = line.split(",");

                if (memberInfo.length >= 2) {
                    int memberNumber = Integer.parseInt(memberInfo[0]);
                    Duration swimTime = Duration.parse(memberInfo[1]);
                    LocalDate dateOfSwim = LocalDate.parse(memberInfo[2]);
                    // TODO måske ligger der en fejl her
                    SwimmingDiscipline swimmingDiscipline = SwimmingDiscipline.valueOf(memberInfo[3]);


                    CompetitiveMember træningsResultat = new CompetitiveMember(memberNumber, swimTime, dateOfSwim,  swimmingDiscipline);
                    loadedTræningsResultater.add(træningsResultat);
                } else {

                }

            }
        }catch (FileNotFoundException | NumberFormatException | NoSuchElementException e) {
            System.err.println("Fejl: " + e.getMessage());
        }
        return loadedTræningsResultater(fileName);
    }




    public void saveListOfMembersToFile(String fileName, ArrayList<Member> members) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for (Member member : members) {
                String memberInfo = member.getName() + "," +
                        member.getDateOfBirth().format(formatter) + "," +
                        member.getGender() + "," +
                        member.getPhonenumber() + "," +
                        member.getAdress() + "," +
                        member.getMemberNumber() + "," +
                        member.getPassiveOrActive() + "," +
                        member.getMemberType() + "," +
                        member.getMotionist();

                writer.println(memberInfo);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Fejl: " + e.getMessage());
        }
    }


    public ArrayList<CompetitiveMember> loadedCompetitiveMember() {
        ArrayList<CompetitiveMember> loadedCompetitiveMember = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNext()) {
                String swimTimeString = fileScanner.nextLine();
                String dateOfSwimString = fileScanner.nextLine();
                String swimmingDisciplineString = fileScanner.nextLine();

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

    public void saveListOfKokurrenceTidToFile(String fileName, ArrayList<CompetitiveMember> compMember) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName, true)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            for (CompetitiveMember CompMember : compMember) {
                for (Member member : compMember) {
                    String memberInfo = member.getMemberNumber() + "," +
                            CompMember.getSwimTime() + "," +
                            CompMember.getDateOfSwim() + "," +
                            CompMember.getSwimmingDiscipline() + "," +
                            CompMember.getEventName() + "," +
                            CompMember.getEventPlacement();

                    memberInfo += System.lineSeparator();
                    fileOutputStream.write(memberInfo.getBytes());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void saveListOfTræningsTidToFile(String fileName, ArrayList<CompetitiveMember> compMember) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName, true)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            for (CompetitiveMember CompMember : compMember) {
                for (Member member : compMember) {
                    String memberInfo = member.getMemberNumber() + "," +
                            CompMember.getSwimTime() + "," +
                            CompMember.getDateOfSwim() + "," +
                            CompMember.getSwimmingDiscipline();


                    memberInfo += System.lineSeparator();
                    fileOutputStream.write(memberInfo.getBytes());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    public ArrayList<Member> loadUsedMemberNumbers(String fileName, ArrayList<CompetitiveMember> coompMeembers) {

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] memberInfo = line.split(",");


                int memberNumber = Integer.parseInt(memberInfo[0]);

                Member usedMembernumber = new Member(memberNumber);
                coompMeembers.add((CompetitiveMember) usedMembernumber);
            }
        } catch (FileNotFoundException | NumberFormatException | NoSuchElementException e) {
            System.err.println("Fejl: " + e.getMessage());
        }

        return loadUsedMemberNumbers(fileName, coompMeembers);
    }


    public void saveListOfMemberNumbersToFile(String fileName, ArrayList<Member> usedMemberNumbers) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName, true)) {
            for (Member memberNumbers : usedMemberNumbers) {
                String memberInfo =
                        memberNumbers.getUsedMemberNumbers() + ",";

                memberInfo += System.lineSeparator();
                fileOutputStream.write(memberInfo.getBytes());
            }
        } catch (IOException e) {
            System.err.println("Fejl: " + e.getMessage());
        }
    }
}
