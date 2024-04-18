package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;
import java.util.ArrayList;

public class VenueHireSystem {

  private ArrayList<Venue> venueList;
  private String systemDate;

  public VenueHireSystem() {
    this.venueList = new ArrayList<>();
    this.systemDate = "not set";
  }

  public void printVenues() {
    int venueCount = this.venueList.size();
    switch (venueCount) {
      case 0:
        MessageCli.NO_VENUES.printMessage();
        break;
      case 1:
        MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
        break;
      case 2:
        MessageCli.NUMBER_VENUES.printMessage("are", "two", "s");
        break;
      case 3:
        MessageCli.NUMBER_VENUES.printMessage("are", "three", "s");
        break;
      case 4:
        MessageCli.NUMBER_VENUES.printMessage("are", "four", "s");
        break;
      case 5:
        MessageCli.NUMBER_VENUES.printMessage("are", "five", "s");
        break;
      case 6:
        MessageCli.NUMBER_VENUES.printMessage("are", "six", "s");
        break;
      case 7:
        MessageCli.NUMBER_VENUES.printMessage("are", "seven", "s");
        break;
      case 8:
        MessageCli.NUMBER_VENUES.printMessage("are", "eight", "s");
        break;
      case 9:
        MessageCli.NUMBER_VENUES.printMessage("are", "nine", "s");
        break;
      default:
        MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(venueCount), "s");
    }

    for (Venue venue : venueList) {
      venue.printDetails();
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // TODO implement this method

    int capacity;
    int hireFee;

    try {
      capacity = Integer.parseInt(capacityInput);
    } catch(Exception e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    }

    try {
      hireFee = Integer.parseInt(hireFeeInput);
    } catch(Exception e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }

    for (Venue venue : venueList) {
      if (venueCode.equals(venue.getCode())) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venue.getName());
        return;
      }
    }

    // Checking if the venue name is empty
    if (venueName.strip().isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
    } else if (capacity <= 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
    } else if (hireFee <= 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
    } else {
      Venue newVenue = new Venue(venueName, venueCode, capacityInput, hireFeeInput);
      this.venueList.add(newVenue);
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
    }

    
  }

  public void setSystemDate(String dateInput) {
    this.systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(systemDate);
  }

  public void printSystemDate() {
    MessageCli.CURRENT_DATE.printMessage(this.systemDate);
  }

  public void makeBooking(String[] options) {
    String code = options[0];
    String date = options[1];
    String email = options[2];
    String attendees = options[3];

    // Check if system date is set.
    if (this.systemDate.equals("not set")) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }

    String[] dateParts = date.split("/");
    int day = Integer.parseInt(dateParts[0]);
    int month = Integer.parseInt(dateParts[1]);
    int year = Integer.parseInt(dateParts[2]);

    String[] systemDateParts = this.systemDate.split("/");
    int systemDay = Integer.parseInt(systemDateParts[0]);
    int systemMonth = Integer.parseInt(systemDateParts[1]);
    int systemYear = Integer.parseInt(systemDateParts[2]);


    

    // Check if booking date is at/after system date.
    if ((systemYear > year) || ((systemYear == year) && (systemMonth > month)) || ((systemYear == year) && (systemMonth == month) && (systemDay > day))) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(date, systemDate);
      return;
    }

    // Check if there are any venues in the system.
    if (this.venueList.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }

    for (Venue venue : venueList) {
      if (venue.getCode().equals(code)) {
        MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(BookingReferenceGenerator.generateBookingReference(), venue.getName(), date, attendees);
        return;
      }
    }
    
    MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(code);

    
  }


  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}

