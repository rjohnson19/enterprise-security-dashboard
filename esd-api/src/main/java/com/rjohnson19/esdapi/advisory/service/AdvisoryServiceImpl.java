package com.rjohnson19.esdapi.advisory.service;

import com.rjohnson19.esdapi.advisory.entity.AdvisoryListItem;
import com.rjohnson19.esdapi.advisory.provider.AdvisoryProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdvisoryServiceImpl implements AdvisoryService {
    private List<AdvisoryProvider> advisoryProviders;

    /**
     * Default constructor.
     */
    public AdvisoryServiceImpl() {
        this.advisoryProviders = new ArrayList<>();
    }

    @Override
    public void registerProvider(final AdvisoryProvider provider) {
        if (advisoryProviders.contains(provider)) {
            throw new IllegalArgumentException("Provider was already registered!");
        }
        advisoryProviders.add(provider);
        LOG.info("Registered provider {}", provider.getClass());
    }

    @Override
    public List<AdvisoryListItem> getAdvisories() {
        return advisoryProviders.parallelStream()
                .flatMap(advisoryProvider -> advisoryProvider.getAdvisories().stream())
                .collect(Collectors.toList());
    }
}
