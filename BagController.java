package airport;

import java.io.IOException;
import java.util.*;


public class BagController {

	static List<BaggageVO> bagList = new ArrayList<BaggageVO>();
	static HashMap<String, String> flightMap = new HashMap<String, String>();
	static BagSorter bagSorter = new BagSorter();
	static Map<String, GateVO> gatesMap = new HashMap<>();

	public static void main(String[] args) {
	  BagHelper bHelper ;
	  try {
		readInput();
		
		bHelper = new BagHelper (bagSorter, flightMap, bagList);
		bHelper.displayTheRoutes();
		
	       }
	  catch (IOException e) {
		e.printStackTrace();
	      }
	}

	private static void readInput() throws IOException {
	Scanner scnr = new Scanner(BagController.class.getResourceAsStream(AirportConstants.INPUT_FILE_NAME));
	
	while(scnr.hasNextLine()) {
		String line = scnr.nextLine();
		
		if(line.startsWith(AirportConstants.SECTION_HEAD_CONVEYORS))
		{
		   while(scnr.hasNextLine() ) {
			line = scnr.nextLine();
			if(line.startsWith(AirportConstants.NEW_SECTION_START))
			{
			  break;
			}
		   String[] splitedNode = line.split(" ");
		   String gate1 = splitedNode[0];
		   String gate2 = splitedNode[1];
		   int time = Integer.parseInt(splitedNode[2]);
		   bagSorter.addLink(createNode(gate1,gatesMap),createNode(gate2,gatesMap),time);
		   bagSorter.addLink(createNode(gate2,gatesMap),createNode(gate1,gatesMap),time);
		}
	      }
	    
	      if(line.startsWith(AirportConstants.SECTION_HEAD_DEPARTURES))
		{
		   while(scnr.hasNextLine()  ) {
			line = scnr.nextLine();
			if(line.startsWith(AirportConstants.NEW_SECTION_START))
			{
			  break;
			}
		        String[] splitedFlight = line.split(" ");
			flightMap.put(splitedFlight[0],splitedFlight[1]);
		     }
		     flightMap.put(AirportConstants.FLIGHT_ARRIVAL, AirportConstants.GATE_BAGGAGE_CLAIM); //adding this infers that Bag with ARRIVAL as flight id needs to reach Baggage Claim
		   }

		if(line.startsWith(AirportConstants.SECTION_HEAD_BAGS))
		{
		   while(scnr.hasNextLine()  ) {
			String bagline = scnr.nextLine();
			String[] splitedBag = bagline.split(" ");
			BaggageVO bag = new BaggageVO(splitedBag[0],splitedBag[1],splitedBag[2]);
			bagList.add(bag);
		     }
		}
	       }
		
		scnr.close();
	}

	private static GateVO createNode(String gate, Map<String, GateVO> nodeMap) {
	if(nodeMap.containsKey(gate)) {
	return nodeMap.get(gate);
	}

	GateVO gateNode = new GateVO(gate);
	nodeMap.put(gate, gateNode);
	return gateNode;
	}
   }



			
	
	