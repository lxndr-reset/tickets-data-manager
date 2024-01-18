package TicketUtils;

public record Ticket(String origin, String origin_name, String destination, String destinationName,
                     String departure_date, String departure_time, String arrival_date, String arrival_time,
                     String carrier, int stops, int price) {
}