package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;

/**
 @author Krish Shah Monteiro
 @version 1.0
 @since 2019-12-01
 */

/**
 this class is used to create the actual physical calculator that appears on the screen
 The functionality of this calculator is limited by the mathematical functions.
 */


public class BigCalc extends JFrame
{
    String buttontext = "";
    private double result = 0,worktemp1 = 0,worktemp2 = 0;
    private final int WIN_HEIGHT = 500;
    private final int WIN_WIDTH = 400;
    private JButton button0;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button_add;
    private JButton button_subtract;
    private JButton button_multiply;
    private JButton button_divide;
    private JButton button_square;
    private JButton button_cube;
    private JButton button_sqrt;
    private JButton button_cbrt;
    private JButton button_ln;
    private JButton button_log;
    private JButton button_factorial;
    private JButton button_PI;
    private JButton button_addfeat;
    private JButton button_clear;
    private JButton button_equals;
    private JButton button_help;
    //the textField
    private JTextField numbertextbr;
    //Boolean for the functions
    Boolean square_status = false;
    Boolean cube_status = false;
    Boolean sqrt_status = false;
    Boolean cbrt_status = false;
    Boolean factorial_status = false;
    Boolean PI_status = false;
    Boolean ln_status = false;
    Boolean log_status = false;
    Boolean add_status = false;
    Boolean subtract_status = false;
    Boolean multiply_status = false;
    Boolean divide_status = false;

    /**
     This constrictor creates the window along with the components, namely,buttons,labels and textfields.
     */

    BigCalc()
    { numbertextbr = new JTextField(20);
        setTitle("BIGCALC");
        setSize(WIN_WIDTH,WIN_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Numbers
        JButton button0 = new JButton("0");
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton button_equals = new JButton("=");
        //functions and operators
        JButton button_add = new JButton("+");
        JButton button_subtract = new JButton("-");
        JButton button_divide = new JButton("/");
        JButton button_multiply = new JButton("*");
        JButton button_square = new JButton("x²");
        JButton button_cube = new JButton("x³");
        JButton button_sqrt = new JButton("√x");
        JButton button_cbrt = new JButton("∛x");
        JButton button_clear = new JButton("CLEAR");
        JButton button_addfeat = new JButton("ADDFEAT");
        JButton button_log = new JButton("log");
        JButton button_ln = new JButton("ln");
        JButton button_PI = new JButton("π");
        JButton button_help = new JButton("HELP");
        JButton button_factorial = new JButton("!");
        /**
         The following panels are used to create individual panels that are finally added to the window using finpanel
         Reference lines - 182 to 185
         */

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel finpanel = new JPanel();

        //making 3 panels and allocating each one of them to a specific border location is the reason we have 3 panels
        //panel1 is dedicated to all the buttons
        //panel 1 is dedicated to all the numbered buttons


        panel1.setLayout(new GridLayout(4,4));
        panel1.add(button0);
        button0.addActionListener(new button0Listener());
        panel1.add(button1);
        button1.addActionListener(new button1Listener());
        panel1.add(button2);
        button2.addActionListener(new button2Listener());
        panel1.add(button3);
        button3.addActionListener(new button3Listener());
        panel1.add(button4);
        button4.addActionListener(new button4Listener());
        panel1.add(button5);
        button5.addActionListener(new button5Listener());
        panel1.add(button6);
        button6.addActionListener(new button6Listener());
        panel1.add(button7);
        button7.addActionListener(new button7Listener());
        panel1.add(button8);
        button8.addActionListener(new button8Listener());
        panel1.add(button9);
        button9.addActionListener(new button9Listener());
        //panel2 is dedicated to all the functions
        panel2.setLayout(new GridLayout(5,5));
        panel2.add(button_add);
        button_add.addActionListener(new buttonaddListener());
        panel2.add(button_subtract);
        button_subtract.addActionListener(new buttonsubtractListener());
        panel2.add(button_multiply);
        button_multiply.addActionListener(new buttonmulitplyListener());
        panel2.add(button_divide);
        button_divide.addActionListener(new buttondivideListener());
        panel2.add(button_square);
        button_square.addActionListener(new buttonsquareListener());
        panel2.add(button_cube);
        button_cube.addActionListener(new buttoncubeListener());
        panel2.add(button_sqrt);
        button_sqrt.addActionListener(new buttonsqrtListener());
        panel2.add(button_cbrt);
        button_cbrt.addActionListener(new buttoncbrtListener());
        panel2.add(button_ln);
        button_ln.addActionListener(new buttonlnListener());
        panel2.add(button_log);
        button_log.addActionListener(new buttonlogListener());
        panel2.add(button_factorial);
        button_factorial.addActionListener(new buttonfactorialListener());
        panel2.add(button_PI);
        button_PI.addActionListener(new buttonPIListener());
        // panel3 is dedicated to the text bar
        panel3.add(numbertextbr);
        //panel4 to hold all the special function keys
        panel4.setLayout(new GridLayout(1,3));
        panel4.add(button_addfeat);
        button_addfeat.addActionListener(new buttonaddfeatListener());
        panel4.add(button_help);
        button_help.addActionListener(new buttonhelpListener());
        panel4.add(button_clear);
        button_clear.addActionListener(new buttonclearListener());
        panel4.add(button_equals);
        button_equals.addActionListener(new EvaluateListener());
        //putting all the panels together using finpanel
        finpanel.setLayout(new GridLayout(4,1));
        finpanel.add(panel3,BorderLayout.NORTH);
        finpanel.add(panel4,BorderLayout.SOUTH);
        finpanel.add(panel1,BorderLayout.SOUTH);
        finpanel.add(panel2,BorderLayout.SOUTH);
        add(finpanel);
        setVisible(true);
    }

    /**
     Actionlisteners form lines 194 - 274 are actionlisteners for the number keypad (0-91)
     */


    class button1Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            buttontext = numbertextbr.getText();
            numbertextbr.setText(buttontext+"1");
        }
    }
    class button2Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            buttontext = numbertextbr.getText();
            numbertextbr.setText(buttontext+"2");
        }
    }
    class button3Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            buttontext = numbertextbr.getText();
            numbertextbr.setText(buttontext+"3");
        }
    }
    class button4Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            buttontext = numbertextbr.getText();
            numbertextbr.setText(buttontext+"4");
        }
    }
    class button5Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            buttontext = numbertextbr.getText();
            numbertextbr.setText(buttontext+"5");
        }
    }
    class button6Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            buttontext = numbertextbr.getText();
            numbertextbr.setText(buttontext+"6");
        }
    }
    class button7Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            buttontext = numbertextbr.getText();
            numbertextbr.setText(buttontext+"7");
        }
    }
    class button8Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            buttontext = numbertextbr.getText();
            numbertextbr.setText(buttontext+"8");
        }
    }
    class button9Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            buttontext = numbertextbr.getText();
            numbertextbr.setText(buttontext+"9");
        }
    }
    class button0Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            buttontext = numbertextbr.getText();
            numbertextbr.setText("0");
        }
    }

    /**
     Actionlisteners from lines 279 to 443 are actionlisteners for the functional buttons
     */
    private class buttonaddListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            worktemp1 = Double.parseDouble(numbertextbr.getText());
            numbertextbr.setText("");
            add_status = true;
        }
    }
    private class buttonsubtractListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            worktemp1 = Double.parseDouble(numbertextbr.getText());
            numbertextbr.setText("");
            subtract_status = true;
        }
    }
    private class buttonmulitplyListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            worktemp1 = Double.parseDouble(numbertextbr.getText());
            numbertextbr.setText("");
            multiply_status  = true;
        }
    }
    private class buttondivideListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            worktemp1 = Double.parseDouble(numbertextbr.getText());
            numbertextbr.setText("");
            divide_status = true;
        }
    }
    private class buttonsquareListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            worktemp1 = Double.parseDouble(numbertextbr.getText());
            square_status = true;
            result = (worktemp1 * worktemp1);
            numbertextbr.setText(Double.toString(result));
            sqrt_status = false;
        }
    }
    private class buttoncubeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            worktemp1 = Double.parseDouble(numbertextbr.getText());
            cube_status = true;
            result = (worktemp1 * worktemp1 * worktemp1);
            numbertextbr.setText(Double.toString(result));
            cube_status = false;
        }
    }
    private class buttonsqrtListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            worktemp1 = Double.parseDouble(numbertextbr.getText());
            sqrt_status = true;
            result = Math.sqrt(worktemp1);
            numbertextbr.setText(Double.toString(result));
            sqrt_status = false;
        }
    }

    private class buttoncbrtListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            worktemp1 = Double.parseDouble(numbertextbr.getText());
            sqrt_status = true;
            result = Math.cbrt(worktemp1);
            numbertextbr.setText(Double.toString(result));
            cbrt_status = false;
        }
    }
    private class buttonPIListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            final double PI = 3.141592;
            worktemp1 = Double.parseDouble(numbertextbr.getText());
            PI_status = true;
            result  = worktemp1 * PI;
            numbertextbr.setText(Double.toString(result));
            PI_status = false;
        }
    }
    private class buttonfactorialListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            double sum = 1;
            worktemp1 = Double.parseDouble(numbertextbr.getText());
            factorial_status = true;
            while(worktemp1 != 1)
            {
                sum *= worktemp1;
                worktemp1--;
            }
            result = sum;
            numbertextbr.setText(Double.toString(result));
            factorial_status = false;
        }
    }

    private class buttonlnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            final double ln = 2.71828;
            ln_status = true;
            worktemp1 = Double.parseDouble(numbertextbr.getText());
            result = worktemp1 * ln;
            numbertextbr.setText(Double.toString(result));
            ln_status = false;
        }
    }
    private class buttonlogListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            worktemp1 = Double.parseDouble(numbertextbr.getText());
            log_status = true;
            result = Math.log(worktemp1);
            numbertextbr.setText(Double.toString(result));
            log_status = false;
        }
    }

    private class buttonhelpListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(null,"   <<------------- INSTRUCTIONS ----------------->>\n"
                    +"\nIMPORTANT \n 1.SUPPORTS NEGATIVE NUMBERS \n 2.ANSWERS ARE NOT ROUNDED\n"
                    +"\nBUTTONS - \n 1.'+' -> ADD - REQUIRES 2 OPERANDS. \n CLICKING ON THE \"=\" BUTTON EVALUATES THE EXPRESSION."
                    + "\n2.'-' -> SUBTRACT - REQUIRES 2 OPERANDS.\n CLICKING ON THE \"=\" BUTTON EVALUATES THE EXPRESSION."
                    + "\n3.'*' -> MULTIPLY - REQUIRES 2 OPERANDS.\n CLICKING ON THE \"=\" BUTTON EVALUATES THE EXPRESSION."
                    + "\n4.'/' -> DIVIDE - REQUIRES 2 OPERANDS.\n CLICKING ON THE \"=\" BUTTON EVALUATES THE EXPRESSION."
                    + "\n5.'x²' -> SQUARE - REQUIRES 1 OPERAND ONLY.\n DOES NOT REQUIRE THE USER TO CLICK ON THE \"=\" BUTTON."
                    + "\n6.'x³' -> CUBE - REQUIRES 1 OPERAND ONLY.\n DOES NOT REQUIRE THE USER TO CLICK ON THE \"=\" BUTTON."
                    + "\n7.'√x' -> SQUARE ROOT - REQUIRES 1 OPERAND ONLY. \n DOES NOT REQUIRE THE USER TO CLICK ON THE \"=\" BUTTON."
                    + "\n8.'∛x' -> CUBE ROOT - REQUIRES 1 OPERAND ONLY.\n DOES NOT REQUIRE THE USER TO CLICK ON THE \"=\" BUTTON."
                    + "\n9.'clear' -> CLEAR SCREEN - CLEARS THE TEXT BAR"
                    + "\n10.'ADDFEAT' -> ADDITIONAL FEATURES - PROVIDES THE USER WITH ADDITIONAL FEATURES\n THESE ADDITIONAL FEATURES CONSIST OF:\n SIMPLE INTEREST,\n COMPOUND INTEREST,\n BUDGETER,\n DISTANCE CONVERTER,\n MASS CONVERTER,\n MORTGAGE CALCULATOR,\n BANK INTEREST ON LOANS,\nSTOCK RETURNS"
                    + "\n11.'HELP' -> HELP FEATURE - OPENS A TEXT FILE TO PROVIDE THE USER WITH BUTTON FUNCTIONALITIES."
                    + "\n12.'!' -> FACTORIAL - REQUIRES 1 OPERAND ONLY. \n DOES NOT REQUIRE THE USER TO CLICK ON THE \"=\" BUTTON."
                    + "\n13. 'ln' -> NATURAL LOG - REQUIRES 1 OPERAND ONLY. \n DOES NOT REQUIRE THE USER TO CLICK ON THE \"=\" BUTTON."
                    + "\n14. 'log' -> LOGARITHM - REQUIRES 1 OPERAND ONLY.\n DOES NOT REQUIRE THE USER TO CLICK ON THE \"=\" BUTTON.");
        }
    }

    private class buttonaddfeatListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            new additionalcalc();
        }
    }

    private class buttonclearListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            numbertextbr.setText("");
            result = 0;
            worktemp1 = 0;
            worktemp2 = 0;
            add_status = false;
            subtract_status = false;
            divide_status = false;
            multiply_status = false;
            square_status = false;
            cube_status = false;
            sqrt_status = false;
            cbrt_status = false;
            PI_status = false;
            log_status = false;
            ln_status = false;
            factorial_status = false;
        }
    }


    /**
     This actionListener requires 2 operands which is why it is used as an actionlistener
     for 4 different functional buttons(+,-,*,/)
     */
    private class EvaluateListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            worktemp2 = Double.parseDouble(numbertextbr.getText());

            if(add_status == true)
            {
                result = (worktemp1 + worktemp2);
                numbertextbr.setText(Double.toString(result));
            }
            else if(subtract_status == true)
            {
                result = (worktemp1 - worktemp2);
                numbertextbr.setText(Double.toString(result));
            }
            else if(multiply_status == true)
            {
                result = (worktemp1 * worktemp2);
                numbertextbr.setText(Double.toString(result));
            }
            else if(divide_status == true)
            {
                result = (worktemp1 / worktemp2);
                numbertextbr.setText(Double.toString(result));
            }
            add_status = false;
            subtract_status = false;
            multiply_status = false;
            divide_status = false;

        }
    }
    public static void main(String[] Args)
    {
        new BigCalc();
    }
}

