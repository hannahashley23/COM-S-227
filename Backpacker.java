package hw1;
/**
 * Backpacker Object
 * simulates a backpacker's trip around Europe
 * includes and edits information such as funds, location, sending postcards, and history of places visited
 * @author hannahashley
 *
 */
public class Backpacker {
	/**
	 * creates and initializes public constant
	 * Proportionality constant when calling home for more money
	 */
	public static final int SYMPATHY_FACTOR = 30;
	
	/**
	 * instance variables for location, journal, funds, total postcards, and nights in train station
	 */
	
	/**
	 * location variable that will be updated as backpacker visits new locations
	 */
	private Location currentLocation; 
	
	/**
	 * String variable that is updated with information regarding backpacker's trip
	 */
	private String journal = "";
	
	/**
	 * int variable that is updated every time the backpacker's funds are increased/decreased
	 */
	private int currentFunds;
	
	/**
	 * int variable that represents how many nights stayed in a location past the amount of nights the backpacker can afford
	 */
	private int numNightsInTrainStation;
	
	/** 
	 * int variable that is updated each time the backpacker sends postcards
	 * reset to 0 after the backpacker calls home for money
	 */
	private int totalPostcardsSent;
	
	/**
	 * constructs a new Backpacker object
	 * takes in parameters of integer initialFunds and the object initialLocation
	 * @param initialFunds
	 * @param initialLocation
	 */
	public Backpacker(int initialFunds, Location initialLocation) 
	{
		currentLocation = initialLocation; 
		currentFunds = initialFunds;
		
		//initializes journal to start with initial location and start value 
		//this journal string will be mutated in visit method once backpacker visits more places
		journal = (currentLocation.getName() + "(start)");
	}
	
	/**
	 * returns current location by calling on getName method from Location class
	 * @return
	 */
	public String getCurrentLocation()
	{
		return (currentLocation.getName());
	}
	
	/**
	 * returns the amount of mo ney the backpackdr currently has
	 * @return
	 */
	public int getCurrentFunds()
	{
		return currentFunds;
	}
	
	/**
	 * returns the journal string as it is constantly having strings added to it
	 * @return
	 */
	public String getJournal()
	{
		return (journal);	
	}
	
	/**
	 * returns a true/false value of whether the backpacker is basically out of money
	 * @return
	 */
	public boolean isSOL()
	{
		//returns true if current funds do not allow backpacker to send a postcard
		//returns false if current funds do allow backpacker to send a postcard
		return getCurrentFunds() < currentLocation.costToSendPostcard();	
	}
	
	/**
	 * returns the integer value of total nights spent in Train Station when visiting a location
	 * without enough money for lodging 
	 * @return
	 */
	public int getTotalNightsInTrainStation()
	{
		return numNightsInTrainStation;
	}
	
	/**
	 * method that takes in the parameters of the object c and integer numNights
	 * updates journal instance variables, calculates local variables, and updates current funds
	 * @param c
	 * @param numNights
	 */
	public void visit(Location c, int numNights)
	{
			currentLocation = c;
			
			// adds the visited location name and number of nights stayed into the journal string 
			journal += (" ," + c.getName() + "(" + numNights + ")");
			
			// calculates maximum affordable nights, number of nights purchased, and number of nights in train station
			int maxAffordableNights = currentFunds / currentLocation.lodgingCost();
			int numNightsPurchased = Math.min(numNights, maxAffordableNights);
			numNightsInTrainStation += numNights - numNightsPurchased;
			
			// updates current funds by subtracting funds spent on housing in location visited
			currentFunds = currentFunds - (currentLocation.lodgingCost() * numNightsPurchased);	
	}
	
	/**
	 * method that takes in how many postcards the backpacker wants to send and compares it to how
	 * many postcards the backpacker can possibly sent and takes the lesser value of these
	 * @param howMany
	 */
	public void sendPostcardsHome(int howMany)
	{
			// calculates number of postcards that are possible to send considering current funds
			// calls on maxNumberOfPostcards method from Location class 
			int numPostcardsSent = Math.min(howMany, currentLocation.maxNumberOfPostcards(currentFunds));
			totalPostcardsSent += numPostcardsSent;
			
			// updates current funds by subtracting cost to send postcards 
			currentFunds = currentFunds - (numPostcardsSent * currentLocation.costToSendPostcard()); 
	}
	
	/**
	 * method that adds money received from parents to the current funds
	 */
	public void callHomeForMoney()
		{
			currentFunds += (SYMPATHY_FACTOR * totalPostcardsSent);
			
			// resets the number of postcards sent to 0
			totalPostcardsSent = 0;
		}
		
	}
	

