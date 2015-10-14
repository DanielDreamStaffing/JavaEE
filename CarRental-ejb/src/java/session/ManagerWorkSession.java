/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Stateless;
import rental.CarRentalCompany;
import rental.RentalStore;

@Stateless
public class ManagerWorkSession implements ManagerSession {

    @Override
    public int getNumberOfReservationsBy(String clientName) {
        int result = 0;
        for(CarRentalCompany company : RentalStore.getRentals().values()){
            result += company.getAmountReservationsByClient(clientName);
        }
        return result;
    }

    @Override
    public int getNumberOfReservationsForCarType(String carRentalName, String carType) {
        CarRentalCompany company = RentalStore.getRentals().get(carRentalName);
        return company.getAmountReservationsByCarType(carType);
    }

    @Override
    public void getCarTypes(String companyName) {
        // gets all the car type for the given company, but doesn't serve any purpose.
        RentalStore.getRentals().get(companyName).getAllTypes();
    }
    
}
