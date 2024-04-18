package nz.ac.auckland.se281;

public class Booking {

  private String reference;
  private String venue;
  private String date;
  private String attendees; 

  public Booking(String reference, String venue, String date, String attendees) {
    this.reference = reference;
    this.venue = venue;
    this.date = date;
    this.attendees = attendees;
  }

  public String getReference() {
    return this.reference;
  }

  public String getVenue() {
    return this.venue;
  }

  public String getDate() {
    return this.date;
  }

  public String getAttendees() {
    return this.attendees;
  }

}