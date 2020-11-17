package carpetcostgui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;

import carpetcost.CostCalculator;

import javax.swing.event.ChangeEvent;
import java.awt.Font;

public class CarpetTaskGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5050199737881369620L;
	private JPanel contentPane;
	private JSpinner costSpinner;
	private JSpinner amountSpinner;
	private JRadioButton zoneAButton;
	private JRadioButton zoneBButton;
	private JCheckBox existingCustomerCheck;
	private JLabel costLabel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private CostCalculator carpetCostCalculator;
	private JPanel panel_1;
	private JLabel carpetCostLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarpetTaskGUI frame = new CarpetTaskGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CarpetTaskGUI() {
		setTitle("We Got You Covered");

		carpetCostCalculator = new CostCalculator();
		carpetCostCalculator.freeDeliveryThreshold = 200;
		carpetCostCalculator.exitingCustomerDiscount = 5;
		carpetCostCalculator.vatRate = 0;
		carpetCostCalculator.deliveryZones.add(5.00);
		carpetCostCalculator.deliveryZones.add(10.00);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 393, 219);
		panel_1.setBorder(new TitledBorder(null, "Calculate Carpet Cost", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_1);
						
								costSpinner = new JSpinner();
								costSpinner.setBounds(107, 19, 86, 30);
								costSpinner.addChangeListener(new ChangeListener() {
									public void stateChanged(ChangeEvent e) {
										calculateCost();
									}
								});
										panel_1.setLayout(null);
								
										JLabel lblNewLabel_1 = new JLabel("Carpet Price");
										lblNewLabel_1.setBounds(10, 9, 87, 50);
										panel_1.add(lblNewLabel_1);
								panel_1.add(costSpinner);
								costSpinner.setModel(new SpinnerNumberModel(10d, 0.01d, null, 1d));
				
						JLabel lblNewLabel_2 = new JLabel("Square Meters");
						lblNewLabel_2.setBounds(203, 11, 84, 43);
						panel_1.add(lblNewLabel_2);
				
						amountSpinner = new JSpinner();
						amountSpinner.setBounds(297, 19, 86, 30);
						amountSpinner.addChangeListener(new ChangeListener() {
							public void stateChanged(ChangeEvent e) {
								calculateCost();
							}
						});
						panel_1.add(amountSpinner);
						amountSpinner.setModel(new SpinnerNumberModel(10d, 1d, null, 1d));
		
				carpetCostLabel = new JLabel("Carpet Cost:");
				carpetCostLabel.setBounds(10, 61, 373, 25);
				carpetCostLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				carpetCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
				panel_1.add(carpetCostLabel);
				
						JLabel lblNewLabel = new JLabel("Delivery Zone");
						lblNewLabel.setBounds(10, 93, 79, 30);
						panel_1.add(lblNewLabel);
		
				JPanel panel = new JPanel();
				panel.setBounds(93, 93, 100, 30);
				panel_1.add(panel);
				panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
				
						zoneAButton = new JRadioButton("A");
						zoneAButton.setSelected(true);
						zoneAButton.addChangeListener(new ChangeListener() {
							public void stateChanged(ChangeEvent e) {
								calculateCost();
							}
						});
						buttonGroup.add(zoneAButton);
						
								panel.add(zoneAButton);
								
										Component horizontalStrut = Box.createHorizontalStrut(20);
										panel.add(horizontalStrut);
										
												zoneBButton = new JRadioButton("B");
												zoneBButton.addChangeListener(new ChangeListener() {
													public void stateChanged(ChangeEvent e) {
														calculateCost();
													}
												});
												buttonGroup.add(zoneBButton);
												panel.add(zoneBButton);
		
				existingCustomerCheck = new JCheckBox("Existing Customer");
				existingCustomerCheck.setBounds(203, 93, 180, 30);
				existingCustomerCheck.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						calculateCost();
					}
				});
				panel_1.add(existingCustomerCheck);
		
				costLabel = new JLabel("Final Cost:");
				costLabel.setBounds(10, 148, 373, 60);
				costLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
				costLabel.setHorizontalAlignment(SwingConstants.CENTER);
				panel_1.add(costLabel);
	}
	private void calculateCost()
	{
		double carpetCost = (double)getCostSpinner().getValue();
		double carpetAmount = (double)getAmountSpinner().getValue();
		double CarpetCost = carpetCost*carpetAmount;
		getCarpetCostLabel().setText(String.format("Carpet Cost: £%.2f",CarpetCost));
		boolean existingCustomer = getExistingCustomerCheck().isSelected();
		int deliveryZone = 0;		
		if(getZoneAButton().isSelected())
			deliveryZone = 0;
		else
			deliveryZone = 1;
		

		double totalCost = carpetCostCalculator.getCost(carpetCost, carpetAmount, existingCustomer, deliveryZone);

		getCostLabel().setText(String.format("Final Cost: £%.2f",totalCost));
	}

	public JSpinner getCostSpinner() {
		return costSpinner;
	}
	public JSpinner getAmountSpinner() {
		return amountSpinner;
	}
	public JRadioButton getZoneAButton() {
		return zoneAButton;
	}
	public JRadioButton getZoneBButton() {
		return zoneBButton;
	}
	public JCheckBox getExistingCustomerCheck() {
		return existingCustomerCheck;
	}
	public JLabel getCostLabel() {
		return costLabel;
	}
	public JLabel getCarpetCostLabel() {
		return carpetCostLabel;
	}
}
