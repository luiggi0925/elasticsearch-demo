package pe.edu.ltmj.repository.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.edu.ltmj.domain.BankAccount;
import pe.edu.ltmj.repository.BankAccountRepository;

@Repository
public class BankAccountRepositoryRestHighImpl implements BankAccountRepository {

    @Autowired
    ElasticSearchRestConnector connector;
    
    @Autowired
    ObjectMapper mapper;

    @Override
    public List<BankAccount> getAll() {
        List<BankAccount> result = new ArrayList<>();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.from(0).size(1000)
        ;
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(searchSourceBuilder);

        try {
            SearchResponse searchResponse = connector.getClient().search(searchRequest);
            searchResponse.getHits().forEach( hit -> {
                try {
                    result.add(mapper.readValue(hit.getSourceAsString(), BankAccount.class));
                } catch (IOException e) {
                    System.out.println("fucking error while parsing results: " + e);
                    e.printStackTrace(System.out);
                }
            } );
        } catch (IOException e) {
            System.out.println("Issue when performing query against ElasticSearch.");
            e.printStackTrace(System.out);
        }
        return result;
    }

}
