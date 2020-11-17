package mavendemo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert.*;

public class CostTests {

	static CostCalculator carpet;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		carpet = new CostCalculator();
		carpet.exitingCustomerDiscount = 5;
		carpet.freeDeliveryThreshold = 200;
		carpet.vatRate = 0;
		carpet.deliveryZones.add(5.0);
		carpet.deliveryZones.add(10.0);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Test
	public void test() {
		List<String> tests = CostTests.openCSV("C:\\Users\\30004001\\OneDrive - North West Regional College\\Foundation Degree\\Software Testing\\CarpetCostTestData.csv");
		for(String s :tests)
		{
			
			String[] split = s.split(",");
			if(split.length==5&&!split[4].equals("NT"))
			{
				int deliveryZone = split[2].toUpperCase().equals("A")?0:1;
				boolean existingCustomer =  split[3].toUpperCase().equals("TRUE")?true:false;
				double expectedResult = Double.parseDouble(split[4]);
				double result = CostTests.carpet.getCost(Double.parseDouble(split[0]), Double.parseDouble(split[1]),existingCustomer,deliveryZone);
				assertEquals(expectedResult, result,0);
			}
		}
	
	}
	private static List<String> openCSV(String path)
	{
		List<String> tests = new ArrayList<String>();
		Scanner file;
		try {
			file = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			return null;
		}
		boolean reading = true;
		do
		{
			String s = file.nextLine();
			tests.add(s);
			if(!file.hasNextLine())reading=false;
			
		}while(reading);
		file.close();
		return tests;
	}

}
