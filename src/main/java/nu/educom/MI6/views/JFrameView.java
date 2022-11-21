package nu.educom.MI6.views;

import nu.educom.MI6.data.objects.Input;
import nu.educom.MI6.presenters.IPresentor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;

public class JFrameView implements IView{
    IPresentor presentor;

    private JFrame frame;
    private HashMap<String, Input> userInput;

    public JFrameView(){
        this.frame = new JFrame("Agent Login System");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(200, 300);
        this.frame.setVisible(true);
        userInput = new HashMap<String, Input>();
    }

    @Override
    public void askForLogin() {
        // Create a fresh panel
        var panel = createPanel();

        // Declaring all items
        var label   = new JLabel("Enter service-number:", JLabel.CENTER);
        var label2  = new JLabel("Enter password:", JLabel.CENTER);
        var input   = new JTextField("Enter here...", 20);
                      input.setMaximumSize(input.getPreferredSize());
        var input2  = new JTextField("Enter here...", 20);
                      input2.setMaximumSize(input.getPreferredSize());
        var button  = new JButton("Submit");
                      button.addActionListener(e -> {
                          userInput.put("Login", new Input(input.getText()));
                          userInput.put("Password", new Input(input2.getText()));
                          presentor.triggerLogin();
                          removePanel(panel);
                      });

        // Building the panel
        panel.add(label);
        panel.add(input);
        panel.add(label2);
        panel.add(input2);
        panel.add(button);

        // Adding it to the frame
        this.frame.add(panel);
    }

    private JPanel createPanel() {
        // Declare & Setup Panel
        var panel       = new JPanel();
                          BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
                          panel.setLayout(boxlayout);
                          panel.setBorder(new EmptyBorder(new Insets(45, 70, 45, 70)));
        return panel;
    }

    private void removePanel(JPanel panel) {
        frame.remove(panel);
        frame.repaint();
        frame.revalidate();
    }

    @Override
    public void askForPassword() {

    }

    @Override
    public void showMessage(String message) {
        // Create a fresh panel
        var panel = createPanel();

        // Set
        var label   = new JLabel(message, JLabel.CENTER);
        var button  = new JButton("Return");
        button.addActionListener(e -> {
            presentor.displayLogin();
            removePanel(panel);
        });

        // Building the panel
        panel.add(label);
        panel.add(button);

        // Adding it to the frame
        this.frame.add(panel);
    }

    @Override
    public void installPresentor(IPresentor presentor) {
        this.presentor = presentor;
    }

    @Override
    public HashMap<String, Input> updateUserInput() {
        return userInput;
    }
}
