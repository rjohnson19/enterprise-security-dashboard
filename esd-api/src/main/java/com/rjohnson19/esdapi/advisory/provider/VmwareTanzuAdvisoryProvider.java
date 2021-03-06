package com.rjohnson19.esdapi.advisory.provider;

import com.rjohnson19.esdapi.Constants;
import com.rjohnson19.esdapi.advisory.entity.AdvisoryListItem;
import com.rjohnson19.esdapi.advisory.service.AdvisoryService;
import com.rjohnson19.esdapi.feed.RSSFeedReader;
import com.rjohnson19.esdapi.feed.dto.FeedEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class VmwareTanzuAdvisoryProvider extends AbstractRemoteFeedAdvisoryProvider {

    public static final String VERSIONS_AFFECTED = "versionsAffected";
    public static final String VENDOR = "vendor";
    public static final String SEVERITY = "severity";
    public static final String ID_PREFIX = "https://pivotal.io/security/";

    /**
     * Constructor.
     * @param rssFeedReader RSSFeedReader
     * @param advisoryService AdvisoryService.
     * @param remoteUrl remoteUrl
     */
    public VmwareTanzuAdvisoryProvider(@Autowired final RSSFeedReader rssFeedReader,
                                       @Autowired final AdvisoryService advisoryService,
                                       @Value("${feeds.vmware-tanzu.remoteurl}") final String remoteUrl) {
        super(rssFeedReader);
        this.remoteUrl = remoteUrl;
        advisoryService.registerProvider(this);
    }
    private final String remoteUrl;
    @Override
    String getRemoteUrl() {
        return remoteUrl;
    }

    @Override
    protected AdvisoryListItem toListItem(final FeedEntry feedEntry) {
        final AdvisoryListItem listItem = super.toListItem(feedEntry);
        listItem.setId(feedEntry.getId().replace(ID_PREFIX, Constants.EMPTY_STRING).toUpperCase());
        listItem.setVendor((String) feedEntry.getFeedData().getOrDefault(VENDOR, Constants.EMPTY_STRING));
        listItem.setSeverity((String) feedEntry.getFeedData().getOrDefault(SEVERITY, Constants.EMPTY_STRING));
        listItem.setProduct(getProduct(feedEntry.getFeedData()));
        return listItem;
    }

    @SuppressWarnings("unchecked")
    private String getProduct(final Map<String, Object> feedData) {
        final Object data = feedData.getOrDefault(VERSIONS_AFFECTED, Collections.emptyList());
        if (data instanceof List) {
            return String.join(", ", (List<String>) data);
        }
        return Constants.EMPTY_STRING;
    }
}
