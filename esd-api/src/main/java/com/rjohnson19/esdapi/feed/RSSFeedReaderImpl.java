package com.rjohnson19.esdapi.feed;

import com.rjohnson19.esdapi.Constants;
import com.rjohnson19.esdapi.feed.dto.FeedDTO;
import com.rjohnson19.esdapi.feed.dto.FeedEntry;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jdom2.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RSSFeedReaderImpl implements RSSFeedReader {

    @Override
    public final FeedDTO read(final @NonNull URL feedUrl) {
        LOG.trace("Reading from {}", feedUrl);
        final SyndFeedInput input = new SyndFeedInput();
        try (XmlReader xmlReader = new XmlReader(feedUrl)) {
            return toFeedDTO(input.build(xmlReader));
        } catch (FeedException e) {
            LOG.error("Failed to parse feed from " + feedUrl, e);
            return null;
        } catch (IOException e) {
            LOG.error("Failed to load / parse feed from " + feedUrl, e);
            return null;
        }
    }

    private FeedDTO toFeedDTO(final SyndFeed syndFeed) {
        final FeedDTO feedDTO = new FeedDTO();
        feedDTO.setTitle(syndFeed.getTitle());
        feedDTO.setPublishedDate(syndFeed.getPublishedDate());
        feedDTO.setEntries(syndFeed.getEntries().stream()
            .map(this::toFeedEntry)
            .collect(Collectors.toList()));
        return feedDTO;
    }

    private FeedEntry toFeedEntry(final SyndEntry syndEntry) {
        FeedEntry feedEntry = new FeedEntry();
        feedEntry.setId(syndEntry.getUri());
        feedEntry.setName(syndEntry.getTitle());
        feedEntry.setDate(syndEntry.getPublishedDate());
        feedEntry.setExternalUrl(syndEntry.getLink());
        feedEntry.setContent(Optional.ofNullable(syndEntry.getDescription())
                .map(SyndContent::getValue).orElse(Constants.EMPTY_STRING));
        Map<String, Object> feedData = new HashMap<>();
        extractForeignElements(syndEntry, feedData);
        feedEntry.setFeedData(feedData);
        return feedEntry;
    }

    private void extractForeignElements(final SyndEntry syndEntry, final Map<String, Object> feedData) {
        syndEntry.getForeignMarkup().forEach(foreign -> {
            if (foreign.getChildren().isEmpty() && !foreign.getTextTrim().isEmpty()) {
                feedData.put(foreign.getName(), foreign.getTextTrim());
            } else {
                List<String> childText = foreign.getChildren().stream()
                        .map(Element::getTextTrim)
                        .filter(text -> !text.isEmpty())
                        .collect(Collectors.toList());
                if (!childText.isEmpty()) {
                    feedData.put(foreign.getName(), childText);
                }
            }
        });
    }
}
