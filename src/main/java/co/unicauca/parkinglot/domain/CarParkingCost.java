/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.domain;

import co.unicauca.parkinglot.infra.Utilities;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author YAQUE
 */
public class CarParkingCost implements IParkingCost{

    @Override
    public long calculateCost(Vehicle veh, LocalDateTime input, LocalDateTime output) {
        double t = (ChronoUnit.MINUTES.between(input,output)/60.0);
        if (t<=1){
            return 2000;
        }
        else{
            long aux = Utilities.redondeo((t-1)*1000+2000);
            return aux;
        }
    }
    
}
