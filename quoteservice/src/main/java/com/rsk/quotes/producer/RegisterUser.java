package com.rsk.quotes.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.rsk.quotes.QuoteException;
import com.rsk.quotes.jndi.JndiServices;
import com.rsk.quotes.messages.UserRegistration;

import javax.jms.DeliveryMode;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.naming.NamingException;

public class RegisterUser {

    private JndiServices jndiServices;
    private String factoryName;
    String queueName;

    public RegisterUser(JndiServices jndiServices, String factoryName, String queueName) throws QuoteException, NamingException {
        this.jndiServices = jndiServices;
        this.factoryName = factoryName;
        this.queueName = queueName;

        jndiServices.startConnections(factoryName);
    }

    public void sendMessage(JMSContext context, UserRegistration userRegistration) {
        try {

            Queue queue = (Queue) jndiServices.getInitialContext().lookup(queueName);

            ObjectMapper mapper = new ObjectMapper();
            String userRegJson = mapper.writeValueAsString(userRegistration);
            JMSProducer producer = context.createProducer();
            if (!userRegistration.deliveryMode) {
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            }
            if (userRegistration.timeToLive != 0) {
                producer.setTimeToLive(userRegistration.timeToLive);
            }
            producer.send(queue, userRegJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
