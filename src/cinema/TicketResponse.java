package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketResponse {

    @JsonProperty("ticket")
    private Seat seat;

    public TicketResponse(Seat seat) {
        this.seat = seat;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
