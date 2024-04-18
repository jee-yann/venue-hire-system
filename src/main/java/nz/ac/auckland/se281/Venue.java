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
    while (!dateFound) {
      dateFound = true;
      for (String date : this.dates) {
        if (nextAvailableDate.equals(date)) {
          String[] dateParts = nextAvailableDate.split("/");
          int day = Integer.parseInt(dateParts[0]) + 1;
          String month = dateParts[1];
          String year = dateParts[2];
          String dayString = Integer.toString(day);
          if (day < 10) {
            dayString = ("0" + dayString);
          }
          nextAvailableDate = (dayString + "/" + month + "/" + year);
          dateFound = false;
          break;
        }
      }
    }

    return nextAvailableDate;
  }

  public void printDetails() {
    MessageCli.VENUE_ENTRY.printMessage(this.name, this.code, this.capacity, this.hireFee, this.getNextAvailableDate());
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

  public ArrayList<String> getDates() {
    return this.dates;
  }

}