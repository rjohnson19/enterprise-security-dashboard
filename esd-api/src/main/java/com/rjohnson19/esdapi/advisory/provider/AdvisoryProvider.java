package com.rjohnson19.esdapi.advisory.provider;

import com.rjohnson19.esdapi.advisory.entity.AdvisoryListItem;

import java.util.List;

/**
 * Base interface representing a component that can provide advisories.
 */
public interface AdvisoryProvider {
    /**
     * Get a list of advisories.
     * @return List of AdvisoryListItem objects.
     */
    List<AdvisoryListItem> getAdvisories();
}
