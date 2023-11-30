import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// systemet
public class Userinterface {
    private final Controller controller;

    private Scanner keyboard = new Scanner(System.in);

    public Userinterface(Controller controller) {
        this.controller = controller;
    }


    public void menu() {
        System.out.println("""
                Velkommen til Delfinen-klubbens systemet.
                Hvilke rolle har du i klubben?
                1: Træner
                2: Formand
                3: Kassere
                9: Afslut program
                """);
    }

    public void start() {

        while (true) {
            menu();

            switch (keyboard.nextInt()) {
                case 1:
                    trænerMenu();
                    break;
                case 2:
                    formandMenu();
                    break;
                case 3:
                    kassereMenu();
                    break;
                case 9:
                    exitProgram();
                    break;
            }
        }
    }

    public void trænerMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    1: Vis alle medlemmers resultaterne
                    2: registrer resultat af en medlem
                    3: afslut programmet
                    """);
            switch (keyboard.nextInt()) {

                case 1:
                    //printResultater();
                    break;
                case 2:
                    indtastResultater();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Ugyldigt valg prøv igen");
                    break;
            }
        }
    }

    public void formandMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    1: Vis alle medlemmer
                    2: registrer medlem
                    3: afslut programmet
                    """);
            switch (keyboard.nextInt()) {

                case 1:
                    medlemmerStamoplysninger();
                    break;
                case 2:
                    registrerMedlem();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Ugyldigt valg prøv igen");
                    break;
            }
        }
    }


    public void kassereMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    1:
                    2:
                    3: afslut programmet
                    """);
            switch (keyboard.nextInt()) {
                case 1:

                case 2:

                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Ugyldigt valg prøv igen");
                    break;
            }
        }
    }

    public void medlemmerStamoplysninger() {
        List<Member> members = controller.getMembers();

        controller.sortMembersByAge(members);

        boolean adskillelseLinjeOU18 = false;


        System.out.println("Listen af medlemmer : \n Under 18 årige: \n-------------------");
        for (Member member : members) {
            if (controller.calculateAge(member.getDateOfBirth()) >= 18 && !adskillelseLinjeOU18) {
                System.out.println("Over 18 år: \n-------------------");
                adskillelseLinjeOU18 = true;
            }
            System.out.println("navn: " + member.getName());
            System.out.println("Fødselsår: " + member.getDateOfBirth());
            System.out.println("køn: " + member.getGender());
            System.out.println("telefon: " + member.getPhonenumber());
            System.out.println("Adresse: " + member.getAdress());
            System.out.println("Medlemsnummer: " + member.getMemberNumber());
            System.out.println("Medlemsstatus: " + member.getMemberType());
            System.out.println("Motionist: " + member.getMotionist());
            System.out.println("konkurrencesvømmer: " + member.getCompetitive());
            System.out.println();
        }
    }


    public void registrerMedlem() {
        keyboard.nextLine();

        System.out.print("Navn: ");
        String name = keyboard.nextLine();

        String dateOfBirth = getValidStringInputFødselsdato("Fødselsdato i format (dd-mm-yyyy): ");

        System.out.print("Køn: ");
        String gender = keyboard.nextLine();

        int phonenumber = getValidIntegerInputTelefonnummer("Telefonnummer: ");

        System.out.print("Adresse: ");
        String adress = keyboard.nextLine();

        int memberNumber = getValidIntegerInputMedlemsnummer("Medlemsnummer: ");

        System.out.print("Passivt eller aktivt medlemskab: ");
        String passiveOrActive = getValidInputForAktivPassiv();
        String memberType = "";

        if ("aktivt".equalsIgnoreCase(passiveOrActive)) {
            memberType = getValidInputForAktivMedlem();
        }

        System.out.print("Motionist: ");
        String motionist = keyboard.nextLine();

        System.out.print("Konkurrence: ");
        String competitive = keyboard.nextLine();


        controller.registrerMedlem(name, dateOfBirth, gender, phonenumber, adress, memberNumber, passiveOrActive, memberType, motionist, competitive);


        System.out.println("Går ud af registrermedlem metoden");


    }

    //TODO resultater af alle træningens tiderne, så træneren kan sætte de bedste ind i konkurrence.
    public void resultater() {
        System.out.println();
    }

    public void indtastResultater() {
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    1: registrer resultater af en træning
                    2: registrer resultater af en konkurrence
                    3: afslut programmet
                    """);
            switch (keyboard.nextInt()) {

                case 1:
                    //registrerTræningTid();
                    break;
                case 2:
                    registrerKonkurrenceTid();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("ugyldigt valg ");

            }
        }
    }

    /*public void registrerTræningTid() {
        keyboard.nextLine();

        System.out.print("Svømmetid (hh:mm:ss): ");
        String svømmeTidInput = keyboard.nextLine();

        LocalTime swimTime = LocalTime.parse(svømmeTidInput, DateTimeFormatter.ofPattern("HH:mm:ss"));


        String svømmeDatoInput = getValidStringInputSvømmeDato("Svømmedato (dd-MM-yyyy): ");
        System.out.println("Indtastet dato: " + svømmeDatoInput);

        LocalDate dateOfSwim = LocalDate.parse(svømmeDatoInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        System.out.println("Svømmedisciplin: vælg en af disse: ");
        SwimmingDiscipline swimmingDiscipline = getValidSwimmingDiscipline();

        controller.registrerTræningTid(swimTime, dateOfSwim, swimmingDiscipline);
    }
*/

    public void registrerKonkurrenceTid() {
        keyboard.nextLine();

        System.out.println("Svømmetid (hh:mm:ss): ");
        String svømmeTidInputKonkurrence = keyboard.nextLine();

        LocalTime swimTime = LocalTime.parse(svømmeTidInputKonkurrence, DateTimeFormatter.ofPattern("HH:mm:ss"));

        String svømmeDatoKonkurrence = getValidStringInputSvømmeDato("Konkurrence dato: ");

        System.out.println("Svømmedisciplin: ");
        SwimmingDiscipline svømmeDiciplinKonkurrence = getValidSwimmingDiscipline();



    }


    //TODO top 5 til at træneren kan se på de bedste 5 i hver disciple.
    public void top5Svimmers() {
        System.out.println();
    }

    public SwimmingDiscipline getValidSwimmingDiscipline() {
        while (true) {
            try {
                //TODO menu med 1-4 ?
                System.out.print("Vælg svømmedisciplin (BUTTERFLY, FRONT_CRAWL, BACKSTROKE, BREASTSTROKE): ");
                String input = keyboard.nextLine().toUpperCase();

                //TODO skifter til switch case?? Så man kan vælge fra 1. til 4. ud af de disciplerne.
                for (SwimmingDiscipline discipline : SwimmingDiscipline.values()) {
                    if (discipline.name().equalsIgnoreCase(input)) {
                        return discipline;
                    }
                }

                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig indtastning. Vælg venligst en gyldig svømmedisciplin.");
            }
        }
    }


    private LocalDate parseLocalDate(String input) {
        try {
            return LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date input: " + input);
            System.out.println("Error details: " + e.getMessage());
            return LocalDate.MIN;
        }
    }
    private String getValidStringInputSvømmeDato(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                String input = keyboard.nextLine();

                LocalDate parsedDate = parseLocalDate(input);
                if (!parsedDate.equals(LocalDate.MIN)) {
                    return input;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Ugyldig input. Indtast venligst en svømmedato i formatet (dd-MM-yyyy).");
            }
        }
    }

    private String getValidStringInputFødselsdato(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = keyboard.nextLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate.parse(input, formatter);

                return input;
            } catch (DateTimeParseException e) {
                System.out.println("Ugyldig input. Indtast venligst en fødselsdato i formatet (dd-mm-yyyy).");
            }
        }
    }

    // metode for at sørger for man indtaster telefonnummer rigtigt ind
    private int getValidIntegerInputTelefonnummer(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = keyboard.nextInt();
                keyboard.nextLine();

                if (String.valueOf(input).length() == 8)
                    return input;
                else {
                    System.out.println("Ugyldig input. Indtast venligst et telefonnummer på 8 cifre.");
                }
            } catch (java.util.InputMismatchException e) {
                // eksempel på error besked (kan laves om)
                System.out.println("Ugyldig input. Indtast venligst et telefonnummer på 8 cifre.");
                keyboard.nextLine();
            }
        }
    }

    private int getValidIntegerInputMedlemsnummer(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = keyboard.nextInt();
                keyboard.nextLine();
                if (String.valueOf(input).length() == 6)
                    return input;
                else {
                    System.out.println("Ugyldig input. Indtast venligst et medlemsnummer på 6 cifre.");

                }
            } catch (java.util.InputMismatchException e) {
                //vi skal spørger hvor mange cifre medlemsnummer har!!
                System.out.println("Ugyldig input. Indtast venligst et medlemsnummer på 6 cifre.");
                keyboard.nextLine();
            }
        }
    }

    private String getValidInputForAktivPassiv() {
        while (true) {
            try {
                System.out.print("Passivt eller aktivt medlemskab: ");
                String input = keyboard.nextLine().toLowerCase();

                if ("passivt".equalsIgnoreCase(input)) {
                    System.out.println("Medlemmer med et passivt medlemskab skal betale 600kr i årligt kontingent");
                    return "Passivt";
                } else if ("aktivt".equalsIgnoreCase(input)) {
                    return "Aktivt";
                } else {
                    System.out.println("Ugyldigt input. Indtast venligst 'aktivt' eller 'passivt'.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Ugyldigt input. Indtast venligst 'aktivt' eller 'passivt'.");
            }
        }
    }

    private String getValidInputForAktivMedlem() {
        while (true) {
            try {
                System.out.println("Hvilken medlemstype hører personen til?");
                System.out.println("Ungdomssvømmer u18" + "\n" + "Ungdomssvømmer o18" + "\n" + "Seniorsvømmer");
                String memberType = keyboard.nextLine();

                if ("ungdomssvømmer u18".equalsIgnoreCase(memberType) ||
                        "ungdomssvømmer o18".equalsIgnoreCase(memberType) ||
                        "seniorsvømmer".equalsIgnoreCase(memberType)) {
                    return memberType;
                } else {
                    System.out.println("Ugyldig medlemstype. Indtast venligst en korrekt medlemstype.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Ugyldigt input. Indtast venligst en korrekt medlemstype");
            }
        }
    }

    private void exitProgram() {
        System.out.println("Afslutter programmet.");
        System.exit(0);
    }
}
/*private LocalTime parseLocalTime(String input) {
        try {
            return LocalTime.parse(input, DateTimeFormatter.ofPattern("HH:mm:ss"));
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Invalid input for svømmetid. Please enter time in hh:mm:ss format.");
            return LocalTime.MIN; // or throw an exception, or handle it accordingly
        }
    }
*/