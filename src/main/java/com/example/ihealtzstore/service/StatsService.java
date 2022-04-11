package com.example.ihealtzstore.service;

import com.example.ihealtzstore.model.view.StatsView;

public interface StatsService {
    void onRequest();
    StatsView getStats();
}
