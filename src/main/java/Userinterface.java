import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

// systemet
public class Userinterface {
    private final Controller controller;
    private List<Member> members;


    private Scanner keyboard = new Scanner(System.in);

    public Userinterface(Controller controller, List<Member> members) {
        this.controller = controller;
        this.members = members;
    }

    private final String ADMIN_PASSWORD = "123";

    private boolean checkPassword() {
        System.out.print("Enter password: ");
        String enteredPassword = keyboard.next();
        return enteredPassword.equals(ADMIN_PASSWORD);
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
                    if (checkPassword()) {
                        trænerMenu();
                    } else {
                        System.out.println("Forkert adgangskode, adgang nægtet");
                    }
                    break;
                case 2:
                    if (checkPassword()) {
                        formandMenu();
                    } else {
                        System.out.println("Forkert adgangskode, adgang nægtet");
                    }
                    break;
                case 3:
                    if (checkPassword()) {
                        kassereMenu();
                    } else {
                        System.out.println("Forkert adgangskode, adgang nægtet");
                    }
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
                    3: se Kokurrence Hold
                    4: exit
                    """);
            switch (keyboard.nextInt()) {

                case 1:
                    seTidsResultater();
                    break;
                case 2:
                    indtastResultater();
                    break;
                case 3:
                    seKokurrenceHold();
                    break;
                case 4:
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
                    3: rediger medlem
                    4: afslut programmet
                    """);
            switch (keyboard.nextInt()) {

                case 1:
                    medlemmerStamoplysninger();
                    break;
                case 2:
                    registrerMedlem();
                    break;
                case 3:
                    editMemberAttribute();
                    break;
                case 4:
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
                    1: se årlig indkomst
                    2: se medlemsstatus og gebyr
                    3: se oversigt for manglende betalinger
                    4: afslut programmet
                    """);
            switch (keyboard.nextInt()) {
                case 1:
                    displayYearlyIncome();
                    break;
                case 2:
                    displayMembershipStatusAndFees();
                    break;
                case 3:
                    showMembershipRenewalMenu();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Ugyldigt valg prøv igen");
                    break;
            }
        }
    }

    public void medlemmerStamoplysninger() {
        ArrayList<Member> members = controller.getMembers();

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
            System.out.println("Motionist eller Konkurrence: " + member.getMotionist());
            System.out.println();
        }
    }


    public void registrerMedlem() {
        keyboard.nextLine();

        System.out.print("Navn: ");
        String name = keyboard.nextLine();

        System.out.print("Fødselsdato i format (dd-mm-yyyy): ");
        String dateOfBirth = getValidStringInputFødselsdato();

        System.out.print("Køn: ");
        String gender = keyboard.nextLine();

        int phonenumber = getValidIntegerInputTelefonnummer("Telefonnummer: ");

        System.out.print("Adresse: ");
        String adress = keyboard.nextLine();


        int memberNumber = controller.generateMemberNumber();
        System.out.println("Autogenereret medlemsnummer: " + memberNumber);

        System.out.print("Er det et aktivt medlemskab?");
        String passiveOrActive = getValidInputForAktivPassiv();
        String memberType = checkIfMemberIsOverOrUnder18(dateOfBirth);


        if ("aktivt".equalsIgnoreCase(passiveOrActive)) {
            memberType = checkIfMemberIsOverOrUnder18(dateOfBirth);
        }

        System.out.print("Motionist eller Konkurrencesvømmer: ");
        String motionist = checkIfMotionistOrCompetitive();


        controller.registrerMedlem(name, dateOfBirth, gender, phonenumber, adress, memberNumber, passiveOrActive, memberType, motionist);


        System.out.println("Går ud af registrermedlem metoden");


    }


    //TODO resultater af alle træningens tiderne, så træneren kan sætte de bedste ind i konkurrence.

    public void seTidsResultater() {
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    1: se resultater fra træning 
                    2: se resultater fra konkurrence 
                    3: afslut programmet 
                    """);
            // resultater fra træning i FRONTCRAWL over 18
// resultater fra træning i FRONTCRAWL under 18
// resultater fra træning i BACKSTROKE over 18
// resultater fra træning i BACKSTROKE under 18
// resultater fra træning i BUTTERFLY over 18
// resultater fra træning i BUTTERFLY under 18
// resultater fra træning i BREASTSTROKE over 18
// resultater fra træning i BREASTSTROKE under 18


            // resultater fra konkurrence i FRONTCRAWL over 18
// resultater fra konkurrence i FRONTCRAWL under 18
// resultater fra konkurrence i BACKSTROKE over 18
// resultater fra konkurrence i BACKSTROKE under 18
// resultater fra konkurrence i BUTTERFLY over 18
// resultater fra konkurrence i BUTTERFLY under 18
// resultater fra konkurrence i BREASTSTROKE over 18
// resultater fra konkurrence i BREASTSTROKE under 18
            switch (keyboard.nextInt()) {

                case 1:
                    seResultaterTræning();
                    break;
                case 2:
                    seResultaterKonkurrence();
                    break;
                    case 3:
                        visTop5SvømmetiderEfterStil();
                        break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("ugyldigt valg ");

            }
        }
    }
    public void visTop5SvømmetiderEfterStil() {
        // Assuming there are four swimming styles: Butterfly, Backstroke, Breaststroke, and Freestyle
        SwimmingDiscipline[] swimmingStyles = SwimmingDiscipline.values();

        for (SwimmingDiscipline style : swimmingStyles) {
            System.out.println("Top 5 fastest times in " + style + ":");
            List<CompetitiveMember> top5Times = controller.getTop5SwimTimes(style);

            if (top5Times.isEmpty()) {
                System.out.println("No times recorded for this style.");
            } else {
                for (int i = 0; i < Math.min(5, top5Times.size()); i++) {
                    CompetitiveMember member = top5Times.get(i);
                    System.out.println((i + 1) + ". Member: " + member.getMemberNumber() +
                            ", Time: " + member.getSwimTime() +
                            ", Date: " + member.getDateOfSwim());
                }
            }

            System.out.println();  // Add a newline between styles
        }
    }


    public void seResultaterTræning() {
        ArrayList<CompetitiveMember> compMembers = controller.getCompMeembersEvent();

        compMembers.sort(Comparator.comparing(CompetitiveMember::getSwimTime));

        System.out.println("Sorteret træningsmedlemmer efter svømmetid: ");
        for (CompetitiveMember CompMember : compMembers) {
            System.out.println("Member Number: " + CompMember.getMemberNumber());
            System.out.println("Swim Time: " + CompMember.getSwimTime());
            System.out.println("Dato: " + CompMember.getDateOfSwim());
            System.out.println("Svømmedisciplin: " + CompMember.getSwimmingDiscipline());
            System.out.println();
        }


    }
    public void seResultaterKonkurrence () {
        ArrayList<CompetitiveMember> members = controller.getCompMeembersEvent();

        controller.sortTrainingMembersBySwimTime();
        System.out.println("Sorteret eventmedlemmer efter svømmetid: ");
        for (CompetitiveMember CompMember : members) {
            System.out.println("Member Number: " + CompMember.getMemberNumber());
            System.out.println("Swim Time: " + CompMember.getSwimTime());
            System.out.println("Dato: " + CompMember.getDateOfSwim());
            System.out.println("Svømmedisciplin: " + CompMember.getSwimmingDiscipline());
            System.out.println("Event navn: " + CompMember.getEventName());
            System.out.println("Arrangementsplacering: " + CompMember.getEventPlacement());
            System.out.println();

        }
    }


        public void indtastResultater () {
            boolean exit = false;
            while (!exit) {
                System.out.println("""
                        1: registrer resultater af en træning
                        2: rediger resultater af en træning
                        3: registrer resultater af en konkurrence
                        4: rediger resultater af en konkurrence
                        5: afslut programmet
                        """);
                switch (keyboard.nextInt()) {

                    case 1:
                        indtastResultaterTræning();
                        break;
                    case 2:
                        redigerResultaterTræning();
                        break;
                    case 3:
                        indtastResultaterKonkurrence();
                        break;
                    case 4:
                        redigerResultaterKonkurrence();
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("ugyldigt valg ");

                }
            }
        }

    public void redigerResultaterTræning() {
        int selectedMemberNumber = getValidIntegerInputTræningMedlemsnummer("Vælg det medlemsnummer du vil redigere:");

        CompetitiveMember competitiveMember = controller.getCompetitiveMemberByMemberNumberEvent(selectedMemberNumber);

        if (competitiveMember != null) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Edit Member Attribute Menu:");
            System.out.println("1. Edit Swim Time");
            System.out.println("2. Edit Date of Birth");
            System.out.println("3. Edit Swimming Discipline");
            System.out.println("4. Edit Event Name");
            System.out.println("5. Edit Event Location");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: " + "\n");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the new swim time: ");
                    Duration newSwimTime = parseDuration(scanner.nextLine());
                    competitiveMember.editSwimTime(String.valueOf(newSwimTime));
                    break;
                case 2:
                    System.out.print("Enter the new swimming date (dd-MM-yyyy): ");
                    LocalDate newDateOfSwim = parseDate(scanner.nextLine());
                    competitiveMember.editDateOfSwim(String.valueOf(newDateOfSwim));
                    break;
                case 3:
                    System.out.print("Enter the new swimming discipline: ");
                    SwimmingDiscipline newSwimmingDiscipline = SwimmingDiscipline.valueOf(scanner.nextLine());
                    competitiveMember.editSwimmingDiscipline(String.valueOf(newSwimmingDiscipline));
                    break;
                case 4:
                    System.out.print("Enter the new event name: ");
                    String newEventName = scanner.nextLine();
                    competitiveMember.editEventName(newEventName);
                    break;
                case 5:
                    System.out.print("Enter the new event location: ");
                    String newEventPlacement = scanner.nextLine();
                    competitiveMember.editEventPlacement(newEventPlacement);
                    break;
                case 0:
                    System.out.println("Exiting edit menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

            controller.updateTræningsTid(competitiveMember);
        } else {
            System.out.println("Selected member is not a CompetitiveMember or does not exist.");
        }
    }




    public void redigerResultaterKonkurrence() {
        int selectedMemberNumber = getValidIntegerInputKonkurrenceMedlemsnummer("Vælg det medlemsnummer du vil redigere:");

        CompetitiveMember competitiveMember = controller.getCompetitiveMemberByMemberNumberEvent(selectedMemberNumber);

        if (competitiveMember != null) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Edit Member Attribute Menu:");
            System.out.println("1. Edit Swim Time");
            System.out.println("2. Edit Date of Birth");
            System.out.println("3. Edit Swimming Discipline");
            System.out.println("4. Edit Event Name");
            System.out.println("5. Edit Event Location");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: " + "\n");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the new swim time: ");
                    Duration newSwimTime = parseDuration(scanner.nextLine());
                    competitiveMember.editSwimTime(String.valueOf(newSwimTime));
                    break;
                case 2:
                    System.out.print("Enter the new swimming date (dd-MM-yyyy): ");
                    LocalDate newDateOfSwim = parseDate(scanner.nextLine());
                    competitiveMember.editDateOfSwim(String.valueOf(newDateOfSwim));
                    break;
                case 3:
                    System.out.print("Enter the new swimming discipline: ");
                    SwimmingDiscipline newSwimmingDiscipline = SwimmingDiscipline.valueOf(scanner.nextLine());
                    competitiveMember.editSwimmingDiscipline(String.valueOf(newSwimmingDiscipline));
                    break;
                case 4:
                    System.out.print("Enter the new event name: ");
                    String newEventName = scanner.nextLine();
                    competitiveMember.editEventName(newEventName);
                    break;
                case 5:
                    System.out.print("Enter the new event location: ");
                    String newEventPlacement = scanner.nextLine();
                    competitiveMember.editEventPlacement(newEventPlacement);
                    break;
                case 0:
                    System.out.println("Exiting edit menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

            controller.updateKonkurrenceTid(competitiveMember);
        } else {
            System.out.println("Selected member is not a CompetitiveMember or does not exist.");
        }
    }





        }

    public void seKokurrenceHold () {
            boolean exit = false;
            while (!exit) {
                System.out.println("""
                        1: se kokurrence hold over 18
                        2: se kokurrence hold under 18
                        3: afslut programmet
                        """);
                switch (keyboard.nextInt()) {

                    case 1:
                        printCompMembersOver18();
                        break;
                    case 2:
                        printCompMembersUnder18();
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("ugyldigt valg ");

                }
            }
        }

        public void printCompMembersUnder18 () {
            List<Member> competitiveMembersUnder18 = controller.getCompetitiveMembersUnder18();


            for (Member member : competitiveMembersUnder18) {
                System.out.println(member);
            }
        }

        public void printCompMembersOver18 () {
            List<Member> competitiveMembersOver18 = controller.getCompetitiveMembersOver18();

            for (Member member : competitiveMembersOver18) {
                System.out.println(member);
            }


        }

        public void indtastResultaterTræning () {


            int selectedMemberNumber = getValidIntegerInputMedlemsnummer("Select Member Number from the list: ");
            System.out.println("Selected Member Number: " + selectedMemberNumber);
            System.out.println();

            if (controller.memberExists(selectedMemberNumber)) {
                System.out.println("Svømmetid (hh:mm:ss): ");
                String svømmeTidInputKonkurrence = keyboard.nextLine();

                Duration svimTime = parseDuration(svømmeTidInputKonkurrence);

                String dateOfSwim = getValidStringInputSvømmeDato("Konkurrence dato: DD-MM-ÅÅÅÅ");

                System.out.println("Svømmedisciplin: ");
                SwimmingDiscipline swimmingDiscipline = chooseBetweenSwimmingStyles();

                controller.registrerTræningsResultat(selectedMemberNumber, svimTime, LocalDate.parse(dateOfSwim, DateTimeFormatter.ofPattern("dd-MM-yyyy")), swimmingDiscipline);
                System.out.println("Member successfully registered.");

            }
        }

        // TODO Den skal vise en liste som man skal vælge fra med navn og meldemsnummer også derefter sætte tider og det til
        public void indtastResultaterKonkurrence () {

            int selectedMemberNumber = getValidIntegerInputMedlemsnummer("Vælg det medlemsnummer du vil tiføje en tid til: ");
            System.out.println("Selected Member Number: " + selectedMemberNumber);
            System.out.println();

            if (controller.memberExists(selectedMemberNumber)) {
                System.out.println("Svømmetid (hh:mm:ss): ");
                String svømmeTidInputKonkurrence = keyboard.nextLine();

                Duration svimTime = parseDuration(svømmeTidInputKonkurrence);

                String dateOfSwim = getValidStringInputSvømmeDato("Konkurrence dato: DD-MM-ÅÅÅÅ");

                System.out.println("Svømmedisciplin: ");
                SwimmingDiscipline swimmingDiscipline = chooseBetweenSwimmingStyles();

                System.out.println("Event navn: ");
                String eventName = getValidEventName();

                System.out.println("Event placering: ");
                String eventPlacement = getValidEventPlacement();

                controller.registrerEventResultat(selectedMemberNumber, svimTime, LocalDate.parse(dateOfSwim, DateTimeFormatter.ofPattern("dd-MM-yyyy")), swimmingDiscipline, eventName, eventPlacement);
                System.out.println("Member successfully registered.");

            }
        }


        public String getValidEventName () {
            if (keyboard.hasNextLine()) {
                String input = keyboard.nextLine();
                return input;
            }
            return getValidEventName();
        }

        public String getValidEventPlacement () {
            if (keyboard.hasNextLine()) {
                String input = keyboard.nextLine();
                return input;
            }
            return getValidEventPlacement();
        }

        public int getValidIntegerInputMedlemsnummer (String prompt){
            ArrayList<Member> members = controller.getMembers();

            System.out.println("List of Member Numbers:");
            for (int i = 0; i < members.size(); i++) {
                Member member = members.get(i);
                System.out.println((i + 1) + ". Member Number: " + member.getMemberNumber());
            }

            while (true) {
                try {
                    System.out.print(prompt);
                    int inputString = keyboard.nextInt();
                    keyboard.nextLine();

                    int input = Integer.parseInt(String.valueOf(inputString));

                    if (String.valueOf(input).length() == 6) {
                        if (controller.memberExists(input)) {
                            return input;
                        } else {
                            System.out.println("Medlemsnummeret findes ikke i systemet.");
                        }
                    } else {
                        System.out.println("Ugyldig input. Indtast venligst et medlemsnummer på 6 cifre.");
                    }
                } catch (NumberFormatException e) {
                    keyboard.nextLine();
                    System.out.println("Ugyldig input. Indtast venligst et medlemsnummer på 6 cifre.");
                }
            }
        }
    public int getValidIntegerInputKonkurrenceMedlemsnummer(String prompt) {
        ArrayList<CompetitiveMember> compMembers = controller.getCompMeembersEvent();

        System.out.println("List of Competitive Member Numbers:");
        for (int i = 0; i < compMembers.size(); i++) {
            CompetitiveMember compMember = compMembers.get(i);
            System.out.println((i + 1) + ". Member Number: " + compMember.getMemberNumber());
        }

        while (true) {
            try {
                System.out.print(prompt);
                int inputString = keyboard.nextInt();
                keyboard.nextLine();

                int input = Integer.parseInt(String.valueOf(inputString));

                if (String.valueOf(input).length() == 6) {
                    if (controller.getCompetitiveMemberByMemberNumberEvent(input) != null) {
                        return input;
                    } else {
                        System.out.println("Competitive member with the specified member number does not exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a member number with 6 digits.");
                }
            } catch (NumberFormatException e) {
                keyboard.nextLine();
                System.out.println("Invalid input. Please enter a member number with 6 digits.");
            }
        }
    }

    public int getValidIntegerInputTræningMedlemsnummer(String prompt) {
        ArrayList<CompetitiveMember> compMembers = controller.getCompMeembersTræning();

        System.out.println("List of Competitive Member Numbers:");
        for (int i = 0; i < compMembers.size(); i++) {
            CompetitiveMember compMember = compMembers.get(i);
            System.out.println((i + 1) + ". Member Number: " + compMember.getMemberNumber());
        }

        while (true) {
            try {
                System.out.print(prompt);
                int inputString = keyboard.nextInt();
                keyboard.nextLine();

                int input = Integer.parseInt(String.valueOf(inputString));

                if (String.valueOf(input).length() == 6) {
                    if (controller.getCompetitiveMemberByMemberNumbertræning(input) != null) {
                        return input;
                    } else {
                        System.out.println("Competitive member with the specified member number does not exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a member number with 6 digits.");
                }
            } catch (NumberFormatException e) {
                keyboard.nextLine();
                System.out.println("Invalid input. Please enter a member number with 6 digits.");
            }
        }
    }







    //TODO top 5 til at træneren kan se på de bedste 5 i hver disciple.
        public void top5Svimmers () {
            System.out.println();
        }

       /* public SwimmingDiscipline getValidSwimmingDiscipline () {
            while (true) {
                try {
                    //TODO menu med 1-4 ?
                    System.out.print("Vælg svømmedisciplin (Butterfly, Crawl, Backstroke, Breaststroke): ");
                    String input = chooseBetweenSwimmingStyles();

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
        }*/


        public SwimmingDiscipline chooseBetweenSwimmingStyles () {
            System.out.println("Vælg mellem Butterfly, Front Crawl, Backstroke, Breaststroke");
            String input = keyboard.nextLine().toUpperCase();
            try {
                return SwimmingDiscipline.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig indtastning. Prøv igen.");
                return chooseBetweenSwimmingStyles();
            }
        }


        private Duration parseDuration (String input){
            String[] timeComponents = input.split(":");
            if (timeComponents.length == 3) {
                try {
                    long hours = Long.parseLong(timeComponents[0]);
                    long minutes = Long.parseLong(timeComponents[1]);
                    long seconds = Long.parseLong(timeComponents[2]);

                    return Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for svømmetid. Please enter time in hh:mm:ss format.");
                }
            } else {
                System.out.println("Invalid input for svømmetid. Please enter time in hh:mm:ss format.");
            }

            return Duration.ZERO;
        }


        private String getValidStringInputSvømmeDato (String prompt){

            while (true)
                try {

                    System.out.println(prompt);
                    String input = keyboard.nextLine();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate.parse(input, formatter);

                    return input;

                } catch (DateTimeParseException e) {
                    System.out.println("Ugyldig input. Indtast venligst en svømmedato i formatet (dd-mm-yyyy).");
                }


        }

        private String getValidStringInputFødselsdato () {
            while (true) {
                try {
                    //System.out.print(prompt);
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
        private int getValidIntegerInputTelefonnummer (String prompt){
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


        private String getValidInputForAktivPassiv () {
            while (true) {
                try {

                    String input = keyboard.nextLine().toLowerCase();

                    if ("Nej".equalsIgnoreCase(input)) {
                        System.out.println("Medlemmer med et passivt medlemskab skal betale 600kr i årligt kontingent");
                        return "Passivt";
                    } else if ("Ja".equalsIgnoreCase(input)) {
                        return "Aktivt";
                    } else {
                        System.out.println("Ugyldigt input. Indtast venligst 'Ja' eller 'Nej'.");
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Ugyldigt input. Indtast venligst 'Ja' eller 'Nej'.");
                }
            }
        }

        private String checkIfMemberIsOverOrUnder18 (String dateOfBirth){

            LocalDate birthdate = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            LocalDate currentDate = LocalDate.now();

            long age = ChronoUnit.YEARS.between(birthdate, currentDate);

            if (age <= 18) {
                return "Ungdomssvømmer u18";
            } else if (age > 18 && age <= 60) {
                return "Ungdomssvømmer o18";
            } else {
                return "Senior";
            }
        }

        private String checkIfMotionistOrCompetitive () {
            while (true) {
                try {
                    String input = keyboard.nextLine().toLowerCase();

                    if ("Nej".equalsIgnoreCase(input)) {
                        return "Konkurrence";
                    } else if ("Ja".equalsIgnoreCase(input)) {
                        return "Motionist";
                    } else {
                        System.out.println("Ugyldigt input. Indtast venligst 'Ja' eller 'Nej'.");
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Ugyldigt input. Indtast venligst 'Ja' eller 'Nej'.");
                }
            }
        }


        // Kasseren metoderne.
        public void displayYearlyIncome () {
            int yearlyIncome = controller.calculateYearlyIncome();
            System.out.println("Forventet Årlig Indtægt: " + yearlyIncome + " kr.");
        }


        public void displayMembershipStatusAndFees () {
            ArrayList<Member> members = controller.getMembers();

            System.out.println("Medlemsstatus og kontingentgebyr:");
            for (Member member : members) {
                System.out.println("Medlem: " + member.getName());
                System.out.println("medlemsstatus: " + member.getPassiveOrActive());

                if ("aktivt".equalsIgnoreCase(member.getPassiveOrActive())) {
                    int subscriptionFee = member.calculateYearlySubscriptionFee();
                    System.out.println("Kontingentgebyr: " + subscriptionFee + " kr. årligt");
                }
                if ("passivt".equalsIgnoreCase(member.getPassiveOrActive())) {
                    int subscriptionFee = member.calculateYearlySubscriptionFee();
                    System.out.println("Kontingentgebyr: " + subscriptionFee + " kr. årligt");

                }
                System.out.println();
            }
        }

        //______________ Renewal membership
        public void showMembershipRenewalMenu () {
            boolean exit = false;
            while (!exit) {
                System.out.println("""
                        1. Renew Membership
                        3. tilbage til kasseren menu
                        """);
                switch (keyboard.nextInt()) {
                    case 1:
                        checkAnnualMembershipPayments();
                        break;
                    case 2:
                        changeAnnualMembershipPayments();
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("ugyldigt valg ");
                }
            }
        }

        private void checkAnnualMembershipPayments () {
            ArrayList<Member> members = controller.getMembers();

            System.out.println("Checking Annual Membership Payments:\n");
            for (Member member : members) {
                System.out.println("Member: " + member.getName() + " (Member Number: " + member.getMemberNumber() + ")");
                System.out.println("medlemsstatus: " + member.getPassiveOrActive());


                if (controller.hasPaidAnnualMembership(member)) {
                    System.out.println("\u001B[32m Paid for the annual membership. \u001B[0m \n");
                } else {
                    System.out.println("\u001B[31m Has not paid for the annual membership. \u001B[0m \n");
                }
            }
        }

        public void changeAnnualMembershipPayments () {
            Scanner scanner = new Scanner(System.in);
            ArrayList<Member> members = controller.getMembers();

            System.out.println("Enter the member number for which you want to change the annual membership payment status:");
            int memberNumber = scanner.nextInt();

            boolean found = false;

            for (Member member : members) {
                if (member.getMemberNumber() == memberNumber) {
                    found = true;

                    System.out.println("Current annual membership payment status for member " + member.getName() + ": " +
                            (member.hasPaidAnnualMembership() ? "Paid" : "Not paid"));

                    System.out.println("Do you want to change the status? (Enter 'Y' for Yes, 'N' for No)");
                    String input = scanner.next();

                    if (input.equalsIgnoreCase("Y")) {
                        boolean newStatus = !member.hasPaidAnnualMembership();
                        member.setAnnualMembershipPaymentStatus(newStatus);
                        System.out.println("Updated annual membership payment status for member " + member.getName() +
                                " to " + (newStatus ? "Paid" : "Not paid"));

                        //controller.();
                    } else {
                        System.out.println("No changes made.");
                    }

                    break;
                }
            }

            if (!found) {
                System.out.println("Member with member number " + memberNumber + " not found.");
            }
        }

        //______________


        public void editMemberAttribute() {
            int selectedMember = getValidIntegerInputMedlemsnummer("Vælg det medlemsnummer du vil redigere:");

            Scanner scanner = new Scanner(System.in);

            System.out.println("Edit Member Attribute Menu:");
            System.out.println("1. Edit Name");
            System.out.println("2. Edit Date of Birth");
            System.out.println("3. Edit Gender");
            System.out.println("4. Phone Number");
            System.out.println("5. Address");
            System.out.println("6. Member Number");
            System.out.println("7. Motionist");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: " + "\n");

            int choice = scanner.nextInt();
            scanner.nextLine();

            Member selectedMemberObject = controller.getMemberByMemberNumber(selectedMember);


            switch (choice) {
                case 1:
                    System.out.print("Enter the new name: ");
                    String newName = scanner.nextLine();
                    selectedMemberObject.editName(newName);
                    break;
                case 2:
                    System.out.print("Enter the new date of birth (dd-MM-yyyy): ");
                    String newDateOfBirth = scanner.nextLine();
                    selectedMemberObject.editDateOfBirth(newDateOfBirth);
                    break;
                case 3:
                    System.out.print("Enter the new gender: ");
                    String newGender = scanner.nextLine();
                    selectedMemberObject.editGender(newGender);
                    break;
                case 4:
                    System.out.print("Enter the new phone number: ");
                    int newPhoneNumber = scanner.nextInt();
                    selectedMemberObject.editPhonenumber(newPhoneNumber);
                    break;
                case 5:
                    System.out.print("Enter the new address: ");
                    String newAddress = scanner.nextLine();
                    selectedMemberObject.editAdress(newAddress);
                    break;
                case 6:
                    System.out.print("Enter the new member number: ");
                    String newPassiveOrActive = scanner.nextLine();
                    selectedMemberObject.editPassiveOrActive(newPassiveOrActive);
                    break;
                case 7:
                    System.out.print("Enter the new member type: ");
                    String newMotionist = scanner.nextLine();
                    selectedMemberObject.editMotionist(newMotionist);
                    break;
                case 0:
                    System.out.println("Exiting edit menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
            controller.updateMember(selectedMemberObject);
        }


    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = (duration.toMinutes() % 60);
        long seconds = (duration.getSeconds() % 60);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }



    public static LocalDate parseDate(String dateString) {
        // Assuming the format is "yyyy-MM-dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dateString, formatter);
    }




    private void exitProgram () {
        System.out.println("Afslutter programmet.");
        System.exit(0);
    }
}



