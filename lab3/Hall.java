import java.util.ArrayList;
import java.util.List;

public class Hall {
    private String name;
    private int rows;
    private int columns;
    private boolean[][] seats;
    private List<MovieSession> sessions;

    public Hall(String name, int rows, int columns) {
        this.name = name;
        this.rows = rows;
        this.columns = columns;
        this.seats = new boolean[rows][columns];
        this.sessions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public boolean isSeatAvailable(int row, int column) {
        return !seats[row][column];
    }

    public void bookSeat(int row, int column) {
        seats[row][column] = true;
    }

    public void addSession(MovieSession session) {
        sessions.add(session);
    }

    public List<MovieSession> getSessions() {
        return sessions;
    }

    public List<MovieSession> getSessionsByMovie(String movie) {
        List<MovieSession> sessionsByMovie = new ArrayList<>();
        for (MovieSession session : sessions) {
            if (session.getMovie().equals(movie)) {
                sessionsByMovie.add(session);
            }
        }
        return sessionsByMovie;
    }

    public void printSeatPlan() {
        System.out.println("План мест для " + name);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (seats[i][j]) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
    }
}
