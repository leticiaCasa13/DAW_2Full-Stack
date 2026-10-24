import controller.ReservationController;

import model.ReservationService;
import view.ConsoleView;

import java.util.Scanner;


public class MainMVC {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        ReservationService service = new ReservationService();
        ConsoleView view = new ConsoleView(sc);
        ReservationController controller = new ReservationController(service, view);


     controller.inicioMenu();

    }
}