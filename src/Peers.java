package IP_Project_new.src.ip_project_new;


import java.util.Date;
import java.util.Random;


public class Peers {
public Peers(String hostName, int PortNumber, int Cookie, int TTL_Peer, Boolean Active, Date date_reg) {
		super();
		PeerName = hostName;
		Port_number = PortNumber;
                Cookie_Peer = Cookie;
                TTL_value = TTL_Peer;
                Active_PEER = Active;
                Date_peer = date_reg;
	}
String PeerName;
int Port_number;
int Cookie_Peer;
int TTL_value;
Boolean Active_PEER;
Date Date_peer;
String data;
String value;
public Peers next;
public String getPeerName() {
	return PeerName;
}
public void setHostName(String hostName) {
	PeerName = hostName;
}
public int getPort_number() {
	return Port_number;
}
public void setUpload_port_number(int PortNumber) {
	Port_number = PortNumber;
}
public int getCookie_Peer() {
        return Cookie_Peer;
}
public void setCookie_Peer(int Cookie) {
	Cookie_Peer = Cookie;
}
public int getTTL_value() {
        return TTL_value;
}
public void setTTL_value(int TTL_Peer) {
	TTL_value = TTL_Peer;
}
public Boolean getActive_PEER() {
        return Active_PEER;
}
public void setActive_PEER(Boolean Active) {
	Active_PEER = Active;
}
public Date getDate_peer() {
        return Date_peer;
}
public void setDate_peer(Date date_reg) {
	Date_peer = date_reg;
}
public String getData(){
		data = (PeerName + " " + Port_number + " " + Cookie_Peer + " " + TTL_value + " " + Active_PEER + " " + Date_peer);
		return data;
        }
}
class indexlist
{
	public Peers firstRow;
	
	indexlist()
	{
		firstRow = null;
	}
        public boolean isEmpty()
	{
		return (firstRow == null);
	}
        public void insertFirst(String hostName, int PortNumber, int Cookie, int TTL_Peer, Boolean Active, Date date_reg)
	{
		Peers newLink = new Peers(hostName,PortNumber,Cookie,TTL_Peer,Active,date_reg);	
		newLink.next = firstRow;
		firstRow = newLink;
	}
        public String getData()
	{
		String data;
		data="";
		Peers currRow = firstRow;
		while (currRow != null)
		{
			data+=currRow.getData()+"\r\n";
			currRow = currRow.next;
			//return data;
		}
		return data;
	}
        public int getLength()
	{
		int len;
		len=0;
		Peers currRow = firstRow;
		while (currRow != null)
		{
			len++;
			currRow=currRow.next;
		}
		return len;
	}
}

        