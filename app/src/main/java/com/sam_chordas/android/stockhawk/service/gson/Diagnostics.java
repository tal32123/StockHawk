
package com.sam_chordas.android.stockhawk.service.gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Diagnostics {

    private List<Url> url = new ArrayList<Url>();
    private String publiclyCallable;
    private List<Cache> cache = new ArrayList<Cache>();
    private List<Query_> query = new ArrayList<Query_>();
    private Javascript javascript;
    private String userTime;
    private String serviceTime;
    private String buildVersion;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The url
     */
    public List<Url> getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(List<Url> url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The publiclyCallable
     */
    public String getPubliclyCallable() {
        return publiclyCallable;
    }

    /**
     * 
     * @param publiclyCallable
     *     The publiclyCallable
     */
    public void setPubliclyCallable(String publiclyCallable) {
        this.publiclyCallable = publiclyCallable;
    }

    /**
     * 
     * @return
     *     The cache
     */
    public List<Cache> getCache() {
        return cache;
    }

    /**
     * 
     * @param cache
     *     The cache
     */
    public void setCache(List<Cache> cache) {
        this.cache = cache;
    }

    /**
     * 
     * @return
     *     The query
     */
    public List<Query_> getQuery() {
        return query;
    }

    /**
     * 
     * @param query
     *     The query
     */
    public void setQuery(List<Query_> query) {
        this.query = query;
    }

    /**
     * 
     * @return
     *     The javascript
     */
    public Javascript getJavascript() {
        return javascript;
    }

    /**
     * 
     * @param javascript
     *     The javascript
     */
    public void setJavascript(Javascript javascript) {
        this.javascript = javascript;
    }

    /**
     * 
     * @return
     *     The userTime
     */
    public String getUserTime() {
        return userTime;
    }

    /**
     * 
     * @param userTime
     *     The user-time
     */
    public void setUserTime(String userTime) {
        this.userTime = userTime;
    }

    /**
     * 
     * @return
     *     The serviceTime
     */
    public String getServiceTime() {
        return serviceTime;
    }

    /**
     * 
     * @param serviceTime
     *     The service-time
     */
    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    /**
     * 
     * @return
     *     The buildVersion
     */
    public String getBuildVersion() {
        return buildVersion;
    }

    /**
     * 
     * @param buildVersion
     *     The build-version
     */
    public void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
