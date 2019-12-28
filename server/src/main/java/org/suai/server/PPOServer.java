package org.suai.server;

import java.io.IOException;
import java.util.logging.*;

public class PPOServer {

    public static void main (String[] args) {

        InputHandler inputHandler = new InputHandler();
        new Thread(inputHandler).start();

        SocketWrapper listener = new SocketWrapper(8081, inputHandler);
        new Thread(listener).start();
    }
}
