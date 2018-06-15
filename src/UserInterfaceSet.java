import set.CustomSet;
import set.Set;
import sun.jvm.hotspot.ui.JavaStackTracePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
        sets.add(new CustomSet<>("1", "3", "hello"));

        JPanel contentPane = (JPanel) super.frame.getContentPane();
        modeSelector = new JComboBox<>(new String[]{"Dec", "Hex", "Set"});
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

    private void setView(boolean activate) {
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
        setListLeft = new JComboBox<>();
        setListRight = new JComboBox<>();
        updateSetComboBoxes();
        operand = new JComboBox<>(new String[]{"+", "-", "∩"});
        result = new JLabel("Result will be displayed here...");
        result.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel calcLayout = new JPanel(new GridLayout(1, 4));

        calcLayout.add(setListLeft);
        calcLayout.add(operand);
        calcLayout.add(setListRight);
        addButton(calcLayout, "=");

        JPanel vbox = new JPanel();
        vbox.setLayout(new BoxLayout(vbox, BoxLayout.Y_AXIS));
        vbox.add(result);

        vbox.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnCreateSet = new JButton("Create a new set");
        btnCreateSet.addActionListener(e -> createSet());
        btnCreateSet.setAlignmentX(Component.CENTER_ALIGNMENT);
        vbox.add(btnCreateSet);

        contentPane.add(calcLayout, BorderLayout.NORTH);
        contentPane.add(vbox, BorderLayout.CENTER);
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
        if (!setDuplicate(sets, newSet)) sets.add(newSet);
        else result.setText("Set already exists (" + calc.getResult().toString() + ")");
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
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        calc.setFirstOperand((Set) setListLeft.getSelectedItem());
        calc.setSecondOperand((Set) setListRight.getSelectedItem());
        calc.setOperator((operand.getSelectedItem().toString()).charAt(0));
        if (command.equals("=")) {
            calc.equals();
            if (!setDuplicate(sets, calc.getResult()))
                sets.add(calc.getResult());
            result.setText("Result of " + setListLeft.getSelectedItem() + "\t" + operand.getSelectedItem() + "\t" +
                    setListRight.getSelectedItem() + " = " + calc.getResult().toString());
            updateSetComboBoxes();
        }

    }

    private boolean setDuplicate(ArrayList<Set<String>> arraySet, Set set) {
        for (Set setElement : arraySet) {
            if (setElement.equals(set)) return true;
        }
        return false;
    }

}
