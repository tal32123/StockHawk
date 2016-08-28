
package com.sam_chordas.android.stockhawk.service.gson;

import java.util.HashMap;
import java.util.Map;

public class Quote {

    private String symbol;
    private String date;
    private String open;
    private String high;
    private String low;
    private String close;
    private String volume;
    private String adjClose;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * 
     * @param symbol
     *     The Symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The Date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     The open
     */
    public String getOpen() {
        return open;
    }

    /**
     * 
     * @param open
     *     The Open
     */
    public void setOpen(String open) {
        this.open = open;
    }

    /**
     * 
     * @return
     *     The high
     */
    public String getHigh() {
        return high;
    }

    /**
     * 
     * @param high
     *     The High
     */
    public void setHigh(String high) {
        this.high = high;
    }

    /**
     * 
     * @return
     *     The low
     */
    public String getLow() {
        return low;
    }

    /**
     * 
     * @param low
     *     The Low
     */
    public void setLow(String low) {
        this.low = low;
    }

    /**
     * 
     * @return
     *     The close
     */
    public String getClose() {
        return close;
    }

    /**
     * 
     * @param close
     *     The Close
     */
    public void setClose(String close) {
        this.close = close;
    }

    /**
     * 
     * @return
     *     The volume
     */
    public String getVolume() {
        return volume;
    }

    /**
     * 
     * @param volume
     *     The Volume
     */
    public void setVolume(String volume) {
        this.volume = volume;
    }

    /**
     * 
     * @return
     *     The adjClose
     */
    public String getAdjClose() {
        return adjClose;
    }

    /**
     * 
     * @param adjClose
     *     The Adj_Close
     */
    public void setAdjClose(String adjClose) {
        this.adjClose = adjClose;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
