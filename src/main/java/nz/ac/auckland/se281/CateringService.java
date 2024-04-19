package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class CateringService extends Service{

  private CateringType cateringType;

  public CateringService(CateringType cateringType) {
    super("Catering (" + cateringType.getName() + ")");
    this.cateringType = cateringType;
  }

  @Override
  public String getTotalCost(String attendees) {
    return (Integer.toString(cateringType.getCostPerPerson() * Integer.parseInt(attendees)));
  }

  @Override
  public String getServiceType() {
    return "Catering";
  }

}