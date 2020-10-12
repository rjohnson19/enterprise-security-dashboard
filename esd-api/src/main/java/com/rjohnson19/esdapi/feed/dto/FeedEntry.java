package com.rjohnson19.esdapi.feed.dto;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class FeedEntry {
    private String id;
    private String name;
    private Date date;
    private String externalUrl;
    private String content;
    private Map<String, Object> feedData;
}
