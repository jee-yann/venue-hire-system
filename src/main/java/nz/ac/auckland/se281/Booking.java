package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking {

  private String reference;
  private String email;
  private String venue;
  private String bookingDate;
  private String partyDate;
  private String attendees; 
  private ArrayList<Service> serviceList;

  public Booking(String reference, String email, String venue, 
  String bookingDate, String partyDate, String attendees) {
    this.reference = reference;
    this.email = email;
    this.venue = venue;
    this.bookingDate = bookingDate;
    this.partyDate = partyDate;
    this.attendees = attendees;
    this.serviceList = new ArrayList<Service>();
  }

  public String getReference() {
    return this.reference;
  }

  public String getEmail() {
    return this.email;
  }

  public String getVenue() {
    return this.venue;
  }

  public String getBookingDate() {
    return this.bookingDate;
  }

  public String getPartyDate() {
    return this.partyDate;
  }

  public String getAttendees() {
    return this.attendees;
  }

  public void addService(Service service) {
    this.serviceList.add(service);
  }

  public ArrayList<Service> getServiceList() {
    return this.serviceList;
  }

}