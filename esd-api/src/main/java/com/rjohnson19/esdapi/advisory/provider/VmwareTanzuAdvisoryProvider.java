package com.rjohnson19.esdapi.advisory.provider;

import com.rjohnson19.esdapi.feed.RSSFeedReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VmwareTanzuAdvisoryProvider extends AbstractRemoteFeedAdvisoryProvider {
    /**
     * Constructor.
     * @param rssFeedReader RSSFeedReader
     * @param remoteUrl remoteUrl
     */
    public VmwareTanzuAdvisoryProvider(@Autowired final RSSFeedReader rssFeedReader,
                                       @Value("${feeds.vmware-tanzu.remoteurl}") final String remoteUrl) {
        super(rssFeedReader);
        this.remoteUrl = remoteUrl;
    }

    private final String remoteUrl;
    @Override
    String getRemoteUrl() {
        return remoteUrl;
    }
}
