import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

/**
 * Creates the main screen that allows the users to select their settings
 */

public class GUI extends JFrame
{
	JLabel labelTitle,labelTitle2 ,labelNumCounts, labelRange,labelRange2, labelInterval,
		labelInterval2,labelInterval3, labelName;
	JButton buttonAllNums, buttonOneNum, buttonNext, buttonReset, buttonAdd, buttonMultiply;
	System system;
	JTextField textFieldNumCounts, textFieldRange1, textFieldRange2;
	JComboBox<Integer> comboBoxInterval,comboBoxInterval2;
    public static void main(String [] args)
    {
        new GUI();
    }

    public GUI()
    {
    	system = new System();
    	JPanel thePanel = new JPanel();
        this.setSize(500, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        thePanel.setLayout(null);
        ListenForButton lForButton = new ListenForButton();
        this.setTitle("SpeedArithmetic");
        
        labelName = new JLabel("Made By: Kenzie Tee");
        thePanel.add(labelName);
        labelName.setFont(new Font("Serif", Font.BOLD, 18 ));
        labelName.setBounds(148,30,200,50);
        
        labelTitle = new JLabel(system.getTitle());
        thePanel.add(labelTitle);
        labelTitle.setFont(new Font("Serif", Font.BOLD, 30 ));
        labelTitle.setBounds(55, 10, 400, 150);
        
        labelTitle2 = new JLabel(system.getTitle2());
        thePanel.add(labelTitle2);
        labelTitle2.setFont(new Font("Serif", Font.BOLD, 16 ));
        labelTitle2.setBounds(45, 55, 400, 20);
        
        buttonAllNums = new JButton("Display all numbers at once");
        thePanel.add(buttonAllNums);
        buttonAllNums.setFont(new Font("Serfi", Font.BOLD, 15));
        buttonAllNums.setBounds(115, 150, 250, 75);
		buttonAllNums.addActionListener(lForButton);
		
		buttonOneNum = new JButton("Display one number at a time");
		thePanel.add(buttonOneNum);
		buttonOneNum.setFont(new Font("Serfi", Font.BOLD, 15));
		buttonOneNum.setBounds(115, 250, 250, 75);
		buttonOneNum.addActionListener(lForButton);
		
		textFieldNumCounts = new JTextField();
		thePanel.add(textFieldNumCounts);
		textFieldNumCounts.setBounds(100,75,30,30);
		textFieldNumCounts.setVisible(false);
        
		labelNumCounts = new JLabel(" Terms (number of terms cannot be greater than 50)");
		thePanel.add(labelNumCounts);
		labelNumCounts.setBounds(140,75,350,30);
		labelNumCounts.setVisible(false);
		
		labelRange = new JLabel("Range:  From ");
		thePanel.add(labelRange);
		labelRange.setBounds(100, 115, 150, 30);
		labelRange.setVisible(false);
		
		textFieldRange1 = new JTextField();
		thePanel.add(textFieldRange1);
		textFieldRange1.setBounds(180,115, 50, 30);
		textFieldRange1.setVisible(false);
		
		labelRange2 = new JLabel(" to ");
		thePanel.add(labelRange2);
		labelRange2.setBounds(235, 115, 50, 30);
		labelRange2.setVisible(false);
		
		textFieldRange2 = new JTextField();
		thePanel.add(textFieldRange2);
		textFieldRange2.setBounds(260, 115, 50, 30);
		textFieldRange2.setVisible(false);
		
		labelInterval = new JLabel("Time between number display ");
		thePanel.add(labelInterval);
		labelInterval.setBounds(100, 155, 200, 30);
		labelInterval.setVisible(false);
		
		comboBoxInterval = new JComboBox<>();
		thePanel.add(comboBoxInterval);
		for (int i = 0; i<=15; i++) {	comboBoxInterval.addItem(i);}
		comboBoxInterval.setBounds(275,155,40,30);
		comboBoxInterval.setVisible(false);
		
		labelInterval3 = new JLabel(".");
		thePanel.add(labelInterval3);
		labelInterval3.setBounds(320, 143, 200, 50);
		labelInterval3.setFont(new Font("Serif", Font.BOLD, 30 ));
		labelInterval3.setVisible(false);
		
		comboBoxInterval2= new JComboBox<>();
		thePanel.add(comboBoxInterval2);
		for (int i = 0; i< 10; i++) {	comboBoxInterval2.addItem(i);}
		comboBoxInterval2.setBounds(330,155,40,30);
		comboBoxInterval2.setVisible(false);
		
		labelInterval2 = new JLabel("seconds ");
		thePanel.add(labelInterval2);
		labelInterval2.setBounds(375, 155, 200, 30);
		labelInterval2.setVisible(false);
		
		buttonNext = new JButton("Next");
		thePanel.add(buttonNext);
		buttonNext.addActionListener(lForButton);
		buttonNext.setBounds(350,350, 75, 40);
		buttonNext.setVisible(false);
		
		buttonReset= new JButton("Reset");
		thePanel.add(buttonReset);
		buttonReset.addActionListener(lForButton);
		buttonReset.setBounds(55, 350, 75, 40);
		buttonReset.setVisible(false);
		
		buttonAdd= new JButton("ADD");
		thePanel.add(buttonAdd);
		buttonAdd.addActionListener(lForButton);
		buttonAdd.setBounds(200, 150, 100, 40);
		buttonAdd.setVisible(false);
		
		buttonMultiply= new JButton("MULTIPLY");
		thePanel.add(buttonMultiply);
		buttonMultiply.addActionListener(lForButton);
		buttonMultiply.setBounds(200, 200, 100, 40);
		buttonMultiply.setVisible(false);
		
        this.add(thePanel);
        this.setVisible(true);
    }
    public void startGUI2()
    {
    	this.dispose();
    	//new GUI2();
    	ThreadExample1 t1 = new ThreadExample1();
        t1.start();
    }
    private class ListenForButton implements ActionListener{
    	int pageNum = 1;
        public void actionPerformed(ActionEvent e){
            if( e.getSource()== buttonAllNums)
            {
            	system.displayOption("all");
            	page(pageNum + 1);
            }
            else if (e.getSource() == buttonOneNum)
            {
            	system.displayOption("one");
            	page(pageNum + 1);
            }
            else if(e.getSource() == buttonNext)
            {
            	page(pageNum +1);
            }
            else if (e.getSource() == buttonReset)
            {
            	firstPage();
            }
            else if(e.getSource()==buttonAdd)
            {
            	system.operation("Add");
            	page(pageNum+1);
            }
            else if (e.getSource()==buttonMultiply)
            {
            	system.operation("Multiply");
            	page(pageNum +1);
            }
        }
        private void page(int inputPageNum)
        {
        	pageNum = inputPageNum;
        	if (pageNum == 1) {firstPage();}
        	else if(pageNum ==2) {secondPage();}
        	else if(pageNum ==3) {thirdPage();}
        	else if(pageNum ==4) {fourthPage();}
        	else {
        		startGUI2();
        		}
        	
        }
        private void firstPage()
        {
        	new GUI();
        }
        private void secondPage()
        {
        	labelName.setVisible(false);
        	buttonAllNums.setVisible(false);
            buttonOneNum.setVisible(false);
            labelTitle.setText(system.getTitle());
            labelTitle.setFont(new Font("Serif", Font.PLAIN, 20 ));
            labelTitle.setBounds(45, 10, 400, 50);
            labelNumCounts.setVisible(true);
            textFieldNumCounts.setVisible(true);
            labelRange.setVisible(true);
            textFieldRange1.setVisible(true);
            labelRange2.setVisible(true);
            textFieldRange2.setVisible(true);
            if(system.getDisplayOption().equals("one"))
            {
            	labelInterval.setVisible(true);
            	comboBoxInterval.setVisible(true);
            	labelInterval2.setVisible(true);
            	labelInterval3.setVisible(true);
            	comboBoxInterval2.setVisible(true);
            }
            buttonNext.setVisible(true);
            buttonReset.setVisible(true);
            
            
        }
        private void thirdPage()
        {
        	boolean numCount = system.numCount(textFieldNumCounts.getText());
        	boolean range = system.range(textFieldRange1.getText(), textFieldRange2.getText());
        	boolean interval = system.interval(Objects.requireNonNull(comboBoxInterval.getSelectedItem()).toString(), Objects.requireNonNull(comboBoxInterval2.getSelectedItem()).toString());
        	if (!range || !numCount || !interval)
        	{
        		JOptionPane.showMessageDialog(null,  "Please Enter the Right Info", "Error",JOptionPane.ERROR_MESSAGE);
        		pageNum --;
        	}
        	else{
	        	labelNumCounts.setVisible(false);
	            textFieldNumCounts.setVisible(false);
	            labelRange.setVisible(false);
	            textFieldRange1.setVisible(false);
	            labelRange2.setVisible(false);
	            textFieldRange2.setVisible(false);
	            labelInterval.setVisible(false);
	           	comboBoxInterval.setVisible(false);
	           	comboBoxInterval2.setVisible(false);
	           	labelInterval3.setVisible(false);
	           	labelInterval2.setVisible(false);
	            labelTitle.setText(system.getTitle());
	            labelTitle2.setText(system.getTitle2());
	            buttonAdd.setVisible(true);
	            buttonMultiply.setVisible(true);
	            buttonNext.setVisible(false);
        	}
        }
        private void fourthPage()
        {
        	buttonAdd.setVisible(false);
        	buttonMultiply.setVisible(false);
        	buttonNext.setText("Start");
        	buttonNext.setVisible(true);
        	labelTitle2.setText(system.getTitle2());
        }
    
    }
    public class ThreadExample1 extends Thread {
   	 
        public void run() {
        	GUI2 a =new GUI2(system, true);
        	a.focus();
        }
    }
}
