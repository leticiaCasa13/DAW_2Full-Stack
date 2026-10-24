package model;

import java.util.ArrayList;
import java.util.List;


public class ReservationService {


    //guardar lista
    private ArrayList<Reservation> reservations;


    //constructor, crea una lista vacía al iniciar
    public ReservationService() {

        reservations = new ArrayList<>();
    }


    //método, crea una reserva (usuario, libro), guarda, devuelve la reseva.

    public Reservation reserve(User user, Book book) {
        Reservation reservation = new Reservation(user, book);
        reservations.add(reservation);
        return reservation;
    }

  //devuelve copia de lista con reservas guardadas
    public List<Reservation> getAllReservations() {

        return new ArrayList<>(reservations);
    }
}

