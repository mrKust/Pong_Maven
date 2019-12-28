package org.suai.server.client;

import org.suai.server.view.PPOGameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ViewUpdater implements Runnable, KeyListener {

    private PPOGameView view;
    private Connector connector;
    private boolean active;

    ViewUpdater(PPOGameView view, Connector connector) {
        this.view = view;
        this.connector = connector;
        active = true;
    }

    @Override
    public void run() {
        long timer = System.currentTimeMillis();

        while (isActive()) {
            long wait = 1000/200 - (System.currentTimeMillis() - timer);

            if (wait > 0) {
                try {
                    Thread.sleep(wait);


                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                    Thread.currentThread().interrupt();
                } catch (NullPointerException e) {
                    return;
                }
            }
            try {
                if (isActive()) {
                    view.render();
                    view.draw();
                }
            } catch (NullPointerException e) {
                return;
            }

        }
    }

    private synchronized boolean isActive() {
        return active;
    }

    synchronized void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //unused
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            connector.send("UP");
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            connector.send("DOWN");
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setActive(false);
            view.dispose();
            connector.send("END");
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            connector.send("SPACE");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
            connector.send("STOP");
        }
    }
}
