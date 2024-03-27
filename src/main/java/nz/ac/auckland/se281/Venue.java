package nz.ac.auckland.se281;

public class Venue {

  private String name;
  private String code;
  private String capacityInput;
  private String hireFeeInput; 

  public Venue(String name, String code, String capacityInput, String hireFeeInput) {
    this.name = name;
    this.code = code;
    this.capacityInput = capacityInput;
    this.hireFeeInput = hireFeeInput;
  }
}