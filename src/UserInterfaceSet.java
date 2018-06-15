import set.CustomSet;
import set.Set;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public class UserInterfaceSet extends UserInterfaceHex {

    private JComboBox<String> modeSelector;
    private JComboBox<Set<String>> setList1;
    private JComboBox<Set<String>> setList2;
    private JPanel oldContentPane;

    private ArrayList<Set<String>> sets;

    UserInterfaceSet(CalcEnginePostfix engine) {
        super(engine);
        setUp();
    }

    private void setUp() {
        super.labelDecHexSwitch.setVisible(false);
        super.buttonDecHexSwitch.setVisible(false);

        sets = new ArrayList<>();
        sets.add(new CustomSet<>("1", "2"));
        sets.add(new CustomSet<>("1"));

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
                super.hexMode = true;
                super.toggleMode();
                super.buttonPanel.setVisible(true);
                break;
            case "Hex":
                super.hexMode = false;
                super.toggleMode();
                super.buttonPanel.setVisible(true);
                break;
            case "Set":
                frame.setContentPane(makeSetButtons());
                frame.pack();
                break;
        }
    }

    private JPanel makeSetButtons() {
        setList1 = new JComboBox<>();
        setList2 = new JComboBox<>();
        updateSetComboBoxes();

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

        buttonPanel = new JPanel(new GridLayout(2, 3));

        buttonPanel.add(setList1);
        addButton(buttonPanel, "+");
        buttonPanel.add(setList2);

        buttonPanel.add(new JLabel("Create new Set:"));
        JButton btnCreateSet = new JButton("Create");
        btnCreateSet.addActionListener(e -> createSet());
        buttonPanel.add(btnCreateSet);
        buttonPanel.add(new JLabel(""));

        contentPane.add(buttonPanel);
        contentPane.add(modeSelector, BorderLayout.SOUTH);

        return contentPane;
    }

    private void createSet() {
        JTextField setInput = new JTextField();
        Object[] dialogItems = {"Enter the new set:", setInput, "(e.g. \"13, Hello, 4.2, abcdef\")"};
        JOptionPane.showMessageDialog(frame, dialogItems, "Create a new set", JOptionPane.PLAIN_MESSAGE);

        String[] setStrings = setInput.getText().split(",");
        Set<String> newSet = new CustomSet<>();

        for (String s : setStrings) {
            newSet.add(s.trim());
        }

        sets.add(newSet);
        updateSetComboBoxes();
    }

    private void updateSetComboBoxes() {
        setList1.removeAllItems();
        setList2.removeAllItems();

        for (Set set : sets) {
            setList1.addItem(set);
            setList2.addItem(set);
        }
    }
}
