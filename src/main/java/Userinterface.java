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
        /*System.out.println("""



                Velkommen til Delfinklubbens systemet.
                Hvad vil du tilgå i systemet?
                1. Medlemmernes stamoplysninger.
                2. Registere en ny medlem til deflinklubben.
                3. Kontingenterne
                4. 
                5.
                6. 
                7. Registere den valgte svømmer til en konkurrence
                8. Svømmeresultaterne (Ny menu så man kan vælge den svømmedisciplin man vil tilgå samt se de top 5.)
                """)

         */
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

    public void trænerMenu(){
        boolean exit = false;
        while(!exit){
            System.out.println("""
                            1: Vis alle medlemmers resultaterne
                            2: registrer resultat af en medlem
                            3: afslut programmet
                            """);
            switch(keyboard.nextInt()){

                case 1:
                    //resultater();
                    break;
                case 2:
                    //indtastResultater;
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


    public void kassereMenu(){
        boolean exit = false;
        while(!exit){
            System.out.println("""
                            1:
                            2:
                            3: afslut programmet
                            """);
            switch (keyboard.nextInt()){
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
            if (controller.calculateAge(member.getDateOfBirth())>= 18 && !adskillelseLinjeOU18){
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
    /*public void registrerMedlem() {
        keyboard.nextLine();

        System.out.print("Navn: ");
        String name = keyboard.nextLine();


        String dateOfBirth = getValidStringInputFødselsdato("Fødselsdato i format (dd-mm-yyyy): ");

        System.out.print("Køn: ");
        String gender = keyboard.nextLine();

        int phonenumber = getValidIntegerInputTelefonnummer("Telefonnummer: ");

        System.out.print("Adresse: ");
        String adress = keyboard.nextLine();

        int memberNumber = getValidIntegerInputMedlemsnummer("Medlemnummer: ");

        System.out.print("Passivt eller aktivt medlemskab: ");
        String passiveOrActive = keyboard.nextLine();

        System.out.print("Motionist: ");
        String motionist = keyboard.nextLine();

        System.out.print("Konkurrence: ");
        String competitive = keyboard.nextLine();


        controller.registrerMedlem(name, dateOfBirth, gender, phonenumber, adress, memberNumber, passiveOrActive, motionist, competitive);


    } */

    // metode for at sørger for man indtaster fødselsdato rigtigt ind
    /*private String getValidStringInputFødselsdato(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = keyboard.nextLine();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");

                dateFormat.parse(input);

                return input;
            } catch (ParseException e) {
                System.out.println("Ugyldig input. Indtast venligst en fødselsdato i formatet (dd-mm-yyyy).");

            }
        }
    }*/
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

// Søren version af menuen
/*
package org.Menu;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void menu() {
        System.out.println("""
        Velkommen til Delfinen-klubbens systemet.
        Hvilke rolle har du i klubben?
        1. formand
        2. kasseren
        3. træneren
        """);

        while (true) {
            String input = scanner.nextLine().toLowerCase();
            processIndtast(input);
        }
    }

    public void processIndtast(String input) {
        String[] words = input.split("\\s+");
        String action = words[0];

        // handle user input and the system logic here.
        switch (action) {
            // Menu metode med switch case.
            case "1", "formand":
                handleFormandMenu();
                break;
            case "2", "kasseren":
                handleKasserenMenu();
                break;
            case "3", "træneren":
                handleTrænerenMenu();
                break;
            default:
                System.out.println("Ukendt handling");
                break;
        }
    }

    private void handleFormandMenu() {
        System.out.println("""
                Hvad vil du tilgå i systemet? Indtast det nummer du ønsker at tilgå.
                    1. Medlemmernes stam-oplysninger.
                    2. Registrer en ny medlem til Delfinen-klubben.
                """);
        String subAction = scanner.nextLine();

        switch (subAction) {
            case "1":
                System.out.println("Medlemmernes stam-oplysninger vises her");
                break;
            case "2":
                System.out.println("Registrer en ny medlem");
                // Implementer logikken for at registrere en ny medlem her.
                break;
            default:
                System.out.println("Ukendt handling");
                break;
        }
    }

    private void handleKasserenMenu() {
        System.out.println("""
                Hvad vil du tilgå i systemet? Indtast det nummer du ønsker at tilgå.
                    1. Få oversigt for kontingenterne
                    2. blabla ver. 1
                    3. blabla ver. 2
                    4. blabla ver 3.
                """);
        String subAction = scanner.nextLine();

        switch (subAction) {
            case "1":
                System.out.println("Oversigten vises her");
                handleKasserenSubMenu();
                break;
            case "2":
                System.out.println("Blabla 1");
                break;
            case "3":
                System.out.println("Blabla 1");
                break;
            case "4":
                System.out.println("Blabla 1");
                break;
            default:
                System.out.println("Ukendt handling");
                break;


        }
    }

    private void handleKasserenSubMenu() {
        System.out.println("""
        Hvad vil du gøre i kontingentoversigten? Indtast det nummer du ønsker at udføre.
                1. handling 1
                2. handling 2.
        """);
        String subSubAction = scanner.nextLine();

        switch (subSubAction) {
            case "1":
                System.out.println("Udfør handling 1 i kontingentoversigten");
                // implementer logikken for handling 1 her
                break;
            case "2":
                System.out.println("Udfør handling 2 i kontingentoversigten");
                // implementer logikken for handling 2 her
                break;
            default:
                System.out.println("Ukendt handling");
                break;
        }
    }

    private void handleTrænerenMenu() {
        System.out.println("""
                 Hvad vil du tilgå i systemet? Indtast den nummer du ønske at tilgå.
                     1. Få vist svømmeresultaterne (Ny menu så man kan vælge den svømmedisciplin man vil tilgå samt se de top 5.)
                     2. Registrer den valgte svømmer til en konkurrence
                 """);
        String subAction = scanner.nextLine(); // Antag, at scanner er din inputscanner.

        switch (subAction) {
            case "1":
                System.out.println("Medlemmernes svømmeresultaterne vises her");
                break;
            case "2":
                System.out.println("Registrer den valgt svømmer til en konkurrence");
                // Implementer logikken for at registrere en ny medlem her.
                break;
            default:
                System.out.println("Ukendt handling");
                break;
        }
    }
}
 */