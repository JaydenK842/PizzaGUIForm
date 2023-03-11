import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.Vector;

public class PizzaGUIFrame extends JFrame {
    Vector<JRadioButton> radioButtons = new Vector<>();
    Vector<JCheckBox> checkBoxes = new Vector<>();

    JFrame frame;
    JPanel main;
    JPanel test = new JPanel();

    JPanel crust;
    JRadioButton thin;
    JRadioButton regular;
    JRadioButton deepDish;
    ButtonGroup group;

    JPanel size;
    JComboBox sizes;

    JPanel toppings;
    JCheckBox peperoni;
    JCheckBox sausage;
    JCheckBox mushrooms;
    JCheckBox olives;
    JCheckBox peppers;
    JCheckBox pineapple;

    JPanel receipt;
    JTextArea text;
    JScrollPane scroll;

    JPanel options;
    JButton order;
    JButton clear;
    JButton quit;

    public PizzaGUIFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        frame = new JFrame();
        main = new JPanel();

        //Gets the dimension of the user's screen
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        //Centers the frame in the middle of the user's screen
        frame.setSize(screenWidth / 2, screenHeight / 2);
        frame.setLocation(screenWidth / 4, screenHeight / 4);

        //Makes a border layout
        main.setLayout(new BorderLayout());

        //Titles the frame
        frame.setTitle("Pizza Order Form");

        //Creates the crust choice panel, then sets its location and size
        crustChoice();
        crust.setBounds((frame.getWidth() - 500) / 2,10, 350, 60);
        main.add(crust);

        //Creates the size choice panel, then sets its location and size
        pizzaSize();
        size.setBounds(((frame.getWidth() - 500) / 2) + 360, 10, 150, 60);
        main.add(size);

        //Creates the topping choice panel, then sets its location and size
        toppingChoice();
        toppings.setBounds((frame.getWidth() - 500)/ 2, 80, 510, 60);
        main.add(toppings);

        //Creates the receipt panel, then sets its location and size
        receiptPanel();
        receipt.setBounds((frame.getWidth() - 525) / 2, 150, 550, 170);
        main.add(receipt);

        //For some reason, if this isn't here, my bounds are completely messed up
        main.add(test);

        //Creates the option choices panel, then sets its location and size
        optionChoices();
        options.setBounds((frame.getWidth() - 500) / 2, 360, 300, 50);
        main.add(options, BorderLayout.PAGE_END);

        //Adds the main panel to the frame
        frame.add(main);

        //Gives it a default close and sets the frame to be visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void crustChoice() {
        crust = new JPanel();
        //Gives the panel a border with the title Crust
        crust.setBorder(new TitledBorder(new EtchedBorder(), "Crust"));

        //Creates a button for thin crust
        thin = new JRadioButton("Thin");
        radioButtons.add(thin);
        crust.add(thin);

        //Creates a button for regular crust
        regular = new JRadioButton("Regular");
        radioButtons.add(regular);
        crust.add(regular);

        //Creates a button for deep-dish crust
        deepDish = new JRadioButton("Deep-Dish - +$1.00");
        radioButtons.add(deepDish);
        crust.add(deepDish);

        //Assembles the three buttons into a group
        group = new ButtonGroup();
        group.add(thin);
        group.add(regular);
        group.add(deepDish);
    }

    public void pizzaSize() {
        size = new JPanel();
        //Gives the panel a border with the title Size
        size.setBorder(new TitledBorder(new EtchedBorder(), "Size"));

        //Creates a combo box with the different pizza sizes
        sizes = new JComboBox();
        sizes.addItem("Small: $8.00");
        sizes.addItem("Medium: $12.00");
        sizes.addItem("Large: $16.00");
        sizes.addItem("Super: $20.00");
        size.add(sizes);
    }

    private void toppingChoice() {
        toppings = new JPanel();
        //Gives the panel a border with the title Toppings - $1.00 per
        toppings.setBorder(new TitledBorder(new EtchedBorder(), "Toppings - $1.00 per"));

        //Creates a checkbox for peperoni
        peperoni = new JCheckBox("Peperoni");
        toppings.add(peperoni);
        checkBoxes.add(peperoni);

        //Creates a checkbox for sausage
        sausage = new JCheckBox("Sausage");
        toppings.add(sausage);
        checkBoxes.add(sausage);

        //Creates a checkbox for mushrooms
        mushrooms = new JCheckBox("Mushrooms");
        toppings.add(mushrooms);
        checkBoxes.add(mushrooms);

        //Creates a checkbox for olives
        olives = new JCheckBox("Olives");
        toppings.add(olives);
        checkBoxes.add(olives);

        //Creates a checkbox for peppers
        peppers = new JCheckBox("Peppers");
        toppings.add(peppers);
        checkBoxes.add(peppers);

        //Creates a checkbox for pineapple
        pineapple = new JCheckBox("Pineapple");
        toppings.add(pineapple);
        checkBoxes.add(pineapple);
    }

    public void receiptPanel() {
        receipt = new JPanel();

        //Creates text area and makes it uneditable
        text = new JTextArea(10, 50);
        text.setEditable(false);

        //Adds the text box to a scroll pane
        scroll = new JScrollPane(text);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        receipt.add(scroll);
    }

    private void optionChoices() {
        options = new JPanel();

        //Creates, formats and adds an action upon click to the order button
        order = new JButton("Order");
        order.setFocusable(false);
        order.addActionListener((ActionEvent ae) -> printReceipt(text, sizes, radioButtons, checkBoxes));
        order.setPreferredSize(new Dimension(75, 50));
        order.setBackground(Color.WHITE);
        order.setFont(new Font("Arial", Font.BOLD, 10));
        options.add(order);

        //Creates, formats and adds an action upon click to the clear button
        clear = new JButton("Clear");
        clear.setFocusable(false);
        clear.addActionListener((ActionEvent ae) -> reset(group, sizes, checkBoxes));
        clear.setPreferredSize(new Dimension(75, 50));
        clear.setBackground(Color.RED);
        clear.setFont(new Font("Arial", Font.BOLD, 10));
        options.add(clear);

        //Creates, formats and adds an action upon click to the quit button
        quit = new JButton("Quit");
        quit.setFocusable(false);
        quit.addActionListener((ActionEvent ae) -> quit());
        quit.setPreferredSize(new Dimension(75, 50));
        quit.setBackground(Color.GRAY);
        quit.setFont(new Font("Arial", Font.BOLD, 10));
        options.add(quit);
    }

    private void reset(ButtonGroup group, JComboBox comboBox, Vector<JCheckBox> checkBoxes) {
        //Resets the radio button group
        group.clearSelection();

        //Sets the combo box to its original value
        comboBox.setSelectedIndex(0);

        //For every checkbox button, it unselects it
        for (JCheckBox check : checkBoxes) {
            check.setSelected(false);
        }
    }

    private void printReceipt(JTextArea text, JComboBox comboBox, Vector<JRadioButton> radioButtons, Vector<JCheckBox> checkBoxes) {
        //Creates a pattern for decimals to round after 2 places
        DecimalFormat round = new DecimalFormat("0.00");

        //Variable declaration
        double sizeChoice, sizePrice, crustPrice = 0, typePrice, itr = 0, toppingPrice = 0, subTotal, tax = 0.07, taxPrice, total;
        String ingredients = "\t";

        //Makes the receipt look better
        text.append("\t===============================================\n");

        //Adds to the receipt what size you got, and adds to the price
        sizeChoice = comboBox.getSelectedIndex();
        if (sizeChoice == 0) {
            text.append("\tSmall ");
            sizePrice = 8;
        } else if (sizeChoice == 1) {
            text.append("\tMedium ");
            sizePrice = 12;
        } else if (sizeChoice == 2) {
            text.append("\tLarge ");
            sizePrice = 16;
        } else {
            text.append("\tSuper ");
            sizePrice = 20;
        }

        //Adds to the receipt what crust you got an adds if there is an additional cost
        for (JRadioButton button : radioButtons) {
            if (button.isSelected()) {
                if (itr == 0) {
                    text.append("thin crust");
                } else if (itr == 1) {
                    text.append("regular crust");
                } else {
                    text.append("deep-dish");
                    crustPrice = 1;
                }
            }
            itr++;
        }

        //Adds the price to the receipt
        typePrice = crustPrice + sizePrice;
        text.append("\t\t$" + round.format(typePrice) + "\n");

        //Creates a string that lists all the toppings chosen, and adds to the price
        for (JCheckBox box : checkBoxes) {
            if (box.isSelected()) {
                ingredients += box.getText() + ", ";
                toppingPrice++;
            }
        }

        //Removes the final comma because there is no ingredient after the last
        ingredients = ingredients.substring(0, ingredients.length() - 2);

        //Adds the price to the receipt and all the ingredients
        text.append("\tIngredients:\t\t\t$" + round.format(toppingPrice) + "\n");
        text.append(ingredients + "\n\n");

        //Adds the subtotal to the receipt
        subTotal = sizePrice + toppingPrice;
        text.append("\tSub-total:\t\t\t$" + round.format(subTotal) + "\n");

        //Adds the tax price to the receipt
        taxPrice = (sizePrice + toppingPrice) * tax;
        text.append("\tTax:\t\t\t$" + round.format(taxPrice) + "\n");

        //Makes the receipt look better
        text.append("\t------------------------------------------------------------------------------------\n");

        //Calculates the total and adds it to the receipt
        total = subTotal + taxPrice;
        text.append("\tTotal:\t\t\t$" + round.format(total) + "\n");

        //Makes the receipt look better
        text.append("\t===============================================\n");
    }
    private void quit() {
        //Creates a message popup if the user clicks quit
        int input = JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?", "Selection", JOptionPane.YES_NO_OPTION);

        //If they say yes, it will exit the GUI
        if (input == 0) {
            System.exit(0);
        }
    }
}