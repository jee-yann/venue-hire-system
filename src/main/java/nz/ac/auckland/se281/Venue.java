package nz.ac.auckland.se281;

public class Venue {

  private String name;
  private String code;
  private String capacity;
  private String hireFee; 

  public Venue(String name, String code, String capacity, String hireFee) {
    this.name = name;
    this.code = code;
    this.capacity = capacity;
    this.hireFee = hireFee;
  }

  public void printDetails() {
    MessageCli.VENUE_ENTRY.printMessage(this.name, this.code, this.capacity, this.hireFee);
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

}