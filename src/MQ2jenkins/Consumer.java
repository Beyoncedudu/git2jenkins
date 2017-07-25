package MQ2jenkins;

/**
 * Created by 85081 on 2017/7/19.
 */
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import MQ2jenkins.jenkinsSender;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.JSONObject;

public class Consumer {

    public static JSONObject consume(String url) {
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session;
        Destination destination;
        MessageConsumer consumer;
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "http://localhost:61616");
        TextMessage message=null;
        JSONObject js=null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.FALSE,
                    Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("JsonQueue");
            consumer = session.createConsumer(destination);
            message = (TextMessage) consumer.receive();
            js=new JSONObject(message);
            jenkinsSender jS=new jenkinsSender();
            jS.sendPost(url,js);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
        return js;
    }
}
