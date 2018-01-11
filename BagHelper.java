package airport;

import java.util.*;

public class BagHelper {
	private List<BaggageVO> bagList;
	private BagSorter bagSorter;
	private HashMap<String, String> flightMap;
	
	public BagHelper(BagSorter bagSorter, HashMap<String, String> flightMap, List<BaggageVO> bagList) {
		this.bagList = bagList;
		this.bagSorter = bagSorter;
		this.flightMap = flightMap;
		
	}
	
	public void displayTheRoutes() {
		
		for (BaggageVO bag : bagList) {
			
			String destinationGate = flightMap.get(bag.getFlightID());
			
			if(destinationGate.isEmpty())
			{
				destinationGate = AirportConstants.GATE_BAGGAGE_CLAIM;
			}
			
			List<GateVO> pathList = bagSorter.findOptimisedRoute(new GateVO(bag.getEntryPoint()), new GateVO(destinationGate));
			
			String path = bag.getBagNumber() +" "+ bag.getEntryPoint();
			GateVO finalNode = null;
			for(GateVO gateStop : pathList)
			{
				path = path + " "+gateStop.getGateName();
				finalNode = gateStop;
			}
			int timeTaken = finalNode.getMinDistance().intValue();
			path = path +" : "+timeTaken;
			System.out.println(path);
		}
	}

}
