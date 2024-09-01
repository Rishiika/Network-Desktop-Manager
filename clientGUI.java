import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class clientGUI extends JFrame implements ActionListener {   //extends jframe and ActionListener to use its component
	private Client client;

	public clientGUI(Client Client) {    //create constructor
		super("Client");                 //extend Client "parent class" through super
		this.client=Client;
		
		JPanel panel=new JPanel();  //create Panel
		
		panel.setBorder(BorderFactory.createEmptyBorder(50,50,10,50)); //set border to panel
		panel.setLayout(new GridLayout(0,1));                          //set layout to panel
		
		JTextField reqHelp=new JTextField();                          //TextField to add text
		reqHelp.setBounds(100,100,100,100);                           //set bounds border etc. 
		reqHelp.setEditable(false);
        
		
		JButton button=new JButton("Request for Help");                //create button
		button.addActionListener(new ActionListener() {                //create and add ActionListener to  button
			@Override
			public void actionPerformed(ActionEvent e) {               //Action to be performed through actionListener
				reqHelp.setText("Server:A computer program or dedicated hardware that provides resources and services to other computers (clients) over a network.                                 Client:A computer program or device that requests and utilizes resources and services provided by a server over a network.");
				//adding text
			}
		});
		panel.add(button);  //add button to panel
		
		panel.add(new JScrollPane(reqHelp), BorderLayout.SOUTH);   //add scroll feature to panel
		
		add(panel,BorderLayout.CENTER);                            //add panel to frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("HELP!!");                                         //set Title
		pack();
		setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Client client=new Client();                 //create Client object
			clientGUI a=new clientGUI(client);          //creates 
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}