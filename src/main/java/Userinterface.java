import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

// systemet
public class Userinterface {
    private Controller controller = new Controller();

    private Scanner keyboard = new Scanner(System.in);


    public void menu() {
        System.out.println("""
                Velkommen til Delfinen-klubbens systemet.
                Hvilke rolle har du i klubben?
                1: Træner 
                2: Formand
                3: Kassere
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
        menu();
        while (true) {


            switch (keyboard.nextInt()) {
                case 1:
                    //trænerMenu();
                    break;
                case 2:
                    formandMenu();
                    break;
                case 3:
                    //kassereMenu();
                    break;

            }
        }
    }


    public void formandMenu() {
        System.out.println("1: Vis alle medlemmer" + "\n" + "2: registrer medlem");
        switch (keyboard.nextInt()) {

            case 1:
                medlemmerStamoplysninger();
                break;
            case 2:
                registrerMedlem();
                break;
            case 3:
                break;
            default:
                System.out.println("Ugyldigt valg prøv igen");
                break;

        }

    }


    public void medlemmerStamoplysninger() {


        controller.printMedlemmerStamoplysninger();


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

        int memberNumber = getValidIntegerInputMedlemsnummer("Medlemnummer: ");

        System.out.print("Passivt eller aktivt medlemskab: ");
        String passiveOrActive = getValidInputForAktivPassiv();

        System.out.print("Motionist: ");
        String motionist = keyboard.nextLine();

        System.out.print("Konkurrence: ");
        String competitive = keyboard.nextLine();


        controller.registrerMedlem(name, String.valueOf(dateOfBirth), gender, phonenumber, adress, memberNumber, passiveOrActive, motionist, competitive);


    }

    // metode for at sørger for man indtaster fødselsdato rigtigt ind
    private String getValidStringInputFødselsdato(String prompt) {
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
                String input = keyboard.nextLine();

                if ("Aktivt".equals(input)){
                    getValidInputForAktivMedlem();
                    return input;
                } else if ("Passivt".equals(input)) {
                    System.out.println("Medlemmer med et passivt medlemskab skal betale 600kr i årligt kontingent");
                    return input;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Ugyldigt input. Indtast venligst en korrekt medlemstype");
            }
        }
    }


    private String getValidInputForAktivMedlem() {
        while (true) {
            try {
                String input = keyboard.nextLine();
                System.out.println("Hvilken medlemstyper hører personen til?");
                System.out.println("Ungdomssvømmer u18" + "\n" + "Ungdomssvømmer o18" + "\n" + "Seniorsvømmer");


                if ("Ungdomssvømmer u18".equals(input)) {
                    System.out.println("Ungdomssvømmere under 18 år betaler 1000kr i årligt kontingent");
                    return input;
                } else if ("Ungdomssvømmer o18".equals(input)) {
                    System.out.println("Ungdomssvømmere over 18 år betaler 1600kr i årligt kontingent");
                    return input;
                } else if ("Seniorsvømmer".equals(input)) {
                    System.out.println("Seniorsvømmere over 60 betaler 1200kr i årligt kontingent");
                    return input;
                }

                } catch (java.util.InputMismatchException e) {
                System.out.println("Ugyldigt input. Indtast venligst en korrekt medlemstype");
            }
        }
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