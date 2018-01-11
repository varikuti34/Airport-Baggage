package airport;

public class GateVO implements Comparable<GateVO>{

  private String gateName;
  private Double minDistance = Double.POSITIVE_INFINITY;
  private GateVO previous;

  public GateVO(String gateName) {
    this.gateName = gateName;
   }

  public void setMinDistance(Double minDistance) {
    this.minDistance = minDistance;
  }

 public void setPrevious(GateVO previous) 
 {
   this.previous = previous;
 }

 public String getGateName() {
   return gateName;
 }

 public Double getMinDistance()
 {
   return minDistance;
 }

 public GateVO getPrevious()
 {
   return previous;
 }

 @Override
 public boolean equals(Object obj)
 {
   if(this == obj)
   {
      return true;
   }

   if(obj == null || !(obj instanceof GateVO)) {
      return false;
   }
    GateVO other = (GateVO) obj;
    return (this.gateName.equals(other.gateName));
 }

 @Override
 public int hashCode(){
   return gateName.hashCode();
  }

 @Override
 public int compareTo(GateVO other)
  {
    return Double.compare(minDistance, other.minDistance);
  }

}