import java.io.*;
import java.net.*;

public class Server {
	

	public static void main(String[] args)throws UnknownHostException,IOException {
		try {
			ServerSocket ss=new ServerSocket(6667);//creates server side
			while(true) {
			Socket s=ss.accept();//create/accept connection with client
			System.out.println("Connected");
			
			// Accept client request
			DataInputStream inputStream = new DataInputStream(s.getInputStream());
			String request = inputStream.readUTF(); // receive request from client
			System.out.println("Received request: " + request);
			
			// Process request and respond
			String response = processRequest(request);
			DataOutputStream outputStream = new DataOutputStream(s.getOutputStream());
			outputStream.writeUTF(response); 

			// Send file if requested
			if (request.equals("GET_FILE")) {
				sendFile(s);
			}
			// Close streams and socket
			inputStream.close();
			outputStream.close();
			s.close();
			System.out.println("Response sent successfully!");
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
}
private static String processRequest(String request) {
	// Process the request and return a response
	if (request.equals("GET_FILE")) {
		return "File request accepted";
	} else {
		return "Invalid request";
	}
}

private static void sendFile(Socket s) throws IOException {
	File filesend = new File("C:\\Users\\lappify\\OneDrive\\Desktop\\Network Desktop Manager\\Hello.txt"); // file to be sent
	String fileName = filesend.getName(); // get file name
	long fileSize = filesend.length(); // get file length

	// sends file information to client
	DataOutputStream outputStream = new DataOutputStream(s.getOutputStream()); // Data output stream to send data
	outputStream.writeUTF(fileName); // send file name
	outputStream.writeLong(fileSize); // send file size

	// sends file to client
	FileInputStream fileInputStream = new FileInputStream(filesend); // reads file content
	BufferedInputStream bin = new BufferedInputStream(fileInputStream); // wrap file content to buffer reader
	byte[] buffer = new byte[4096]; // creates buffer
	int bytesRead;
	while ((bytesRead = bin.read(buffer)) != -1) { // read file in chunks
		outputStream.write(buffer, 0, bytesRead); // send file in chunks to data
	}

	bin.close(); // close buffered reader
	outputStream.close(); // close output stream
	System.out.println("File sent successfully!");
}
}
			