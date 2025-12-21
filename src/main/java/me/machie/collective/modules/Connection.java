package me.machie.collective.modules;

import me.machie.collective.util.SafeLoggingThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Connection extends SafeLoggingThread {
    public Socket socket;

    public Connection(Socket socket) {
        this.socket = socket;
    }

    public Connection(String ip, int port) {
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            socket = null;
            error("Server not found at %s:%s", ip, port);
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        info("New connection established to %s", getAddress());

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
            error("Error with connection to %s",  getAddress());
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            error("Error closing socket.");
            e.printStackTrace();
        }

        info("Connection to %s closed.", getAddress());
    }

    public boolean shouldRemove() {
        return socket.isClosed();
    }

    public String getAddress() {
        String address = socket.getInetAddress().getHostAddress();
        return (address.equals("127.0.0.1")  ? "localhost" : address) + ":" + socket.getPort();
    }
}
