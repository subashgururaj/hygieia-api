package com.capitalone.dashboard.util;

import java.util.HashMap;
import java.util.Map;

public class WidgetOptionsBuilder {
    private final Map<String, Object> options = new HashMap<>();

    public Map<String, Object> get() {
        return options;
    }

    public WidgetOptionsBuilder put(String key, Object value) {
        options.put(key, value);
        return this;
    }
}
