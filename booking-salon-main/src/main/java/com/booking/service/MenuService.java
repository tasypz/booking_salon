package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;
import com.booking.repositories.ServiceRepository;

public class MenuService {
    private static List<Person> personList = PersonRepository.getAllPerson();
    private static List<Customer> customerList = getCustomerFromPersonList(personList);
    private static List<Employee> employeeList = getEmployeeFromPersonList(personList);
    private static List<Service> serviceList = ServiceRepository.getAllService();
    private static List<Reservation> reservationList = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void mainMenu() {
        String[] mainMenuArr = {"Show Data", "Create Reservation", "Complete/cancel reservation", "Exit"};
        String[] subMenuArr = {"Recent Reservation", "Show Customer", "Show Available Employee", "Show History Reservation + Total Keuntungan", "Back to main menu"};
    
        int optionMainMenu;
        int optionSubMenu;

		boolean backToMainMenu = false;
        boolean backToSubMenu = false;
        do {
            PrintService.printMenu("Main Menu", mainMenuArr);
            optionMainMenu = Integer.valueOf(input.nextLine());
            switch (optionMainMenu) {
                case 1:
                    do {
                        PrintService.printMenu("Show Data", subMenuArr);
                        optionSubMenu = Integer.valueOf(input.nextLine());
                        // Sub menu - menu 1
                        switch (optionSubMenu) {
                            case 1:
                                PrintService.showRecentReservation(reservationList);
                                break;
                            case 2:
                                PrintService.showAllCustomer(customerList);
                                break;
                            case 3:
                                PrintService.showAllEmployee(employeeList);
                                break;
                            case 4:
                                PrintService.showHistoryReservation(reservationList);
                                break;
                            case 0:
                                backToSubMenu = true;
                        }
                    } while (!backToSubMenu);
                    break;
                case 2:
                    ReservationService.createReservation(customerList, employeeList, serviceList, reservationList);
                    break;
                case 3:
                    ReservationService.editReservationWorkstage(reservationList);
                    break;
                case 0:
                    ReservationService.exit();
                    break;
            }
        } while (!backToMainMenu);
		
	}


    private static List<Customer> getCustomerFromPersonList(List<Person> personList) {
        List<Customer> customerList = new ArrayList<>();
        for (Person person : personList) {
            if(person instanceof Customer){
                customerList.add((Customer) person);
            }
        }
        return customerList;
    }

    private static List<Employee> getEmployeeFromPersonList(List<Person> personList) {
        List<Employee> employeeList = new ArrayList<>();
        for (Person person : personList) { 
            if(person instanceof Employee){
                employeeList.add((Employee) person);
            }
        }
        return employeeList;
    }

}
