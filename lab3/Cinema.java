import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private String name;
    private List<Hall> halls;

    public Cinema(String name) {
        this.name = name;
        this.halls = new ArrayList<>();
    }

    public void addHall(Hall hall) {
        halls.add(hall);
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public String getName() {
        return name;
    }

    public List<MovieSession> getSessionsByMovie(String movie) {
        List<MovieSession> sessionsByMovie = new ArrayList<>();
        for (Hall hall : halls) {
            sessionsByMovie.addAll(hall.getSessionsByMovie(movie));
        }
        return sessionsByMovie;
    }
}
