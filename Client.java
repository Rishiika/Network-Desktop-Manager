import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args)throws UnknownHostException,IOException{
		try {
			Socket s=new Socket("10.70.1.5",6667);//creates socket object ie. Client to connect to server
			

            // Send request to server
            DataOutputStream outputStream = new DataOutputStream(s.getOutputStream());
            String request = "GET_FILE"; // example request
            outputStream.writeUTF(request);

            // send request to server
			DataInputStream inputStream = new DataInputStream(s.getInputStream());//create input stream to receive data from server
			 String fileName = inputStream.readUTF();//receive file name
	         long fileSize = inputStream.readLong();//receive file size
	         
	         //receive file from server
			 FileOutputStream fileOutputStream = new FileOutputStream(fileName);//writes received content to file
	         BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);//wraps in buffer
            
             

            
	        // Receive file contents in chunks
	        byte[] buffer = new byte[4096];//creates buffer
	        int bytesRead;
	        while ((bytesRead = inputStream.read(buffer)) != -1) {//read file chunks
	            bufferedOutputStream.write(buffer, 0, bytesRead);//writes file content to client
	          
	            }

	            // Close streams and socket
	            System.out.println(fileName+" File received successfully!");
	            bufferedOutputStream.close();
	            inputStream.close();
	            s.close();
	            
	            
	            
	       
	            
					
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}