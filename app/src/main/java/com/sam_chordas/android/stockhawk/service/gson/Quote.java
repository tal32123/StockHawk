
package com.sam_chordas.android.stockhawk.service.gson;

import java.util.HashMap;
import java.util.Map;

public class Quote {

    private String Symbol;
    private String Date;
    private String Open;
    private String High;
    private String Low;
    private String Close;
    private String Volume;
    private String Adj_Close;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Symbol
     */
    public String getSymbol() {
        return Symbol;
    }

    /**
     * 
     * @param symbol
     *     The Symbol
     */
    public void setSymbol(String symbol) {
        this.Symbol = symbol;
    }

    /**
     * 
     * @return
     *     The Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * 
     * @param date
     *     The Date
     */
    public void setDate(String date) {
        this.Date = date;
    }

    /**
     * 
     * @return
     *     The Open
     */
    public String getOpen() {
        return Open;
    }

    /**
     * 
     * @param open
     *     The Open
     */
    public void setOpen(String open) {
        this.Open = open;
    }

    /**
     * 
     * @return
     *     The High
     */
    public String getHigh() {
        return High;
    }

    /**
     * 
     * @param high
     *     The High
     */
    public void setHigh(String high) {
        this.High = high;
    }

    /**
     * 
     * @return
     *     The Low
     */
    public String getLow() {
        return Low;
    }

    /**
     * 
     * @param low
     *     The Low
     */
    public void setLow(String low) {
        this.Low = low;
    }

    /**
     * 
     * @return
     *     The Close
     */
    public String getClose() {
        return Close;
    }

    /**
     * 
     * @param close
     *     The Close
     */
    public void setClose(String close) {
        this.Close = close;
    }

    /**
     * 
     * @return
     *     The Volume
     */
    public String getVolume() {
        return Volume;
    }

    /**
     * 
     * @param volume
     *     The Volume
     */
    public void setVolume(String volume) {
        this.Volume = volume;
    }

    /**
     * 
     * @return
     *     The Adj_Close
     */
    public String getAdj_Close() {
        return Adj_Close;
    }

    /**
     * 
     * @param adj_Close
     *     The Adj_Close
     */
    public void setAdj_Close(String adj_Close) {
        this.Adj_Close = adj_Close;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
