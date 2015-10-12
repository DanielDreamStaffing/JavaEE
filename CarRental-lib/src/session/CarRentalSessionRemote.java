package session;

import java.util.Collection;
import java.util.Set;
import javax.ejb.Remote;
import rental.Quote;
import rental.Reservation;
import rental.ReservationConstraints;

@Remote
public interface CarRentalSessionRemote {

    Set<String> getAllRentalCompanies();
    
    Quote createQuote(ReservationConstraints constraints, String carRenter) throws Exception;
    
    Collection<Quote> getCurrentQuotes();
    
    Reservation confirmQuote(Quote quote) throws Exception;
}
