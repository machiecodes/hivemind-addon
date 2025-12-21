package me.machie.collective.systems.collective;

import meteordevelopment.meteorclient.utils.player.ChatUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class ManagerConnection extends Thread {
    public ServerSocket socket;
    public final List<WorkerConnection> connections = new ArrayList<>(8);

    public ManagerConnection(int port) {
        try {
            socket = new ServerSocket(port);
            socket.setSoTimeout(50);
        } catch (IOException e) {
            socket = null;
            ChatUtils.errorPrefix("Collective", "Could not start server on port %s.", port);
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        ChatUtils.infoPrefix("Collective", "Server started on port %s.", socket.getLocalPort());

        while (!isInterrupted()) {
            try {
                WorkerConnection connection = new WorkerConnection(socket.accept());
                connection.start();

                connections.add(connection);
            } catch (SocketTimeoutException ignored) {
            } catch (IOException e) {
                ChatUtils.errorPrefix("Collective", "Error accepting connection.");
                e.printStackTrace();
            }

            connections.removeIf(WorkerConnection::shouldRemove);
        }

        connections.forEach(Thread::interrupt);
        connections.clear();

        try {
            socket.close();
        } catch (IOException e) {
            ChatUtils.errorPrefix("Collective", "Error closing socket.");
            e.printStackTrace();
        }

        ChatUtils.infoPrefix("Collective", "Server stopped.");
    }
}
