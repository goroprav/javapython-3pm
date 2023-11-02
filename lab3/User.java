public class User {
    public void buyTicket(Hall hall, int row, int column) {
        if (hall.isSeatAvailable(row, column)) {
            hall.bookSeat(row, column);
            System.out.println("Билет успешно продан!");
        } else {
            System.out.println("Извините, место уже занято.");
        }
    }
}
