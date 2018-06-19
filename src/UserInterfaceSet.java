import set.CustomSet;
import set.Set;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserInterfaceSet implements ActionListener {

    private CalcEngineSet calc;
    private JFrame frame;

    private JComboBox<Set<String>> setListLeft;
    private JComboBox<Set<String>> setListRight;
    private JComboBox<Character> operand;
    private JTextField result;

    private ArrayList<Set<String>> sets;

    UserInterfaceSet(CalcEngineSet engine) {
        calc = engine;
        setUp();
        makeFrame();
    }

    private void setUp() {
        sets = new ArrayList<>();
        sets.add(new CustomSet<>("1", "2"));
        sets.add(new CustomSet<>("1", "3", "hello"));
    }

    private void makeFrame()
    {
        frame = new JFrame("AWESOME CALCULATOR 3000!!!");
        frame.setContentPane(makeSetLayout());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel makeSetLayout() {
        setListLeft = new JComboBox<>();
        setListRight = new JComboBox<>();
        updateSetComboBoxes();
        operand = new JComboBox<>(new Character[]{'+', '-', '∩'});
        result = new JTextField("Result will be displayed here...");
        result.setHorizontalAlignment(SwingConstants.CENTER);
        result.setEditable(false);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel calcLayout = new JPanel(new GridLayout(1, 4));

        calcLayout.add(setListLeft);
        calcLayout.add(operand);
        calcLayout.add(setListRight);
        JButton btnEquals = new JButton("=");
        btnEquals.addActionListener(this);
        calcLayout.add(btnEquals);

        JButton btnCreateSet = new JButton("Create a new set");
        btnCreateSet.addActionListener(e -> createSet());

        contentPane.add(calcLayout, BorderLayout.NORTH);
        contentPane.add(result, BorderLayout.CENTER);
        contentPane.add(btnCreateSet, BorderLayout.SOUTH);

        return contentPane;
    }

    private void createSet() {
        JTextField setInput = new JTextField();
        Object[] dialogItems = {"Enter the new set:", setInput, "(e.g. \"13, Hello, 4.2, abcdef\")"};
        JOptionPane.showMessageDialog(frame, dialogItems, "Create a new set", JOptionPane.PLAIN_MESSAGE);

        String[] setStrings = setInput.getText().split("[ ]*,[ ]*");
        Set<String> newSet = new CustomSet<>();

        for (String s : setStrings) {
            newSet.add(s.trim());
        }
        if (setDuplicate(sets, newSet))
            result.setText("Set already exists (" + calc.getResult().toString() + ")");
        else
            sets.add(newSet);
        updateSetComboBoxes();
    }

    private void updateSetComboBoxes() {
        setListLeft.removeAllItems();
        setListRight.removeAllItems();

        for (Set<String> set : sets) {
            setListRight.addItem(set);
            setListLeft.addItem(set);
        }
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        calc.setFirstOperand((Set) setListLeft.getSelectedItem());
        calc.setSecondOperand((Set) setListRight.getSelectedItem());
        calc.setOperator((char)(operand.getSelectedItem()));
        if (command.equals("=")) {
            calc.equals();
            if (!setDuplicate(sets, calc.getResult()))
                sets.add(calc.getResult());
            result.setText("Result of " + setListLeft.getSelectedItem() + " " + operand.getSelectedItem() + " " +
                    setListRight.getSelectedItem() + " = " + calc.getResult().toString());
            updateSetComboBoxes();
        }

    }

    private boolean setDuplicate(ArrayList<Set<String>> arraySet, Set<String> set) {
        for (Set<String> setElement : arraySet) {
            if (setElement.equals(set)) return true;
        }
        return false;
    }

}
