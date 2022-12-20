package com.jomardev25.ecommerce.commonservice.utils.resulttransformers;

import org.hibernate.transform.ResultTransformer;

import java.util.List;

@FunctionalInterface
public interface IListResultTransformer extends ResultTransformer {

    /**
     * Default implementation returning the tuples list as-is.
     *
     * @param tuples tuples list
     * @return tuples list
     */
    @SuppressWarnings("rawtypes")
	@Override
    default List transformList(List tuples) {
        return tuples;
    }

}