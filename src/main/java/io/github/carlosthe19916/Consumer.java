package io.github.carlosthe19916;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by carlos on 5/4/18.
 */
public class Consumer {

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
            JMSConsumer consumer = context.createConsumer(queue);
            String body = consumer.receiveBody(String.class);
            System.out.println(body);
        }
    }
}
