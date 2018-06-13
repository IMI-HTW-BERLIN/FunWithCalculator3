import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;

public class UserInterfaceSet extends UserInterfaceHex {
    private final String[] items = {"Dec", "Hex", "Set"};
    private JComboBox<String> setList1;
    private JComboBox<String> setList2;
    private JPanel oldContentPane;

    UserInterfaceSet(CalcEnginePostfix engine) {
        super(engine);
        setUp();
    }

    private void setUp() {
        super.labelDecHexSwitch.setVisible(false);
        super.buttonDecHexSwitch.setVisible(false);

        JPanel contentPane = (JPanel) super.frame.getContentPane();
        JComboBox<String> cb = new JComboBox<>(items);
        contentPane.add(cb, BorderLayout.SOUTH);
        cb.addItemListener(this::itemSelected);
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
                makeSetButtons();
                frame.pack();
        }
    }

    private JPanel makeSetButtons() {
        setList1 = new JComboBox<>(new String[]{"1,2", "1"});
        setList2 = new JComboBox<>(new String[]{"1,2", "1"});
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

        buttonPanel = new JPanel(new GridLayout(1, 3));

        buttonPanel.add(setList1);
        addButton(buttonPanel, "+");
        buttonPanel.add(setList2);

        contentPane.add(buttonPanel);

        return contentPane;
    }
}
