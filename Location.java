package hw1;
/**
 * Location Object
 * represents a place name and cost per night
 * contains a constructor for initializing the name and lodging cost
 * contains several accesor methods
 * @author hannahashley
 *
 */
public class Location {

	/**
	 * creates and initializes public constant
	 * The cost to mail a postcard from a location is this fraction times the location's lodging cost
	 */
	public static final double RELATIVE_COST_OF_POSTCARD = 0.05;

	/**
	 * declares instance variable locationName which will be modified as new locations are visited
	 */
	private String locationName;
	/**
	 * declares instance variable that represents the lodging cost per night
	 */
	private int costPerNight;
	
	/**
	 * constructs a new location with the given name and given lodging cost per night
	 * @param givenName
	 * @param givenLodgingCost
	 */
	public Location(String givenName, int givenLodgingCost)
	{
		locationName = givenName;
		costPerNight = givenLodgingCost;
	}
	
	/**
	 * returns the location's name
	 * @return
	 */
	public String getName()
	{
		return locationName;
	}
	
	/**
	 * returns the location's lodging cost per night
	 * @return
	 */
	public int lodgingCost()
	{
		return costPerNight;
	}
	
	/**
	 * returns the cost to send a postcard from this location
	 * @return
	 */
	public int costToSendPostcard()
	{
		// calculates cost to send one postcard by multiplying the constant variable and cost per night
		double costToSend = RELATIVE_COST_OF_POSTCARD * costPerNight;
		
		// rounds the cost to send to the nearest integer
		return (int) Math.round(costToSend);
	}
	
	/**
	 * returns the number of nights of lodging in this location that a backpacker could
	 * afford with the given amount of money
	 * @param funds
	 * @return
	 */
	public int maxLengthOfStay(int funds)
	{
		return funds / costPerNight;
	}
	
	/**
	 * returns the maximum number of postcards the backpacker can send with their current funds
	 * @param funds
	 * @return
	 */
	public int maxNumberOfPostcards(int funds)
	{
		// calls on costToSendPostcard method from this Location class
		return funds / costToSendPostcard();
	}
}
