package IP_Project_new.src.ip_project_new;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class P2P_Client {
static String hostname;
static int port_number;
static int Reg_prt = 65423;

  public static void main(String args[]) throws IOException{
	  Scanner in = new Scanner(System.in);
	  System.out.println("Enter Registration Server Address:");
	  String Server_Address=in.nextLine();
	  Socket client=new Socket(Server_Address,Reg_prt);
	  BufferedReader In_from_server = new BufferedReader(new InputStreamReader(client.getInputStream()));
      PrintWriter out_to_server = new PrintWriter(client.getOutputStream(), true);
      System.out.println("Enter Hostname");
      hostname=in.nextLine();
      System.out.println("Enter Upload  Port:");
	  port_number=Integer.parseInt(in.nextLine());
          System.out.println(hostname+"creating new client socket to connect to Registration server");
          
          //RG-Server queries
        System.out.println("REGISTER");
        out_to_server.println("REGISTER"+hostname+" "+port_number);
	  //run client upload server
	  ServerSocket client_listener=new ServerSocket(port_number);
	  Runnable client2clientInstance=new PC_clients(client_listener);
	  Thread client2clientThread=new Thread(client2clientInstance);
	  client2clientThread.start();
	  
	  //sending request to server
         out_to_server.println(hostname+" "+port_number);
	  System.out.println(In_from_server.readLine());
	  System.out.println("Enter Your Request");
	  String Request=in.nextLine();
	  while(!Request.equalsIgnoreCase("LEAVE")){
		  	if(!Request.contains("GET")){
		  				out_to_server.println(Request);
		  				System.out.println(In_from_server.readLine());
                        }
//                                                
//		  				
		  else {
                            String[] request_to_client=Request.split(" ");
			  int rfc_requested_number=Integer.parseInt(request_to_client[1]);
			  int port_client=Integer.parseInt(request_to_client[2]);
			  Socket client_request=new Socket(hostname,port_client);
			  BufferedReader in_from_clientRequest=new BufferedReader(new InputStreamReader(client_request.getInputStream()));
			  PrintWriter out_to_clientRequest = new PrintWriter(client_request.getOutputStream(), true);
			  out_to_clientRequest.println(Request);
			
			    byte[] mybytearray = new byte[4096];
			    InputStream is = client_request.getInputStream();
			    FileOutputStream fos = new FileOutputStream("rfc.txt");
			    BufferedOutputStream bos = new BufferedOutputStream(fos);
			    int bytesRead = is.read(mybytearray, 0, mybytearray.length);
			    bos.write(mybytearray, 0, bytesRead);
			    bos.close();
			  
		  }
	  System.out.println("Enter Your Request");
	  Request=in.nextLine();
	  }
          while(!Request.equalsIgnoreCase("LEAVE")){
          if (Request.equalsIgnoreCase("DOWNLOAD")){
              System.out.println("File downloaded");
              System.out.println(In_from_server.readLine());
              
          }
          else if (Request.equalsIgnoreCase("PQUERY")){
              
              System.out.println(In_from_server.readLine());
          //    System.out.println(In_from_server.readLine());
              
          }
          else if (Request.equalsIgnoreCase("UPDATE TTL")){
              
          }
	  client.close();
  }
}
}
