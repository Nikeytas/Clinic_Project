import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GuiDieuthinti1 extends JFrame {
	
	private JTextField code= new JTextField("������� ���������");
	
	private JButton button =new JButton("�������");

	private JLabel label= new JLabel();
	private JPanel CentralPanel;

	
	public GuiDieuthinti1() {
		CentralPanel=new JPanel();
		
		label.setText("������ ������ ���������");
		
		CentralPanel.add(label);
		CentralPanel.add(code);
		CentralPanel.add(button);
		
		ButtonListener listener = new ButtonListener();
		button.addActionListener(listener);
		

		ImageIcon icon = new ImageIcon("hospital1.png");
		JLabel lb = new JLabel(icon);
		CentralPanel.add(lb);
		
		lb.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
		    {	 
		    	setVisible(false);
		        new BasicGUI();           
		    }
		});

		this.setContentPane(CentralPanel);
		this.setVisible(true);
		this.setSize(500, 250);
		this.setTitle("����������/�������");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == button) {
				String password= code.getText();
				//������� ��� ���������� �������(���� ������ ���������)
		
				
				//�� ������� �������:
				/*if(password == kwdiko stin vasi){
				  setVisible(false);
				  new GuiDieuthinti2();
				 }
				 else{
				 	JOptionPane.showMessageDialog(CentralPanel, "� ������� ��� ����� �������");
				 }*/

				//(��� ������ ����� �� ���� � ����)
				setVisible(false);
				new GuiDieuthinti2();
				
			}
			
		}
	}
	
	
	
}
