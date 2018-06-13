import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class UserInterfaceSet extends UserInterfaceHex {
    private final String[] items = {"Dec", "Hex", "Set"};

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
    }

    private void itemSelected(ItemEvent event) {
        String itemSelected = event.getItem().toString();
        switch (itemSelected) {
            case "Dec":
                super.hexMode = true;
                super.toggleMode();
                break;
            case "Hex":
                super.hexMode = false;
                super.toggleMode();
                break;
            case "Set":
                super.buttonPanel.setVisible(false);
        }
    }
}
