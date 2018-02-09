package pe.edu.ltmj.repository.impl;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Repository;

@Repository
public class ElasticSearchRestConnector {

    private RestHighLevelClient client;

    public RestHighLevelClient getClient() {
        return client;
    }

    @PostConstruct
    public void init() {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
    }

    @PreDestroy
    public void finish() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
