package com.rjohnson19.esdapi.advisory.controller;

import com.rjohnson19.esdapi.advisory.entity.AdvisoryListItem;
import com.rjohnson19.esdapi.advisory.service.AdvisoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AdvisoryControllerImpl {
    private final AdvisoryService advisoryService;
    /**
     * GET /advisories method.
     * @return Response with advisories.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/advisories", produces = "application/json")
    public ResponseEntity<List<AdvisoryListItem>> getAdvisories() {
        return ResponseEntity.ok(advisoryService.getAdvisories());
    }
}
