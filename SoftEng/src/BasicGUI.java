import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BasicGUI extends JFrame {
	
	private JButton button1 = new JButton("������������");
	private JButton button2 = new JButton("����������  ");
	private JButton button3 = new JButton("������      ");
	private JPanel basicPanel = new JPanel(new BorderLayout());
	private JPanel panel = new JPanel();

	
	private JLabel label;
	private JLabel user = new JLabel("����� ������������ ��:");

	public BasicGUI() {
	
		label = new JLabel("��������� ����������� ���������� ���������� & ������ ����� �� ������������� �������");
		panel.add(label);
		panel.add(user);
		basicPanel.add(button1, BorderLayout.WEST);
		basicPanel.add(button2, BorderLayout.CENTER);
		basicPanel.add(button3, BorderLayout.EAST);
		
		ButtonListener listener = new ButtonListener();
		button1.addActionListener(listener);
		button2.addActionListener(listener);
		button3.addActionListener(listener);
		
		panel.add(basicPanel);
		this.setContentPane(panel);
		this.setVisible(true);
		this.setSize(600, 200);
		this.setTitle("����� ������!");
					 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			setVisible(false);

			if(e.getSource() == button1) { //������������
		
				//����� GUI ��� ��� �����������
			}
			
			else if(e.getSource() == button2) { // ���������
			
				new GuiDieuthinti1();
			}
			
			else if(e.getSource() == button3) { //�������
				new GuiGiatros1();
			}
			
		}
	}
	
}
