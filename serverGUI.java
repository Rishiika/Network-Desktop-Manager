import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
//import org.jdesktop.swingx.JXDesktopSharingServer;


public class serverGUI extends JFrame implements ActionListener{
	

	private JTextField folderName;
	private JTextArea messageText;
	private Server server;
	public serverGUI(Server my_server) { //creates constroctor and call parent class
		super("Server");
		this.server=my_server;
		JButton button1=new JButton("Create Folder");    //creates button using JButton
		button1.addActionListener(new ActionListener() {  //add ActionListener to button
			@Override
			public void actionPerformed(ActionEvent e) {
				createFolder();  //perform createfolder
			}
		});
		
		JButton button2=new JButton("Rename Folder");      //create button
		button2.addActionListener(new ActionListener() {   //add actionListener to buttonm
			public void actionPerformed(ActionEvent e) {
				renameFolder();   //perforn rename folder
			}
		});
		
		JPanel panel=new JPanel();//creates Panel
		JPanel buttonpanel=new JPanel();//creates button panel
		
		panel.setBorder(BorderFactory.createEmptyBorder(50,50,10,50));  //add border 
		panel.setLayout(new GridLayout(0,1));//add alyout
		panel.add(new JLabel("FolderName: "));//add Folder name to panel
		folderName=new JTextField(20);//create textfield area for folder name
		panel.add(folderName);// add textfield for folder name to panel
		
		buttonpanel.add(button1);//add buttons to button panel
		buttonpanel.add(button2);
		
		
		
		messageText = new JTextArea(3, 10);// creates Textarea for meaasges to be shown 
        messageText.setEditable(false);
        add(new JScrollPane(messageText), BorderLayout.SOUTH);//add scroll element
		
		add(panel,BorderLayout.NORTH);//add panel to frame
		add(buttonpanel,BorderLayout.CENTER);//add buttonpanel to frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close frame on exit
		setTitle("Operation on Folder");//frame title
		pack();
		setVisible(true);
		
		
	}
	
	
	public void createFolder() {
		String fName=folderName.getText();//takes folder name
		File folder=new File(fName);//assign folder name 
		try {
			if(folder.mkdir()) {//creates colder in directory
				messageText.setText("Folder was created successfully");
				
			}
			else {
				messageText.setText("Failed");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void renameFolder() {
		JFileChooser chooser = new JFileChooser(); //creates chooser object to choose file to rename
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//all us to selec file to be renamed 
        int result = chooser.showOpenDialog(this);//open dialog box
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = chooser.getSelectedFile();  //selects folder
            String newFolderName = folderName.getText();//take ne folder name
            File newFolder = new File(selectedFolder.getParentFile(), newFolderName);
            try {
                if (selectedFolder.renameTo(newFolder)) {//remane selected folder to new name
                    messageText.setText("Folder renamed successfully.");
                } else {
                    messageText.setText("Failed to rename folder.");
                }
            } catch (Exception ex) {
                messageText.setText("Error: " + ex.getMessage());
            }
        }
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			Server server=new Server();//creates server object
			serverGUI a=new serverGUI(server);//creates class object
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
