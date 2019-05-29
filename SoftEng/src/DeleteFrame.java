import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;



public class DeleteFrame extends JFrame {
	
	private JPanel panel;
	private JButton confirm;
	private JLabel storageTitle, deleteTitle;
	private JMenuBar mb;
	private JMenu orderMenu;
	private JMenu storageMenu;
	private JMenu statisticsMenu;
	private JMenu centralMenu_Menu;
	private JPanel menuPanel;
	private JMenuItem i1,i2,i3,i4,i5,iCentralMenu;
	private JTable storageTable;
	private JTable deleteTable;
	private DefaultTableModel storageModel,basketModel;
	private Order order;
	private db conn;

	
	public DeleteFrame(db connection) {
		conn=connection;
				//���������� ������ �����
				mb = new JMenuBar();
	
				//���������� ��� ������ �����������
				confirm = new JButton("Delete Medicine(s)");
				ButtonListenerDeleteButton deleteButton = new ButtonListenerDeleteButton();
				confirm.addActionListener(deleteButton);
				
				// Prosthiki eikonas kai listener gia aythn
				
				ImageIcon icon = new ImageIcon("hospital1.png");
				JLabel lb = new JLabel(icon);
			
				lb.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) 
				    {	 
						dispose();
						new GlobalHomeFrame(conn);   ;           
				                
				    }
					
				}	);
						
	

			    //�������
				storageModel = new DefaultTableModel();
			    basketModel = new DefaultTableModel();
			    
			    storageModel.addColumn("Id");
			    storageModel.addColumn("Name");
			    storageModel.addColumn("Availability");
			    
			    basketModel.addColumn("Id");
			    basketModel.addColumn("Name");
			    basketModel.addColumn("Quantity");
				
			    String medicineName = null;
			    String medicineId = null;
			    int medicineAvailability = -1;
			    
			    for(int i=0;i<Storage.getMedicineList().size();i++) {
			    	
			    	medicineName = Storage.getMedicineList().get(i).getName();
			    	medicineId = Storage.getMedicineList().get(i).getId();
			    	medicineAvailability = Storage.getMedicineList().get(i).getAvailability();
			    	storageModel.addRow(new Object[] {medicineId,medicineName,medicineAvailability});
			    }
				
			    // Dimiourgia tou pinaka me basi tis parapanw stiles kai grammes
			    
			    storageTable = new JTable(storageModel);
			    deleteTable = new JTable(basketModel);
		 
			    // Kathorismos topothetisis tou pinaka
			    
			    storageTable.setAlignmentX(Component.LEFT_ALIGNMENT);
			    deleteTable.setAlignmentX(Component.RIGHT_ALIGNMENT);
			    
			    // Dimiourgia scrollPane gia ton pinaka 
			    
			    JScrollPane storageScrollPane = new JScrollPane(storageTable);
			    JScrollPane orderScrollPane = new JScrollPane(deleteTable);
				storageScrollPane.setBounds(36, 37, 407, 79);
				orderScrollPane.setBounds(36, 37, 407, 79);
			    
			    // Dimiourgia titlou tou pinaka (apothiki), tou pinaka (kalathi) kai tou TextField gia to totalCost
				
			    storageTitle = new JLabel("STORAGE");
			    deleteTitle = new JLabel("Delete");

				//���������� �����
			    centralMenu_Menu = new JMenu("Central Menu");
				orderMenu = new JMenu("Order");
				storageMenu = new JMenu("Storage");
				statisticsMenu = new JMenu("Statistics");
				
				
				//���������� �������� ���� �����
	
				i1 = new JMenuItem("Prescription");  
			    i2 = new JMenuItem("Supply");  
			    i3 = new JMenuItem("Add");
			    i4 = new JMenuItem("Delete"); 
			    i5 = new JMenuItem("Show Statistics");
			    iCentralMenu = new JMenuItem("Go to Central Menu");
			    
			    // Eisagwgi ActionListener gia ta pedia tou Menu
			    
			    JTablePopupMenuListener menuListener = new JTablePopupMenuListener();
			    i1.addActionListener(menuListener);
			    i2.addActionListener(menuListener);
			    i3.addActionListener(menuListener);
			    i4.addActionListener(menuListener);
			    i5.addActionListener(menuListener);
			    iCentralMenu.addActionListener(menuListener);
				
			    //E������� ��� �����
			    centralMenu_Menu.add(iCentralMenu);
			    orderMenu.add(i1);
			    orderMenu.add(i2);
			    storageMenu.add(i3);
			    storageMenu.add(i4);
			    statisticsMenu.add(i5);
			    
			    //�������� ��� ����� ���� �����
			    mb.add(iCentralMenu);
			    mb.add(orderMenu);
			    mb.add(storageMenu);
			    mb.add(statisticsMenu);
			    mb.add(lb);	
			    
			    // Kathorismos topothethshs tou titlou
			    
			    storageTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
			    deleteTitle.setAlignmentX(Component.RIGHT_ALIGNMENT);
			    

				//���������� panel
				panel = new JPanel();
				menuPanel = new JPanel();
			   
			    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
				panel.setAlignmentX(Component.LEFT_ALIGNMENT);
						
			    
			    //�������� ����� ��� �����
			    menuPanel.add(mb);
					    
			    
				//�������� ��� panel   
			    
			    panel.add(menuPanel);
			    
			    panel.add(storageTitle);
			    panel.add(storageScrollPane);
				
				panel.add(deleteTitle);
			    panel.add(orderScrollPane);
			 
				panel.add(confirm);
				
				
				//���������� ������������ Supply ���� ��� ��� ������
				order = new Supply();
				
				//Epikoinwnia
				
				storageTable.setCellSelectionEnabled(true);
		 	    ListSelectionModel cellSelectionModel = storageTable.getSelectionModel();
		 	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						
				
		 	   cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
		 	    	
		  	      public void valueChanged(ListSelectionEvent e) {
		  	    	  
		  	    	if( !e.getValueIsAdjusting() ) {
		  	    		
		  	    	
		  	    	  String medicineName = "";
		  	    	  String medicineId= "";
		  	    	  int selectedRow = storageTable.getSelectedRow();
		  	    	  
		  	    	 if(selectedRow != -1) {
		  
		  	    		 medicineId = (String) storageTable.getModel().getValueAt(selectedRow,0);
		  	        	  	
		  	    		 medicineName = (String) storageTable.getModel().getValueAt(selectedRow, 1);
		  	    		 
		  	    		 Drug clickedMedicine = Storage.searchMedicine(medicineName,medicineId);
		  	    		 
		  	    		 if(!order.searchForMedicineInOrder( clickedMedicine.getId())) {
		  	    			 
		  	    			 order.addMedicineInTheOrder(clickedMedicine, 1); 		// Arxika 1 kai meta ginetai tropopoihsh
		  	    			 
		  	    			 DefaultTableModel modelForOrderTable = (DefaultTableModel) deleteTable.getModel();
		  	    			 
		  	    			 modelForOrderTable.addRow(new Object[]{clickedMedicine.getId(), clickedMedicine.getName(), 1}); 
		  	    			 
		  	    			 for(int i=0;i<Storage.getMedicineList().size();i++) {
		  	    				 
		  	    				 int medicineAvailability = Storage.getMedicineList().get(i).getAvailability();
		  	    				 storageTable.getModel().setValueAt(medicineAvailability, i, 2); 
		  	    				 }
		  	    			 }
		  	    		 }
		  	    	 }
		  	    	}
		  	      
		 	   } );
		 	   
		 	   
		 	   
		 	  deleteTable.addMouseListener(new MouseAdapter() {
		 	    	
		 	    	
		 	    	public void mousePressed (MouseEvent e) {
		 	    		
		 	    		 String medicineName = "";
		 	    		 String medicineId = "";

		 		    	 int selectedRowFromOrderTable = e.getY()/deleteTable.getRowHeight();
		 		    	  
		 		    	  
		 		    	 for (int j = 0; j < 2; j++) {
		 		        	  
		 		    		 
		 		        	  	if( j==0 )
		 		        	  		medicineId = (String) deleteTable.getModel().getValueAt(selectedRowFromOrderTable,j);
		 		        	  	else if( j==1 )
		 		        	  		medicineName = (String) deleteTable.getModel().getValueAt(selectedRowFromOrderTable, j);
		 		        	  	
		 		    	  }
		 		    	  
		 		    	Drug clickedMedicine = Storage.searchMedicine(medicineName,medicineId);
		 		    	
		 	    		
		 	    		if( e.getButton() == MouseEvent.BUTTON3) {
		 	    			
		 	    			String message = "Would you like to delete this medicine from the list?\n";
		 	    	        int returnValue = JOptionPane.showConfirmDialog(null, message,"Delete",JOptionPane.YES_NO_OPTION);
		 	    	        
		 	    	        if (returnValue == JOptionPane.YES_OPTION) {
		 	    			
		 	    			order.deleteMedicineFronTheOrder(clickedMedicine);
		 	    			
		 	    			String medId = "";
		 	    			
		 	    			for(int i=0;i<Storage.getMedicineList().size();i++) {
		 	    				
		 	    				medId = (String) storageTable.getModel().getValueAt(i,1);
		 	    				
		 	    				if(clickedMedicine.getId().equals(medId)) {
		 	    					
		 	    					DefaultTableModel model = (DefaultTableModel)storageTable.getModel();
		 	    					model.setValueAt(clickedMedicine.getAvailability(),i, 2);
		 	    					
		 	    					}
		 	    				}
		 	    			
		 	    			
		 	    			((DefaultTableModel) deleteTable.getModel()).removeRow(selectedRowFromOrderTable);
		 	   	    	  	
		 	   	    	  	for(int i=0;i<Storage.getMedicineList().size();i++) {
		 	   	    	  		
		 	   	    	  		int medicineAvailability = Storage.getMedicineList().get(i).getAvailability();
		 	   	    	  		storageTable.getModel().setValueAt(medicineAvailability, i, 2);
		 	   	    	  		
		 	   	    	  	}
		 	    	       }
		 	    	      }
		 	            }
		 	    	
		 	    	} 	);
		 	    

				//�������� ��� panel ��� contentpane
						
				this.setContentPane(panel);
						
						
				//���������� ��� ������� ��������������� ��� panel
						
				this.setSize(600,400);
				this.setTitle("/Supply Chain/Pharmacist/Storage/Delete");
				this.setVisible(true);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			}
			
			class JTablePopupMenuListener implements ActionListener {


				public void actionPerformed(ActionEvent e) {
					
					if(e.getActionCommand().equals("Go to Central Menu")){
						
						dispose();
						new SupplyChainMainFrame(conn);
					}
					
					else if(e.getActionCommand().equals("Prescription") ) {
							
						dispose();
						new PrescriptionAndSupplyFrame(true,conn);
						
					}
					
					else if(e.getActionCommand().equals("Supply")){
						
						dispose();
						new PrescriptionAndSupplyFrame(false,conn);
						
					}
					
					else if(e.getActionCommand().equals("Add")) {
						
						dispose();
						new AddFrame(conn);
					}
					
					else if(e.getActionCommand().equals("Delete")) {
						
						dispose();
						new DeleteFrame(conn);
					}
					
					else if(e.getActionCommand().equals("Show Statistics")) {
						
						dispose();
						new StatisticsFrame(conn);
					}
					
				}
				
			}
			
			
			class ButtonListenerDeleteButton implements ActionListener {
				
				public void actionPerformed(ActionEvent e) {
					
					
					for (int i = 0 ; i<deleteTable.getRowCount(); i++) {
						String name = (String) deleteTable.getValueAt(i, 1);
						String id = (String) deleteTable.getValueAt(i, 0);
						Storage.removeMedicine(name, id);
						
						//Enhmerwsi ths basis
					
					}
					
					dispose();
					new DeleteFrame(conn);
					
				}
			}	
			
			
		}
