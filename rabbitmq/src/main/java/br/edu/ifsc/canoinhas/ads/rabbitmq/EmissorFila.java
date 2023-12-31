package br.edu.ifsc.canoinhas.ads.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmissorFila {

    private final static String QUEUE_NAME = "luciano";
    private final static String VHOST = "/";
    public final static String USER = "guest";
    public final static String PASSWD = "guest";
    public final static String HOST = "localhost";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setUsername(USER);
        factory.setPassword(PASSWD);
        factory.setVirtualHost(VHOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "[Luciano] Teste!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println("Mensagem enviada para a fila " + QUEUE_NAME);

        channel.close();
        connection.close();
    }
}
