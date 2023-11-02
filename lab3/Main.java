import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Cinema> cinemas = new ArrayList<>();
        Admin admin = new Admin();
        User user = new User();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Выберите режим входа (admin/user/exit): ");
            String mode = scanner.nextLine();

            if (mode.equals("admin")) {
                System.out.print("Выберите действие (addCinema/existingCinema/exitAdmin): ");
                String action = scanner.nextLine();

                if (action.equals("addCinema")) {
                    System.out.print("Введите название кинотеатра: ");
                    String cinemaName = scanner.nextLine();
                    admin.addCinema(cinemas, cinemaName);
                } else if (action.equals("existingCinema")) {
                    listCinemas(cinemas);
                    System.out.print("Введите название кинотеатра: ");
                    String cinemaName = scanner.nextLine();
                    Cinema cinema = getCinemaByName(cinemas, cinemaName);
                    if (cinema != null) {
                        while (true) {
                            System.out.print("Выберите действие (addHall/addSession/exitAdmin): ");
                            String subAction = scanner.nextLine();

                            if (subAction.equals("addHall")) {
                                System.out.print("Введите название зала: ");
                                String hallName = scanner.nextLine();
                                System.out.print("Введите количество рядов в зале: ");
                                int rows = scanner.nextInt();
                                System.out.print("Введите количество мест в ряду: ");
                                int columns = scanner.nextInt();
                                scanner.nextLine(); // Считываем символ новой строки
                                admin.addHall(cinema, hallName, rows, columns);
                            } else if (subAction.equals("addSession")) {
                                System.out.print("Введите название зала: ");
                                String hallName = scanner.nextLine();
                                Hall hall = getHallByName(cinema, hallName);
                                if (hall != null) {
                                    System.out.print("Введите название фильма: ");
                                    String movie = scanner.nextLine();
                                    System.out.print("Введите продолжительность фильма (в минутах): ");
                                    int duration = scanner.nextInt();
                                    scanner.nextLine(); // Считываем символ новой строки
                                    System.out.print("Введите дату и время начала сеанса (в формате dd-MM-yyyy HH:mm): ");
                                    String startTimeString = scanner.nextLine();
                                    Date startTime = parseDateTime(startTimeString);
                                    admin.addSession(hall, movie, duration, startTime);
                                } else {
                                    System.out.println("Зал " + hallName + " не найден в кинотеатре " + cinemaName + ".");
                                }
                            } else if (subAction.equals("exitAdmin")) {
                                break;
                            } else {
                                System.out.println("Некорректное действие.");
                            }
                        }
                    } else {
                        System.out.println("Кинотеатр " + cinemaName + " не найден.");
                    }
                } else if (action.equals("exitAdmin")) {
                    break;
                } else {
                    System.out.println("Некорректное действие.");
                }
            } else if (mode.equals("user")) {
                // Режим пользователя
                System.out.print("Введите название кинотеатра: ");
                String cinemaName = scanner.nextLine();
                Cinema cinema = getCinemaByName(cinemas, cinemaName);
                if (cinema != null) {
                    System.out.print("Введите название фильма: ");
                    String movie = scanner.nextLine();
                    List<MovieSession> sessionsByMovie = cinema.getSessionsByMovie(movie);
                    if (!sessionsByMovie.isEmpty()) {
                        System.out.println("Ближайшие сеансы фильма " + movie + ":");
                        for (MovieSession session : sessionsByMovie) {
                            System.out.println("Фильм: " + session.getMovie());
                            System.out.println("Начало: " + session.getStartTime());
                            System.out.println("Длительность: " + session.getDuration() + " минут");
                            System.out.println("Зал: " + session.getHall().getName());
                            System.out.println("----------------------");
                        }
                    } else {
                        System.out.println("Сеансы для фильма " + movie + " не найдены.");
                    }
                } else {
                    System.out.println("Кинотеатр " + cinemaName + " не найден.");
                }
            } else if (mode.equals("exit")) {
                break;
            } else {
                System.out.println("Некорректный режим входа.");
            }

        }

        scanner.close();
    }

    private static Cinema getCinemaByName(List<Cinema> cinemas, String name) {
        for (Cinema cinema : cinemas) {
            if (cinema.getName().equals(name)) {
                return cinema;
            }
        }

        return null;
    }

    private static Hall getHallByName(Cinema cinema, String name) {
        for (Hall hall : cinema.getHalls()) {
            if (hall.getName().equals(name)) {
                return hall;
            }
        }
        return null;
    }

    private static void listCinemas(List<Cinema> cinemas) {
        System.out.println("Список кинотеатров:");
        for (Cinema cinema : cinemas) {
            System.out.println(cinema.getName());
        }
    }

    private static Date parseDateTime(String dateTimeString) {

        return new Date();
    }
}
