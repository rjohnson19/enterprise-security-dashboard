package com.rjohnson19.esdapi.feed.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FeedDTO {
    String title;
    Date publishedDate;
    List<FeedEntry> entries;
}
