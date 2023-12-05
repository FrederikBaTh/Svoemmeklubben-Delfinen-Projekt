import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    public Userinterface(Controller controller,List<Member> members) {
        this.controller = controller;
        this.members=members;
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
                    3: se Kokurrence Hold
                    4: exit
                    """);
            switch (keyboard.nextInt()) {

                case 1:
                    tidsresultater();
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
                    1: se årlig indkomst
                    2: se medlemsstatus og gebyr
                    3: fornyelse af medlemskab
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


    public void tidsresultater () {
        ArrayList<CompetitiveMember> competitiveMembers = controller.getCompetitiveMembers();
        for (CompetitiveMember competitiveMember : competitiveMembers) {
            System.out.println("Medlemsnummer: " + competitiveMember.getMemberNumber());
            System.out.println("Navn: " + competitiveMember.getName());
            System.out.println("Svømmedisciplin: " + competitiveMember.getSwimmingDiscipline());
            System.out.println("Svømmetid: " + competitiveMember.getSwimTime());
            System.out.println("Svømmedato: " + competitiveMember.getDateOfSwim());
            System.out.println();
        }
    }

        public void indtastResultater () {
            boolean exit = false;
            while (!exit) {
                System.out.println("""
                        1: registrer resultater af en træning
                        2: registrer resultater af en konkurrence
                        3: afslut programmet
                        """);
                switch (keyboard.nextInt()) {

                    case 1:
                        indtastResultaterTræning();
                        break;
                    case 2:
                        indtastResultaterKonkurrence();
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("ugyldigt valg ");

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
    public void printCompMembersUnder18() {
        List<Member> competitiveMembersUnder18 = controller.getCompetitiveMembersUnder18();


        for (Member member : competitiveMembersUnder18) {
            System.out.println(member);
        }
    }
    public void printCompMembersOver18(){
        List<Member> competitiveMembersOver18 = controller.getCompetitiveMembersOver18();

        for (Member member : competitiveMembersOver18) {
            System.out.println(member);
        }


    }
        public void indtastResultaterTræning() {

            ArrayList<Member> members = controller.getMembers();

            System.out.println("List of Member Numbers:");
            for (int i = 0; i < members.size(); i++) {
                Member member = members.get(i);
                System.out.println((i + 1) + ". Member Number: " + member.getUsedMemberNumbers());
            }


            int selectedMemberNumber = getValidIntegerInputMedlemsnummer("Select Member Number from the list: ");

            if (controller.memberExists(selectedMemberNumber)) {
                System.out.println("Svømmetid (hh:mm:ss): ");
                String svømmeTidInputKonkurrence = keyboard.nextLine();

                Duration svimTime = parseDuration(svømmeTidInputKonkurrence);

                String dateOfSwim = getValidStringInputSvømmeDato("Konkurrence dato: ");

                System.out.println("Svømmedisciplin: ");
                SwimmingDiscipline swimmingDiscipline = chooseBetweenSwimmingStyles();

                controller.registrerTræningsResultat(selectedMemberNumber, svimTime, LocalDate.parse(dateOfSwim, DateTimeFormatter.ofPattern("dd-MM-yyyy")), swimmingDiscipline);
            }
        }

// TODO Den skal vise en liste som man skal vælge fra med navn og meldemsnummer også derefter sætte tider og det til
        public void indtastResultaterKonkurrence() {

            ArrayList<Member> members = controller.getMembers();

            System.out.println("List of Member Numbers:");
            for (int i = 0; i < members.size(); i++) {
                Member member = members.get(i);
                System.out.println((i + 1) + ". Member Number: " + member.getUsedMemberNumbers());
            }


            int selectedMemberNumber = getValidIntegerInputMedlemsnummer("Select Member Number from the list: ");

            if (controller.memberExists(selectedMemberNumber)) {
                System.out.println("Svømmetid (hh:mm:ss): ");
                String svømmeTidInputKonkurrence = keyboard.nextLine();

                Duration svimTime = parseDuration(svømmeTidInputKonkurrence);

                String dateOfSwim = getValidStringInputSvømmeDato("Konkurrence dato: ");

                System.out.println("Svømmedisciplin: ");
                SwimmingDiscipline swimmingDiscipline = chooseBetweenSwimmingStyles();

                System.out.println("Event navn: ");
                String eventName = getValidEventName();

                System.out.println("Event placering: ");
                String eventPlacement = getValidEventPlacement();

                controller.registrerEventResultat(selectedMemberNumber, svimTime, LocalDate.parse(dateOfSwim, DateTimeFormatter.ofPattern("dd-MM-yyyy")), swimmingDiscipline, eventName, eventPlacement);
            }
        }


        public String getValidEventName() {
        if (keyboard.hasNextLine()) {
            String input = keyboard.nextLine();
            return input;
        }
            return getValidEventName();
        }
    public String getValidEventPlacement() {
        if (keyboard.hasNextLine()) {
            String input = keyboard.nextLine();
            return input;
        }
        return getValidEventPlacement();
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
                            if (controller.memberNumberUsed(input)) {
                                System.out.println("Medlemsnummeret findes ikke i systemet.");
                            }
                        }
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Ugyldig input. Indtast venligst et medlemsnummer på 6 cifre.");
                        keyboard.nextLine();
                    }
                }
            }


        public void displayYearlyIncome () {
            int yearlyIncome = controller.calculateYearlyIncome();
            System.out.println("Forventet Årlig Indtægt: " + yearlyIncome + " kr.");
        }

        //______________ Renewal membership
        public void showMembershipRenewalMenu() {
            boolean exit = false;
            while (!exit) {
                System.out.println("""
                    1. Renew Membership
                    """);
                switch (keyboard.nextInt()) {
                    case 1:
                        checkAnnualMembershipPayments();
                        break;
                    case 2:

                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("ugyldigt valg ");
                }
            }
        }
        private void checkAnnualMembershipPayments() {
            System.out.println("Checking Annual Membership Payments:");

            for (Member member : members) {
                if (controller.hasPaidAnnualMembership(member)) {
                    System.out.println(member.getName() + " has paid for the annual membership.");
                } else {
                    System.out.println(member.getName() + " has not paid for the annual membership.");
                }
            }
        }
        //______________

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

       /* public SwimmingDiscipline chooseBetweenSwimmingStyles() {
            System.out.println("Vælg mellem Butterfly, Crawl, Backstroke, Breaststroke");
            String input = keyboard.nextLine();
                        switch (keyboard.nextInt()) {
                case 1:
                    Butterfly(input);
                    break;
                case 2:
                    Crawl(input);
                    break;
                case 3:
                    Backstroke(input);
                    break;
                case 4:
                    Breaststroke(input);
                    break;

            }
            return null;
        }*/
       public SwimmingDiscipline chooseBetweenSwimmingStyles() {
           System.out.println("Vælg mellem Butterfly, Front Crawl, Backstroke, Breaststroke");
           String input = keyboard.nextLine().toUpperCase();
           try {
               return SwimmingDiscipline.valueOf(input);
           } catch (IllegalArgumentException e) {
               System.out.println("Ugyldig indtastning. Prøv igen.");
               return chooseBetweenSwimmingStyles();
           }
       }
/*
        public SwimmingDiscipline Butterfly(String input) {
            for (SwimmingDiscipline discipline : SwimmingDiscipline.values()) {
                if (discipline.name().equalsIgnoreCase("Butterfly")) {
                    return discipline;
                }
            }
            return null;
        }

        public SwimmingDiscipline Crawl(String input) {
            for (SwimmingDiscipline discipline : SwimmingDiscipline.values()) {
                if (discipline.name().equalsIgnoreCase("Crawl")) {
                    return discipline;
                }
            }
            return null;
        }

        public SwimmingDiscipline Backstroke(String input) {
            for (SwimmingDiscipline discipline : SwimmingDiscipline.values()) {
                if (discipline.name().equalsIgnoreCase("Backstroke")) {
                    return discipline;
                }
            }
            return null;
        }
        public SwimmingDiscipline Breaststroke(String input) {
            for (SwimmingDiscipline discipline : SwimmingDiscipline.values()) {
                if (discipline.name().equalsIgnoreCase("Breaststroke")) {
                    return discipline;
                }
            }
            return null;
        }
*/

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
    private String checkIfMotionistOrCompetitive() {
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



    private void exitProgram () {
        System.out.println("Afslutter programmet.");
        System.exit(0);
    }
}

