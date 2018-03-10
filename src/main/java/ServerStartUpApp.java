import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ServerStartUpApp {
    // Wait for client to connect on 4432
    private static int PORT = 4432;

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.submit(new MyClass(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class MyClass implements Runnable {
        Socket socket;

        public MyClass(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                int x = dataInputStream.readInt();
                TimeUnit.SECONDS.sleep(5);
                dataOutputStream.writeInt(2 * x);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
