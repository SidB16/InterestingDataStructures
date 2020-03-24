/*
 * A simple class that represents a Flower
 * 
 * @author sid16
 */
public class Flower {
	
	/*
	 * Declare the feilds; what the object has.
	 */
	public String name;
	public int numOfPetals;
	public float price;
	
	//remeber there is diffracnce between primivtive type and wrapper type
	
	/*
	 * Construtor to initliaze state the of object
	 */
	public Flower( String nm, int petals, float prc) {
		name = nm;
		numOfPetals = petals;
		price = prc;
	}
	
	//getter methods
	public String getName() { return name;	}
	public int getPetals() { return numOfPetals; }
	public float getPrice() { return price; }
	
	//setter methods
	public void setName( String nm) { nm = name; };
	public void setPetals ( int petl) { petl = numOfPetals; } 
	public void setPrice ( float prc) { prc = price; }
	
}
