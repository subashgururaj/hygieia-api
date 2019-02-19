package com.capitalone.dashboard.service;

import com.capitalone.dashboard.model.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConfigurationService {
	List<Configuration> insertConfigurationData(List<Configuration> config);

	List<Configuration> getConfigurationData();
}
