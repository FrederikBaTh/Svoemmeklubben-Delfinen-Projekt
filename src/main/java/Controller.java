//
public class Controller {

    Database database = new Database();


    public void printMedlemmerStamoplysninger(){
        database.printMedlemmerStamoplysninger();
    }

    public void registrerMedlem(String name, int dateOfBirth, String gender, int phonenumber, String adress, int memberNumber, String passiveOrActive, String motionist, String competitive){
        database.registrerMedlem(name, dateOfBirth,  gender,  phonenumber,  adress,  memberNumber,  passiveOrActive,  motionist,  competitive);
    }



}
