package pe.edu.ltmj.repository.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Repository;

@Repository
public class ElasticSearchTransportConnector {

    private TransportClient client;

    @PostConstruct
    public void init() {
        try {
            System.out.println("Opening ElasticSearch connector");
            client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
            System.out.println("ElasticSearch connector opened without issues.");
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("No se pudo conectar a elasticsearch.", e);
        }
    }

    public TransportClient getClient() {
        return client;
    }

    @PreDestroy
    public void finish() {
        System.out.println("Closing ElasticSearch connector");
        client.close();
    }
}
