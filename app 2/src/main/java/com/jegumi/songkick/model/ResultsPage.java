package com.jegumi.songkick.model;

import java.io.Serializable;

public class ResultsPage implements Serializable {

    public String status;
    public Result results;
    public int perPage;
    public int page;
    public int totalEntries;
}
