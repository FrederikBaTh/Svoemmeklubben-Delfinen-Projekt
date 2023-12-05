import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args ) {

        Controller controller = new Controller();
        List<Member> members = new ArrayList<>();
        Userinterface ui = new Userinterface(controller, members);

        ui.start();

    }
}
