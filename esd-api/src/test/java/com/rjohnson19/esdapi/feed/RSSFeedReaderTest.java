package com.rjohnson19.esdapi.feed;

import com.rjohnson19.esdapi.feed.dto.FeedDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RSSFeedReaderTest {

    public static final String VMWARE_TANZU_PARSED_RSS_XML = "/test-feeds/vmware-tanzu-parsed-rss.xml";
    @Autowired
    private RSSFeedReader rssFeedReader;

    @Test
    public void readsVmwareTanzuParsedRss() {
        final URL vmwareTanzuParsedFeed = getClass().getResource(VMWARE_TANZU_PARSED_RSS_XML);
        assertNotNull("Feed missing from classpath!", vmwareTanzuParsedFeed);

        final FeedDTO feed = rssFeedReader.read(vmwareTanzuParsedFeed);
        assertNotNull("Feed should not be null", feed);
        LOG.info("Parsed feed: {}", feed);
    }
}