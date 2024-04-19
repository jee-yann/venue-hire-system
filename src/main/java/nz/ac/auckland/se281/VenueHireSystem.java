package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;
import java.util.ArrayList;

public class VenueHireSystem {

  private ArrayList<Venue> venueList;
  private ArrayList<Booking> bookingList;
  private String systemDate;

  public VenueHireSystem() {
    this.venueList = new ArrayList<Venue>();
    this.bookingList = new ArrayList<Booking>();
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
      newVenue.updateSystemDate(this.systemDate);
      this.venueList.add(newVenue);
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
    }

    
  }

  public void setSystemDate(String dateInput) {
    this.systemDate = dateInput;
    for (Venue venue : venueList) {
      venue.updateSystemDate(this.systemDate);
    }
    MessageCli.DATE_SET.printMessage(systemDate);
  }

  public void printSystemDate() {
    MessageCli.CURRENT_DATE.printMessage(this.systemDate);
  }

  public void makeBooking(String[] options) {
    String code = options[0];
    String date = options[1];
    String email = options[2];
    int attendees = Integer.parseInt(options[3]);

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

    // Check if the venue exists.
    for (Venue venue : venueList) {
      if (venue.getCode().equals(code)) {
        int capacity = Integer.parseInt(venue.getCapacity());

        // Check if number of attendees is less than 25% or more than 100% of venue capacity.
        int oldAttendees = attendees;
        if (attendees < 0.25 * capacity) {
          attendees = (int) (capacity * 0.25);
        } else if (attendees > capacity) {
          attendees = capacity;
        }

        // Check if the venue is not already booked on the same day.
        for (Booking booking : bookingList) {
          if (booking.getVenue().equals(venue.getCode()) && booking.getPartyDate().equals(date)) {
            MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(venue.getName(), date);
            return;
          }
        }


        if (oldAttendees != attendees) {
          MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(Integer.toString(oldAttendees), Integer.toString(attendees), Integer.toString(capacity));
        }
        String reference = BookingReferenceGenerator.generateBookingReference();
        MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(reference, venue.getName(), date, Integer.toString(attendees));
        Booking newBooking = new Booking(reference, email, venue.getCode(), systemDate, date, Integer.toString(attendees));
        bookingList.add(newBooking);
        venue.addDate(date);
        return;
      }
    }

    
    MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(code);

    
  }


  public void printBookings(String venueCode) {
    for (Venue venue : venueList) {
      if (venue.getCode().equals(venueCode)) {
        MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venue.getName());

        // Checking if there are no bookings at the venue.
        if (venue.getDates().isEmpty()) {
          MessageCli.PRINT_BOOKINGS_NONE.printMessage(venue.getName());
        } else {
          for (Booking booking : bookingList) {
            if (booking.getVenue().equals(venueCode)) {
              MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(booking.getReference(), booking.getPartyDate());
            }
          }
        }
        return;
      }
    }

    MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);

  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    for (Booking booking : bookingList) {
      if (bookingReference.equals(booking.getReference())) {
        Service service = new CateringService(cateringType);
        booking.addService(service);
        MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Catering (" + cateringType.getName() + ")", bookingReference);
        return;
      }
    }
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
  }

  public void addServiceMusic(String bookingReference) {
    for (Booking booking : bookingList) {
      if (bookingReference.equals(booking.getReference())) {
        Service service = new MusicService();
        booking.addService(service);
        MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
        return;
      }
    }
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    for (Booking booking : bookingList) {
      if (bookingReference.equals(booking.getReference())) {
        Service service = new FloralService(floralType);
        booking.addService(service);
        MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Floral (" + floralType.getName() + ")", bookingReference);
        return;
      }
    }
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
  }

  public void viewInvoice(String bookingReference) {
    for (Booking booking : bookingList) {
      if (bookingReference.equals(booking.getReference())) {
        Venue bookedVenue = null;
        for (Venue venue : venueList) {
          if (booking.getVenue().equalsIgnoreCase(venue.getCode())) {
            bookedVenue = venue;
          }
        }
    
        MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(bookingReference, booking.getEmail(), booking.getBookingDate(), booking.getPartyDate(), booking.getAttendees(), bookedVenue.getName());
        MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(bookedVenue.getHireFee());
        for (Service service : booking.getServiceList()) {
          switch (service.getServiceType()) {
            case ("Catering"):
              MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(service.getServiceName(), service.getTotalCost(booking.getAttendees()));
              break;
            case ("Music"):
              MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(service.getServiceName(), service.getTotalCost(booking.getAttendees()));
              break;
            case ("Floral"):
              MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(service.getServiceName(), service.getTotalCost(booking.getAttendees()));
              break;
          }
        


        }
        return;
      }
    }
    MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
  }
}

