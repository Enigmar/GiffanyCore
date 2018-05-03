/*
 * Copyright (C) 2018. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 */

package de.linzn.leegianOS.internal.lifeObjects;

import java.util.concurrent.TimeUnit;

public class TimeData {
    public int delay;
    public int period;
    public TimeUnit timeUnit;

    public TimeData(int delay, int period, TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        this.delay = delay;
        this.period = period;
    }
}
