package com.rsk.quotes.presentation.messages;


import com.rsk.quotes.models.MessageDetails;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class MessagesPresentation implements Initializable {

    @Inject
    private ObservableList<MessageDetails> messageDetails;

    @FXML
    TableView<MessageDetails> messagesTable;

    @FXML
    private TableColumn<MessageDetails, String> messageTypeColumn;
    @FXML
    private TableColumn<MessageDetails, String> messageDataColumn;
    @FXML
    private TableColumn<MessageDetails, String> messageDateColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        messageDetails.add(new MessageDetails("","",""));

        messageTypeColumn.setCellValueFactory(cellData -> cellData.getValue().messageTypeProperty());
        messageDataColumn.setCellValueFactory(cellData -> cellData.getValue().messageBodyProperty());
        messageDateColumn.setCellValueFactory(cellData -> cellData.getValue().dateTimeProperty());

        messagesTable.setItems(messageDetails);

    }
}
