package me.machie.hivemind.modules;

import me.machie.hivemind.util.SafeLoggingThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class ServerThread extends SafeLoggingThread {
    public ServerSocket socket;
    public final List<Connection> connections = new ArrayList<>(8);

    public ServerThread(int port) {
        try {
            socket = new ServerSocket(port);
            socket.setSoTimeout(50);
        } catch (IOException e) {
            socket = null;
            error("Could not start server on port %s.", port);
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        info("Server started on port %s.", socket.getLocalPort());

        while (!isInterrupted()) {
            try {
                Connection connection = new Connection(socket.accept());
                connection.start();

                connections.add(connection);
            } catch (SocketTimeoutException ignored) {
            } catch (IOException e) {
                error("Error accepting connection.");
                e.printStackTrace();
            }

            connections.removeIf(Connection::shouldRemove);
        }

        connections.forEach(Thread::interrupt);
        connections.clear();

        try {
            socket.close();
        } catch (IOException e) {
            error("Error closing socket.");
            e.printStackTrace();
        }

        info("Server stopped.");
    }
}
