package me.machie.collective.systems.collective;

import meteordevelopment.meteorclient.utils.player.ChatUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class WorkerConnection extends Thread {
    public Socket socket;

    public WorkerConnection(Socket socket) {
        this.socket = socket;
    }

    public WorkerConnection(String ip, int port) {
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            socket = null;
            ChatUtils.errorPrefix("Collective", "Server not found at %s:%s", ip, port);
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        ChatUtils.infoPrefix("Collective", "Connection to %s established.", getAddress());

        try {
            socket.setSoTimeout(50);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while (!isInterrupted()) {
                try {
                    if (in.read() == -1) break;
                } catch (SocketTimeoutException ignored) {}

                // TODO communication :3
            }
        } catch (IOException e) {
            ChatUtils.errorPrefix("Collective", "Error with connection to %s",  getAddress());
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            ChatUtils.errorPrefix("Collective", "Error closing socket.");
            e.printStackTrace();
        }

        ChatUtils.infoPrefix("Collective", "Connection to %s closed.", getAddress());
    }

    public boolean shouldRemove() {
        return socket.isClosed();
    }

    public String getAddress() {
        String address = socket.getInetAddress().getHostAddress();
        return (address.equals("127.0.0.1")  ? "localhost" : address) + ":" + socket.getPort();
    }
}
