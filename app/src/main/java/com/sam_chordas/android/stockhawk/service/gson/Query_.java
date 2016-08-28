
package com.sam_chordas.android.stockhawk.service.gson;

import java.util.HashMap;
import java.util.Map;

public class Query_ {

    private String executionStartTime;
    private String executionStopTime;
    private String executionTime;
    private String params;
    private String content;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The executionStartTime
     */
    public String getExecutionStartTime() {
        return executionStartTime;
    }

    /**
     * 
     * @param executionStartTime
     *     The execution-start-time
     */
    public void setExecutionStartTime(String executionStartTime) {
        this.executionStartTime = executionStartTime;
    }

    /**
     * 
     * @return
     *     The executionStopTime
     */
    public String getExecutionStopTime() {
        return executionStopTime;
    }

    /**
     * 
     * @param executionStopTime
     *     The execution-stop-time
     */
    public void setExecutionStopTime(String executionStopTime) {
        this.executionStopTime = executionStopTime;
    }

    /**
     * 
     * @return
     *     The executionTime
     */
    public String getExecutionTime() {
        return executionTime;
    }

    /**
     * 
     * @param executionTime
     *     The execution-time
     */
    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    /**
     * 
     * @return
     *     The params
     */
    public String getParams() {
        return params;
    }

    /**
     * 
     * @param params
     *     The params
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * 
     * @return
     *     The content
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
