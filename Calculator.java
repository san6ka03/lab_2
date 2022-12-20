package bsu.rfct.course2.group9.Todadze;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.*;


public class Calculator {


    private JFrame mainFrame = new JFrame();

    private JLabel resultLabel = new JLabel();

    private JLabel memoryTextLabelData = new JLabel("0");
    private int activeFormula = 1;

    Calculator() {


        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth() * 480 / 1920;
        int height = gd.getDisplayMode().getHeight() * 400 / 1080;


        mainFrame.setSize(width, height);

        JButton buttonMemoryPlus = new JButton("M+");
        buttonMemoryPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double newValue = Double.parseDouble(resultLabel.getText()) + Double.parseDouble(memoryTextLabelData.getText());
                memoryTextLabelData.setText(newValue.toString());
            }
        });


        JButton buttonMemoryClear = new JButton("MC");
        buttonMemoryClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memoryTextLabelData.setText("0");
            }
        });


        JRadioButton rbFormula1 = new JRadioButton("1");
        JRadioButton rbFormula2 = new JRadioButton("2");

        rbFormula1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calculator.this.activeFormula = 1;
            }
        });

        rbFormula2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calculator.this.activeFormula = 2;
            }
        });

        rbFormula1.setSelected(true);

        ButtonGroup formulaButtonGroup = new ButtonGroup();
        formulaButtonGroup.add(rbFormula1);
        formulaButtonGroup.add(rbFormula2);

        JTextField textVariableX = new JTextField("0", 6);
        JTextField textVariableY = new JTextField("0", 6);
        JTextField textVariableZ = new JTextField("0", 6);

        JButton buttonCalculate = new JButton("Calculate");

        buttonCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double result = null;
                double x = Double.parseDouble(textVariableX.getText());
                double y = Double.parseDouble(textVariableY.getText());
                double z = Double.parseDouble(textVariableZ.getText());
                switch (Calculator.this.activeFormula) {
                    case (1) -> result = calculateFirstFormula(x, y, z);
                    case (2) -> result = calculateSecondFormula(x, y, z);
                }

                Calculator.this.resultLabel.setText(result.toString());
            }
        });


        Box hboxFormulaChoice = Box.createHorizontalBox();
        hboxFormulaChoice.add(Box.createHorizontalGlue());
        hboxFormulaChoice.add(rbFormula1);
        hboxFormulaChoice.add(rbFormula2);
        hboxFormulaChoice.add(Box.createHorizontalGlue());

        Box hboxMemory = Box.createHorizontalBox();
        hboxMemory.add(Box.createHorizontalGlue());
        hboxMemory.add(buttonMemoryPlus);
        hboxMemory.add(buttonMemoryClear);
        hboxMemory.add(Box.createHorizontalStrut(20));
        hboxMemory.add(memoryTextLabelData);
        hboxMemory.add(Box.createHorizontalGlue());


        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.add(Box.createHorizontalStrut(width / 5));
        Box hboxFunc = Box.createHorizontalBox();

        hboxFunc.add(textVariableX);
        hboxFunc.add(textVariableY);
        hboxFunc.add(textVariableZ);
        hboxFunc.setMaximumSize(new Dimension(30, 20));

        hboxVariables.add(hboxFunc);
        hboxVariables.add(resultLabel);
        hboxVariables.add(Box.createHorizontalGlue());


        Box hboxCalculate = Box.createHorizontalBox();
        hboxCalculate.add(buttonCalculate);


        Box contentBox = Box.createVerticalBox();
        contentBox.add(hboxFormulaChoice);
        contentBox.add(hboxMemory);
        contentBox.add(hboxVariables);
        contentBox.add(hboxCalculate);
        contentBox.add(Box.createVerticalGlue());

        mainFrame.getContentPane().add(contentBox);
    }

    private Double calculateFirstFormula(Double x, Double y, Double z) {

        return Math.sin(Math.log(y) + Math.sin(PI * y * y)) * Math.pow(x * x + Math.sin(z) + Math.exp(Math.cos(z)), 1. / 4.);
    }

    private Double calculateSecondFormula(Double x, Double y, Double z) {
        return (Math.exp(1. / 2. * x)) / (Math.sqrt(z + y) * Math.log(Math.pow(x, z)));
    }

    public void setVisible(boolean state) {
        mainFrame.setVisible(state);
    }
}