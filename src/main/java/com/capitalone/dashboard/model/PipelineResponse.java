package com.capitalone.dashboard.model;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PipelineResponse {
    private String name;
    private ObjectId collectorItemId;
    private List<String> unmappedStages;
    private Map<String, List<PipelineResponseCommit>> stages = new LinkedHashMap<>();
    private String prodStage;
    private Map<String,String> orderMap = new LinkedHashMap<>();

    public Map<String, String> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<String, String> orderMap) {
        this.orderMap = orderMap;
    }
    public String getProdStage() { return prodStage; }

    public void setProdStage(String prodStage) { this.prodStage = prodStage; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectId getCollectorItemId() {
        return collectorItemId;
    }

    public void setCollectorItemId(ObjectId collectorItemId) {
        this.collectorItemId = collectorItemId;
    }

    public Map<String, List<PipelineResponseCommit>> getStages() {
        return stages;
    }

    public void setStages(Map<String, List<PipelineResponseCommit>> stages) {
        this.stages = stages;
    }

    public List<String> getUnmappedStages() {
        return unmappedStages;
    }

    public void setUnmappedStages(List<String> unmappedStages) {
        this.unmappedStages = unmappedStages;
    }

    public List<PipelineResponseCommit> getStageCommits(PipelineStage stage) {
        return stages.get(stage.getName());
    }

    public void setStageCommits(PipelineStage stage, List<PipelineResponseCommit> commits) {
        stages.put(stage.getName(), commits);
    }

    public void addToStage(PipelineStage stage, PipelineResponseCommit pipelineCommit) {
        List<PipelineResponseCommit> pipelineStage = stages.computeIfAbsent(stage.getName(), k -> new ArrayList<>());
        pipelineStage.add(pipelineCommit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PipelineResponse that = (PipelineResponse) o;

        if (!Objects.equals(name, that.name)) return false;
        if (!collectorItemId.equals(that.collectorItemId)) return false;
        return stages.equals(that.stages);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + collectorItemId.hashCode();
        result = 31 * result + stages.hashCode();
        return result;
    }
}
