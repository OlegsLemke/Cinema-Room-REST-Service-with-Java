package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.UUID;

@RestController
public class Controller {

    Cinema cinema = new Cinema();

    @GetMapping("/seats")
    public Cinema getCinema() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody SeatRequest request) {
        int row = request.getRow();
        int column = request.getColumn();

        /*if (row < 1 || row > 9 || column < 1 || column > 9) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The number of a row or a column is out of bounds!");
        }*/
        for (Seat seat : cinema.getSeats()) {
            if (seat.getRow() == row && seat.getColumn() == column) {
                if (!seat.isSold()) {
                    seat.setSold(true);
                    Ticket ticket = new Ticket(seat);
                    cinema.addTickets(ticket);
                    return ResponseEntity.ok(ticket);
                } else {
                    return ResponseEntity.badRequest().body(Map.of("error", "The ticket has been already purchased!"));
                    //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The ticket has been already purchased!");
                }
            }
        }
        return ResponseEntity.badRequest().body(Map.of("error", "The number of a row or a column is out of bounds!"));
        //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The number of a row or a column is out of bounds!");
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody TokenRequest request) {
        UUID token = request.getToken();
        for (Ticket ticket : cinema.getTickets()) {
            if (ticket.getToken().equals(token)) {
                cinema.removeTicket(ticket);
                return ResponseEntity.ok(new TicketResponse(ticket.getSeat()));
            }
        }
        return ResponseEntity.badRequest().body(Map.of("error", "Wrong token!"));
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam(value = "password", required = false) String password) {
        if ("super_secret".equals(password)) {
            return ResponseEntity.ok(cinema.getStatistics());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "The password is wrong!"));
    }

}
