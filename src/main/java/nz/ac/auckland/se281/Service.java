package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public abstract class Service {

  private String serviceName;

  public Service(String serviceName) {
    this.serviceName = serviceName;
  }

  public abstract String getTotalCost(String attendees);

  public abstract String getServiceType();

  public String getServiceName() {
    return this.serviceName;
  }

}