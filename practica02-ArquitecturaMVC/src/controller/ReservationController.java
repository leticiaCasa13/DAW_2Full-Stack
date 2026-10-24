package controller;

import model.Book;

import model.ReservationService;
import model.User;
import view.ConsoleView;
import model.Reservation;
import java.util.ArrayList;
import java.util.List;




public class ReservationController {

    private ReservationService service;
    private ConsoleView view;


    public ReservationController(ReservationService service, ConsoleView view) {
        this.service = service;
        this.view = view;
    }


   public void inicioMenu() {

        //condición

       int opcio = -1;

       while(opcio != 0){

           view.showMenu();

           opcio = view.readOptio();


           switch(opcio){

               case 1:
                   //nova reserva

                   String nom = view.readUserName(); //pedimos nom


                    //validación, para q nom no esté vacío
                   if (nom.trim().isEmpty()) {   //trim, elimina espacios, al principio y al final
                       view.showMessage("El nom de l'usuari no pot estar buit.");
                       break;
                   }

                   String  title = view.readBookTitle();


                   if(title.trim().isEmpty()) {
                       view.showMessage("El title de l'libre no pot estar buit.");
                       break;

               }



                   User user = new User(nom);   //creamos objetos
                   Book book = new Book(title);

                   service.reserve(user, book);

                   view.showMessage("Reserva realizada: " + nom + " ha reservat \"" + title + "\".");


                   break;


               case 2:
                   // Llista de reserves

                   List<Reservation> reservations = service.getAllReservations();

                   List<String> reservationsText = new ArrayList<>();

                   for (Reservation reservation : reservations) {
                       String text = "Reserva: "
                               + reservation.getUser().getNom()
                               + " ha reservat \""
                               + reservation.getBook().getBook()
                               + "\"";

                       reservationsText.add(text);
                   }

                   view.showReservations(reservationsText);
                   break;

               case 0:
                   //sortir de la app

                   break;

               default:

                   //opcio invalida

                   view.showMessage("Opció no vàlida. Torna-ho a intentar.");




           }

       }

   }


}



