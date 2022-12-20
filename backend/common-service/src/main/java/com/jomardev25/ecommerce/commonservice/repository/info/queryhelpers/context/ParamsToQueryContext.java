package com.jomardev25.ecommerce.commonservice.repository.info.queryhelpers.context;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ParamsToQueryContext {

    String sortBy;
    HashMap<Integer, Object> mapParams;
    List<String> conditions;
    String[] pageInfo;

    public ParamsToQueryContext(String sortBy, HashMap<Integer, Object> mapParams,
                                List<String> conditions, String[] pageInfo) {
        this.sortBy = sortBy;
        this.mapParams = mapParams;
        this.conditions = conditions;
        this.pageInfo = pageInfo;
    }

}