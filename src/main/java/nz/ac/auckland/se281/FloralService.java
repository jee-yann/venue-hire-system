package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.FloralType;

public class FloralService extends Service{

  private FloralType floralType;

  public FloralService(FloralType floralType) {
    super("Floral (" + floralType.getName() + ")");
    this.floralType = floralType;
  }

  @Override
  public String getTotalCost(String attendees) {
    return (Integer.toString(floralType.getCost()));
  }

  @Override
  public String getServiceType() {
    return "Floral";
  }

}