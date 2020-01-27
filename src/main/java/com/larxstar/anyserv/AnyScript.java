package com.larxstar.anyserv;

import com.larxstar.anyserv.fx.MainController;
import com.larxstar.anyserv.strats.impl.GUIStrat;
import org.parabot.core.ui.utils.UILog;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.ScriptManifest;

import java.awt.*;
import java.util.Arrays;

@ScriptManifest(author = "Shadowrs",
        name = "Universal Test Script",
        category = Category.OTHER,
        version = 1.0,
        description = "",
        servers = {"Novea", "Zenyte", "Dreamscape", "dreamscape", "ds", "battlescape"})
public class AnyScript extends org.parabot.environment.scripts.Script implements Paintable {

    public String args = null;
    public MainController.UIConfig uiConfig;

    @Override
    public void paint(Graphics graphics) {

    }

    @Override
    public void onFinish() {
        if (GUIStrat.INSTANCE.frame != null) {
            GUIStrat.INSTANCE.frame.dispose();
            GUIStrat.INSTANCE.frame = null;
        }
        super.onFinish();
    }

    @Override
    public boolean onExecute() {
        try {
            Class.forName("javafx.application.Application");
            System.out.println("you have javafx wtf");
        } catch (ClassNotFoundException e) {
            System.err.println("Missing javafx - cannot run script");
            UILog.log("Cannot run script", "Script cannot run without JavaFX. Please downgrade to Java 8 (you are on Java "+ org.parabot.environment.api.utils.JavaUtil.JAVA_VERSION+")");
            return false;
        }
        if (org.parabot.environment.api.utils.JavaUtil.JAVA_VERSION > 1.8) {
            System.err.println("Javafx unsupported on java "+ org.parabot.environment.api.utils.JavaUtil.JAVA_VERSION);
            UILog.log("Cannot run script", "Script cannot run without JavaFX. Please downgrade to Java 8 (you are on Java "+ org.parabot.environment.api.utils.JavaUtil.JAVA_VERSION+")");
            return false;
        }
        provide( Arrays.asList(
                new GUIStrat(this)
        ));
        return true;
    }
    public boolean setup() {
        return uiConfig != null;
    }

    public void postGuiSetup(MainController.UIConfig uiConfig) {

    }
}
