# TCP Sockets

## Client side

For defining a client socket we will make use of the Socket class, which will receive two arguments:

- The server name
- The server port

After defining the client socket itself, we will create both the input and the output streams. They will not be the thing that we'll use for sending and receiving messages, but they will be useful for initializing the `DataInputStream` and `DataOutputStream`, which will actually handle the messages (receive and send respectively).

Example of declaration of the variables:

```java
Socket socket = new Socket(serverName, SERVER_PORT); // serverName could be localhost or IP address
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

The server socket will only be waiting for clients to connect to it. Therefore, the `ServerSocket` constructor will only receive a single parameter -> the server port.

Now, the work flow from the server side follows this pattern:

- Server waits for clients to connect to it
- The client socket is initialized as the "accept" signal from the `ServerSocket` instance
- Once we have the client socket instance from the server perspective, we can create the `DataInputStream` and `DataOutputStream` instances for the communication with the other client from the other side.

Example of declaration of the variables:

```java
ServerSocket ss = new ServerSocket(SERVER_PORT); // This server keeps waiting for a client connection

Socket clientSocket = ss.accept(); // When a client connects, we can use the Socket instance to send info to the client Socket
OutputStream os = clientSocket.getOutputStream();
InputStream is = clientSocket.getInputStream();
DataInputStream inputFlow = new DataInputStream(is);
DataOutputStream outputFlow = new DataOutputStream(os);
```

At this point, the flow for sending and receiving messages is the same as the one we've already seen from the client perspective:

```java
outputFlow.writeUTF("Serving to client..."); // Sending the message first (The client waits for a message before sending anything)

while (true) {
    msg = inputFlow.readUTF();
    if (msg.equals("Bye")) {
        outputFlow.writeUTF("See ya!");
        break;
    }
    switch (msg) {
        case "Hallo!":
            outputFlow.writeUTF("Hallo sir! This is the server, how can I help ya ;P");
            break;
        case "¿How u doing?":
            outputFlow.writeUTF("Pretty good! Thanks for asking my dear client");
            break;
        default:
            System.out.println("Client: " + msg);
            outputFlow.writeUTF("Sorry man, I do not recognize ur message :(");
    }
}
```
