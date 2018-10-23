package com.neo.common.json;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * depend on jackson
 * @author Diamond
 */
public class CustomerJsonSerializer {


    ObjectMapper mapper = new ObjectMapper();
    JacksonJsonFilter jacksonFilter = new JacksonJsonFilter();

    /**
     * @param clazz target type
     * @param include include fields
     * @param filter filter fields
     */
    public void filter(Class<?> clazz, String include, String filter) {
        if (clazz == null) return;
        if (StringUtils.isNotBlank(include)) {
            jacksonFilter.include(clazz, include.split(","));
        }
        if (StringUtils.isNotBlank(filter)) {
            jacksonFilter.filter(clazz, filter.split(","));
        }
        mapper.addMixIn(clazz, jacksonFilter.getClass());
    }

    public String toJson(Object object) throws JsonProcessingException {
        mapper.setFilters(jacksonFilter);
        return mapper.writeValueAsString(object);
    }
    public void filter(JSON json) {
    	if(json.type() == null) return;
    	for(int i=0;i<json.type().length;i++){
    		Class clazz = json.type()[i];
    		String inc = null;
    		String fil = null;
    		if(json.include()!=null && i<json.include().length){
    			inc = json.include()[i];
    		}
    		if(json.filter()!=null && i<json.filter().length){
    			fil = json.filter()[i];
    		}
            this.filter(json.type()[i], inc, fil);
    	}

    }
}