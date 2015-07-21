package com.marfeel.itomas.webcrawler;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UriEntryEditor extends PropertyEditorSupport  {


	@Override
    public void setAsText(String text) throws IllegalArgumentException {
        ObjectMapper mapper = new ObjectMapper();

        List<UriEntry> values = new ArrayList<UriEntry>();

        try {
            JsonNode root = mapper.readTree(text);
            Iterator<JsonNode> it = root.iterator();
            while(it.hasNext()){
            	JsonNode node = it.next();
            	UriEntry value = new UriEntry();
            	value.setUri(node.path("url").asText());
            	value.setRank(node.path("rank").asText());
            	values.add(value);
            }
            
        } catch (IOException e) {
            // handle error
        }

        setValue(values);
    }
}