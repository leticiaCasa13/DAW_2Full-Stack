package view;


import java.util.List;
import java.util.Scanner;

public class ConsoleView {

     private Scanner sc;


    public ConsoleView(Scanner sc) {
        this.sc = sc;
    }

    public void  showMenu() {
        System.out.println("-- Mostrar Menú --");
        System.out.println("1. Nova Reserva");
        System.out.println("2. Llista de Reserves");
        System.out.println("0. Sortir");

    }




    public int readOptio() {
        System.out.print("-- Escull una opció: ");

        String input = sc.nextLine();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }





    public String readUserName() {

        System.out.print("Nom de l'usuari: ");
        return sc.nextLine();
    }


    public String readBookTitle() {

        System.out.println("Títol del libre: ");

        return sc.nextLine();
    }


   public void showMessage(String message){

       System.out.println(message);
   }



    public void showReservations(List<String> reservations) {
        if (reservations.isEmpty()) {
            System.out.println("La llista està buida.");
            return;
        }

        for (String reservation : reservations) {
            System.out.println(reservation);
        }
    }





}
