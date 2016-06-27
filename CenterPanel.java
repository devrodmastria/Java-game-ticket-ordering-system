/*Programmer: Rodrigo Mesquita
Project 8 - Center Panel
Date: 04/25/2016
*/

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class CenterPanel extends JPanel{
   
   
    private JLabel mealInstruction, souvenirInstruction;
    private JPanel centerPanel, comboPanel, souvenirPanel;
    private String mealPlace;
    private Icon[] imageIcon = new ImageIcon[2];
    private JComboBox comboBox;
    private int myMealIndex;
    private String[] meal = {"McDonald's", "Papa John's"};
    private JList souvenirList;
    private JScrollPane scrollPane;
    private String[] allSouvenirsArray = { 
      "Mug", "Cap", "Tee shirt", "Sweat shirt", 
      "Pennant", "Mini stick", "Bobblehead", 
      "Paper bag", "Foam paw", "Thunderstix"};
    private int [] souvIndex;
    private double [] souvenirPrices = {2.0,10.0,15.0,25.0,3.0,5.0,9.0,8.0,12.0,6.0};

   
   public CenterPanel(){
       
        setLayout(new GridBagLayout());
        setBackground(Color.ORANGE);
             
        GridBagConstraints gbc = new GridBagConstraints();

        mealInstruction = new JLabel(" Free Meal From:");
        mealInstruction.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode("#2F4172")));
        
        souvenirInstruction = new JLabel(" Select Souvenirs:");
        souvenirInstruction.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.decode("#2F4172")));
        
        Font myFont = new Font("Arial", Font.ITALIC, 24);
      
        souvenirInstruction.setFont(myFont);
        souvenirInstruction.setBackground(Color.decode("#7986AC"));
        souvenirInstruction.setOpaque(true);
        
        mealInstruction.setFont(myFont);
        mealInstruction.setBackground(Color.decode("#7986AC"));
        mealInstruction.setOpaque(true);
        
        
        
        comboPanel = new JPanel();
        comboPanel.setLayout(new BorderLayout());
        comboPanel.setBackground(Color.white);
        imageIcon[0] = new ImageIcon(getClass().getResource("mcd.png"));
        imageIcon[1] = new ImageIcon(getClass().getResource("papas.jpg"));
        comboBox = new JComboBox(imageIcon);
        comboBox.addActionListener(new ComboBoxListener());
        comboPanel.add(comboBox);
        
        souvenirPanel = new JPanel();
        souvenirPanel.setLayout(new BorderLayout());
        souvenirPanel.setBackground(Color.white);
        myFont = new Font("Serif", Font.BOLD, 16);

        souvenirList = new JList(allSouvenirsArray);
        souvenirList.setVisibleRowCount(4);
        souvenirList.setFixedCellHeight(25);
        souvenirList.setFont(myFont);

        scrollPane = new JScrollPane(souvenirList);

        souvenirList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        souvenirList.addListSelectionListener(new ListListener());
        souvenirPanel.add(scrollPane);
        
        
        
        int width = 40;
        
        gbc.gridx = 0;
        gbc.gridy = 1;   
        gbc.fill = 1;
        gbc.ipady = 72;
        gbc.ipadx = width;
        add(comboPanel,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;   
        gbc.fill = 1;
        gbc.ipadx = width;
        add(souvenirPanel,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;   
        gbc.ipady = 155;
        add(souvenirInstruction,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 0;  
        add(mealInstruction,gbc);
        
        
        
   }
   
      private class ComboBoxListener implements ActionListener{
    
       public void actionPerformed(ActionEvent e){
           
           myMealIndex = comboBox.getSelectedIndex();
       
       }
    
    }
    
        private class ListListener implements ListSelectionListener{
   
      public void valueChanged(ListSelectionEvent e){
      
            souvIndex = souvenirList.getSelectedIndices();
         
      }
      
  }
  
  public String getMeal(){
       
       mealPlace = meal[myMealIndex];
       return mealPlace;
   }
   
   public void setMeal(String meal){
       mealPlace = meal;
   }
   
   public double[] getSouvCostList(){
       
        if(souvIndex != null){
            int listSize = souvIndex.length;
              double[] newList = new double[listSize];
              for(int i = 0; i < listSize; i++) {
                       newList[i] = souvenirPrices[souvIndex[i]];
              }

               return newList;
        }else{
            double []newList = {0.0};
            return newList;
        }
    
   }
    
    public void clearSouvList(){
        souvIndex = null;
    }
   
    public String[] getSouvenirList(){
        if(souvIndex != null){
            int listSize = souvIndex.length;
             String[] newList = new String[listSize];
             for(int i = 0; i < listSize; i++) {
                  newList[i] = souvenirList.getModel().getElementAt(souvIndex[i]).toString();
            }

              return newList;
        }else{
            String[]newList = {"No souvenir selected"};
            return newList;
        }
        
    }
  
     public void clear(){
         
         // Clear Souvenir List and data
          souvenirList.clearSelection();
          souvIndex = null;
  
          // Clear combo box and meal option
           setMeal(null);
         comboBox.setSelectedIndex(0);
     
     }
  
}