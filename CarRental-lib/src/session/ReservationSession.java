/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import rental.Quote;
import rental.Reservation;
import rental.ReservationConstraints;
import rental.ReservationException;

@Remote
public interface ReservationSession {
    
    /**
     * We have a feeling this method was not intended to be implemented and was added to the client
     * by mistake.  To be sure, we have implemented it to work for both Hertz and Dockx.
     */
    void checkForAvailableCarTypes(Date start, Date end);
    
    void createQuote(ReservationConstraints constraints, String carRentalName, String clientName) throws ReservationException;
    
    Collection<Quote> getCurrentQuotes();
    
    List<Reservation> confirmQuotes() throws ReservationException;
    
}
