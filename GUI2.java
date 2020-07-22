import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalTime;
import java.awt.event.*;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Objects;

/** 
 * Creates the screen that shows the users the numbers and an input field
 */
public class GUI2 extends JFrame
{
    int length, width, result;
    System system;
    JButton buttonSubmit;
    JTextField textFieldAnswer;
    ImageIcon correct, wrong;
    Image correctImage, wrongImage;
    JLabel labelAnswer, labelReady;
    LocalTime start;
    boolean first;
    public GUI2(System system, boolean firstWindow)
    //public GUI2()
    {
        //system = new System("one", 3, 1, 2, 2000, "add" );
        this.system= system;
        first = firstWindow;
        if(system.getNumCount() >=25) {length= 745;}
        else{length = system.getNumCount()*26 + 110;}
        if(length<250) { length = 250;}
        width = 250;
        if(system.getNumCount()>25) {width = 400;}
        JPanel thePanel = new JPanel();
        this.setSize(width, length);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        thePanel.setLayout(null);
        this.add(thePanel);
        this.setVisible(true);
        ListenForButton lForButton = new ListenForButton();
        
        //correct = new ImageIcon("Correct_Icon.png");
        correct = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Correct_Icon.png")));
        correctImage = correct.getImage();
        correctImage = correctImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        correct = new ImageIcon(correctImage);
        //wrong = new ImageIcon("Wrong_Icon.png");
        wrong = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Wrong_Icon.png")));
        wrongImage = wrong.getImage();
        wrongImage = wrongImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        wrong = new ImageIcon(wrongImage);
        
        buttonSubmit = new JButton("SUBMIT");
        thePanel.add(buttonSubmit);
        buttonSubmit.addActionListener(lForButton);
        buttonSubmit.setBounds(width/2, length-120, 100, 30);
        buttonSubmit.setVisible(false);
        
        textFieldAnswer = new JTextField();
        thePanel.add(textFieldAnswer);
        textFieldAnswer.setBounds(width/2-100,length-120, 90,30);
        textFieldAnswer.setVisible(false);
        textFieldAnswer.addKeyListener(lForButton);
        
        if(first) {
	        labelReady = new JLabel("Ready!");
	        thePanel.add(labelReady);
	        labelReady.setFont(new Font("Serif", Font.BOLD, 30));
	        labelReady.setForeground(Color.RED);
	        labelReady.setHorizontalAlignment(JLabel.CENTER);
	        labelReady.setVerticalAlignment(JLabel.CENTER);
	        labelReady.setBounds(0,0,width-20,length-100);
	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        labelReady.setForeground(Color.GREEN);
	        labelReady.setText("GO!");
	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        labelReady.setVisible(false);
        }
        
        if(system.getDisplayOption().equals("all"))
        {
            int y = 0;
            int[] numbers = system.getNumbers();
            for(int i = 0; i < system.getNumCount() && i<25; i++)
            {
                int newNum = numbers[i];
                String newNumString = String.valueOf(newNum);
                JLabel newLabel = new JLabel(newNumString);
                thePanel.add(newLabel);
                newLabel.setFont(new Font("Serif", Font.BOLD, 20 ));
                newLabel.setHorizontalAlignment(JLabel.CENTER);
                newLabel.setBounds(0,y,250-30,20);
                y = y+25;
            }
            if(system.getNumCount()>25)
            {
                y=0;
                for(int i = 25; i<system.getNumCount();i++)
                {
                    int newNum = numbers[i];
                    String newNumString = String.valueOf(newNum);
                    JLabel newLabel = new JLabel(newNumString);
                    thePanel.add(newLabel);
                    newLabel.setFont(new Font("Serif", Font.BOLD, 20 ));
                    newLabel.setHorizontalAlignment(JLabel.CENTER);
                    newLabel.setBounds(170,y,250-30,20);
                    y = y+25;
                }
            }
            start = LocalTime.now();
            buttonSubmit.setVisible(true);
            textFieldAnswer.setVisible(true);
        }
        else
        {
            int[] numbers = system.getNumbers();
            for(int i = 0; i < system.getNumCount(); i++)
            {
                int newNum = numbers[i];
                String newNumString = String.valueOf(newNum);
                JLabel newLabel = new JLabel(newNumString);
                thePanel.add(newLabel);
                newLabel.setFont(new Font("Serif", Font.BOLD, 30 ));
                if(i%2==0) {newLabel.setForeground(Color.RED);}
                else {newLabel.setForeground(Color.BLUE);}
                newLabel.setHorizontalAlignment(JLabel.CENTER);
                newLabel.setVerticalAlignment(JLabel.CENTER);
                newLabel.setBounds(0,0,250-30,250-120);
                
                try {
                    Thread.sleep(system.getInterval());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                newLabel.setVisible(false);
            }
            textFieldAnswer.setVisible(true);
            textFieldAnswer.requestFocusInWindow();
            labelAnswer = new JLabel("Enter Answer");
            thePanel.add(labelAnswer);
            labelAnswer.setHorizontalAlignment(JLabel.CENTER);
            labelAnswer.setVerticalAlignment(JLabel.CENTER);
            labelAnswer.setFont(new Font("Serif", Font.BOLD, 30 ));
            labelAnswer.setBounds(0,0,250-30,250-120);
            start = LocalTime.now();
            buttonSubmit.setVisible(true);
            textFieldAnswer.setVisible(true);
        } 
    }
    public void focus()
    {
    	textFieldAnswer.requestFocusInWindow();
    }
    public void reset()
    {
        new GUI();
        this.dispose();
        
    }
    public void nextQuestion()
    {
        this.dispose();
        //new GUI2();
        //new GUI2(system);
        ThreadExample1 t1 = new ThreadExample1();
        t1.start();
        
    }
    
    private class ListenForButton implements ActionListener, KeyListener{
        int response=5;
        public void actionPerformed(ActionEvent e){
            if( e.getSource()== buttonSubmit)
            {
            	buttonSubmitPressed();
            }
        }
        public void buttonSubmitPressed()
        {
        	try {
                int answer = Integer.parseInt(textFieldAnswer.getText());
                Duration duration = Duration.between(start, LocalTime.now());
                result = (int) duration.getSeconds();
                Object[] options = {"Reset", "Next Question"};
                if (answer == system.getAnswer())
                {
                    response = JOptionPane.showOptionDialog(null, "You are Correct!\nThe answer is " + system.getAnswer() +".\nIt took you " + result + " seconds.", "Corrrect", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, correct, options, options[1]);
                }
                else
                {
                    response = JOptionPane.showOptionDialog(null, "You are Wrong! \n The answer is " + system.getAnswer() + ".", "Incorrect", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, wrong, options, options[1]);
                }
            }
            catch(NumberFormatException excep){
                JOptionPane.showMessageDialog(null,  "Please Enter the Right Info", "Error",JOptionPane.ERROR_MESSAGE);
            }
        	if(response==0 )
            {
                reset();
            }
            else if(response == 1)
            {
                nextQuestion();
            }
        }
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				buttonSubmitPressed();
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
    }
    public class ThreadExample1 extends Thread {
    	 
        public void run() {
        	GUI2 a = new GUI2(system, false);
        	a.focus();
        }
     
    }
    

}
