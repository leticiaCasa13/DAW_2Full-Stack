package model;

public class Reservation {
    private  User user;
    private  Book book;

    public Reservation(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }



    @Override
    public String toString() {
        return "Reservation{user=" + user.getNom() + "," + " book=  " + book.getBook() + '}';
    }





}



