package org.suai.server.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Connector {

    private InetAddress addr;
    private int port;
    private DatagramSocket socket;

    Connector(InetAddress addr, int port) throws SocketException {
        this.socket = new DatagramSocket();
        this.addr = addr;
        this.port = port;
    }

    public void send(String str) {
        try {
            socket.send(new DatagramPacket(str.getBytes(), str.getBytes().length, addr, port ));
        } catch (IOException e) {
            System.out.println("Failed to send packet");
        }
    }

    public String receive() {
        byte[] databuffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(databuffer, databuffer.length);

        try {
            socket.receive(packet);
        } catch (IOException e) {
            System.out.println("Failed to receive packet");
        }

        return new String(packet.getData(), packet.getOffset(), packet.getLength());
    }
}
