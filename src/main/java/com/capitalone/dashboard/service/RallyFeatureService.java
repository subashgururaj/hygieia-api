package com.capitalone.dashboard.service;

import com.capitalone.dashboard.model.CollectorItem;
import com.capitalone.dashboard.model.RallyFeature;
import com.capitalone.dashboard.request.RallyFeatureRequest;
import com.capitalone.dashboard.response.RallyBurnDownResponse;

import java.util.List;
public interface RallyFeatureService {
	
	CollectorItem getCollectorItem(RallyFeatureRequest request);
	List<RallyFeature> rallyProjectIterationType(String projectId);
	
	
	List<RallyFeature> rallyWidgetDataDetails(CollectorItem item);
	
	RallyBurnDownResponse rallyBurnDownData(RallyFeature request);

}
