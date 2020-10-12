package com.rjohnson19.esdapi.advisory.provider;

import com.rjohnson19.esdapi.advisory.entity.AdvisoryListItem;
import com.rjohnson19.esdapi.advisory.entity.AdvisoryStatus;
import com.rjohnson19.esdapi.feed.RSSFeedReader;
import com.rjohnson19.esdapi.feed.dto.FeedDTO;
import com.rjohnson19.esdapi.feed.dto.FeedEntry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Base class for advisory providers that read from a remote RSS/Atom feed.
 */
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public abstract class AbstractRemoteFeedAdvisoryProvider implements AdvisoryProvider {
    private final RSSFeedReader rssFeedReader;
    /**
     * Get the remote URL to load the feed from.
     * @return URL to load from.
     */
    abstract String getRemoteUrl();

    /**
     * Predicate for filtering feed entries.
     * Defaults to including all entries.
     * @return Predicate for filtering FeedEntry
     */
    protected Predicate<FeedEntry> getEntryFilterPredicate() {
        return feedEntry -> true;
    }

    /**
     * Convert an entry from a feed to an AdvisoryListItem.
     * The base implementation is very simple and is missing a few things, such as vendor, product, and severity.
     * @param feedEntry FeedEntry to convert.
     * @return AdvisoryListItem.
     */
    protected AdvisoryListItem toListItem(final FeedEntry feedEntry) {
        final AdvisoryListItem listItem = new AdvisoryListItem();
        listItem.setId(feedEntry.getId());
        listItem.setDate(feedEntry.getDate());
        listItem.setName(feedEntry.getName());
        listItem.setExternalUrl(feedEntry.getExternalUrl());
        listItem.setStatus(AdvisoryStatus.NEW);
        return listItem;
    }

    @Override
    public List<AdvisoryListItem> getAdvisories() {
        try {
            final FeedDTO feedDTO = rssFeedReader.read(new URL(getRemoteUrl()));
            if (Objects.isNull(feedDTO)) {
                LOG.error("rssFeedReader returned null (failed) for {}", getRemoteUrl());
            }
            return feedDTO.getEntries().stream()
                    .filter(getEntryFilterPredicate())
                    .map(this::toListItem)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }
}
