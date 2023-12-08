import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args ) {

        Controller controller = new Controller();
        Userinterface ui = new Userinterface(controller);

        ui.start();

    }
}
