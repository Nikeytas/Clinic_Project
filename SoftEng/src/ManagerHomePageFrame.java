import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class ManagerHomePageFrame extends JFrame {
	
	private JPanel centralPanel;
	private JPanel mainPanel;
	private JPanel secondPanel;
	
	private JMenuBar menubar;
	private JMenu employeeMenu;
	private JMenu programMenu;
	private JMenuItem item1,item2;
	
	private JButton button1, button2;
	private JTextField FindField;
	private JScrollPane scrollPane, scrollPane2;
	private JTable table, program;
	private JLabel hrLabel;
	private db conn;
	
	private ArrayList<Doctor> doctors = new ArrayList<Doctor>(); // this list contains all the doctors
	// �� �� ���� ���� ������ ����� ��� ���� ��� �����


	public ManagerHomePageFrame(db connection) {
		conn= connection;
		centralPanel = new JPanel(new BorderLayout());
		mainPanel= new JPanel();
		secondPanel= new JPanel();
		
		menubar= new JMenuBar();
		employeeMenu= new JMenu("�����������");
		programMenu= new JMenu("���������");
		
		item1= new JMenuItem("��������");
		item2= new JMenuItem("����������");
		
		employeeMenu.add(item1);
		programMenu.add(item2);
		
		menubar.add(employeeMenu);
		menubar.add(programMenu);
		
		mainPanel.add(menubar);
		
		// Gets all the doctors from the database and puts them in the ArrayList doctors
		db.getAllDoctors(doctors, conn.getMyConn());
		
		// Insert the doctors to the JTable
		
		
		//�������� ������� ��� ��������� ��� ������ �����
		ImageIcon icon = new ImageIcon("hospital1.png");
		JLabel lb = new JLabel(icon);
		mainPanel.add(lb);
		
		lb.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
		    {	 
		    	setVisible(false);
		        new GlobalHomeFrame(conn);           
		    }
		});
		
		
		ButtonListener listener = new ButtonListener();
		item1.addActionListener(listener);
		item2.addActionListener(listener);
		
		centralPanel.add(mainPanel, BorderLayout.NORTH);
		centralPanel.add(secondPanel, BorderLayout.CENTER);
		
		this.setContentPane(centralPanel);
		this.setVisible(true);
		this.setSize(600, 600);
		this.setTitle("����������/���������");
		
	}
	
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		
			secondPanel.removeAll();
			
			//������: �����������->��������
			if(e.getSource()== item1 ) {
				
				hrLabel = new JLabel ("�� ��������� ��������");
			
				// get all doctors from the database
				
				
				String columnNames[] = {"�������������" , "��" };
			    String rowData[][] = { {"����� ������������","99"},
			    					   {"������� ������������","888"}	};
			    
			    table = new JTable(rowData,columnNames);
			    // Kathorismos topothetisis tou pinaka
		
			    table.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			    // Dimiourgia scrollPane gia ton pinaka 
			   
			    scrollPane = new JScrollPane(table);
				scrollPane.setBounds(36, 37, 407, 79);
					
				
				FindField= new JTextField("��������� �����������"); 
				
				button1 =new JButton("��������"); 
				button2 =new JButton("��������");
			
				secondPanel.add(hrLabel);
				secondPanel.add(scrollPane);
				secondPanel.add(FindField);
				secondPanel.add(button1);
				secondPanel.add(button2);
				pack(); 
				
				
				//�������� ���� ����������� ���� ���� ���
				button1.addActionListener(new ActionListener()
			    {	
				      public void actionPerformed(ActionEvent e)
				      {
				    	  secondPanel.removeAll();
				    	  JLabel label = new JLabel("���������� �����������");
				    	  JButton button = new JButton("�������� �����������");
				    	  JTextField firstNameField = new JTextField("�����");
				    	  JTextField lastNameField = new JTextField("�������");
				    	  //�������� ��� �������� 
				    	  JTextField amField = new JTextField("��");
				    	  String AM, firstName, lastName;
				    
				    	  
				    	  secondPanel.add(label, BorderLayout.NORTH);
				    	  secondPanel.add(firstNameField, BorderLayout.CENTER);
				    	  secondPanel.add(lastNameField, BorderLayout.CENTER);
				    	  secondPanel.add(amField, BorderLayout.CENTER);
				   
				    	  secondPanel.add(button, BorderLayout.CENTER);
				    	  pack();
				    	  
				    	  //button ->������ ��� ���������
				    	  button.addActionListener(new ActionListener()
						    {	
							      public void actionPerformed(ActionEvent e)
							      { 
							    	 
							    	  String AM, firstName, lastName;
							    	  int NumberOfDocs;
							    	  
							    	  firstName = firstNameField.getText();
							    	  lastName = lastNameField.getText();
							    	  AM = amField.getText();
							    	  
							    	  // ----------SEARCH IF THE DOCTOR ALREADY EXISTS------------
							    	  //check if the typed AM already exists
							    	  NumberOfDocs =  db.getNumberOfEntries("doctor", "RN", AM, conn.getMyConn());
							    	  
				    	  			  if (NumberOfDocs == -1)
				    	  			  {
				    	  				  // show error message and exit program??
				    	  			  }
				    	  			  else if (NumberOfDocs == 1 )
				    	  			  {
				    	  				  // show that the doctor with the given AM already exists
				    	  			  }
				    	  			  else
				    	  			  {
								    	  //-----------INSERT DOCTOR-----------
					    	  			  Doctor d = new Doctor(firstName, lastName, AM);
					    	  			  
					    	  			  // add the doctor
					    	  			  db.addDoctor(d, conn.getMyConn()); 
				    	  			  }	
							      }
						    });
				    	  
				    	  //button2 -> ������ ��� ���������
				    	  button2.addActionListener(new ActionListener()
						    {	
						      public void actionPerformed(ActionEvent e)
						      {		
						    	  
						    	  
						    	  
						    	  //�������� ����������� ��� ����
						    	  
						  
						      }
					    });
				    	  
				      }
			    });
				
			}
			//������: ���������-> ����������
			else if(e.getSource() == item2) {
				JLabel label= new JLabel();
				JTextField text1 = new JTextField("������ �������");
				JButton button1 = new JButton("�������� ������������");
				JButton button2 = new JButton("�����");
				
				//text1.setText(); ������ �������, �� ������� ��� ��� ����
		
				
				label.setText("���������� - ������� ��������� ������������");
				secondPanel.add(label, BorderLayout.NORTH);
				secondPanel.add(text1, BorderLayout.CENTER);
				secondPanel.add(button1, BorderLayout.SOUTH);
				secondPanel.add(button2, BorderLayout.SOUTH);
				pack();
				
				button1.addActionListener(new ActionListener()
			    {	
			      public void actionPerformed(ActionEvent e)
			      {
			    	  secondPanel.removeAll();
			    	  JLabel label= new JLabel();
			    	 // JTable program; 
			    	  JButton save = new JButton("����������");
			    	  JButton amendment = new JButton("�����������");
			    	  
			    	  label.setText("����������� ��������� ���������");
			    	 
			    	  Object[] columnNames = {"������" , "�������" , "�����", "�������", "������", "���������", "������", "�������"};
			  	      Object[][] rowData = { {"06:00-14:00", "-" , "-", "-", "-", "-", "-", "-" },
			  	    					   {"14:00-22:00", "-", "-", "-", "-", "-", "-", "-"},
			  	    					   {"22:00-06:00", "-", "-", "-", "-", "-", "-", "-"} };
			    	 
			    	 
			  	       program = new JTable(rowData,columnNames);
			  	       
			  	       // Kathorismos topothetisis tou pinaka
		
			  	       program.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			  	       // Dimiourgia scrollPane gia ton pinaka 
			   
			  	       scrollPane2 = new JScrollPane(program);
			  	       scrollPane2.setBounds(20, 20, 10, 30);
			  	      
			  	       
			    	   secondPanel.add(label);
			    	   secondPanel.add(scrollPane2);
			    	   secondPanel.add(save);	
			    	   secondPanel.add(amendment);
			    	   pack();
			    	   
			    	   save.addActionListener(new ActionListener()
					    {	
					      public void actionPerformed(ActionEvent e)
					      {
					    	 // APOTHIKEUSH STIN VASI DEDOMENWN
					    	   
					      }
					    });
			    	  
			    	   amendment.addActionListener(new ActionListener()
					    {	
					      public void actionPerformed(ActionEvent e)
					      {
					    	 //Ston idi uparxonta pinaka. TON kanw na mporei na metavlithei
					    	   
					      }
					    });
			    	   
			    	  }
			    });
				
				button2.addActionListener(new ActionListener()
			    {	
			      public void actionPerformed(ActionEvent e)
			      {	  
			    	  
			    	  secondPanel.removeAll();
			    	  pack();
			      }
			    });

			}
	
		}	
	}
}