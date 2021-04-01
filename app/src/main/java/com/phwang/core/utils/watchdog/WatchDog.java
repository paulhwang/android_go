/*
 ******************************************************************************
 *
 *  Copyright (c) 2021 phwang. All rights reserved.
 *
 ******************************************************************************
 */

package com.phwang.core.utils.watchdog;

import android.os.Handler;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

public class WatchDog {
    private Timer timer_ = null;
    private TimerTask timerTask_ = null;
    private Handler handler_= null;
    private int delay_;
    private int period_;

    public WatchDog(int delay_val, int period_val) {
        this.delay_ = delay_val;
        this.period_ = period_val;
    }

    public void start() {
        if (this.timer_ != null) {
            return;
        }
        handler_ = new Handler();
        timer_ = new Timer();
        timerTask_ = new TimerTask() {
            int count;
            @Override
            public void run() {
                handler_.post(new Runnable() {
                    @Override
                    public void run() {
                        count++;
                        Log.e("++++++++++++++++++", "watchDog()");
                    }
                });
            }
        };
        timer_.schedule(timerTask_, this.delay_, this.period_);
    }

    public void cancel() {
        if (this.timer_ != null) {
            this.timer_.cancel();
            this.timer_ = null;
            this.timerTask_ = null;
            this.handler_ = null;
        }
    }
}
