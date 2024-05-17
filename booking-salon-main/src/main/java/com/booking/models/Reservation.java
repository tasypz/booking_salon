package com.booking.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    private String reservationId;
    private Customer customer;
    private Employee employee;
    private List<Service> services;
    private double reservationPrice;
    private String workstage;
    //   workStage (In Process, Finish, Canceled)

    public Reservation(String reservationId, Customer customer, Employee employee, List<Service> services,
            String workstage) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.employee = employee;
        this.services = services;
        this.reservationPrice = calculateReservationPrice();
        this.workstage = workstage;
    };

    private double calculateReservationPrice() {
        double total = 0;
        for (Service service : services) {
            total += service.getPrice();
        }

        double discount = 0;
        String membership = customer.getMember().getMembershipName();

        if (membership.equalsIgnoreCase("silver")) {
            discount = 0.05;
        } else if (membership.equalsIgnoreCase("gold")) {
            discount = 0.1;
        }
        return total * (1 - discount);
    }

    public double getReservationPrice() {
        return reservationPrice;
    }
}
