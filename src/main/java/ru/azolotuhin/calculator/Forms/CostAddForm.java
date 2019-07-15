package ru.azolotuhin.calculator.Forms;

import ru.azolotuhin.calculator.Costs.Cost;
import ru.azolotuhin.calculator.Costs.CostList;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import static ru.azolotuhin.calculator.Costs.CostConstants.*;

public final class CostAddForm extends JDialog {

    private JPanel mainPanel;
    private JList selectionList;
    private JButton addCostButton;
    private JPanel upperPanel;
    private JRadioButton constructionLightRadioButton;
    private JRadioButton constructionHeavyRadioButton;
    private JPanel upperPanelRight;
    private JPanel upperPanelLeft;
    private JRadioButton humanRadioButton;
    private JRadioButton materialRadioButton;
    private JRadioButton allRadioButton;
    private List<Cost> costList;
    private DefaultListModel<String> dlm;

    private static CostAddForm instance;

    {
        dlm = new DefaultListModel<>();
    }

    public static CostAddForm getInstance(MainForm owner) {
        if (instance == null) {
            instance = new CostAddForm(owner);
        }
        instance.setVisible(true);
        return instance;
    }

    private CostAddForm(MainForm owner) throws HeadlessException {
        super(owner, "Выбор затрат");
        $$$setupUI$$$();
        //Логика заполнения списка
        buildCostList(owner.getConstructionType());

        selectionList.setModel(dlm);
        selectionList.setSelectionMode(SINGLE_SELECTION);
        selectionList.setSelectedIndex(0);

        //Создание ГУИ
        mainPanel.add(new JScrollPane(selectionList));

        if (owner.getConstructionType() == ConstructionType.Light) {
            constructionLightRadioButton.setSelected(true);
        } else {
            constructionHeavyRadioButton.setSelected(true);
        }

        allRadioButton.setSelected(true);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(400, 300);
        setLocation(new Point(550, 0));
        setVisible(true);

        addCostButton.addActionListener(e -> {
            int idx = selectionList.getSelectedIndex();
            try {
                owner.addToList(costList.get(idx).clone());
            } catch (CloneNotSupportedException e1) {
                e1.printStackTrace();
            }
        });

        constructionLightRadioButton.addActionListener(e -> getState());

        constructionHeavyRadioButton.addActionListener(e -> getState());

        allRadioButton.addActionListener(e -> getState());

        humanRadioButton.addActionListener(e -> getState());

        materialRadioButton.addActionListener(e -> getState());
    }

    private void getState() {
        boolean heavy = constructionHeavyRadioButton.isSelected();
        boolean light = constructionLightRadioButton.isSelected();
        boolean human = humanRadioButton.isSelected();
        boolean material = materialRadioButton.isSelected();
        boolean all = allRadioButton.isSelected();

        ConstructionType constructionType = null;
        CostType costType = null;
        /* TODO заменить на что-то нормальное*/
        if (heavy) {
            constructionType = ConstructionType.Heavy;
        }
        if (light) {
            constructionType = ConstructionType.Light;
        }
        if (!all) {
            if (human) {
                costType = CostType.Human;
            }
            if (material) {
                costType = CostType.Material;
            }
            buildCostList(constructionType, costType);
        } else {
            buildCostList(constructionType);
        }
    }

    private void buildCostList(ConstructionType constructionType) {
        costList = CostList.getInstance().getBaseCostList(constructionType);
        dlm.clear();
        for (Cost cost : costList) {
            dlm.addElement(cost.toString());
        }
    }

    private void buildCostList(ConstructionType constructionType, CostType costType) {
        costList = CostList.getInstance().getBaseCostList(constructionType, costType);
        dlm.clear();
        for (Cost cost : costList) {
            dlm.addElement(cost.toString());
        }
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        selectionList = new JList();
        mainPanel.add(selectionList, BorderLayout.CENTER);
        addCostButton = new JButton();
        addCostButton.setActionCommand("Add");
        addCostButton.setPreferredSize(new Dimension(177, 40));
        addCostButton.setText("Добавить в список затрат");
        mainPanel.add(addCostButton, BorderLayout.SOUTH);
        upperPanel = new JPanel();
        upperPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.add(upperPanel, BorderLayout.NORTH);
        upperPanelRight = new JPanel();
        upperPanelRight.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        upperPanel.add(upperPanelRight, BorderLayout.EAST);
        allRadioButton = new JRadioButton();
        allRadioButton.setText("Все");
        upperPanelRight.add(allRadioButton);
        humanRadioButton = new JRadioButton();
        humanRadioButton.setText("Трудозатраты");
        upperPanelRight.add(humanRadioButton);
        materialRadioButton = new JRadioButton();
        materialRadioButton.setText("Материалы");
        upperPanelRight.add(materialRadioButton);
        upperPanelLeft = new JPanel();
        upperPanelLeft.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        upperPanel.add(upperPanelLeft, BorderLayout.WEST);
        constructionLightRadioButton = new JRadioButton();
        constructionLightRadioButton.setText("Лёгкие");
        upperPanelLeft.add(constructionLightRadioButton);
        constructionHeavyRadioButton = new JRadioButton();
        constructionHeavyRadioButton.setText("Тяжелые");
        upperPanelLeft.add(constructionHeavyRadioButton);
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(constructionHeavyRadioButton);
        buttonGroup.add(constructionLightRadioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(allRadioButton);
        buttonGroup.add(humanRadioButton);
        buttonGroup.add(materialRadioButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}