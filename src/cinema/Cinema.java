package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private final int ROWS = 9;
    private final int COLUMNS = 9;
    private List<Seat> seats;
    @JsonIgnore
    private List<Ticket> tickets;
    @JsonIgnore
    private Statistics statistics;

    public Cinema() {
        this.seats = new ArrayList<>();
        for (int i = 1; i <= ROWS; i++) {
            for (int j = 1; j <= COLUMNS; j++) {
                if (i <= 4) {
                    this.seats.add(new Seat(i, j, 10));
                } else {
                    this.seats.add(new Seat(i, j, 8));
                }
            }
        }
        this.tickets = new ArrayList<>();
        this.statistics = new Statistics(0, 81, 0);
    }

    public int getROWS() {
        return ROWS;
    }

    public int getCOLUMNS() {
        return COLUMNS;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTickets(Ticket ticket) {
        statistics.addIncome(ticket.getSeat().getPrice());
        statistics.addPurchased();
        statistics.subtractAvailable();
        this.tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        statistics.subtractIncome(ticket.getSeat().getPrice());
        statistics.addAvailable();
        statistics.subtractPurchased();
        ticket.getSeat().setSold(false);
        this.tickets.remove(ticket);
    }

    public Statistics getStatistics() {
        return statistics;
    }

}
