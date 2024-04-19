package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.FloralType;

public class MusicService extends Service{

  public MusicService() {
    super("Music");
  }

  @Override
  public String getTotalCost(String attendees) {
    return "500";
  }

}