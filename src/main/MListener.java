package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MListener implements KeyListener {

    public boolean q_pressed = false;
    public boolean e_pressed = false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'e': e_pressed = true;
            case 'q': q_pressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'e': e_pressed = false;
            case 'q': q_pressed = false;
        }
    }
}
