import TicketUtils.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FastestFlightCalculator {

    public static void calculateAndPrint(List<Ticket> tickets) {
        HashMap<String, Duration> fastestFlight = getFastestFlight(tickets);

        for (Map.Entry<String, Duration> entry : fastestFlight.entrySet()) {
            System.out.println("Carrier: " + entry.getKey() + ", fastest flight: " + entry.getValue().toString().substring(2));
        }
    }

    private static HashMap<String, Duration> getFastestFlight(List<Ticket> tickets) {
        HashMap<String, Duration> carrierDurationMap = new HashMap<>();

        for (Ticket ticket : tickets) {
            Duration currTicketTimeDiff = getTimeDiff(ticket);
            String carrier = ticket.carrier();

            if (!carrierDurationMap.containsKey(carrier)) {
                carrierDurationMap.put(carrier, currTicketTimeDiff);
            } else {
                if (carrierDurationMap.get(carrier).compareTo(currTicketTimeDiff) > 0) {
                    carrierDurationMap.put(carrier, currTicketTimeDiff);
                }
            }
        }

        return carrierDurationMap;
    }

    private static Duration getTimeDiff(Ticket ticket) {
        String arrivalDate = ticket.arrival_date(), arrivalTime = ticket.arrival_time();
        String departureDate = ticket.departure_date(), departureTime = ticket.departure_time();

        String arrivalDateTime = getStringDateTime(arrivalDate, arrivalTime);
        String departureDateTime = getStringDateTime(departureDate, departureTime);

        String format = "dd.MM.yy::H:mm";

        LocalDateTime arrivalLDT = parseLDT(arrivalDateTime, format);
        LocalDateTime departureLDT = parseLDT(departureDateTime, format);

        return Duration.between(departureLDT, arrivalLDT);
    }

    private static LocalDateTime parseLDT(String dateTime, String format) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(format));
    }

    private static String getStringDateTime(String date, String time) {
        return date + "::" + time;
    }
}
