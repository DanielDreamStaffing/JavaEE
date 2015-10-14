package client;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import rental.Reservation;
import rental.ReservationConstraints;
import session.CarRentalSessionRemote;
import session.ManagerSession;
import session.ReservationSession;

public class Main extends AbstractTestAgency<ReservationSession, ManagerSession> {
    
    @EJB
    static CarRentalSessionRemote session;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        System.out.println("found rental companies: "+session.getAllRentalCompanies());
        Main client = new Main();
        client.run();
    }

    public Main() {
        super("simpleTrips");
    }

    @Override
    protected ReservationSession getNewReservationSession(String name) throws Exception {
        InitialContext context = new InitialContext();
        ReservationSession session = (ReservationSession) context.lookup(ReservationSession.class.getName());
        return session;
    }

    @Override
    protected ManagerSession getNewManagerSession(String name, String carRentalName) throws Exception {
        InitialContext context = new InitialContext();
        ManagerSession session = (ManagerSession) context.lookup(ManagerSession.class.getName());
        return session;
    }

    @Override
    protected void checkForAvailableCarTypes(ReservationSession session, Date start, Date end) throws Exception {
        session.checkForAvailableCarTypes(start, end);
    }

    @Override
    protected void addQuoteToSession(ReservationSession session, String name, Date start, Date end, String carType, String carRentalName) throws Exception {
        session.createQuote(new ReservationConstraints(start, end, carType), carRentalName, name);
    }

    @Override
    protected List<Reservation> confirmQuotes(ReservationSession session, String name) throws Exception {
        return session.confirmQuotes();
    }

    @Override
    protected int getNumberOfReservationsBy(ManagerSession ms, String clientName) throws Exception {
        return ms.getNumberOfReservationsBy(clientName);
    }

    @Override
    protected int getNumberOfReservationsForCarType(ManagerSession ms, String carRentalName, String carType) throws Exception {
        return ms.getNumberOfReservationsForCarType(carRentalName, carType);
    }
    
    /**
     * Implemented because it was in the assignment, but serves no purpose.
     */
    protected void getCarTypes(ManagerSession ms, String companyName){
        ms.getCarTypes(companyName);
    }
}
