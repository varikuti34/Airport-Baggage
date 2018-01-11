package airport;

public class GateRouteVO{
  private GateVO from;
  private GateVO to;
  private double cost;

  public GateRouteVO(GateVO from, GateVO to, double cost) {
	this.from = from;
	this.to = to;
	this.cost = cost;
   }

  public GateVO getFrom() {
    return from;
  }

  public GateVO getTo() {
    return to;
  }

  public double getCost() {
     return cost;
  }


}