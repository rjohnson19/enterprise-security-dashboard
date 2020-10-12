package com.rjohnson19.esdapi.feed;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/**
 * Reads an RSS or Atom feed and provides parsed entries.
 */
@Slf4j
@Component
public class RSSFeedReader {
    public SyndFeed read(final @NonNull URL feedUrl) {
        LOG.trace("Reading from {}", feedUrl);
        final SyndFeedInput input = new SyndFeedInput();
        try (final XmlReader xmlReader = new XmlReader(feedUrl)) {
            return input.build(xmlReader);
        } catch (FeedException e) {
            LOG.error("Failed to parse feed from " + feedUrl, e);
            return null;
        } catch (IOException e) {
            LOG.error("Failed to load / parse feed from " + feedUrl, e);
            return null;
        }
    }
}
