package ui;

import javax.swing.*;
import java.awt.*;

public class ContributionsPanel extends JPanel {

    public ContributionsPanel() {

        setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setEditable(false);

        area.setText("Contribution tracking system will calculate participation credits here.");

        add(new JScrollPane(area), BorderLayout.CENTER);
    }
}