package airport;

import java.util.*;

public class BagSorter {
	private Map<GateVO, List<GateRouteVO>> gateRoutes = new HashMap<>();
	private Set<GateVO> nodes = new HashSet<>();
	private Set<GateRouteVO> links = new HashSet<>();

	public void addNode(GateVO node) {
	if(!gateRoutes.containsKey(node)) {
		gateRoutes.put(node, new ArrayList<GateRouteVO>());
		nodes.add(node);
		}
     	}

	public boolean isLink(GateVO from, GateVO to)
	{
	  List<GateRouteVO> links = gateRoutes.get(from);
	  if(links != null && !links.isEmpty()){
		for(GateRouteVO gateLink: links)
		{
		  if(gateLink.getTo().equals(to)){
		  return true;
		  }
		}
	     }
	    return false;
	}

	public void addLink(GateVO from, GateVO to, double cost){
	  addNode(from);
	  addNode(to);

	  if(!isLink(from,to)){
	   GateRouteVO link = new GateRouteVO(from, to, cost);
	   gateRoutes.get(from).add(link);
	   links.add(link);
	  }
	}

	public GateRouteVO getLink(GateVO source, GateVO target){
		 List<GateRouteVO> links = gateRoutes.get(source);
		for(GateRouteVO link: links)
		{
		  if(link.getTo().equals(target)){
		  return link;
		  }
		}
		return null;
	}

	public Iterable<GateVO> getNodes(){
 		return nodes;
	}

	public int getOrder(){
 		return gateRoutes.size();
	}
	
	public Iterable<GateRouteVO> getLinks(){
 		return links;
	}

	public int getSize(){
 		return links.size();
	}

	public boolean containsNode(GateVO gateNode){
 		return gateRoutes.containsKey(gateNode);
	}

	public boolean containsLink(GateRouteVO gateLink){
 		return links.contains(gateLink);
	}

	public List<GateVO> findOptimisedRoute(GateVO source, GateVO target) {
		List<GateVO> shortestPath = new ArrayList<>();

		source.setMinDistance(0D);
		PriorityQueue<GateVO> vertexQueue = new PriorityQueue<>();
		
		for(GateVO vertex : nodes) {
		  if(!vertex.equals(source)){
		     vertex.setMinDistance(Double.POSITIVE_INFINITY);
		     vertex.setPrevious(null);
		  }
		 else {
		     vertex = source;
		 }
		 vertexQueue.add(vertex);
		}

		while(!vertexQueue.isEmpty()){
		  GateVO u = vertexQueue.poll();

		  if(u.equals(target)) {
			while(u.getPrevious() != null) {
			  shortestPath.add(u);
			  u = u.getPrevious();
			}
			break;
		  }

		vertexQueue.remove(u);

		List<GateRouteVO> edges = gateRoutes.get(u);
		for(GateRouteVO edge: edges){
		   GateVO v = edge.getTo();
		
		   double weight = edge.getCost();
		   double distanceThroughU = u.getMinDistance() + weight;

		   if(distanceThroughU < v.getMinDistance()) {
			v.setMinDistance(distanceThroughU);
			v.setPrevious(u);
			vertexQueue.remove(v);
			vertexQueue.add(v);
		   }
		}
	     }

		Collections.reverse(shortestPath);
		return shortestPath;
	}

}