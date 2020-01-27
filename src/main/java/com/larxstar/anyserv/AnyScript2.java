package com.larxstar.anyserv;

import org.parabot.api.output.Logger;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.ScriptManifest;
import org.rev317.min.api.methods.Npcs;

import java.awt.*;

@ScriptManifest(author = "Shadowrs",
        name = "Universal Test Script 2",
        category = Category.OTHER,
        version = 1.0,
        description = "",
        servers = {"Novea", "Zenyte", "Dreamscape", "dreamscape", "ds", "battlescape"})
public class AnyScript2 extends org.parabot.environment.scripts.Script implements Paintable {

    @Override
    public void paint(Graphics graphics) {

    }

    @Override
    public void onFinish() {
        super.onFinish();
    }

    @Override
    public boolean onExecute() {
        Logger.debug("", "Close NPCs: " + Npcs.getNearest().length);
        return false;
    }

}
