package carpetcost;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculate the cost of an item based on the price of the item and the quantity plus  discount for existing customers, VAT and delivery costs 
 * @author Colm Canavan
 *
 */
public class CostCalculator {
	/**
	 * The percentage of discount applied to existing customers 0-100% Default:0
	 */
	public double exitingCustomerDiscount=0;
	/**
	 * The rate of VAT to be applied 0-100% Default:20
	 */
	public double vatRate =20;
	/**
	 * A list of of delivery costs
	 */
	public List<Double> deliveryZones;
	/**
	 * Price when cost is greater than or equal to delivery is free
	 */
	public double freeDeliveryThreshold =200;
	public CostCalculator()
	{
		deliveryZones = new ArrayList<Double>();
		
	}
	/**
	 * Calculate the cost of an item based on the price of the item and the quantity plus  discount for existing customers, VAT and delivery costs 
	 * @param price the price of the item
	 * @param quanity the number of items purchased
	 * @param existingCustomer false if customer is a new customer
	 * @param deliveryZone The index of the delivery zone list to apply
	 * @return Total cost of the item
	 */
	public double getCost(double price, double quanity,boolean existingCustomer,int deliveryZone)
	{
		double carpetCost = price * quanity;//calculate the initial cost of the carpet
		double deliveryCost = 0;
		double totalCost;
		if(carpetCost<=freeDeliveryThreshold-0.005)//check if cost is above the free delivery threshold
		{
			if(deliveryZones.size()>0)//apply the selected delivery cost
			{
				deliveryCost = deliveryZones.get(deliveryZone);
			}
		}
		totalCost = carpetCost+deliveryCost;//add the delivery cost to the total cost
		if(existingCustomer)//apply the existing customer discount
		{
			totalCost*=1-exitingCustomerDiscount/100;
		}
		if(vatRate>0)//if vat applies add it to the cost
		{
			totalCost*=1+vatRate/100;
		}
		return totalCost;
	}
	
}
