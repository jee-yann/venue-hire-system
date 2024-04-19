package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Venue {

  private String name;
  private String code;
  private String capacity;
  private String hireFee; 
  private String systemDate;
  private ArrayList<String> dates;

  public Venue(String name, String code, String capacity, String hireFee) {
    this.name = name;
    this.code = code;
    this.capacity = capacity;
    this.hireFee = hireFee;
    this.systemDate = "1/01/1970";
    this.dates = new ArrayList<String>();
  }

  public void addDate(String date) {
    this.dates.add(date);
  }

  public void updateSystemDate(String date) {
    this.systemDate = date;
  }

  public String getNextAvailableDate() {
    String nextAvailableDate = systemDate;
    Boolean dateFound = false;

    // Keep incrementing the day by one until unique date is found.
    while (!dateFound) {
      dateFound = true;
      for (String date : this.dates) {
        if (nextAvailableDate.equals(date)) {
          String[] dateParts = nextAvailableDate.split("/");
          int day = Integer.parseInt(dateParts[0]) + 1;
          String month = dateParts[1];
          String year = dateParts[2];
          String dayString = Integer.toString(day);

          // Add a trailing 0 if day in date is single digit.
          if (day < 10) {
            dayString = ("0" + dayString);
          }

          // Combine into string again.
          nextAvailableDate = (dayString + "/" + month + "/" + year);
          dateFound = false;
          break;
        }
      }
    }

    return nextAvailableDate;
  }

  public void printDetails() {
    MessageCli.VENUE_ENTRY.printMessage(this.name, this.code, this.capacity, 
                                        this.hireFee, this.getNextAvailableDate());
  }

  public String getName() {
    return this.name;
  }

  public String getCode() {
    return this.code;
  }

  public String getCapacity() {
    return this.capacity;
  }

  public String getHireFee() {
    return this.hireFee;
  }

  public ArrayList<String> getDates() {
    return this.dates;
  }

}