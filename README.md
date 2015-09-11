# Peer-to-PeerDistributedIndexSystem
Source code

Implemented a client-server model to upload, download and manage RFCs from server. Designed a multithreaded server for 
concurrency that is capable of carrying out communication with multiple clients simultaneously.

Editor Used : NetBeans
JDK Version : java version "1.8.0_20"

Compilation Of the Projects.

1. Files in the projects are dependent on each other as we use the reference of one file into another.
2. For compilation use the "Makefile" file that will automatically make the classes of the java files. 
3. It is must for all the files to get compiled before running this project.

Compilation and Running If using terminal:

For Registration Server:

make     					            ---compiling file

java PC_Server           			---executing 

For Every Peer:

make          				       	---compiling file (Don't write, if already done at server terminal)

java P2P_Client      	        ---executing file

If using NetBeans/Eclipse Please directly run as Java Project.

Conditions while running:
I have specified the file name that you can wish to download as "rfc5000.txt" and attached with the source code in the zip file. You may change the text file by changing the path as mentioned in the code of "REG_Server.java". 
Each peer will download the file to a specific folder. I have mentioned a specific destination path of the folder which can be changed according to the user. It can be done on "REG_Server.java"


Running the Project :

PreRequisite  : No build errors. HOSTNAME WILL BE THE IP ADDRESS OF THE MACHINE. For the inputs, you can either enter IP address or host name of the machine. 

1. First Run PC_Server.java, it will start the registration server.
2. Run P2P_Client.java at each PC. This will start all the Client and Server at the Peer Process.
3. Once you start this, at the server side, "The server is running." pops up.
4. At the client side, you need to enter the IP address of Registration Server.
5. Then enter the upload port number of the client from where you wish you run the queries.
6. After pressing enter, the peer will automatically get registered.
7. Server will show the details of the client in the format as follows:
Host Name: <IP address of host>
Port: <upload port number>
Registered with: <IP address of Registration server>
Cookie: <Random number>
TTL: <7200>
Active: <true>
Date: <Date of registration>
8. Now, the client side will show "Enter Your Request". In order to enter the request, you need to follow the format below:-

>>To ADD an RFC-

Enter: "ADD <rfc_no.> <rfc_name.txt>"

The RFC will be added to that client.

>>To SEARCH an RFC-

Enter: "LOOKUP <rfc_no.> <rfc_name.txt>"

It will show the client details which has the RFC you are looking for. If the RFC will not be found, "404 Not Found" will be 

displayed.

>>To SEARCH all the RFCs- 

Enter: "LISTALL"

It will show all the clients with their respective RFCs. 

>>To SEARCH active peers-

Enter: "PQUERY"

It will show first active peer with the cookie value, port number, and date of registration.  

Again Enter: "PQUERY"

It will show next active peer with the details and so on.

The server side will display the total number of peers registered.

>>To DOWNLOAD an RFC-

First you can change the path of the file to be receieved and to be sent.

Enter: "DOWNLOAD"

It will automatically the specific RFC and will display " Download complete" on the client side.
 
>>To LEAVE-

Enter: "LEAVE"

The server will automatically close the socket.


