package com.rsk.quotes.presentation.main;

import com.rsk.quotes.models.CarDetailsMessage;
import com.rsk.quotes.presentation.cardetails.CardetailsView;
import com.rsk.quotes.presentation.jndi.JndiView;
import com.rsk.quotes.presentation.quoteList.QuotelistView;
import com.rsk.quotes.presentation.userdetails.UserdetailsView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kevinj on 01/05/2017.
 */
public class MainPresenter implements Initializable {
    @FXML
    AnchorPane jndiInput;

    @FXML
    AnchorPane carDetails;

    @FXML
    AnchorPane userDetails;
    
    @FXML
    Button getQuotes;

    @FXML
    AnchorPane quoteList;

    @Inject
    CarDetailsMessage carDetailsMessage;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JndiView jndiView = new JndiView();
        CardetailsView cardetailsView = new CardetailsView();
        UserdetailsView userdetailsView = new UserdetailsView();
        QuotelistView quotelistView = new QuotelistView();

        jndiInput.getChildren().addAll(jndiView.getView());
        carDetails.getChildren().addAll(cardetailsView.getView());
        userDetails.getChildren().addAll(userdetailsView.getView());
        quoteList.getChildren().addAll(quotelistView.getView());
    }

    public void getQuotes(ActionEvent actionEvent) {
        System.out.println("QueueName is: " + carDetailsMessage.getQueueName());

    }
}
