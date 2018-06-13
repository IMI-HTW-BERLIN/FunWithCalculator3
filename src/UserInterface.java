import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * A graphical user interface for the calculator. No calculation is being
 * done here. This class is responsible just for putting up the display on
 * screen. It then refers to the "CalcEngine" to do all the real work.
 *
 * @author David Panagiotopulos and Bernhard Hoffmann | Original: David J. Barnes and Michael Kolling
 * @version 2018.05.24
 */
public class UserInterface implements ActionListener {
    CalcEnginePostfix calc;

    JFrame frame;
    private JTextField display;
    JButton dotButton;
    JPanel buttonPanel;

    /**
     * Create a user interface.
     * @param engine The calculator engine.
     */
    UserInterface(CalcEnginePostfix engine)
    {
        calc = engine;
        makeFrame();
        frame.setVisible(true);
    }

    /**
     * Make the frame for the user interface.
     */
    private void makeFrame()
    {
        frame = new JFrame("AWESOME CALCULATOR 3000!!!");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                 InstantiationException |
                 IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

        display = new JTextField();
        contentPane.add(display, BorderLayout.NORTH);

        buttonPanel = new JPanel(new GridLayout(6, 4));
        addButton(buttonPanel, "c");
        addButton(buttonPanel, "^");
        addButton(buttonPanel, "(");
        addButton(buttonPanel, ")");

        addButton(buttonPanel, "7");
        addButton(buttonPanel, "8");
        addButton(buttonPanel, "9");
        addButton(buttonPanel, "+");

        addButton(buttonPanel, "4");
        addButton(buttonPanel, "5");
        addButton(buttonPanel, "6");
        addButton(buttonPanel, "-");

        addButton(buttonPanel, "1");
        addButton(buttonPanel, "2");
        addButton(buttonPanel, "3");
        addButton(buttonPanel, "*");

        dotButton = new JButton(".");
        dotButton.addActionListener(this);
        buttonPanel.add(dotButton);
        addButton(buttonPanel, "0");
        addButton(buttonPanel, "=");
        addButton(buttonPanel, "/");

        buttonPanel.setPreferredSize(new Dimension(400,400));
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        //status = new JLabel(calc.getAuthor());
        //contentPane.add(status, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * Add a button to the button panel.
     * @param panel The panel to receive the button.
     * @param buttonText The text for the button.
     */
    void addButton(Container panel, String buttonText)
    {
        JButton button = new JButton(buttonText);
        button.addActionListener(this);
        panel.add(button);
    }

    /**
     * An interface action has been performed.
     * Find out what it was and handle it.
     * @param event The event that has occurred.
     */
    public void actionPerformed(ActionEvent event)
    {
        String command = event.getActionCommand();

        switch (command) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "+":
            case "-":
            case "*":
            case "/":
            case "^":
            case ".":
            case "(":
            case ")":
                calc.buttonPressed(command);
                break;
            case "=":
                calc.equals();
                break;
            case "c":
                calc.clear();
                break;
        }
        // else unknown command.

        redisplay();
    }

    /**
     * Update the interface display to show the current value of the
     * calculator.
     */
    void redisplay()
    {
        display.setText(calc.getDisplay());
    }

}
