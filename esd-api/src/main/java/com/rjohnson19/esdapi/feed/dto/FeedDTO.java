package com.rjohnson19.esdapi.feed.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FeedDTO {
    private String title;
    private Date publishedDate;
    private List<FeedEntry> entries;
}
