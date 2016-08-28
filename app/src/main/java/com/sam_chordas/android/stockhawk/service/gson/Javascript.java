
package com.sam_chordas.android.stockhawk.service.gson;

import java.util.HashMap;
import java.util.Map;


public class Javascript {

    private String executionStartTime;
    private String executionStopTime;
    private String executionTime;
    private String instructionsUsed;
    private String tableName;
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
     *     The instructionsUsed
     */
    public String getInstructionsUsed() {
        return instructionsUsed;
    }

    /**
     * 
     * @param instructionsUsed
     *     The instructions-used
     */
    public void setInstructionsUsed(String instructionsUsed) {
        this.instructionsUsed = instructionsUsed;
    }

    /**
     * 
     * @return
     *     The tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 
     * @param tableName
     *     The table-name
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
