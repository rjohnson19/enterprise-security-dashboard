package com.rjohnson19.esdapi.advisory.service;

import com.rjohnson19.esdapi.advisory.entity.AdvisoryListItem;
import com.rjohnson19.esdapi.advisory.provider.AdvisoryProvider;

import java.util.List;

/**
 * Service for managing advisories.
 */
public interface AdvisoryService {
    /**
     * Get list of advisories.
     * @return List of AdvisoryListItem objects.
     */
    List<AdvisoryListItem> getAdvisories();

    /**
     * Register a provider with the service.
     * @param provider AdvisoryProvider.
     */
    void registerProvider(AdvisoryProvider provider);
}
