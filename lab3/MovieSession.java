import java.util.Date;

public class MovieSession {
    private String movie;
    private int duration;
    private Date startTime;
    private Hall hall;

    public MovieSession(String movie, int duration, Date startTime, Hall hall) {
        this.movie = movie;
        this.duration = duration;
        this.startTime = startTime;
        this.hall = hall;
    }

    public String getMovie() {
        return movie;
    }

    public int getDuration() {
        return duration;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Hall getHall() {
        return hall;
    }
}
