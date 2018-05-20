package com.rsk.quotes.jndi;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QuotesJndi implements JndiServices {

    ConnectionFactory connectionFactory = null;
    InitialContext initialContext;
    JMSContext context = null;

    @Override
    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    @Override
    public InitialContext getInitialContext() {
        return initialContext;
    }

    @Override
    public boolean startConnections(String connectionFactoryName) throws NamingException {
        if (initialContext == null) {
            initialContext = new InitialContext();
            connectionFactory = (ConnectionFactory) initialContext.lookup(connectionFactoryName);
            return true;
        } else {
            return false;
        }
    }

    public JMSContext getContext() {
        if(connectionFactory == null) {
            throw new RuntimeException("Connection has not been started");
        }
        if (context == null) {
            context = connectionFactory.createContext();
        }
        return context;
    }



}
