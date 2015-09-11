package IP_Project_new.src.ip_project_new;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class PC_Server{
public static ArrayList<Peers> Peer_Block_list=new ArrayList<Peers>();
public static indexlist peer_linked_list = new indexlist();
public static ArrayList<RFC_Block> rfc_block_list=new ArrayList<RFC_Block>();
	public static void main(String[] args) throws Exception {
        System.out.println("The server is running.");
        ServerSocket listener = new ServerSocket(65423);
        try {
            while (true) {
            	Socket server = listener.accept();
            	Runnable server_instance=new REG_Server(server);
            	Thread server_thread=new Thread(server_instance);
            	server_thread.start();
            }
        } finally {
            listener.close();
        }
    }
}