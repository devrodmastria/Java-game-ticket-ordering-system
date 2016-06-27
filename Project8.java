/*Programmer: Rodrigo Mesquita
Project 8
Date: 04/25/2016
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.text.NumberFormat;

public class Project8 extends JFrame{

    
    private CenterPanel centerPanel;
    private SeatSelection seatSelectionPanel;
    private QuantitySlider quantitySliderPanel;
    private JLabel instruction, greeting;
    private JPanel buttonPanel, titlePanel;
    private JButton addToCart;
    private JButton clearCart;
    private JButton exit;
    private String outputString;
    
    
   public Project8(){
   
      setTitle("Project 8");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(false);
      setLayout(new BorderLayout());
      setBackground(Color.white);
      
      quantitySliderPanel = new QuantitySlider();
      seatSelectionPanel = new SeatSelection();
      centerPanel = new CenterPanel();
        
      titlePanel = new JPanel();
      Font myFont = new Font("Serif", Font.BOLD, 40);
      greeting = new JLabel("Tigers Tickets");
      greeting.setFont(myFont);
      titlePanel.setBackground(Color.white);
      Icon imageIcon = new ImageIcon(getClass().getResource("icon.png"));
      greeting.setIcon(imageIcon);
      titlePanel.add(greeting);
       
      buildButtonPanel();
      
      add(centerPanel, BorderLayout.CENTER);
      add(titlePanel, BorderLayout.NORTH);
      add(buttonPanel, BorderLayout.EAST);
      add(seatSelectionPanel, BorderLayout.WEST);
      add(quantitySliderPanel, BorderLayout.SOUTH);
      
      pack();
      setVisible(true);
   }
   
   private void buildButtonPanel(){
   
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
      
        GridBagConstraints gbc = new GridBagConstraints();

        Font myFont = new Font("Arial", Font.BOLD, 12);
        instruction = new JLabel(" Click to complete ");
        instruction.setFont(myFont);
        instruction.setBackground(Color.lightGray);
        instruction.setOpaque(true);      
        
        addToCart = new JButton("Add to cart");
        clearCart = new JButton("Clear cart");
        exit = new JButton("Exit");

        addToCart.setToolTipText("Alt + A");
        clearCart.setToolTipText("Alt + C");
        exit.setToolTipText("Alt + E");

        addToCart.setMnemonic(KeyEvent.VK_A);
        clearCart.setMnemonic(KeyEvent.VK_C);
        exit.setMnemonic(KeyEvent.VK_E);
        
        gbc.gridy = 0;
        gbc.fill = 1;
        gbc.weighty = 1;
        buttonPanel.add(instruction, gbc);

        gbc.gridy = 1;
        gbc.fill = 1;
        gbc.weighty = 1;
        buttonPanel.add(addToCart, gbc);

        gbc.gridy = 2;
        gbc.fill = 1;      
        gbc.weighty = 1;
        buttonPanel.add(clearCart, gbc);

        gbc.gridy = 3;
        gbc.fill = 1;
        gbc.weighty = 1;
        buttonPanel.add(exit, gbc);

        ButtonListener handler = new ButtonListener();

        addToCart.addActionListener(handler);
        clearCart.addActionListener(handler);
        exit.addActionListener(handler);
        
   }
      
   
   private class ButtonListener implements ActionListener
   {
      
      public void actionPerformed (ActionEvent e)
      {
         if(e.getSource() == exit)
            System.exit(0);
            
         else if(e.getSource() == addToCart){
             
            int souvenirPriceTotal = 0;
            double[] souvenirP = centerPanel.getSouvCostList();
            for(int y = 0; y < souvenirP.length;y++){
                souvenirPriceTotal += souvenirP[y];
            }
            
            String newList = "";
            String[] souvenir = centerPanel.getSouvenirList();
            for(int x = 0; x < souvenir.length; x++){
                newList += souvenir[x] + " $" + souvenirP[x] + "\n";
            }
            
            NumberFormat moneyfmt = NumberFormat.getCurrencyInstance(Locale.US);
            int numPackages = quantitySliderPanel.getPackages();
            int seatPrice = seatSelectionPanel.getSeatPrice();
            int subtotal = seatPrice + souvenirPriceTotal;
            int totalCost = numPackages * subtotal;
            
            String myMeals = centerPanel.getMeal();
            String seat = seatSelectionPanel.getSeat();
            
            JTextArea textArea = new JTextArea(outputString);
            textArea.setBackground(Color.orange);
            
            textArea.append("Team: Tigers\n");
            textArea.append("Meal: " + myMeals + "\n\n");
            textArea.append("Seat ordered:\n" + seat + " " + moneyfmt.format(seatPrice) + "\n\n");
            textArea.append("Items ordered:\n" + newList + "\n");
            textArea.append("Subtotal: " + moneyfmt.format(subtotal) + "\n\n");
            textArea.append("Packages ordered: " + numPackages + "\n"); 
            textArea.append("Total cost: " + moneyfmt.format(totalCost) + "\n");

            ImageIcon ic = new ImageIcon(getClass().getResource("icon.png")); 
            JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "Your Order",
                    JOptionPane.OK_OPTION, ic);
            
         }
         
        else if (e.getSource() == clearCart ){
            
            centerPanel.clearSouvList(); // both lists depend on the same variable indexSouv which is reset in this method

            quantitySliderPanel.setPackages(0);

            seatSelectionPanel.setSeat("No seat selected", 0); // resets both seat type and price
            
            centerPanel.setMeal("No meal selected");

            outputString = "";
            quantitySliderPanel.clear();
            seatSelectionPanel.clear();

            centerPanel.clear();
            
        }
      
      }
      
   }
   
   public static void main(String[]args){
      new Project8();
   }
}