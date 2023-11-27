import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Controller controller = new Controller();
        Userinterface ui = new Userinterface(controller);

        ui.start();

    }
}
