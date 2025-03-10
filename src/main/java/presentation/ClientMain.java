/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;
import access.IVehicleRepository;
import access.RepositoryFactory;
import domain.Vehicle;
import domain.TypeEnum;
import domain.service.Service;
import infra.Lottery;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Scanner;


/**
 * Un cliente main de prueba
 *
 * @author ADMIN
 */
public class ClientMain {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.println("Ingrese la placa");
        String placa = scanner.nextLine();
        
        Vehicle veh = new Vehicle(placa, TypeEnum.MOTO);
        LocalDateTime input = LocalDateTime.of(2021, Month.FEBRUARY, 22, 8, 0);
        LocalDateTime output = LocalDateTime.of(2021, Month.FEBRUARY, 22, 19, 30);
        IVehicleRepository repo = RepositoryFactory.getInstance().getRepository("default");
        Service service = new Service(repo); //Inyección de dependencias
        long result = service.calculateParkingCost(veh, input, output);
        if(veh.getType().equals(TypeEnum.TRUCK)){
            int n=(int)(Math.random()*100+1);
            int l = Lottery.getLottery(n);
            System.out.println("Numero jugado: "+n);
            if(l==0){
                System.out.println("Ganaste el servicio es gratis, ahorraste: "+result);
                result = 0;
            }  
            else {
                System.out.println("Resultado: "+l +" \nVuelve a intentarlo!");
            }
        }
        System.out.println("Valor a pagar : " + result);
        service.saveVehicle(veh);
        veh = new Vehicle("JNK-124", TypeEnum.CAR);
        service.saveVehicle(veh);
        List<Vehicle> list = service.listVehicles();
        list.forEach(vehicle -> {
            System.out.println(vehicle.toString());
        });
        scanner.close();
    }
}

