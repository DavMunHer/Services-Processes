# TCP Sockets

## Client side
For defining a client socket we will make use of the Socket class, which will receive two arguments:
- The server name
- The server port

After defining the client socket itself, we will create both the input and the output streams. They will not be the thing that we'll use for sending and receiving messages, but they will be useful for initializing the `DataInputStream` and `DataOutputStream`, which will actually handle the messages (receive and send respectively).

Example of declaration of the variables:

```java
Socket socket = new Socket(serverName, SERVER_PORT);
InputStream is = socket.getInputStream();
DataInputStream inputFlow = new DataInputStream(is);
OutputStream os = socket.getOutputStream();
DataOutputStream outputFlow = new DataOutputStream(os);
```

Example on how to receive and send a message:
```java
// Receiving message (The server would start writing before reading):
System.out.println("Server: " + inputFlow.readUTF());

// Sending a message:
outputFlow.writeUTF(msg);
```

This would be the simplest way to stablish a communication between a server and a client from the client perspective, but this flow would only read and write a single message. If we're actually interested in a persistent communication, we would put all the messages sending and receival in a loop.

## Server side
