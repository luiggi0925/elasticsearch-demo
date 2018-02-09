package pe.edu.ltmj.repository.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.edu.ltmj.domain.BankAccount;
import pe.edu.ltmj.repository.BankAccountRepository;

@Repository
public class BankAccountRepositoryTransportImpl implements BankAccountRepository {

    @Autowired
    ElasticSearchTransportConnector connector;
    
    @Autowired
    ObjectMapper mapper;

    @Override
    public List<BankAccount> getAll() {
        List<BankAccount> result = new ArrayList<>();
        SearchResponse response = connector.getClient().prepareSearch("bank")
            .setTypes("account")
            .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
            .setQuery(QueryBuilders.matchAllQuery())
            .setFrom(0).setSize(1000)
            .get();
        response.getHits().forEach( hit -> {
            try {
                result.add(mapper.readValue(hit.getSourceAsString(), BankAccount.class));
            } catch (IOException e) {
                System.out.println("fucking error while parsing results: " + e);
                e.printStackTrace(System.out);
            }
        } );
        return result;
    }

}
