package com.rjohnson19.esdapi.feed;

import com.rjohnson19.esdapi.feed.dto.FeedDTO;
import lombok.NonNull;

import java.net.URL;

/**
 * Reads an RSS or Atom feed and provides parsed entries.
 */
public interface RSSFeedReader {
    /**
     * Read a feed from a URL.
     * @param feedUrl URL of an RSS or Atom feed.
     * @return FeedDTO containing feed metadata and entries.
     */
    FeedDTO read(@NonNull URL feedUrl);
}
