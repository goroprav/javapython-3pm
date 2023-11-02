import java.util.Date;
import java.util.List;

public class Admin {
    public void addCinema(List<Cinema> cinemas, String cinemaName) {
        Cinema cinema = new Cinema(cinemaName);
        cinemas.add(cinema);
        System.out.println("Кинотеатр " + cinemaName + " успешно добавлен.");
    }

    public void addHall(Cinema cinema, String hallName, int rows, int columns) {
        Hall hall = new Hall(hallName, rows, columns);
        cinema.addHall(hall);
        System.out.println("Зал " + hallName + " успешно добавлен в кинотеатр " + cinema.getName() + ".");
    }

    public void addSession(Hall hall, String movie, int duration, Date startTime) {
        MovieSession session = new MovieSession(movie, duration, startTime, hall);
        hall.addSession(session);
        System.out.println("Сеанс фильма " + movie + " успешно добавлен в зал " + hall.getName() + ".");
    }
}
