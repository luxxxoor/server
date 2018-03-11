import controllers.LoginController;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repositories.UtilizatorRepository.UtilizatorRepository;
import repositories.UtilizatorRepository.UtilizatorRepositoryImpl;
import services.LoginService.LoginService;
import services.LoginService.LoginServiceImpl;
import sun.rmi.runtime.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerStartUpApp {
    // Wait for client to connect on 4432
    private static int PORT = 4432;
    private static StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    private static Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
    private static SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
    private static UtilizatorRepository utilizatorRepository = new UtilizatorRepositoryImpl(sessionFactory);
    private static LoginService loginService = new LoginServiceImpl(utilizatorRepository);
    private static LoginController loginController = new LoginController(loginService);

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
        @SuppressWarnings("unchecked")
        public void run() {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                Map<String, String> map = (Map<String, String>) objectInputStream.readObject();
                handleClient(map);
                socket.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private void handleClient(Map<String, String> map) {
            String controller = map.get("api").split("/")[0];
            String api = map.get("api").split("/")[1];
            switch (controller) {
                case "logincontroller":
                    switch (api) {
                        case "creazauserpentrudonator":
                            loginController.creeazaUserPentruDonator(map);
                            break;
                    }
                    break;
            }
        }
    }
}
