/**
 * $Revision$
 * $Date$
 *
 * Copyright (C) 1999-2005 Jive Software. All rights reserved.
 * This software is the proprietary information of Jive Software. Use is subject to license terms.
 */

package org.jivesoftware.sparkplugin;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 */
public class CallPanelButton extends JButton implements MouseListener {

    private Icon normalIcon;
    private Icon hoverIcon;
    private Icon downIcon;
    private Image backgroundImage;
    private String text;

    private boolean selected;

    public CallPanelButton(Image image, String text) {
        super();

        this.text = text;

        normalIcon = JinglePhoneRes.getImageIcon("CALLPANEL_BIG_BUTTON");
        hoverIcon = JinglePhoneRes.getImageIcon("CALLPANEL_BIG_BUTTON_HOVER");
        downIcon = JinglePhoneRes.getImageIcon("CALLPANEL_BIG_BUTTON_DOWN");
        backgroundImage = image;

        setIcon(normalIcon);

        decorate();

        addMouseListener(this);

        setDisabledIcon(normalIcon);
    }

    /**
     * Decorates the button with the approriate UI configurations.
     */
    private void decorate() {
        setBorderPainted(false);
        setOpaque(true);

        setContentAreaFilled(false);
        setMargin(new Insets(0, 0, 0, 0));
    }


    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        setIcon(downIcon);
    }

    public void mouseReleased(MouseEvent e) {
        if (!selected) {
            setIcon(normalIcon);
        }

    }

    public void mouseEntered(MouseEvent e) {
        if (!selected) {
            setIcon(hoverIcon);
        }
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void mouseExited(MouseEvent e) {
        if (!selected) {
            setIcon(normalIcon);
        }
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public void setButtonSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            setIcon(downIcon);
        }
        else {
            setIcon(normalIcon);
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        int x = (width - backgroundImage.getWidth(null)) / 2;
        int y = (height - backgroundImage.getHeight(null)) / 2;
        g.drawImage(backgroundImage, x, y - 5, null);

        if (isEnabled()) {
            g.setColor(Color.black);
        }
        else {
            g.setColor(Color.lightGray);
        }
        g.setFont(new Font("Dialog", Font.PLAIN, 11));


        int stringWidth = g.getFontMetrics().stringWidth(text);

        x = (width - stringWidth) / 2;
        y = height - 12;
        g.drawString(text, x, y);

    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (!enabled) {
            removeMouseListener(this);
        }
        else {
            addMouseListener(this);
        }
    }
}
