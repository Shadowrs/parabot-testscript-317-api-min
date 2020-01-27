package com.larxstar.anyserv.strats;

import com.larxstar.anyserv.AnyScript;
import org.parabot.environment.scripts.framework.Strategy;

public abstract class BossStrat implements Strategy {

    public final AnyScript root;

    public BossStrat(AnyScript main) {
        this.root = main;
    }


}
