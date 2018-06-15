import set.CustomSet;
import set.Set;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public class UserInterfaceSet extends UserInterfaceHex {

    private JComboBox<String> modeSelector;
    private JComboBox<Set<String>> setListLeft;
    private JComboBox<Set<String>> setListRight;
    private JComboBox<String> operand;
    private JLabel result;
    private JPanel oldContentPane;

    private ArrayList<Set<String>> sets;

    UserInterfaceSet(CalcEngineSet engine) {
        super(engine);
        setUp();
    }

    private void setUp() {
        super.labelDecHexSwitch.setVisible(false);
        super.buttonDecHexSwitch.setVisible(false);

        sets = new ArrayList<>();
        sets.add(new CustomSet<>("1", "2"));
        sets.add(new CustomSet<>("1","3","hello"));

        JPanel contentPane = (JPanel) super.frame.getContentPane();
        modeSelector = new JComboBox<>(new String[] {"Dec", "Hex", "Set"});
        contentPane.add(modeSelector, BorderLayout.SOUTH);
        modeSelector.addItemListener(this::itemSelected);
        super.frame.pack();
        oldContentPane = (JPanel) frame.getContentPane();
    }

    private void itemSelected(ItemEvent event) {
        String itemSelected = event.getItem().toString();
        switch (itemSelected) {
            case "Dec":
                super.hexMode = false;
                super.toggleMode();
                setView(false);
                break;
            case "Hex":
                super.hexMode = true;
                super.toggleMode();
                setView(false);
                break;
            case "Set":
                setView(true);
        }
    }

    private void setView (boolean activate) {
        if (activate) {
            frame.setContentPane(makeSetLayout());
            frame.pack();
        } else {
            super.toggleMode();
            oldContentPane.add(modeSelector, BorderLayout.SOUTH);
            frame.setContentPane(oldContentPane);
            frame.pack();
        }
    }

    private JPanel makeSetLayout() {
        setListLeft = new JComboBox<>() ;
        setListRight = new JComboBox<>();
        updateSetComboBoxes();
        operand = new JComboBox<>(new String[]{"+", "-", "∩"});
        result = new JLabel("Result will be displayed here...");

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

        JPanel layout = new JPanel(new GridLayout(4, 4));

        layout.add(setListLeft);
        layout.add(operand);
        layout.add(setListRight);
        addButton(layout, "=");

        layout.add(result);
        layout.add(new JLabel());
        layout.add(new JLabel());
        layout.add(new JLabel());

        layout.add(new JLabel("Create new Set:"));
        layout.add(new JLabel());
        layout.add(new JLabel());
        layout.add(new JLabel());

        JButton btnCreateSet = new JButton("Create");
        btnCreateSet.addActionListener(e -> createSet());
        layout.add(btnCreateSet);
        layout.add(new JLabel(""));

        layout.setPreferredSize(new Dimension(1000,100));

        contentPane.add(layout, BorderLayout.NORTH);
        contentPane.add(modeSelector, BorderLayout.SOUTH);

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
        if (!sets.contains(newSet)) sets.add(newSet);
        updateSetComboBoxes();
    }

    private void updateSetComboBoxes() {
        setListLeft.removeAllItems();
        setListRight.removeAllItems();

        for (Set set : sets) {
            setListRight.addItem(set);
            setListLeft.addItem(set);
        }
    }


    @Override
    public void actionPerformed (ActionEvent event) {
        String command = event.getActionCommand();
        calc.setFirstOperand((Set) setListLeft.getSelectedItem());
        calc.setSecondOperand((Set) setListRight.getSelectedItem());
        calc.setOperator((operand.getSelectedItem().toString()).charAt(0));
        if (command.equals("=")) {
            calc.equals();
            if (!setDuplicate(sets,calc.getResult())) sets.add(calc.getResult());
            updateSetComboBoxes();
            result.setText("Result: " + calc.getResult().toString());
        }

     }

     private boolean setDuplicate(ArrayList<Set<String>> arraySet, Set set) {
        for (Set setElement : arraySet) {
            if(setElement.equals(set)) return true;
        }
        return false;
     }

}
