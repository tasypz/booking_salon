package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class ReservationService {
        public static int count = 1;

        public static void createReservation(List<Customer> customerList, List<Employee> employeeList, List<Service> serviceList, List<Reservation> reservationList) {
        Scanner input = new Scanner(System.in);
        System.out.println("Create Reservation");
        System.out.print("Masukkan Customer ID: ");
        String customerID = input.nextLine();
        Customer customer = getCustomerByCustomerId(customerID, customerList);
        if (customer == null) {
            System.out.println("Customer ID tidak ditemukan, silahkan input ulang");
            return;
        }

        System.out.print("Masukkan Employee ID: ");
        String employeeID = input.nextLine();

        Employee employee = getEmployeeByEmployeeId(employeeID, employeeList);
        if (employee == null) {
            System.out.println("Employee ID tidak ditemukan, silahkan input ulang");
            return;
        }

        List<Service> selectedServices = new ArrayList<>();
        boolean continueSelecting = true;
        while (continueSelecting) {
            System.out.print("Masukkan Service ID: ");
            String serviceID = input.nextLine();

            Service service = getServiceByServiceId(serviceID, serviceList);
            if (service != null) {
                if(selectedServices.contains(service)) {
                    System.out.println("Service ini sudah dipilih, silakan input service lainnya");
                } else {
                    selectedServices.add(service);
                }
            } else {
                System.out.println("Service ID tidak ditemukan, silahkan input ulang");
            }

            String jawaban;
            while (true) {
                System.err.println("Ingin pilih service yang lain (Y/T)?");
                jawaban = input.nextLine();
                if(jawaban.equalsIgnoreCase("Y") || jawaban.equalsIgnoreCase("T")) {
                    break;
                } else {
                    System.out.println("Input tidak valid. Silakan masukkan 'Y' atau 'T'");
                }
            } if (jawaban.equalsIgnoreCase("T")) {
                continueSelecting = false;
            }
        }

        String reservationID = "Rsv-0" + count++;
        Reservation newReservation = new Reservation(reservationID, customer, employee, selectedServices, "In Process");
        reservationList.add(newReservation);
        
        System.out.println("Booking Berhasil!");
        System.out.printf("Reservation ID: %s\n", reservationID);
        System.out.printf("Total Biaya Booking: Rp%.2f\n", newReservation.getReservationPrice());
    }

    public static void editReservationWorkstage(List<Reservation> reservationList) {
        Scanner input = new Scanner(System.in);
        System.out.println("Finish / Cancel Reservation");
        System.out.print("Masukkan Reservation ID: ");
        String reservationID = input.nextLine();
        Reservation reservation = getReservationByReservationId(reservationID, reservationList);
        if (reservation == null) {
            System.out.println("Reservation ID tidak ditemukan, silahkan input ulang");
            return;
        }

        String finishOrCancelReservation;
        while (true) {
            System.out.println("Selesaikan reservasi: (Finish / Cancel)");
            finishOrCancelReservation = input.nextLine();
            if(Pattern.matches("Finish|Cancel", finishOrCancelReservation)) {
                break;
            } else{
                System.out.println("Input tidak valid. Silakan masukkan 'Finish' atau 'Cancel'");
            }
        }
        reservation.setWorkstage(finishOrCancelReservation);
        System.out.println("Reservasi dengan ID: " + reservationID + " sudah " + finishOrCancelReservation);
        }

    public static void exit() {
        System.out.println("Terimakasih sudah berkunjung ke salon kami. Semoga anda puas dengan layanan yang diberikan.");
        System.exit(0);
    }

    public static Customer getCustomerByCustomerId(String customerID, List<Customer> customerList) {
        for (Customer customer : customerList) {
            if (customer.getId().equals(customerID)) {
                return customer;
            }
        }
        return null;
    }

    public static Employee getEmployeeByEmployeeId(String employeeID, List<Employee> employeeList) {
        for (Employee employee : employeeList) {
            if (employee.getId().equals(employeeID)) {
                return employee;
            }
        }
        return null;
    }

    public static Service getServiceByServiceId(String serviceID, List<Service> serviceList) {
        for (Service service : serviceList) {
            if (service.getServiceId().equals(serviceID)) {
                return service;
            }
        }
        return null;
    }

    public static Reservation getReservationByReservationId(String reservationID, List<Reservation> reservationList) {
        for (Reservation reservation : reservationList) {
            if (reservation.getReservationId().equals(reservationID)) {
                return reservation;
            }
        }
        return null;
    }


    
}



    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan

