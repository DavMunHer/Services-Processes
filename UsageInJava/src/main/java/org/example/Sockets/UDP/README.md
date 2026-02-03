# UDP Sockets
When talking about the UDP connectivity, both the server and the client behave almost exactly: They both use the same class for stablishing the communication.

But this time, the only thing that changes are the arguments received in the constructor.

Instead of using the Socket class, as we did in TCP sockets, here we use the `DatagramSocket` class.

## Sockets definition
For defining the DatagramSocket in the server side, we have to specify the port where we will be hearing for the connection:

```java
DatagramSocket dSocket = null;

/* Inside the try catch block*/
dSocket = new DatagramSocket(socket_no);
```

For defining the client, we can just instantiate the DatagramSocket class without passing any arguments:
```java
DatagramSocket dSocket = null;

/* Inside the try catch block*/
dSocket = new DatagramSocket();
```

## Sending messages
This is the most important thing to understand to stablish a UDP communication. Starting from the server perspective, it first will have to wait for a client message (codified in bytes):
```java
receivedMessage = new byte[1000];
DatagramPacket receivedDp = new DatagramPacket(receivedMessage, receivedMessage.length);
dSocket.receive(receivedDp);

System.out.println("Client said: " + new String(receivedDp.getData()).trim()); // Ensure to decode the info before printing

// Sending messages back
DatagramPacket answerDp = new DatagramPacket(dpRebut.getData(), dpRebut.getLength(),
						dpRebut.getAddress(), dpRebut.getPort());
dSocket.send(answerDp);
```

As you can see, for receiving messages all we have to do is instantiate the DatagramPacket class with the max bytes array we would expect and then specify the actual length. 

This will then allow us to receive the datagramPacket filled with information that the client sends by just using the `receive` method, specifying where to store the data.

---
Now, from the client perspective, since we haven't yet specified where to send the messages, we will have to specify this in the DatagramPacket instances:
```java
String host = "localhost"; // Or an IP
byte[] messageToSend = kb.nextLine().getBytes();
InetAddress aHost = InetAddress.getByName(host); 
int serverPort = 3000;
DatagramPacket dpEnviament = new DatagramPacket(messageToSend, messageToSend.length(), aHost, serverPort);
dSocket.send(dpEnviament); // Send the datagram

// Same logic for receiving as the one we've already seen from the server side
DatagramPacket receivedDp = new DatagramPacket(receivedMessage, receivedMessage.length);
dSocket.receive(receivedDp);
```

