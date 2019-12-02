package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

/**
 @author Krish Shah Monteiro
 @version 1.0
 @since 2019-12-01
 */

/**
 This class is responsible for creating the physical addfeat or the
 additional features that are a part of BIGCALC
 The class uses components from RadioButton, JPanel, JTextField, JLabel.
 */

public class additionalcalc extends JFrame
{
    private int WIN_WID = 1000;
    private int WIN_HEI = 1000;
    private double finresult;
    private double tempresult;

    private JPanel panel0 = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    private JPanel panel5 = new JPanel();
    private JPanel panel6 = new JPanel();
    private JPanel finpanel = new JPanel();

    // mass converter
    private JLabel kilomessage;
    private JTextField kilobar;
    private JLabel poundmessage;
    private JTextField poundbar;
    private JButton getmass;
    private JButton clear1;
    //distance converter
    private JLabel kilomessage2;
    private JTextField kilobar2;
    private JLabel milesmessage;
    private JTextField milesbar;
    private JButton getdistance;
    private JButton clear2;
    //budgeter
    private JTextField income,entertainment, savings, insurance,rent,bills;
    private JLabel incomemess,entertainmentmess,savingsmess,insurancemess,rentmess,billsmess;
    private JButton budget,clear3;

    //Simple interest and compound interest
    private JButton calsi,clear4;
    private JLabel principalmess, ratemess,timemess,simpleinterstmess;
    private JTextField principal, rate, time, simpleinterest;

    //Stock returns
    private JLabel origvalabel, finalvalabel,dividendlabel,returnslabel;
    private JTextField origval,finalval,returns;
    private JRadioButton choice1,choice2,choice3,choice4,choice5;
    private JButton stockret,clear5;

    //mortgage calculator
    private JLabel loanlabel,numberofylabel,minterestlabel,mortlabel;
    private JTextField loan,numberofy,minterest,mort;
    private JButton clear6,calcmort;


    /**
     The constructor acts as a buildpanel() function that uses different panels responsible for different components
     and puts all together using the finpanel.
     */
    public additionalcalc()
    {
        setTitle("ADDITIONAL FEATURES");
        setSize(WIN_WID,WIN_HEI);
        JButton redbutton = new JButton("RED");
        redbutton.addActionListener(new redbuttonlistener());
        JButton yellowbutton = new JButton("YELLOW");
        yellowbutton.addActionListener(new yellowbuttonlistener());
        JButton orangebutton = new JButton("ORANGE");
        orangebutton.addActionListener(new orangebuttonlistener());
        panel0.setBorder(BorderFactory.createTitledBorder("COLOR SCHEME"));
        panel0.add(redbutton);
        panel0.add(yellowbutton);
        panel0.add(orangebutton);
//mass converter
        panel1.setBorder(BorderFactory.createTitledBorder("MASS CONVERTER"));
        kilomessage = new JLabel("KILOGRAM:");
        poundmessage = new JLabel("POUND:");
        kilobar = new JTextField(10);
        poundbar = new JTextField(10);
        getmass = new JButton("KILOGRAMS TO POUNDS");
        clear1 = new JButton("CLEAR");
        getmass.addActionListener(new massbuttonlistener());
        clear1.addActionListener(new clear1listener());
        panel1.setLayout(new GridLayout(4,1));
        panel1.add(kilomessage);
        panel1.add(kilobar);
        panel1.add(poundmessage);
        panel1.add(poundbar);
        panel1.add(getmass);
        panel1.add(clear1);
//distance converter
        panel2.setBorder(BorderFactory.createTitledBorder("DISTANCE CONVERTER"));
        kilomessage2 = new JLabel("KILOMETERS:");
        milesmessage = new JLabel("MILES:");
        kilobar2 = new JTextField(10);
        milesbar = new JTextField(10);
        getdistance = new JButton("KILOMETERS TO MILES");
        clear2 = new JButton("CLEAR");
        getdistance.addActionListener(new distancebuttonlistener());
        clear2.addActionListener(new clear2listener());
        panel2.setLayout(new GridLayout(4,1));
        panel2.add(kilomessage2);
        panel2.add(kilobar2);
        panel2.add(milesmessage);
        panel2.add(milesbar);
        panel2.add(getdistance);
        panel2.add(clear2);
//budgeter
        panel3.setBorder(BorderFactory.createTitledBorder("BUDGETER"));
        incomemess = new JLabel("INCOME:");
        entertainmentmess = new JLabel("ENTERTAINMENT:");
        savingsmess = new JLabel("SAVINGS:");
        insurancemess = new JLabel("INSURANCE:");
        rentmess = new JLabel("RENT:");
        billsmess = new JLabel("BILLS");
        income = new JTextField(10);
        entertainment = new JTextField(10);
        savings = new JTextField(10);
        insurance = new JTextField(10);
        rent = new JTextField(10);
        bills = new JTextField(10);
        budget = new JButton("GET BUDGET");
        clear3 = new JButton("CLEAR");
        budget.addActionListener(new budgetbuttonlistener());
        clear3.addActionListener(new clear3listener());
        panel3.setLayout(new GridLayout(7,1));
        panel3.add(incomemess);
        panel3.add(income);
        panel3.add(entertainmentmess);
        panel3.add(entertainment);
        panel3.add(savingsmess);
        panel3.add(savings);
        panel3.add(insurancemess);
        panel3.add(insurance);
        panel3.add(rentmess);
        panel3.add(rent);
        panel3.add(billsmess);
        panel3.add(bills);
        panel3.add(budget);
        panel3.add(clear3);
//simple interest
        panel4.setBorder(BorderFactory.createTitledBorder("SIMPLE INTEREST CALCULATOR"));
        principalmess = new JLabel("PRINCIPAL AMOUNT:$ ");
        principal = new JTextField(10);
        ratemess = new JLabel("RATE:% ");
        rate = new JTextField(10);
        timemess = new JLabel("TIME:yrs ");
        time = new JTextField(5);
        simpleinterstmess = new JLabel("Simple Interest:$ ");
        simpleinterest = new JTextField(10);
        calsi = new JButton("GET SIMPLE INTEREST");
        calsi.addActionListener(new calsilistener());
        clear4 = new JButton("CLEAR");
        clear4.addActionListener(new clear4listener());
        panel4.setLayout(new GridLayout(5,1));
        panel4.add(principalmess);
        panel4.add(principal);
        panel4.add(ratemess);
        panel4.add(rate);
        panel4.add(timemess);
        panel4.add(time);
        panel4.add(simpleinterstmess);
        panel4.add(simpleinterest);
        panel4.add(calsi);
        panel4.add(clear4);
//Stock returns
        panel5.setBorder(BorderFactory.createTitledBorder("STOCK RETURNS"));
        origvalabel = new JLabel("PURCHASE COST:$ ");
        finalvalabel = new JLabel("SELLING COST:$ ");
        dividendlabel = new JLabel("DIVIDEND:$ ");
        returnslabel = new JLabel("STOCK RETURNS:$ ");
        origval = new JTextField(10);
        finalval = new JTextField(10);
        returns = new JTextField(10);
        choice1 = new JRadioButton("$5");
        choice1.addActionListener(new RadioButtonlistener());
        choice2 = new JRadioButton("$10");
        choice2.addActionListener(new RadioButtonlistener());
        choice3 = new JRadioButton("$15");
        choice3.addActionListener(new RadioButtonlistener());
        choice4 = new JRadioButton("$20");
        choice4.addActionListener(new RadioButtonlistener());
        choice5 = new JRadioButton("$25");
        choice5.addActionListener(new RadioButtonlistener());
        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(choice1);
        bgroup.add(choice2);
        bgroup.add(choice3);
        bgroup.add(choice4);
        clear5 = new JButton("CLEAR");
        clear5.addActionListener(new clear5listener());
        panel5.setLayout(new GridLayout(8,1));
        panel5.add(origvalabel);
        panel5.add(origval);
        panel5.add(finalvalabel);
        panel5.add(finalval);
        panel5.add(dividendlabel);
        panel5.add(choice1);
        panel5.add(choice2);
        panel5.add(choice3);
        panel5.add(choice4);
        panel5.add(choice5);
        panel5.add(returnslabel);
        panel5.add(returns);
        panel5.add(clear5);
        // mortgage calculator
        panel6.setBorder(BorderFactory.createTitledBorder("MONTHLY MORTGAGE CALCULATOR"));
        loanlabel = new JLabel("AMOUNT:$ ");
        loan = new JTextField(10);
        minterestlabel = new JLabel("INTEREST RATE:% ");
        minterest = new JTextField(10);
        numberofylabel = new JLabel("YEARS:yrs ");
        numberofy = new JTextField(10);
        mortlabel = new JLabel("MONTHLY PAYMENTS:$ ");
        mort = new JTextField(10);
        clear6 = new JButton("CLEAR");
        clear6.addActionListener(new clear6listener());
        calcmort = new JButton("CALCULATE PAYMENT");
        calcmort.addActionListener(new mortbuttonlistener());
        panel6.setLayout(new GridLayout(6,1));
        panel6.add(loanlabel);
        panel6.add(loan);
        panel6.add(minterestlabel);
        panel6.add(minterest);
        panel6.add(numberofylabel);
        panel6.add(numberofy);
        panel6.add(mortlabel);
        panel6.add(mort);
        panel6.add(calcmort);
        panel6.add(clear6);
        finpanel.setLayout(new GridLayout(3,1));
        finpanel.add(panel0);
        finpanel.add(panel1);
        finpanel.add(panel2);
        finpanel.add(panel3);
        finpanel.add(panel4);
        finpanel.add(panel5);
        finpanel.add(panel6);
        add(finpanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Actionlisteners form line to 264 - 305 are color button actionlisteners that allow the user to pick the color
     * schema for all the panels on the window.
     */

    class redbuttonlistener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            panel0.setBackground(Color.RED);
            panel1.setBackground(Color.RED);
            panel2.setBackground(Color.RED);
            panel3.setBackground(Color.RED);
            panel4.setBackground(Color.RED);
            panel5.setBackground(Color.RED);
            panel6.setBackground(Color.RED);
            finpanel.setBackground(Color.RED);
        }
    }
    class orangebuttonlistener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            panel0.setBackground(Color.ORANGE);
            panel1.setBackground(Color.ORANGE);
            panel2.setBackground(Color.ORANGE);
            panel3.setBackground(Color.ORANGE);
            panel4.setBackground(Color.ORANGE);
            panel5.setBackground(Color.ORANGE);
            panel6.setBackground(Color.ORANGE);
            finpanel.setBackground(Color.ORANGE);
        }
    }
    class yellowbuttonlistener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            panel0.setBackground(Color.YELLOW);
            panel1.setBackground(Color.YELLOW);
            panel2.setBackground(Color.YELLOW);
            panel3.setBackground(Color.YELLOW);
            panel4.setBackground(Color.YELLOW);
            panel5.setBackground(Color.YELLOW);
            panel6.setBackground(Color.YELLOW);
            finpanel.setBackground(Color.YELLOW);
        }
    }

    /**
     ActionListeners from line 311 - 470 are actionlisteners for calculating the features provided in the addfeat
     calculator.Additionally some of these actionListeners help clear the textfields for the user to make more
     unrelated calculations
     */
    class massbuttonlistener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            tempresult = Double.parseDouble(kilobar.getText());
            finresult = tempresult * 2.204;
            poundbar.setText(Double.toString(finresult));
        }
    }
    class clear1listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            kilobar.setText("");
            poundbar.setText("");
        }
    }
    class distancebuttonlistener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            tempresult = Double.parseDouble(kilobar2.getText());
            finresult = tempresult * 0.621;
            milesbar.setText(Double.toString(finresult));
        }
    }
    class clear2listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            kilobar2.setText("");
            milesbar.setText("");
        }
    }
    class budgetbuttonlistener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            double holdins,holdenter,holdsave,holdrent,holdbills;
            final double budgetnum1 = 0.3,budgetnum2 = 0.1,budgetnum3 = 0.2,budgetnum4 = 0.15,budgetnum5 = 0.25;
            tempresult = Double.parseDouble(income.getText());
            holdbills = budgetnum1 * tempresult;
            holdenter = budgetnum2 * tempresult;
            holdins = budgetnum3 * tempresult;
            holdsave = budgetnum4 * tempresult;
            holdrent = budgetnum5 * tempresult;
            insurance.setText(Double.toString(holdbills));
            entertainment.setText(Double.toString(holdenter));
            bills.setText(Double.toString(holdbills));
            savings.setText(Double.toString(holdsave));
            rent.setText(Double.toString(holdrent));
        }
    }
    class clear3listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            income.setText("");
            rent.setText("");
            bills.setText("");
            savings.setText("");
            insurance.setText("");
            entertainment.setText("");
        }
    }
    class calsilistener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            double rat,tim;
            tempresult = Double.parseDouble(principal.getText());
            rat = (Double.parseDouble(rate.getText())/100);
            tim = Double.parseDouble(time.getText());
            finresult = tempresult * rat * tim;
            simpleinterest.setText(Double.toString(finresult));
        }
    }
    class clear4listener implements  ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            principal.setText("");
            rate.setText("");
            time.setText("");
            simpleinterest.setText("");
        }
    }
    class RadioButtonlistener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            double initial_value,final_value,dividend;
            double c1=5,c2=10,c3=15,c4=20,c5=25;
            initial_value = Double.parseDouble(origval.getText());
            final_value = Double.parseDouble(finalval.getText());
            if(e.getSource() == choice1)
            {
                tempresult = final_value - initial_value;
                finresult = (tempresult + c1) / initial_value;
                returns.setText(Double.toString(finresult));
            }

            else if(e.getSource() == choice2)
            {
                tempresult = final_value - initial_value;
                finresult = (tempresult + c2) / initial_value;
                returns.setText(Double.toString(finresult));

            }
            else if(e.getSource() == choice3)
            {
                tempresult = final_value - initial_value;
                finresult = (tempresult + c3) / initial_value;
                returns.setText(Double.toString(finresult));
            }
            else if(e.getSource()==choice4)
            {
                tempresult = final_value - initial_value;
                finresult = (tempresult + c4) / initial_value;
                returns.setText(Double.toString(finresult));
            }
            else if(e.getSource()==choice5)
            {
                tempresult = final_value - initial_value;
                finresult = (tempresult + c5) / initial_value;
                returns.setText(Double.toString(finresult));
            }
        }
    }
    class clear5listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            origval.setText("");
            finalval.setText("");
            returns.setText("");
        }
    }
    class mortbuttonlistener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            tempresult = Double.parseDouble(loan.getText());
            double rateofint = Double.parseDouble(minterest.getText());
            double y = Double.parseDouble(numberofy.getText());
            double ratepown = Math.pow(1+rateofint,y);
            finresult = ((tempresult*ratepown) / (ratepown -1));
            mort.setText(Double.toString(finresult));
        }
    }
    class clear6listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            loan.setText("");
            numberofy.setText("");
            minterest.setText("");
            mort.setText("");
        }
    }
    public static void main(String[] Args)
    {
        new additionalcalc();
    }

}
