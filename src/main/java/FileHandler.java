import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
                    String motionist = memberInfo[7];


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
                String line = fileScanner.nextLine().trim();
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] memberInfo = line.split(",");

                if (memberInfo.length >= 1) {
                    int memberNumber = Integer.parseInt(memberInfo[0]);
                    Duration swimTime = parseDuration(memberInfo[1]);
                    LocalDate dateOfSwim = parseDate(memberInfo[2]);
                    SwimmingDiscipline swimmingDiscipline = SwimmingDiscipline.valueOf(memberInfo[3]);

                    CompetitiveMember træningsResultat = new CompetitiveMember(memberNumber, swimTime, dateOfSwim, swimmingDiscipline);
                    loadedTræningsResultater.add(træningsResultat);
                } else {
                }
            }
        } catch (FileNotFoundException | NumberFormatException | NoSuchElementException e) {
            System.err.println("Fejl: " + e.getMessage());
        }
        return loadedTræningsResultater;
    }



    public ArrayList<CompetitiveMember> loadedCompetitiveMember(String fileName) {
        ArrayList<CompetitiveMember> loadedCompetitiveMember = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] memberInfo = line.split(",");

                if (memberInfo.length >= 5) {
                int memberNumber = Integer.parseInt(memberInfo[0]);
                Duration swimTime = parseDuration(memberInfo[1]);
                LocalDate dateOfSwim = parseDate(memberInfo[2]);
                SwimmingDiscipline swimmingDiscipline = SwimmingDiscipline.valueOf(memberInfo[3]);
                String eventName = memberInfo[4];
                String eventPlacement = memberInfo[5];



                CompetitiveMember træningMember = new CompetitiveMember(memberNumber, swimTime, dateOfSwim, swimmingDiscipline, eventName, eventPlacement);
                loadedCompetitiveMember.add(træningMember);
            } else {
            }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Fejl: " + e.getMessage());
        }


        return loadedCompetitiveMember;
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






    public void saveListOfKokurrenceTidToFile(String fileName, ArrayList<CompetitiveMember> compMembers) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            for (CompetitiveMember compMember : compMembers) {
                String swimTimeFormatted = formatDuration(compMember.getSwimTime());

                String memberInfo = compMember.getMemberNumber() + "," +
                        swimTimeFormatted + "," +
                        compMember.getDateOfSwim().format(formatter) + "," +
                        compMember.getSwimmingDiscipline() + "," +
                        compMember.getEventName() + "," +
                        compMember.getEventPlacement();

                writer.println(memberInfo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveListOfTræningsTidToFile(String fileName, ArrayList<CompetitiveMember> compMembers) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            for (CompetitiveMember compMember : compMembers) {
                String swimTimeFormatted = formatDuration(compMember.getSwimTime());

                String memberInfo = compMember.getMemberNumber() + "," +
                        swimTimeFormatted + "," +
                        compMember.getDateOfSwim().format(formatter) + "," +
                        compMember.getSwimmingDiscipline();

                writer.println(memberInfo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = (duration.toMinutes() % 60);
        long seconds = (duration.getSeconds() % 60);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }



    private Duration parseDuration(String durationString) {
        String[] components = durationString.split(":");

        // Assuming the format is "HH:mm:ss"
        long hours = Long.parseLong(components[0]);
        long minutes = Long.parseLong(components[1]);
        long seconds = Long.parseLong(components[2]);

        return Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds);
    }
    public static LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dateString, formatter);
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





}
