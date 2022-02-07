package com.example.tipcalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TipCalculatorController implements Initializable {


    @FXML
    private Label tip;

    @FXML
    private TextField billAmountTextField;

    @FXML
    private Spinner<Integer> tipPercentageSpinner;

    @FXML
    private Button calculateTipButton;

    @FXML
    private TextField tipAmountTextField;

    @FXML
    private TextField totalAmountTextField;

    @FXML
    void calculateTip(ActionEvent event) {
        calculateTip();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipAmountTextField.setEditable(false);
        totalAmountTextField.setEditable(false);
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 20, 5);
        tipPercentageSpinner.setValueFactory(spinnerValueFactory);

        // to add event listener we need to convert spinner into TextField
        tipPercentageSpinner.setEditable(true);
        TextField spinnerTextField = tipPercentageSpinner.getEditor();

        //using lambda expression to add event listener
        spinnerTextField.textProperty().addListener((obs, oldValue, newValue) ->{
           try{
               Integer.parseInt(newValue);
               spinnerTextField.setText(newValue);
               calculateTip();
           }
           catch(Exception e) {
               spinnerTextField.setText(oldValue);
            }

        });
    }

    private void calculateTip(){
        billAmountTextField.setEditable(false);
        double billAmount = Double.parseDouble(billAmountTextField.getText());
        int tipPercentage =(tipPercentageSpinner.getValue());
        double tipAmount = billAmount * tipPercentage/100;
        tipAmountTextField.setText(String.format("%.2f", tipAmount));
        totalAmountTextField.setText(String.format("%.2f",tipAmount + billAmount ));
    }

}

