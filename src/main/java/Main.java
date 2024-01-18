import TicketUtils.ParseTicketsFromFile;
import TicketUtils.Ticket;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String pathName;

        System.out.println("Введите абсолютный путь до файла");
        System.out.println("Пример: C:/users/user/tickets.json");

        Scanner scanner = new Scanner(System.in);
        pathName = scanner.nextLine();
        scanner.close();

        if (!pathName.endsWith("tickets.json")) {
            System.out.println("Неверный формат файла");
        }
        List<Ticket> tickets = new ParseTicketsFromFile(pathName).parse();

        FastestFlightCalculator.calculateAndPrint(tickets);
        System.out.println();
        logMedianAndAverageDiff(tickets);
    }

    private static void logMedianAndAverageDiff(List<Ticket> tickets) {
        int median = getMedian(tickets);
        double average = getAverage(tickets);

        double diff = average - median;

        System.out.println("median: " + median);
        System.out.println("average: " + average);
        System.out.println("Difference between median and average: " + Math.abs(diff));
    }

    private static int getMedian(List<Ticket> tickets) {
        return tickets.get(tickets.size() / 2).price();
    }

    private static double getAverage(List<Ticket> tickets) {
        double sum = 0d;

        for (Ticket ticket : tickets) {
            sum += ticket.price();
        }
        sum /= tickets.size();

        return sum;
    }
}

