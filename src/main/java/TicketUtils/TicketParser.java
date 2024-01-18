package TicketUtils;

import TicketUtils.Ticket;

import java.io.IOException;
import java.util.List;


public interface TicketParser {

     List<Ticket> parse() throws IOException;
}
