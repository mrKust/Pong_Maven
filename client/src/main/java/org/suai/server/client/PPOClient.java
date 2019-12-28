package org.suai.server.client;

import org.suai.server.view.PPOMenuView;

import java.io.IOException;
import java.net.*;

public class PPOClient {

    public static void main (String[] args) {

        try {
            Connector connector = new Connector(InetAddress.getByName("localhost"), 8081);
            new Thread(new PPOMenuView(connector)).start();
        } catch (SocketException e) {
            System.out.println("Can't open socket");
        } catch (UnknownHostException e) {
            System.out.println("Can't resolve server address");
        }
    }
}
