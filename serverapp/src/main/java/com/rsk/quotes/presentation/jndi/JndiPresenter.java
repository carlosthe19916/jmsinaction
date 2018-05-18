package com.rsk.quotes.presentation.jndi;

import com.rsk.quotes.QuoteException;
import com.rsk.quotes.jndi.JndiServices;
import com.rsk.quotes.models.JMSDetails;
import com.rsk.quotes.models.MessageDetails;
import com.rsk.quotes.consumer.UserRegistration;
import com.rsk.quotes.tasks.RegisterUserTask;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.inject.Inject;
import javax.naming.NamingException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;

public class JndiPresenter implements Initializable {

    @Inject
    ExecutorService executorService;

    @FXML
    Label lblTitle;

    @FXML
    Label lblQueueName;

    @FXML
    TextField queueName;

    @FXML
    Label lblTopicName;

    @FXML
    TextField topicName;

    @FXML
    Button btnListen;

    @Inject
    JMSDetails jmsDetails;

    @Inject
    String JMSQueueName;

    @Inject
    String JMSTopicName;

    @Inject
    JndiServices quotesJndi;

    @Inject
    private ObservableList<MessageDetails> messageDetails;

    public void addRegistration(String message) {
        System.out.println("addRegistration: " + messageDetails);
        MessageDetails details = new MessageDetails("RegisterUser", new Date().toString(), message);
        messageDetails.add(details);
    }

    UserRegistration userRegistration;

    @FXML
    private void startListeners(ActionEvent evt) throws QuoteException, NamingException {
        userRegistration = new UserRegistration(quotesJndi, "jms/__defaultConnectionFactory", queueName.getText());
        RegisterUserTask registerUserTask =  new RegisterUserTask(userRegistration, this::addRegistration);
        executorService.submit(registerUserTask);
        // multiple quote tasks; set number in configuration file
        // each task will wait for a random short time
//        GetQuoteTask getQuoteTask =  new GetQuoteTask();

        // add tasks to executor
        System.out.println(evt.getEventType());
        btnListen.setDisable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lblQueueName.setText(resources.getString("queueLabel"));
        lblTopicName.setText(resources.getString("topicLabel"));

        ToggleGroup ackGroup = new ToggleGroup();

        btnListen.setText(resources.getString("startLabel"));

        queueName.textProperty().bindBidirectional(jmsDetails.queueNameProperty());
        queueName.setText(JMSQueueName);
        topicName.textProperty().bindBidirectional(jmsDetails.topicNameProperty());
        topicName.setText(JMSTopicName);
        lblTitle.setText(resources.getString("title"));

    }

}
