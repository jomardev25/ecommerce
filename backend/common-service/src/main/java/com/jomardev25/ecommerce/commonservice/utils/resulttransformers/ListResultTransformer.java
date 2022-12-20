package com.jomardev25.ecommerce.commonservice.utils.resulttransformers;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jomardev25.ecommerce.commonservice.dto.FilterAttributesDTO;
import com.jomardev25.ecommerce.commonservice.dto.FilterAttributesWithTotalItemsDTO;
import com.jomardev25.ecommerce.commonservice.dto.SearchSuggestionForThreeAttrDTO;
import com.jomardev25.ecommerce.commonservice.dto.SearchSuggestionForTwoAttrDTO;

import java.util.HashMap;
import java.util.List;

public class ListResultTransformer {

    @SuppressWarnings({ "unchecked", "deprecation" })
	public List<FilterAttributesWithTotalItemsDTO> getFilterAttributesWithTotalItemsResultTransformer(String queryStr, HashMap<Integer, Object> mapParams,
                                                       EntityManager entityManager) {

        Query query = entityManager.createQuery(queryStr);

        if(mapParams != null) {
            mapParams.forEach(query::setParameter);
        }
        return query.unwrap(org.hibernate.query.Query.class)
                	.setResultTransformer((IListResultTransformer)
                        (tuple, aliases) -> new FilterAttributesWithTotalItemsDTO((Integer) tuple[0],
                                (String) tuple[1], (Long) tuple[2]
                        )
                ).getResultList();
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
	public List<FilterAttributesDTO>
    getFilterAttributesResultTransformer(String queryStr, HashMap<Integer, Object> mapParams, EntityManager entityManager) {

        Query query = entityManager.createQuery(queryStr);

        if(mapParams != null) {
            mapParams.forEach(query::setParameter);
        }

        return query.unwrap(org.hibernate.query.Query.class)
                .setResultTransformer((IListResultTransformer)
                        (tuple, aliases) -> new FilterAttributesDTO((Integer) tuple[0], (String) tuple[1]
                        )
                ).setMaxResults(10)
                .getResultList();
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
	public List<SearchSuggestionForThreeAttrDTO>
    getSearchSuggestionForThreeAttrResultTransformer(String queryStr,
                                         EntityManager entityManager) {

        Query query = entityManager.createQuery(queryStr);
        return query.unwrap(org.hibernate.query.Query.class)
                .setResultTransformer((IListResultTransformer)
                        (tuple, aliases) -> new SearchSuggestionForThreeAttrDTO((Integer) tuple[0], (String) tuple[1],
                                (Integer) tuple[2], (String) tuple[3],(Integer) tuple[4], (String) tuple[5])
                ).getResultList();
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
	public List<SearchSuggestionForTwoAttrDTO>
    getSearchSuggestionForTwoAttrResultTransformer(String queryStr,
                                         EntityManager entityManager) {

        Query query = entityManager.createQuery(queryStr);
        return query.unwrap(org.hibernate.query.Query.class)
                .setResultTransformer((IListResultTransformer)
                        (tuple, aliases) -> new SearchSuggestionForTwoAttrDTO((Integer) tuple[0], (String) tuple[1],
                                (Integer) tuple[2], (String) tuple[3])
                ).getResultList();
    }
}
