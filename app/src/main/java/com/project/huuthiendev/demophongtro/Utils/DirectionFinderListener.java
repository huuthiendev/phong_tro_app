package com.project.huuthiendev.demophongtro.Utils;

import com.project.huuthiendev.demophongtro.Model.Route;

import java.util.List;

/**
 * Created by HUUTHIENDEV on 10/9/2016.
 */

public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
