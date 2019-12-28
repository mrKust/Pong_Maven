package org.suai.server;

import java.io.IOException;
import java.net.InetAddress;

public class UDPConnection {

    private String address;
    private int port;
    private GameSession session;
    private int playerNum;
    private String name = null;

    UDPConnection(String addr, int port, String name) {
        address = addr;
        this.port = port;
        this.name = name;
        session = null;
    }

    void handlePacket(String packet) {
        if (session != null) {
            session.addPacket(playerNum + ":" + packet);
        }
    }

    void send(String message) {
        try {
            SocketWrapper.send(InetAddress.getByName(address), port, message);
        } catch (IOException e) {
            System.out.println("Can't send");
        }
    }

    GameSession getSession() {
        return session;
    }

    void setSession(GameSession session) {
        this.session = session;
    }

    void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    String getName() {
        return name;
    }

    String getAddress() { return address;}

    int getPort() {return port;}

    public String toString() {
        return address + ":" + port + ":" + name;
    }
}
