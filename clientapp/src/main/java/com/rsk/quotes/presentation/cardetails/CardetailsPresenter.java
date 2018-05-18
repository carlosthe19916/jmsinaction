package com.rsk.quotes.presentation.cardetails;

import com.rsk.quotes.models.CarDetailsMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kevinj on 02/05/2017.
 */
public class CardetailsPresenter implements Initializable {
    @FXML
    Label lblRegistration;

    @FXML
    Label lblValue;

    @FXML
    Label lblMileage;

    @FXML
    TextField registration;

    @FXML
    TextField value;

    @FXML
    TextField mileage;

    @Inject
    CarDetailsMessage carDetailsMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblRegistration.setText(resources.getString("registration"));
        lblValue.setText(resources.getString("value"));
        lblMileage.setText(resources.getString("mileage"));

    }
}
