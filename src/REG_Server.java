package IP_Project_new.src.ip_project_new;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.util.Random;


public class REG_Server implements Runnable {
private Socket server;
//public final static String FILE_TO_SEND = "rfc5000.txt";
	public REG_Server(Socket server) throws IOException {
		// TODO Auto-generated method stub
		this.server=server;
	  
	}
	public void run() {
		try{
		System.out.println("connection established!!!");
   	    BufferedReader in_from_client = new BufferedReader(new InputStreamReader(server.getInputStream()));
        PrintWriter out_to_client = new PrintWriter(server.getOutputStream(), true);
        
        String host_information=in_from_client.readLine();
        String hostname=host_information.substring(8,host_information.indexOf(' '));
        int port_number=Integer.parseInt(host_information.substring(host_information.indexOf(' ')+1));
        String Reg_IP=InetAddress.getLocalHost().getHostAddress();
            int TTL_peer = 7200;
            Boolean Active_peer = true;
            Date recent_date = new Date();
            Random rand = new Random();
            int cookie_peer = rand.nextInt((500 - 1) + 1) + 1;
            Peers peer_new=new Peers(hostname, port_number, cookie_peer, TTL_peer, Active_peer, recent_date);    
            System.out.println("Host Name: "+peer_new.PeerName+"\n Port:"+peer_new.Port_number+"\n Registered with:"+Reg_IP+"\n Cookie:"+peer_new.Cookie_Peer+"\n TTL:"+peer_new.TTL_value+"\n Active:"+peer_new.Active_PEER+"\n Date:"+peer_new.Date_peer.toString());
            PC_Server.Peer_Block_list.add(peer_new);
            PC_Server.peer_linked_list.insertFirst(hostname, port_number, cookie_peer, TTL_peer, Active_peer, recent_date);
            //displaying the details to client
            out_to_client.println(peer_new.PeerName+"--is registered in the list\r"); 
    
        while(true){
        String Client_Request=in_from_client.readLine();
        //System.out.println(Client_Request);
        String[] client_request_parced=Client_Request.split(" ");
        
        if(client_request_parced[0].equalsIgnoreCase("ADD")){
       	 
       	 RFC_Block rfc_block=new RFC_Block(Integer.parseInt(client_request_parced[1]), client_request_parced[2],hostname);
         PC_Server.rfc_block_list.add(rfc_block);
       	 out_to_client.println("200 OK");
        }
        
        else if(client_request_parced[0].contains("LOOKUP")){
       	 int flag=0;
       	 String Lookup_List="";
       	int port=0;
       	 for(RFC_Block current_block:PC_Server.rfc_block_list){
       		 if(current_block.RFC_number==Integer.parseInt(client_request_parced[1]) || current_block.RFC_Title.equalsIgnoreCase(client_request_parced[2])){
       			for(Peers cuBlock:PC_Server.Peer_Block_list){
       				if(cuBlock.PeerName.equalsIgnoreCase(current_block.HostName))
       				port=cuBlock.Port_number;
       			}
       			 Lookup_List=Lookup_List+"->"+current_block.HostName+"->"+Integer.toString(port);
       			 
       			 flag=1;
       		 }
       	 }
       	 if(flag!=1)
       		 out_to_client.println("404 Not Found");
       	 else
       		out_to_client.println(Lookup_List);
       		 
        }
        else if(Client_Request.equalsIgnoreCase("PQUERY")){
            System.out.println("Reg server: Sending peer list because client asked. Number of peers IS " + PC_Server.Peer_Block_list.size());
           
           String string1=PC_Server.peer_linked_list.getData();
            System.out.println(string1);
            String[] stringpart = string1.split("\r\n");
            for (int i=0;i<=PC_Server.peer_linked_list.getLength();i++){
                String[] stringparts1 = stringpart[i].split(" ");
			        	{
				         	out_to_client.println(stringparts1[0] + " " + stringparts1[1] + " " + stringparts1[2] + " " + stringparts1[5]);
			        		
			        	}
        		   	}
            }
        else if(Client_Request.contains("LISTALL")){
       	 String S="";
       	 int count=1;
       	 for(RFC_Block current_block:PC_Server.rfc_block_list){
       		S=S+" "+Integer.toString(count)+"."+current_block.HostName+"-->"+Integer.toString(current_block.RFC_number)+"-->"+current_block.RFC_Title; 
       		count++;
       	 }
       	 out_to_client.println(S);
        }
        else if(Client_Request.contains("LEAVE"))
        { 
        server.close();
        break;
        }
        else if(Client_Request.contains("DOWNLOAD")){
        
            File file = new File("RFC5000copy.txt");
            
      // creates the file
      file.createNewFile();
//      File path = new File("D://New folder");
      BufferedReader br = new BufferedReader(new FileReader("rfc5000.txt"));
      StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }
        String read_file = sb.toString();
      // creates a FileWriter Object
      FileWriter writer = new FileWriter(file); 
      // Writes the content to the file
      // DISPLAY THE FILE IN THE SAME FOLDER
      writer.write(read_file);
      writer.flush();
      writer.close();
      
        // TO DISPLAY THE FILE IN DIFFERENT FOLDERR
//      if(file.renameTo(new File("D://" + file.getName())))
//                {
//    		System.out.println("File is moved successful!");
//    	   }else
//                {
//    		System.out.println("File is failed to move!");
//                }

      out_to_client.println("Download complete");
        }
        }
        }
	catch (Exception e) {
		// TODO: handle exception
	}
	
	}
}
