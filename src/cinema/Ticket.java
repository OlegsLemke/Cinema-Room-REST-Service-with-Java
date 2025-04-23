package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Ticket {

    private UUID token;
    @JsonProperty("ticket")
    private Seat seat;

    public Ticket(Seat seat) {
        this.seat = seat;
        this.token = UUID.randomUUID();
        seat.setSold(true);
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
