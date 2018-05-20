package com.rsk.quotes.jndi;

import com.rsk.quotes.QuoteException;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by user on 5/8/2017.
 */
public interface JndiServices {
    ConnectionFactory getConnectionFactory();

    InitialContext getInitialContext();

    boolean startConnections(String connectionFactoryName) throws NamingException, QuoteException;

    JMSContext getContext();

}
