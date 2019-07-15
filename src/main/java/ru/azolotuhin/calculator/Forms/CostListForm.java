package ru.azolotuhin.calculator.Forms;

import ru.azolotuhin.calculator.Costs.Cost;
import ru.azolotuhin.calculator.Costs.CostConstants;
import ru.azolotuhin.calculator.Costs.CostConstants.ConstructionType;
import ru.azolotuhin.calculator.Costs.CostList;
import ru.azolotuhin.calculator.TableModels.CostListTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static ru.azolotuhin.calculator.Costs.CostList.*;

class CostListForm extends JFrame {
    private JTable costListTable;
    private JPanel costListPanel;
    private JButton saveButton;
    private JPanel buttonPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JScrollPane costListScrollPane;
    private CostList costList;
    private CostListTableModel tableModel;

    {
        //получаю экземпляр кост листа
        costList = getInstance();
        //передаём БАЗОВЫЙ кост лист для изменения
        tableModel = new CostListTableModel(costList.getBaseCostList());
    }

    CostListForm() throws HeadlessException {

        costListTable.setModel(tableModel);
        costListTable.getTableHeader().setReorderingAllowed(false);

        setContentPane(costListPanel);
        setLocation(600, 200);
        setSize(800, 600);

        //Редактор ячеек (переопределение)
        //Тип конструкций
        DefaultComboBoxModel<ConstructionType> cbModel = new DefaultComboBoxModel<>();
        final ConstructionType[] conTypeValues = ConstructionType.values();
        for (ConstructionType conTypeValue : conTypeValues) {
            cbModel.addElement(conTypeValue);
        }
        JComboBox<ConstructionType> combo = new JComboBox<>(cbModel);
        DefaultCellEditor editor = new DefaultCellEditor(combo);
        costListTable.getColumnModel().getColumn(1).setCellEditor(editor);

        //Тип затраты
        DefaultComboBoxModel<CostConstants.CostType> cbModelType = new DefaultComboBoxModel<>();
        final CostConstants.CostType[] values = CostConstants.CostType.values();
        for (CostConstants.CostType value : values) {
            cbModelType.addElement(value);
        }
        JComboBox<CostConstants.CostType> comboType = new JComboBox<>(cbModelType);
        DefaultCellEditor editor1 = new DefaultCellEditor(comboType);
        costListTable.getColumnModel().getColumn(3).setCellEditor(editor1);

        //Ед.изм затраты
        DefaultComboBoxModel<CostConstants.Unit> cbModelUnit = new DefaultComboBoxModel<>();
        cbModelUnit.addElement(CostConstants.Unit.RubToKilo);
        cbModelUnit.addElement(CostConstants.Unit.Percent);
        JComboBox<CostConstants.Unit> comboUnit = new JComboBox<>(cbModelUnit);
        DefaultCellEditor editor2 = new DefaultCellEditor(comboUnit);
        costListTable.getColumnModel().getColumn(4).setCellEditor(editor2);

        //Обработчики событий формы
        saveButton.addActionListener(e ->
        {
            costList.saveBaseCostListToDB();
            JOptionPane.showMessageDialog(this, "Данные сохранены!");
        });

        addButton.addActionListener(e -> addToList());

        deleteButton.addActionListener(e -> {
            if (showConfirmDialog("Удалить данные?")) {
                deleteFromList();
            }
        });

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                Object[] buttons = {"Да", "Нет", "Отмена"};
                int ans = JOptionPane.showOptionDialog(CostListForm.this, "Сохранить изменения в БД?", "Диалог сохранения", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
                if (ans == 0) {
                    costList.saveBaseCostListToDB();
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } else if (ans == 1) {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } else if (ans == 2) {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }

    private void addToList() {
        int idx = costListTable.getSelectedRow();
        if (idx != -1) {
            costList.addToBaseCostList(idx + 1, new Cost());
            tableModel.updateData(costList.getBaseCostList());
            tableModel.fireTableDataChanged();
        }
    }

    private void deleteFromList() {
        int idx = costListTable.getSelectedRow();
        if (idx != -1) {
            costList.deleteFromBaseCostList(idx);
        }
        tableModel.updateData(costList.getBaseCostList());
        tableModel.fireTableDataChanged();
    }

    private boolean showConfirmDialog(String message) {
        int result = JOptionPane.showConfirmDialog(
                CostListForm.this,
                message,
                "Подтверждение",
                JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        costListPanel = new JPanel();
        costListPanel.setLayout(new BorderLayout(0, 0));
        costListScrollPane = new JScrollPane();
        costListPanel.add(costListScrollPane, BorderLayout.CENTER);
        costListTable = new JTable();
        costListTable.setColumnSelectionAllowed(false);
        costListTable.setFillsViewportHeight(true);
        costListTable.setName("");
        costListTable.setOpaque(true);
        costListTable.setRowSelectionAllowed(true);
        costListTable.setSelectionForeground(new Color(-1544));
        costListTable.setShowVerticalLines(true);
        costListTable.putClientProperty("JTable.autoStartsEdit", Boolean.FALSE);
        costListTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        costListScrollPane.setViewportView(costListTable);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        costListPanel.add(buttonPanel, BorderLayout.SOUTH);
        addButton = new JButton();
        addButton.setHideActionText(false);
        addButton.setText("Добавить затрату");
        addButton.putClientProperty("hideActionText", Boolean.FALSE);
        buttonPanel.add(addButton);
        deleteButton = new JButton();
        deleteButton.setText("Удалить затрату");
        buttonPanel.add(deleteButton);
        saveButton = new JButton();
        saveButton.setText("Сохранить изменения");
        buttonPanel.add(saveButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return costListPanel;
    }

}