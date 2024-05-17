package com.booking.service;

import java.util.List;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class PrintService {
    public static void printMenu(String title, String[] menuArr){
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {   
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);   
            num++;
        }
    }

    public static String printServices(List<Service> serviceList){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i<serviceList.size(); i++){
            result.append(serviceList.get(i).getServiceName());
            if (i < serviceList.size() - 1){
                result.append(", ");
            }
        }
        return result.toString();
    }

    
    // Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
    public static void showRecentReservation(List<Reservation> reservationList){
        int num = 1;
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-7s | %-15s | %-50s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Total Biaya", "Workstage");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Waiting") || reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-7s | %-15s | %-50s | %-15s | %-10s |\n",
                num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), reservation.getReservationPrice(), reservation.getWorkstage());
                num++;
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");

            }
        }
    }

    public static void showAllCustomer(List<Customer> customerList){
        int num = 1;
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-10s | %-14s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Alamat", "Membership", "Uang");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Customer customer : customerList) {
            System.out.printf("| %-4s | %-10s | %-14s | %-15s | %-15s | %-10s |\n",
                num, customer.getId(), customer.getName(), customer.getAddress(), customer.getMember().getMembershipName(), customer.getWallet());
                num++;
        System.out.println("-----------------------------------------------------------------------------------------");

            }

    }

    public static void showAllEmployee(List<Employee> employeeList) {
        int num = 1;
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-10s | %-14s | %-15s | %-15s |\n",
                "No.", "ID", "Nama", "Alamat", "Pengalaman");
        System.out.println("--------------------------------------------------------------------------");
        for (Employee employee : employeeList) {
            System.out.printf("| %-4s | %-10s | %-14s | %-15s | %-15s |\n",
                num, employee.getId(), employee.getName(), employee.getAddress(), employee.getExperience());
                num++;
        System.out.println("--------------------------------------------------------------------------");

        }
    }

    public static void showHistoryReservation(List<Reservation> reservationList){
        int num = 1;
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-6s | %-13s | %-50s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Total Biaya", "Workstage");
        double totalKeuntungan = 0;
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Finish")) {
                totalKeuntungan += reservation.getReservationPrice();
            System.out.printf("| %-4s | %-6s | %-13s | %-50s | %-15s | %-10s |\n",
                num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), reservation.getReservationPrice(), reservation.getWorkstage());
                num++;
        System.out.println("---------------------------------------------------------------------------------------------------------------------");

            }
        }
        String formattedTotalKeuntungan = String.format("Rp%.2f", totalKeuntungan);
        System.out.format("| %-100s | %-10s |\n", "Total Keuntungan ", formattedTotalKeuntungan);
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
    }
}
