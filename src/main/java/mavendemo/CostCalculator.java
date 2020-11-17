package mavendemo;

import java.util.ArrayList;
import java.util.List;

public class CostCalculator {
	public double exitingCustomerDiscount=0;
	public double vatRate =20;
	public List<Double> deliveryZones;
	public double freeDeliveryThreshold =200;
	public CostCalculator()
	{
		deliveryZones = new ArrayList<Double>();
		
	}
	public double getCost(double price, double quanity,boolean existingCustomer,int deliveryZone)
	{
		double carpetCost = price * quanity;
		double deliveryCost = 0;
		double totalCost;
		if(carpetCost<=freeDeliveryThreshold-0.01)
		{
			if(deliveryZones.size()>0)
			{
				deliveryCost = deliveryZones.get(deliveryZone);
			}
		}
		totalCost = carpetCost+deliveryCost;
		if(existingCustomer)
		{
			totalCost*=1-exitingCustomerDiscount/100;
		}
		if(vatRate>0)
		{
			totalCost*=1+vatRate/100;
		}
		return totalCost;
	}
	
}
