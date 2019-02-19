package com.capitalone.dashboard.service;

import com.capitalone.dashboard.settings.ApiSettings;
import com.capitalone.dashboard.util.Encryption;
import com.capitalone.dashboard.util.EncryptionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptionServiceImpl.class);
    private final ApiSettings apiSettings;

    @Autowired
    public EncryptionServiceImpl(final ApiSettings apiSettings) {
        this.apiSettings = apiSettings;
    }

    @Override
    public String encrypt(String message) {
        String key = apiSettings.getKey();
        String returnString = "ERROR";
        if (key != null && !key.isEmpty()) {
            try {
                returnString = Encryption.encryptString(message, key);
            } catch (EncryptionException ee) {
                LOGGER.debug("Caught encryption exception", ee);
            }
        }
        return returnString;
    }
}
