package airport;

public class BaggageVO {

  private String bagNumber = null;
  private String entryPoint = null;
  private String flightID = null;

  public BaggageVO(String bagNumber, String entryPoint, String flightID)
  {
    super();
    this.bagNumber = bagNumber;
    this.entryPoint = entryPoint;
    this.flightID = flightID;
  }

  public String getBagNumber() {
    return bagNumber;
  }

  public void setBagNumber(String bagNumber) {
    this.bagNumber = bagNumber;
  }

  public String getEntryPoint() {
    return entryPoint;
  }

  public void setEntryPoint(String entryPoint) {
    this.entryPoint = entryPoint;
  }

  public String getFlightID() {
    return flightID;
  }

  public void setFlightID(String flightID) {
    this.flightID = flightID;
  }

}
 