/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateful;
import rental.CarRentalCompany;
import rental.CarType;
import rental.Quote;
import rental.RentalStore;
import rental.Reservation;
import rental.ReservationConstraints;
import rental.ReservationException;

@Stateful
public class CarReservationSession implements ReservationSession {
    private List<Quote> quotes;
    
    public CarReservationSession(){
        this.quotes = new ArrayList<Quote>();
    }
    
    @Override
    public void createQuote(ReservationConstraints constraints, String carRentalName, String clientName) throws ReservationException{
        CarRentalCompany company = RentalStore.getRentals().get(carRentalName);
        this.quotes.add(company.createQuote(constraints, clientName));
    }
    
    @Override
    public Collection<Quote> getCurrentQuotes(){
        return this.quotes;
    }

    @Override
    public List<Reservation> confirmQuotes() throws ReservationException {
        List<Reservation> reservations = new ArrayList<Reservation>();
        for(Quote q : this.quotes){
            CarRentalCompany company = RentalStore.getRentals().get(q.getRentalCompany());
            reservations.add(company.confirmQuote(q));
        }
        this.quotes.clear();
        return reservations;
    }

    @Override
    public void checkForAvailableCarTypes(Date start, Date end) {
        CarRentalCompany company1 = RentalStore.getRentals().get("Hertz");
        CarRentalCompany company2 = RentalStore.getRentals().get("Dockx");
        Set<CarType> carTypes = new HashSet<CarType>();
        carTypes.addAll(company1.getAvailableCarTypes(start, end));
        carTypes.addAll(company2.getAvailableCarTypes(start, end));
        // do nothing with the resulting car types  :)
    }
}
