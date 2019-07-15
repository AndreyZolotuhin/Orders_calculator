package ru.azolotuhin.calculator.Forms;

import org.w3c.dom.events.Event;
import ru.azolotuhin.calculator.Calculations.Calculation;
import ru.azolotuhin.calculator.Calculations.Calculations;
import ru.azolotuhin.calculator.Calculations.Calculator;
import ru.azolotuhin.calculator.Costs.Cost;
import ru.azolotuhin.calculator.Costs.CostConstants.ConstructionType;
import ru.azolotuhin.calculator.Orders.Customer;
import ru.azolotuhin.calculator.Orders.CustomersList;
import ru.azolotuhin.calculator.Orders.Order;
import ru.azolotuhin.calculator.Orders.OrderList;
import ru.azolotuhin.calculator.Metal.MetalSortList;
import ru.azolotuhin.calculator.Security.AuthorizationConstants;
import ru.azolotuhin.calculator.Security.User;
import ru.azolotuhin.calculator.TableModels.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import static ru.azolotuhin.calculator.Costs.CostConstants.*;

public class MainForm extends JFrame {
    private JTable costHumanTable;
    private JPanel mainPanel;
    private JPanel humanTablePanel;
    private JButton addRowButton;
    private JButton deleteRowButton;
    private JPanel buttonPanel;
    private JTabbedPane calculationTabbedPanel;
    private JButton resetButton;
    private JTable costMaterialsTable;
    private JPanel materialTablePanel;
    private JLabel constructionTypeLabel;
    private JTable metalTable;
    private JLabel totalHumanAmount;
    private JPanel metalHeadTablePanel;
    private JPanel infoPanel1;
    private JLabel constructionAmountLabel;
    private JPanel infoPanel2;
    private JPanel buttonPanel1;
    private JButton addRowButton1;
    private JButton deleteRowButton1;
    private JButton testButton;
    private JTextField totalMaterialsAmount;
    private JLabel totalHumanAmountText;
    private JLabel totalMaterialsAmountText;
    private JPanel totalTablePanel;
    private JTable costTotalTable;
    private JPanel infoPanel4;
    private JLabel label4;
    private JPanel infoPanel5;
    private JLabel label5;
    private JTextField heavyAmountTextField;
    private JTextField lightAmounttextField;
    private JPanel humanAmountPanel;
    private JRadioButton heavyRadioButton;
    private JRadioButton lightRadioButton;
    private JTabbedPane mainTabbedPane;
    private JPanel mainCalculationPanel;
    private JPanel mainOrdersPanel;
    private JTable orderTable;
    private JButton addCalculationButton;
    private JPanel infoPanel6;
    private JTextField orderNumberTextPane;
    private JLabel label6;
    private JPanel orderTablePanel;
    private JPanel calculationTablePanel;
    private JTable calculationTable;
    private JPanel addCalculationPane;
    private JPanel calculationButtonPanel;
    private JButton deleteCalculationButton;
    private JButton saveCalculationButton;
    private JPanel orderButtonPanel;
    private JButton addOrderButton;
    private JButton deleteOrderButton;
    private JButton saveOrderButton;
    private JPanel dataPanel;
    private JTextField constructionAmountTextField;
    private JLabel labelMain;
    private JPanel mainMetalPanel;
    private JPanel metalSortTablePanel;
    private JTable metalSortTable;
    private JPanel metalSortPanel;
    private JComboBox metalSortComboBox;
    private JPanel mainCustomerPanel;
    private JPanel customerButtonPanel;
    private JButton addCustomerButton;
    private JButton deleteCustomerButton;
    private JTable customersTable;
    private JPanel customersTablePanel;
    private JButton saveCustomerButton;
    private JPanel infoPanel7;
    private JPanel rentPannel;
    private JCheckBox rentCheckBox;
    private Calculator calculator;
    private CostHumanTableModel costHumanTableModel;
    private CostMaterialsTableModel costMaterialsTableModel;
    private CostTotalTableModel costTotalTableModel;
    private MetalListTableModel metalListTableModel;
    private CalculationsTableModel calculationTableModel;
    private CustomersTableModel customersTableModel;
    private OrderTableModel orderTableModel;
    private ConstructionType constructionType = ConstructionType.Light;

    public MainForm(User user) throws HeadlessException {

        super("Калькулятор стоимости металлоконструкций версия 1.1");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createViewMenu(user));
        setJMenuBar(menuBar);

        calculator = new Calculator(constructionType);

        //tables
        costHumanTableModel = new CostHumanTableModel(calculator.getCalculatorList());
        costMaterialsTableModel = new CostMaterialsTableModel(calculator.getCalculatorList());
        costTotalTableModel = new CostTotalTableModel(calculator.getTotalCostCalculatorList());
        metalListTableModel = new MetalListTableModel(MetalSortList.getInstance().getMetalSortList());
        orderTableModel = new OrderTableModel(OrderList.getInstance().getMetalOrderList());
        calculationTableModel = new CalculationsTableModel(Calculations.getInstance().getMetalCalculationList());
        customersTableModel = new CustomersTableModel(CustomersList.getInstance().getCustomerList());

        costHumanTable = new JTable(costHumanTableModel);
        costHumanTable.getTableHeader().setReorderingAllowed(false);
        costHumanTable.setSelectionMode(SINGLE_SELECTION);
        costHumanTable.getColumnModel().getColumn(0).setMaxWidth(30);

        costMaterialsTable = new JTable(costMaterialsTableModel);
        costMaterialsTable.getTableHeader().setReorderingAllowed(false);
        costMaterialsTable.setSelectionMode(SINGLE_SELECTION);
        costMaterialsTable.getColumnModel().getColumn(0).setMaxWidth(30);

        costTotalTable = new JTable(costTotalTableModel);
        costTotalTable.getTableHeader().setReorderingAllowed(false);
        costTotalTable.setSelectionMode(SINGLE_SELECTION);
        costTotalTable.getColumnModel().getColumn(0).setMaxWidth(30);

        metalTable = new JTable(metalListTableModel);
        metalTable.getTableHeader().setReorderingAllowed(false);
        costTotalTable.setSelectionMode(SINGLE_SELECTION);

        orderTable = new JTable(orderTableModel);
        orderTable.getTableHeader().setReorderingAllowed(false);
        orderTable.setSelectionMode(SINGLE_SELECTION);
        orderTable.getColumnModel().getColumn(0).setMaxWidth(30);
        orderTable.getColumnModel().getColumn(2).setMaxWidth(160);
        orderTable.getColumnModel().getColumn(3).setMinWidth(250);
        for (int i = 4; i < 12; i++) {
            orderTable.getColumnModel().getColumn(i).setMaxWidth(140);
        }
        //Зададим сортировщиков в таблицу после того, как настроим там ИДшки и переход от индекса вью к модели
        RowSorter<TableModel> orderTableSorter = new TableRowSorter<>(orderTableModel);
        orderTable.setRowSorter(orderTableSorter);

        calculationTable = new JTable(calculationTableModel);
        calculationTable.getTableHeader().setReorderingAllowed(false);
        calculationTable.setSelectionMode(SINGLE_SELECTION);
        //Зададим сортировщиков в таблицу после того, как настроим там ИДшки и переход от индекса вью к модели
        RowSorter<TableModel> calculationTableSorter = new TableRowSorter<>(calculationTableModel);
        calculationTable.setRowSorter(calculationTableSorter);

        metalSortTable = new JTable(metalListTableModel);
        metalSortTable.getTableHeader().setReorderingAllowed(false);
        metalSortTable.setSelectionMode(SINGLE_SELECTION);

        customersTable = new JTable(customersTableModel);
        customersTable.getTableHeader().setReorderingAllowed(false);
        customersTable.setSelectionMode(SINGLE_SELECTION);

        //Редактор ячеек для таблицы заказов (переопределение)
        //Тип конструкций
        DefaultComboBoxModel<Customer> cbModel = new DefaultComboBoxModel<>();
        final List<Customer> conTypeValues = CustomersList.getInstance().getCustomerList();
        cbModel.addAll(conTypeValues);

        JComboBox<Customer> combo = new JComboBox<>(cbModel);
        combo.setPreferredSize(new Dimension(200, combo.getItemCount() * 40));
        DefaultCellEditor editor = new DefaultCellEditor(combo);
        orderTable.getColumnModel().getColumn(1).setCellEditor(editor);

        //Собирамем в скроллпэйны
        humanTablePanel.add(new JScrollPane(costHumanTable));
        materialTablePanel.add(new JScrollPane(costMaterialsTable));
        totalTablePanel.add(new JScrollPane(costTotalTable));
        metalHeadTablePanel.add(new JScrollPane(metalTable));
        orderTablePanel.add(new JScrollPane(orderTable));
        calculationTablePanel.add(new JScrollPane(calculationTable));
        metalSortTablePanel.add(new JScrollPane(metalSortTable));
        customersTablePanel.add(new JScrollPane(customersTable));

        //Для обновления итогов таблиц
        costHumanTableModel.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                System.out.println("UPDATED HUMAN!");
                totalHumanAmount.setText(String.format("%.2f%n", calculator.getTotalResult(CostType.Human)));
            }
        });

        costMaterialsTableModel.addTableModelListener(e -> {
            System.out.println("UPDATED MATERIALS!");
            totalMaterialsAmount.setText(String.format("%.2f%n", calculator.getTotalResult(CostType.Material)));
        });

        calculationTableModel.addTableModelListener(e -> System.out.println("UPDATED CALCUL!!!!"));

        // устанавливаем дефолнтые значения для полей формы
        lightRadioButton.setSelected(true);

        //обновляем данные калькулятора при изменении полей
        updatePlantPower();
        updateConstructionWeight();
        updateResult();

        setContentPane(mainPanel);
        //делаем максимального размера
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = dimension.width;
        int y = dimension.height;
        this.setSize(x, y);
        this.setLocation(0, 0);
        setExtendedState(MAXIMIZED_BOTH);
        setTabsAndPanes(user);
        setVisible(true);

        //Обработчики событий формы
        lightRadioButton.addActionListener(e -> {
            if (showConfirmDialog("Данные будут перезаполнены!")) {
                constructionType = ConstructionType.Light;
                calculator.setConstructionType(constructionType);
                updateResult();
            } else {
                heavyRadioButton.setSelected(true);
            }
        });

        heavyRadioButton.addActionListener(e -> {
            if (showConfirmDialog("Данные будут перезаполнены!")) {
                constructionType = ConstructionType.Heavy;
                calculator.setConstructionType(constructionType);
                updateResult();
            } else {
                lightRadioButton.setSelected(true);
            }
        });

        lightAmounttextField.addActionListener(e -> {
            calculator.setPlantPlanPower(Integer.parseInt(lightAmounttextField.getText()) + Integer.parseInt(heavyAmountTextField.getText()));
            updateResult();
        });

        lightAmounttextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                lightAmounttextField.getActionListeners()[0].actionPerformed(new ActionEvent(lightAmounttextField, Event.AT_TARGET, ""));
            }
        });

        heavyAmountTextField.addActionListener(e -> {
            calculator.setPlantPlanPower(Integer.parseInt(lightAmounttextField.getText()) + Integer.parseInt(heavyAmountTextField.getText()));
            updateResult();
        });

        heavyAmountTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                heavyAmountTextField.getActionListeners()[0].actionPerformed(new ActionEvent(lightAmounttextField, Event.AT_TARGET, ""));
            }
        });

        constructionAmountTextField.addActionListener(e -> updateConstructionWeight());

        constructionAmountTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                constructionAmountTextField.getActionListeners()[0].actionPerformed(new ActionEvent(constructionAmountTextField, Event.AT_TARGET, ""));
            }
        });

        //обработчики событий формы калькулятора
        addRowButton.addActionListener(e -> createDialogForm());

        deleteRowButton.addActionListener(e -> {
            int idx = costHumanTable.getSelectedRow();
            if (idx != -1) {
                //получаем ИД строки (она скрыта, поэтому обращаемся через модель)
                Object value = costHumanTable.getModel().getValueAt(costHumanTable.getSelectedRow(), 5);
                calculator.deleteFromCostList((int) value);
            }
            updateResult();
        });

        resetButton.addActionListener(e -> {
            if (showConfirmDialog("Очистить калькуляцию")) {
                calculator.deleteAll();
                updateResult();
            }
        });

        addRowButton1.addActionListener(e -> createDialogForm());

        deleteRowButton1.addActionListener(e -> {
            int idx = costMaterialsTable.getSelectedRow();
            if (idx != -1) {
                //получаем ИД строки (она скрыта, поэтому обращаемся через модель)
                Object value = costMaterialsTable.getModel().getValueAt(costMaterialsTable.getSelectedRow(), 5);
                calculator.deleteFromCostList((int) value);
            }
            updateResult();
        });

        testButton.addActionListener(e -> {
            List<Calculation> orderList = Calculations.getInstance().getMetalCalculationList();
            for (Calculation order : orderList) {
                System.out.println(order);
            }
            setColumnsWidth(orderTable);
        });

        addCalculationButton.addActionListener(e -> {
            try {
                Calculations.getInstance().addCalculation(new Calculation(0, Integer.parseInt(orderNumberTextPane.getText()),
                        (double) calculator.getConstructionWeight(), calculator.getResult(CostType.Metal),
                        calculator.getResult(CostType.Paint), calculator.getResult(CostType.Zinc),
                        calculator.getTotalResult(CostType.Human),
                        calculator.getResult(CostType.Delivery), calculator.getCostTotalResultAfterVAT(), " ",
                        calculator.getResult(CostType.Material)));
                updateResult();
                JOptionPane.showMessageDialog(this, "Калькуляция создана!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Некорректные данные!");
            }
        });

        deleteCalculationButton.addActionListener(e -> {
            if (showConfirmDialog("Удалить калькуляцию?")) {
                int idx = calculationTable.getSelectedRow();
                if (idx != -1) {
                    //получаем ИД строки (она скрыта, поэтому обращаемся через модель + приводим ИД после сортировки)
                    int idxReal = calculationTableSorter.convertRowIndexToModel(calculationTable.getSelectedRow());
                    Object value = calculationTable.getModel().getValueAt(idxReal, 11);
                    Calculations.getInstance().deleteCalculationById((int) value);
                    updateResult();
                }
            }
        });

        saveCalculationButton.addActionListener(e -> {
            Calculations.getInstance().saveCalculationListToDb();
            JOptionPane.showMessageDialog(this, "Данные сохранены!");
        });

        addOrderButton.addActionListener(e -> {
            OrderList.getInstance().addOrder(new Order());
            updateResult();
        });

        deleteOrderButton.addActionListener(e -> {
            if (showConfirmDialog("Удалить заказ?")) {
                int idx = orderTable.getSelectedRow();
                if (idx != -1) {
                    int idxReal = orderTableSorter.convertRowIndexToModel(orderTable.getSelectedRow());
                    Object value = orderTable.getModel().getValueAt(idxReal, 15);
                    OrderList.getInstance().deleteOrderBuId((int) value);
                    updateResult();
                }
            }
        });

        saveOrderButton.addActionListener(e -> {
            OrderList.getInstance().saveOrderListToDb();
            JOptionPane.showMessageDialog(this, "Данные сохранены!");
        });

        addCustomerButton.addActionListener(e -> {
            CustomersList.getInstance().addCustomer(new Customer(""));
            updateResult();
        });

        deleteCustomerButton.addActionListener(e -> {
            if (showConfirmDialog("Удалить заказчика?")) {

                int idx = customersTable.getSelectedRow();
                if (idx != -1) {
                    CustomersList.getInstance().deleteCustomer(idx);
                    updateResult();
                }
            }
        });

        saveCustomerButton.addActionListener(e -> CustomersList.getInstance().saveCustomerListToDb());

        rentCheckBox.addActionListener(e -> {
            if (rentCheckBox.isSelected()) {
                calculator.setUseRentAmount(true);
                updateResult();
            } else {
                calculator.setUseRentAmount(false);
                updateResult();
            }
        });
    }

    //Методы для обработки событий
    private void updatePlantPower() {
        calculator.setPlantPlanPower(Integer.parseInt(lightAmounttextField.getText()) + Integer.parseInt(heavyAmountTextField.getText()));
    }

    private void updateConstructionWeight() {
        try {
            calculator.setConstructionWeight(Integer.parseInt(constructionAmountTextField.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(MainForm.this, "Ошибка ввода. Введите целое значение!");
        }
    }

    ConstructionType getConstructionType() {
        return constructionType;
    }

    void addToList(Cost costToAdd) {
        int idx = costHumanTable.getSelectedRow();
        int idx1 = costMaterialsTable.getSelectedRow();

        if (idx != -1 || (idx1 != -1)) {
            if (costToAdd.getCostType() == CostType.Human) {
                calculator.addToCostList(idx + 1, costToAdd);
            }
            if (costToAdd.getCostType() != CostType.Human) {
                calculator.addToCostList(idx1 + 1 + costHumanTable.getRowCount(), costToAdd);
            }
        } else {
            if (costToAdd.getCostType() == CostType.Human) {
                calculator.addToCostList(costHumanTable.getRowCount(), costToAdd);
            }
            if (costToAdd.getCostType() != CostType.Human) {
                calculator.addToCostList(costHumanTable.getRowCount() + costMaterialsTable.getRowCount(), costToAdd);
            }
        }
        updateResult();
    }

    private void updateResult() {
        totalHumanAmount.setText(String.format("%.2f%n", calculator.getTotalResult(CostType.Human)));
        totalMaterialsAmount.setText(String.format("%.2f%n", calculator.getTotalResult(CostType.Material)));

        costHumanTableModel.updateData(calculator.getCalculatorList());
        costHumanTableModel.fireTableDataChanged();

        costMaterialsTableModel.updateData(calculator.getCalculatorList());
        costMaterialsTableModel.fireTableDataChanged();

        //обновление данных не используем, т.к. работаем с основным объектом
        costTotalTableModel.fireTableDataChanged();
        orderTableModel.fireTableDataChanged();
        calculationTableModel.fireTableDataChanged();
        customersTableModel.fireTableDataChanged();
        System.out.println("Result updated!");
    }

    private void setTabsAndPanes(User user) {

        List<AuthorizationConstants.Rule> rulesList = user.getRulesList();
        for (AuthorizationConstants.Rule rule : rulesList) {
            switch (rule) {
                case All: {
                    orderTablePanel.setVisible(true);
                    break;
                }
                case Calculation: {
                    mainTabbedPane.remove(mainCustomerPanel);
                    calculationTabbedPanel.remove(metalHeadTablePanel);
                    orderTablePanel.setVisible(false);
                    break;
                }
                case Orders: {
                    mainTabbedPane.remove(mainCustomerPanel);
                    calculationTabbedPanel.remove(metalHeadTablePanel);
                    orderTablePanel.setVisible(true);
                    break;
                }
                default: {
                    mainTabbedPane.remove(mainCustomerPanel);
                    calculationTabbedPanel.remove(metalHeadTablePanel);
                    orderTablePanel.setVisible(false);
                }
            }
        }
    }

    private void createDialogForm() {
        CostAddForm.getInstance(this);
    }

    private JMenu createFileMenu() {
        JMenu file = new JMenu("Файл");
        JMenuItem costListChange = new JMenuItem("Редактировать лист затрат");
        JMenuItem exit = new JMenuItem(new ExitAction());

        file.add(costListChange);
        file.addSeparator();
        file.add(exit);

        costListChange.addActionListener(arg0 -> new CostListForm().setVisible(true));
        return file;
    }

    private JMenu createViewMenu(User user) {

        List<AuthorizationConstants.Rule> rulesList = user.getRulesList();
        JMenu viewMenu = new JMenu("Вид");
        JCheckBoxMenuItem calc = new JCheckBoxMenuItem("Калькулятор");
        calc.setState(true);
        calc.addItemListener(e -> changeView(mainTabbedPane, mainCalculationPanel, e, mainTabbedPane.getTabCount()));

        JCheckBoxMenuItem sort = new JCheckBoxMenuItem("Сортамент");
        sort.setState(true);
        sort.addItemListener(e -> changeView(mainTabbedPane, mainMetalPanel, e, mainTabbedPane.getTabCount()));

        JCheckBoxMenuItem ord = new JCheckBoxMenuItem("Заказы");
        ord.setState(true);
        ord.addItemListener(e -> changeView(mainTabbedPane, mainOrdersPanel, e, mainTabbedPane.getTabCount()));

        viewMenu.add(calc);
        viewMenu.add(sort);
        viewMenu.add(ord);

        for (AuthorizationConstants.Rule rule : rulesList) {
            switch (rule) {
                case All: {

                    JCheckBoxMenuItem cust = new JCheckBoxMenuItem("Заказчики");
                    cust.setState(true);
                    cust.addItemListener(e -> changeView(mainTabbedPane, mainCustomerPanel, e, mainTabbedPane.getTabCount()));

                    viewMenu.add(cust);
                }
                case Calculation: {
                }
            }
        }
        return viewMenu;
    }

    private void changeView(JTabbedPane tabbedPane, Component component, ItemEvent e, int index) {
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            tabbedPane.remove(component);
        } else {
            tabbedPane.add(component, index);
        }
    }

    private boolean showConfirmDialog(String message) {
        int result = JOptionPane.showConfirmDialog(
                MainForm.this,
                message,
                "Подтверждение",
                JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

    // Для подгона столбцов по размеру содержимого
    private static void setColumnsWidth(JTable table) {
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int prefWidth;
        JTableHeader th = table.getTableHeader();
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            int prefWidthMax = 0;
            for (int j = 0; j < table.getRowCount(); j++) {
                try {
                    String s = table.getModel().getValueAt(j, i).toString();
                    prefWidth =
                            Math.round(
                                    (float) th.getFontMetrics(
                                            th.getFont()).getStringBounds(s,
                                            th.getGraphics()
                                    ).getWidth()
                            );
                    if (prefWidth > prefWidthMax) {
                        prefWidthMax = prefWidth;
                    }
                } catch (NullPointerException ignored) {

                }
            }
            column.setPreferredWidth(prefWidthMax + 10);
        }
    }

    class ExitAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        ExitAction() {
            putValue(NAME, "Выход");
        }

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
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
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setMinimumSize(new Dimension(200, 200));
        mainPanel.setPreferredSize(new Dimension(320, 200));
        mainTabbedPane = new JTabbedPane();
        mainTabbedPane.setName("");
        mainTabbedPane.setTabLayoutPolicy(0);
        mainTabbedPane.setTabPlacement(1);
        mainPanel.add(mainTabbedPane, BorderLayout.CENTER);
        mainTabbedPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION));
        mainOrdersPanel = new JPanel();
        mainOrdersPanel.setLayout(new GridBagLayout());
        mainOrdersPanel.setName("Заказы");
        mainOrdersPanel.setRequestFocusEnabled(true);
        mainOrdersPanel.setVisible(false);
        mainTabbedPane.addTab("Заказы", mainOrdersPanel);
        orderTablePanel = new JPanel();
        orderTablePanel.setLayout(new BorderLayout(0, 0));
        orderTablePanel.setMinimumSize(new Dimension(250, 400));
        orderTablePanel.setPreferredSize(new Dimension(250, 500));
        orderTablePanel.setVisible(false);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 500.0;
        gbc.weighty = 200.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainOrdersPanel.add(orderTablePanel, gbc);
        orderTable = new JTable();
        orderTable.setAutoResizeMode(2);
        orderTable.setAutoscrolls(true);
        orderTable.setIntercellSpacing(new Dimension(2, 2));
        orderTable.setMaximumSize(new Dimension(2147483647, 500));
        orderTable.setMinimumSize(new Dimension(30, 5000));
        orderTable.setPreferredSize(new Dimension(350, 32));
        orderTable.setRowHeight(20);
        orderTable.setRowMargin(2);
        orderTable.setSelectionForeground(new Color(-16512837));
        orderTablePanel.add(orderTable, BorderLayout.CENTER);
        orderButtonPanel = new JPanel();
        orderButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        orderTablePanel.add(orderButtonPanel, BorderLayout.SOUTH);
        addOrderButton = new JButton();
        addOrderButton.setText("Добавить заказ");
        orderButtonPanel.add(addOrderButton);
        deleteOrderButton = new JButton();
        deleteOrderButton.setText("Удалить заказ");
        orderButtonPanel.add(deleteOrderButton);
        saveOrderButton = new JButton();
        saveOrderButton.setText("Сохранить изменения");
        orderButtonPanel.add(saveOrderButton);
        calculationTablePanel = new JPanel();
        calculationTablePanel.setLayout(new BorderLayout(0, 0));
        calculationTablePanel.setMinimumSize(new Dimension(250, 300));
        calculationTablePanel.setPreferredSize(new Dimension(250, 300));
        calculationTablePanel.setRequestFocusEnabled(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 500.0;
        gbc.weighty = 200.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainOrdersPanel.add(calculationTablePanel, gbc);
        calculationTable = new JTable();
        calculationTable.setIntercellSpacing(new Dimension(2, 5));
        calculationTable.setRowHeight(20);
        calculationTable.setRowMargin(5);
        calculationTablePanel.add(calculationTable, BorderLayout.CENTER);
        calculationButtonPanel = new JPanel();
        calculationButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        calculationTablePanel.add(calculationButtonPanel, BorderLayout.SOUTH);
        deleteCalculationButton = new JButton();
        deleteCalculationButton.setLabel("Удалить калькуляцию");
        deleteCalculationButton.setMaximumSize(new Dimension(200, 40));
        deleteCalculationButton.setPreferredSize(new Dimension(190, 40));
        deleteCalculationButton.setText("Удалить калькуляцию");
        calculationButtonPanel.add(deleteCalculationButton);
        saveCalculationButton = new JButton();
        saveCalculationButton.setMaximumSize(new Dimension(200, 40));
        saveCalculationButton.setPreferredSize(new Dimension(190, 40));
        saveCalculationButton.setText("Сохранить изменения");
        calculationButtonPanel.add(saveCalculationButton);
        mainCalculationPanel = new JPanel();
        mainCalculationPanel.setLayout(new BorderLayout(0, 0));
        mainCalculationPanel.setName("Калькулятор");
        mainTabbedPane.addTab("Калькулятор", mainCalculationPanel);
        calculationTabbedPanel = new JTabbedPane();
        mainCalculationPanel.add(calculationTabbedPanel, BorderLayout.CENTER);
        dataPanel = new JPanel();
        dataPanel.setLayout(new GridBagLayout());
        dataPanel.setMaximumSize(new Dimension(800, 800));
        dataPanel.setMinimumSize(new Dimension(500, 500));
        calculationTabbedPanel.addTab("Параметры", dataPanel);
        infoPanel2 = new JPanel();
        infoPanel2.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 10));
        infoPanel2.setMinimumSize(new Dimension(229, 20));
        infoPanel2.setPreferredSize(new Dimension(200, 35));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        dataPanel.add(infoPanel2, gbc);
        constructionTypeLabel = new JLabel();
        constructionTypeLabel.setHorizontalAlignment(0);
        constructionTypeLabel.setHorizontalTextPosition(11);
        constructionTypeLabel.setText("Тип конструкций");
        infoPanel2.add(constructionTypeLabel);
        infoPanel4 = new JPanel();
        infoPanel4.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        infoPanel4.setMinimumSize(new Dimension(327, 20));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 10, 0);
        dataPanel.add(infoPanel4, gbc);
        label4 = new JLabel();
        label4.setPreferredSize(new Dimension(100, 16));
        label4.setText("Загрузка ЛК, т");
        infoPanel4.add(label4);
        lightAmounttextField = new JTextField();
        lightAmounttextField.setHorizontalAlignment(4);
        lightAmounttextField.setMaximumSize(new Dimension(250, 250));
        lightAmounttextField.setMinimumSize(new Dimension(25, 30));
        lightAmounttextField.setPreferredSize(new Dimension(100, 35));
        lightAmounttextField.setText("500");
        infoPanel4.add(lightAmounttextField);
        infoPanel1 = new JPanel();
        infoPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        infoPanel1.setMinimumSize(new Dimension(494, 20));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 5, 10, 5);
        dataPanel.add(infoPanel1, gbc);
        constructionAmountLabel = new JLabel();
        constructionAmountLabel.setAlignmentY(0.0f);
        constructionAmountLabel.setAutoscrolls(false);
        constructionAmountLabel.setHorizontalAlignment(2);
        constructionAmountLabel.setHorizontalTextPosition(2);
        constructionAmountLabel.setMaximumSize(new Dimension(94, 16));
        constructionAmountLabel.setMinimumSize(new Dimension(94, 16));
        constructionAmountLabel.setOpaque(false);
        constructionAmountLabel.setPreferredSize(new Dimension(100, 16));
        constructionAmountLabel.setRequestFocusEnabled(false);
        constructionAmountLabel.setText("Масса заказа, т");
        constructionAmountLabel.setVisible(true);
        infoPanel1.add(constructionAmountLabel);
        constructionAmountTextField = new JTextField();
        constructionAmountTextField.setHorizontalAlignment(4);
        constructionAmountTextField.setMaximumSize(new Dimension(250, 250));
        constructionAmountTextField.setMinimumSize(new Dimension(25, 30));
        constructionAmountTextField.setPreferredSize(new Dimension(100, 35));
        constructionAmountTextField.setText("550");
        infoPanel1.add(constructionAmountTextField);
        infoPanel5 = new JPanel();
        infoPanel5.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        infoPanel5.setMinimumSize(new Dimension(326, 20));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        dataPanel.add(infoPanel5, gbc);
        label5 = new JLabel();
        label5.setPreferredSize(new Dimension(100, 16));
        label5.setText("Загрузка ТК, т");
        infoPanel5.add(label5);
        heavyAmountTextField = new JTextField();
        heavyAmountTextField.setHorizontalAlignment(4);
        heavyAmountTextField.setMaximumSize(new Dimension(250, 250));
        heavyAmountTextField.setMinimumSize(new Dimension(25, 50));
        heavyAmountTextField.setPreferredSize(new Dimension(100, 35));
        heavyAmountTextField.setText("1000");
        infoPanel5.add(heavyAmountTextField);
        infoPanel6 = new JPanel();
        infoPanel6.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        infoPanel6.setMinimumSize(new Dimension(317, 20));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        dataPanel.add(infoPanel6, gbc);
        label6 = new JLabel();
        label6.setPreferredSize(new Dimension(100, 16));
        label6.setText("Номер КМД");
        infoPanel6.add(label6);
        orderNumberTextPane = new JTextField();
        orderNumberTextPane.setHorizontalAlignment(4);
        orderNumberTextPane.setInheritsPopupMenu(false);
        orderNumberTextPane.setMargin(new Insets(2, 5, 2, 5));
        orderNumberTextPane.setMaximumSize(new Dimension(250, 200));
        orderNumberTextPane.setMinimumSize(new Dimension(250, 200));
        orderNumberTextPane.setPreferredSize(new Dimension(100, 35));
        orderNumberTextPane.setText("");
        infoPanel6.add(orderNumberTextPane);
        labelMain = new JLabel();
        labelMain.setHorizontalAlignment(2);
        labelMain.setHorizontalTextPosition(0);
        labelMain.setPreferredSize(new Dimension(200, 20));
        labelMain.setText("Введите параметры заказа:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        dataPanel.add(labelMain, gbc);
        infoPanel7 = new JPanel();
        infoPanel7.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        infoPanel7.setPreferredSize(new Dimension(200, 35));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        dataPanel.add(infoPanel7, gbc);
        lightRadioButton = new JRadioButton();
        lightRadioButton.setHorizontalAlignment(0);
        lightRadioButton.setHorizontalTextPosition(11);
        lightRadioButton.setPreferredSize(new Dimension(80, 16));
        lightRadioButton.setText("Лёгкие");
        infoPanel7.add(lightRadioButton);
        heavyRadioButton = new JRadioButton();
        heavyRadioButton.setHorizontalAlignment(0);
        heavyRadioButton.setPreferredSize(new Dimension(80, 16));
        heavyRadioButton.setText("Тяжелые");
        heavyRadioButton.setVerticalTextPosition(0);
        infoPanel7.add(heavyRadioButton);
        rentPannel = new JPanel();
        rentPannel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        dataPanel.add(rentPannel, gbc);
        rentCheckBox = new JCheckBox();
        rentCheckBox.setText("Учитывать аренду");
        rentPannel.add(rentCheckBox);
        humanTablePanel = new JPanel();
        humanTablePanel.setLayout(new BorderLayout(0, 0));
        calculationTabbedPanel.addTab("Трудозатраты", humanTablePanel);
        costHumanTable = new JTable();
        costHumanTable.setAutoCreateRowSorter(false);
        costHumanTable.setInheritsPopupMenu(false);
        costHumanTable.setIntercellSpacing(new Dimension(5, 5));
        costHumanTable.setMaximumSize(new Dimension(2500, 300));
        costHumanTable.setPreferredScrollableViewportSize(new Dimension(450, 400));
        costHumanTable.setPreferredSize(new Dimension(150, 400));
        costHumanTable.setRequestFocusEnabled(false);
        costHumanTable.setRowHeight(40);
        costHumanTable.setRowMargin(5);
        costHumanTable.setSelectionBackground(new Color(-11457861));
        costHumanTable.setSelectionForeground(new Color(-11457861));
        costHumanTable.setShowHorizontalLines(true);
        costHumanTable.putClientProperty("JTable.autoStartsEdit", Boolean.TRUE);
        humanTablePanel.add(costHumanTable, BorderLayout.CENTER);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        humanTablePanel.add(buttonPanel, BorderLayout.SOUTH);
        addRowButton = new JButton();
        addRowButton.setText("Добавить затрату");
        buttonPanel.add(addRowButton);
        deleteRowButton = new JButton();
        deleteRowButton.setText("Удалить затрату");
        buttonPanel.add(deleteRowButton);
        totalHumanAmountText = new JLabel();
        totalHumanAmountText.setText("Итого трудозатраты");
        buttonPanel.add(totalHumanAmountText);
        humanAmountPanel = new JPanel();
        humanAmountPanel.setLayout(new BorderLayout(0, 0));
        buttonPanel.add(humanAmountPanel);
        totalHumanAmount = new JLabel();
        totalHumanAmount.setHorizontalAlignment(0);
        totalHumanAmount.setHorizontalTextPosition(0);
        totalHumanAmount.setMinimumSize(new Dimension(80, 16));
        totalHumanAmount.setPreferredSize(new Dimension(50, 20));
        totalHumanAmount.setText("Label");
        totalHumanAmount.setVerticalAlignment(0);
        humanAmountPanel.add(totalHumanAmount, BorderLayout.CENTER);
        materialTablePanel = new JPanel();
        materialTablePanel.setLayout(new BorderLayout(0, 0));
        calculationTabbedPanel.addTab("Материалы", materialTablePanel);
        costMaterialsTable = new JTable();
        costMaterialsTable.setDragEnabled(false);
        costMaterialsTable.setIntercellSpacing(new Dimension(0, 5));
        costMaterialsTable.setOpaque(true);
        costMaterialsTable.setRowHeight(20);
        costMaterialsTable.setRowMargin(5);
        costMaterialsTable.setUpdateSelectionOnSort(false);
        costMaterialsTable.setVerifyInputWhenFocusTarget(false);
        costMaterialsTable.putClientProperty("JTable.autoStartsEdit", Boolean.TRUE);
        materialTablePanel.add(costMaterialsTable, BorderLayout.CENTER);
        buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        materialTablePanel.add(buttonPanel1, BorderLayout.SOUTH);
        addRowButton1 = new JButton();
        addRowButton1.setText("Добавить затрату");
        buttonPanel1.add(addRowButton1);
        deleteRowButton1 = new JButton();
        deleteRowButton1.setText("Удалить затрату");
        buttonPanel1.add(deleteRowButton1);
        totalMaterialsAmountText = new JLabel();
        totalMaterialsAmountText.setText("Итого материалы");
        buttonPanel1.add(totalMaterialsAmountText);
        totalMaterialsAmount = new JTextField();
        totalMaterialsAmount.setEditable(false);
        totalMaterialsAmount.setHorizontalAlignment(0);
        totalMaterialsAmount.setMinimumSize(new Dimension(40, 30));
        totalMaterialsAmount.setOpaque(false);
        totalMaterialsAmount.setPreferredSize(new Dimension(50, 30));
        buttonPanel1.add(totalMaterialsAmount);
        metalHeadTablePanel = new JPanel();
        metalHeadTablePanel.setLayout(new BorderLayout(0, 0));
        calculationTabbedPanel.addTab("Металл", metalHeadTablePanel);
        final JScrollPane scrollPane1 = new JScrollPane();
        metalHeadTablePanel.add(scrollPane1, BorderLayout.CENTER);
        metalTable = new JTable();
        metalTable.setRequestFocusEnabled(false);
        metalTable.setRowHeight(20);
        metalTable.setRowMargin(5);
        scrollPane1.setViewportView(metalTable);
        totalTablePanel = new JPanel();
        totalTablePanel.setLayout(new BorderLayout(0, 0));
        calculationTabbedPanel.addTab("Итого", totalTablePanel);
        costTotalTable = new JTable();
        costTotalTable.setRowHeight(20);
        costTotalTable.setRowMargin(5);
        totalTablePanel.add(costTotalTable, BorderLayout.CENTER);
        addCalculationPane = new JPanel();
        addCalculationPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        mainCalculationPanel.add(addCalculationPane, BorderLayout.SOUTH);
        addCalculationButton = new JButton();
        addCalculationButton.setMinimumSize(new Dimension(142, 25));
        addCalculationButton.setPreferredSize(new Dimension(150, 40));
        addCalculationButton.setText("Новая калькуляция");
        addCalculationPane.add(addCalculationButton);
        resetButton = new JButton();
        resetButton.setPreferredSize(new Dimension(100, 40));
        resetButton.setText("Очистить");
        addCalculationPane.add(resetButton);
        testButton = new JButton();
        testButton.setLabel("Тест");
        testButton.setPreferredSize(new Dimension(100, 40));
        testButton.setText("Тест");
        addCalculationPane.add(testButton);
        mainMetalPanel = new JPanel();
        mainMetalPanel.setLayout(new BorderLayout(0, 0));
        mainMetalPanel.setName("Сортамент");
        mainTabbedPane.addTab("Сортамент", mainMetalPanel);
        metalSortTablePanel = new JPanel();
        metalSortTablePanel.setLayout(new BorderLayout(0, 0));
        mainMetalPanel.add(metalSortTablePanel, BorderLayout.CENTER);
        metalSortTable = new JTable();
        metalSortTablePanel.add(metalSortTable, BorderLayout.CENTER);
        metalSortPanel = new JPanel();
        metalSortPanel.setLayout(new BorderLayout(0, 0));
        metalSortTablePanel.add(metalSortPanel, BorderLayout.NORTH);
        metalSortComboBox = new JComboBox();
        metalSortPanel.add(metalSortComboBox, BorderLayout.CENTER);
        mainCustomerPanel = new JPanel();
        mainCustomerPanel.setLayout(new BorderLayout(0, 0));
        mainCustomerPanel.setName("Заказчики");
        mainTabbedPane.addTab("Заказчики", mainCustomerPanel);
        customersTablePanel = new JPanel();
        customersTablePanel.setLayout(new BorderLayout(0, 0));
        mainCustomerPanel.add(customersTablePanel, BorderLayout.CENTER);
        customersTable = new JTable();
        customersTablePanel.add(customersTable, BorderLayout.CENTER);
        customerButtonPanel = new JPanel();
        customerButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        customersTablePanel.add(customerButtonPanel, BorderLayout.SOUTH);
        addCustomerButton = new JButton();
        addCustomerButton.setText("Добавить заказчика");
        customerButtonPanel.add(addCustomerButton);
        deleteCustomerButton = new JButton();
        deleteCustomerButton.setText("Удалить заказчика");
        customerButtonPanel.add(deleteCustomerButton);
        saveCustomerButton = new JButton();
        saveCustomerButton.setText("Сохранить изменения");
        customerButtonPanel.add(saveCustomerButton);
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(lightRadioButton);
        buttonGroup.add(heavyRadioButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}

