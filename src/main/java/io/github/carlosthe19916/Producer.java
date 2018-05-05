package io.github.carlosthe19916;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Producer {

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


            MessageProducer messageProducer = session.createProducer(queue);
            TextMessage textMessage = session.createTextMessage("Mensaje Enviado Jms In Action");
            messageProducer.send(textMessage);

            System.out.printf("Message sent");

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