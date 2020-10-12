package com.rjohnson19.esdapi.advisory.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AdvisoryListItem {
    private String id;
    private String name;
    private Date date;
    private String externalUrl;
    private String vendor;
    private String product;
    private String severity;
    private AdvisoryStatus status;
}
