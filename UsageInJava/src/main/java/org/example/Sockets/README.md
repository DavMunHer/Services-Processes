# Sockets
This file tells you how to handle advanced connections from the server perspective, creating a new Thread each time a new client connects to the server.

Before checking this out, I highly recommend the `TCP` and `UDP` folders, where you will be able to find basic examples and a `README.md` file where everything is explained.


# Advanced usage
For managing appropiately the sockets connections from the server perspective, you should create a new Thread for each one of the clients you manage.

For doing this, you can create a class that will implement the Runnable interface, where you will locate all the logic necessary to run in each one of the Threads.

Example of implementation:
```java
class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final List<String> questions; // You can adjust the constructor variables according to the system you are implementing
    private final List<String> answers;

    public ClientHandler(Socket clientSocket,
                         List<String> questions,
                         List<String> answers) {
        this.clientSocket = clientSocket;
        this.questions = questions;
        this.answers = answers;
        System.out.println("Created a new server thread");
    }


    @Override
    public void run() {
        try (
                DataInputStream inputFlow = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream outputFlow = new DataOutputStream(clientSocket.getOutputStream())
        ) {

            outputFlow.writeUTF("Welcome to Quiz Magic");
            String playerName = inputFlow.readUTF();
            System.out.println("Starting server thread");
            System.out.println("In thread: communicating with " + clientSocket);
            System.out.println("In thread: reading name:" + playerName);

            int score = 0;

            for (int i = 0; i < questions.size(); i++) {
                outputFlow.writeUTF(questions.get(i));
                String playerAnswer = inputFlow.readUTF();

                if (playerAnswer.equalsIgnoreCase(answers.get(i))) {
                    score++;
                }
            }

            outputFlow.writeUTF("Well done " + playerName + ". Score: " + score);
            System.out.println("End with socket "+ clientSocket);

        } catch (IOException e) {
            System.out.println("Client disconnected");
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ignored) {
            }
        }
    }
}
```

With this class defined, the server could then create and start the threads each time it receives a new client connection:

```java
public class QuizMagicServer {
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String nextLine = null;
        List<String> questions = new ArrayList<>();
        List<String> answers = new ArrayList<>();

        try {
            fileReader = new FileReader("MyFile.txt");
            bufferedReader = new BufferedReader(fileReader);

            int lineCount = 0;
            while ((nextLine = bufferedReader.readLine()) != null) {
                if (lineCount % 2 == 1) {
                    answers.add(nextLine);
                } else {
                    questions.add(nextLine);
                }
                lineCount++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Inici servidor en " + SERVER_PORT);

            while (true) {
                // THIS IS THE IMPORTANT THING TO NOTE!!
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, questions, answers);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

