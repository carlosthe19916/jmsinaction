package io.github.carlosthe19916;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Producer {

    public static void main(String[] args) {
        System.out.println("Jms In Action");

        ConnectionFactory connectionFactory = null;
        Queue queue = null;

        try {
            InitialContext initialContext = new InitialContext();
            queue = (Queue) initialContext.lookup("jms/P2PQueue");
            connectionFactory = (ConnectionFactory) initialContext.lookup("jms/__defaultConnectionFactory");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try (JMSContext context = connectionFactory.createContext()) {
            TextMessage textMessage = context.createTextMessage("Mensage desde el producer");
            context.createProducer().send(queue, textMessage);
        }

        System.out.printf("Message sent");

    }
}