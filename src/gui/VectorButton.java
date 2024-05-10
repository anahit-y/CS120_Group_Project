package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.vectoroperations.*;

public class VectorButton extends JButton implements ActionListener {
    private JPanel buttonPanel;
    private JPanel rightPanel; // This is the panel that each button will manipulate

    public VectorButton(JPanel buttonPanel, JPanel rightPanel, String name) {
        super(name);
        this.buttonPanel = buttonPanel;
        this.rightPanel = rightPanel; // Store the reference to the right panel
    }

    public void actionPerformed(ActionEvent e) {
        rightPanel.removeAll();  // Clear previous components
        buttonPanel.removeAll();

        VectorAddition add = new VectorAddition("Addition",rightPanel);
        add.configurations();
        VectorSubtraction sub = new VectorSubtraction("Subtraction", rightPanel);
        sub.configurations();
        VectorScalar scalar = new VectorScalar("Scalar Multiplication", rightPanel);
        scalar.configurations();
        DotProduct dot = new DotProduct("Dot Product", rightPanel);
        dot.configurations();
        CrossProduct cross = new CrossProduct("Cross Product", rightPanel);
        cross.configurations();
        Normalize norm = new Normalize("Normalize", rightPanel);
        norm.configurations();
        Distance distance = new Distance("Distance", rightPanel);
        distance.configurations();
        Cosine cos = new Cosine("Cosine", rightPanel);
        cos.configurations();
        Orthogonal orth = new Orthogonal("Orthogonality", rightPanel);
        orth.configurations();

        buttonPanel.add(add);
        add.addActionListener(k -> add.openPanel());
        buttonPanel.add(sub);
        sub.addActionListener(k -> sub.openPanel());
        buttonPanel.add(scalar);
        scalar.addActionListener(k -> scalar.openPanel());
        buttonPanel.add(dot);
        dot.addActionListener(k -> dot.openPanel());
        buttonPanel.add(cross);
        cross.addActionListener(k -> cross.openPanel());
        buttonPanel.add(norm);
        norm.addActionListener(k -> norm.openPanel());
        buttonPanel.add(distance);
        distance.addActionListener(k -> distance.openPanel());
        buttonPanel.add(cos);
        cos.addActionListener(k -> cos.openPanel());
        buttonPanel.add(orth);
        orth.addActionListener(k -> orth.openPanel());


        buttonPanel.revalidate();
        buttonPanel.repaint();
    }
}
