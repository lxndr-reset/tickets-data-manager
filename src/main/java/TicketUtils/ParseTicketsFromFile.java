package TicketUtils;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParseTicketsFromFile implements TicketParser {

    private static final String ORIG_CONDITION = "Владивосток";
    private static final String DEST_CONDITION = "Тель-Авив";
    private final String path;

    public ParseTicketsFromFile(String pathToFile) {
        path = pathToFile;
    }

    private static boolean isDirectionValid(Ticket ticket) {
        return ticket.destinationName().equalsIgnoreCase(DEST_CONDITION)
                && ticket.origin_name().equalsIgnoreCase(ORIG_CONDITION);
    }

    @Override
    public List<Ticket> parse() {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(IOUtils.toString(new FileInputStream(path), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JSONArray jsonTicketsArr = jsonObject.getJSONArray("tickets");
        List<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < jsonTicketsArr.length(); i++) {
            JSONObject ticketObj = jsonTicketsArr.getJSONObject(i);
            Ticket ticket = new Ticket(
                    ticketObj.getString("origin"),
                    ticketObj.getString("origin_name"),
                    ticketObj.getString("destination"),
                    ticketObj.getString("destination_name"),
                    ticketObj.getString("departure_date"),
                    ticketObj.getString("departure_time"),
                    ticketObj.getString("arrival_date"),
                    ticketObj.getString("arrival_time"),
                    ticketObj.getString("carrier"),
                    ticketObj.getInt("stops"),
                    ticketObj.getInt("price")
            );

            if (isDirectionValid(ticket)) {
                tickets.add(ticket);
            }
        }

        tickets.sort(Comparator.comparingInt(Ticket::price));
        return tickets;
    }
}
