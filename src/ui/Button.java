package ui;

import java.awt.Color;

import javax.swing.JButton;
import constants.Constants;
import java.awt.event.ActionListener;

public class Button extends JButton {

    public Button(ActionListener actionListener) {
        super(Constants.PLAY_AGAIN);
        initButton(actionListener);
    }

    private void initButton(ActionListener actionListener) {
        addActionListener(actionListener);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setForeground(java.awt.Color.WHITE);
        // TODO magic numbers
        setBounds(0, 0, 100, 30); 
        setVisible(false);
    }

    public void centerButton(int parentWidth, int parentHeight) {
        setBounds(parentWidth / 2 - 50, parentHeight / 2 - 15, 100, 30);
    }


}
