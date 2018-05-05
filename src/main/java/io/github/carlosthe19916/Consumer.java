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

        ConnectionFactory connectionFactory;
        Connection connection = null;

        try {
            InitialContext initialContext = new InitialContext();
            Queue queue = (Queue) initialContext.lookup("jms/P2PQueue");
            connectionFactory = (ConnectionFactory) initialContext.lookup("jms/__defaultConnectionFactory");

            connection =connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();

            MessageConsumer messageConsumer = session.createConsumer(queue);
            TextMessage textMessage = (TextMessage) messageConsumer.receive();

            String body = textMessage.getText();
            System.out.printf(body);

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
