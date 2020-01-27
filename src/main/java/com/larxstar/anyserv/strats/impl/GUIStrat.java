package com.larxstar.anyserv.strats.impl;

import com.larxstar.anyserv.AnyScript;
import com.larxstar.anyserv.fx.MainController;
import com.larxstar.anyserv.strats.BossStrat;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import org.parabot.api.io.WebUtil;
import org.parabot.core.Directories;
import org.parabot.core.ui.components.VerboseLoader;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.framework.SleepCondition;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GUIStrat extends BossStrat {

    public static GUIStrat INSTANCE;

    public GUIStrat(AnyScript main) {
        super(main);
        INSTANCE = this;
    }

    public boolean activate() {
        return !root.setup() && (frame == null || !frame.isVisible());
    }

    public void execute() {
        if (root.args != null) {
          //  parseArgs(root.args.split(" "));
            return;
        }
       // System.out.println("Calling swing.invokeLater via "+Thread.currentThread().toString());
        open();
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return root.setup() || root.getState() != Script.STATE_RUNNING;
            }
        }, 20_000);
    }


    private void open() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                // System.out.println("thread: "+Thread.currentThread().toString());
                createJFrame();

                Platform.setImplicitExit(false);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        addJavaFX(jfxp);
                        // System.out.println("thread: "+Thread.currentThread().toString());
                    }
                });

                // FXApp.main();
            }
        });
    }

    public static void main(String[] args) {
        new GUIStrat(null).open();
    }

    public JFrame   frame;
    public JFXPanel jfxp;

    private void createJFrame() {
        System.out.println("Creating JFrame for JavaFXPanel");
        if (frame != null) {
            System.err.println("frame exists");
            return;
        }
        frame = new JFrame("Swing Frame for JavaFX");
        jfxp  = new JFXPanel();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(jfxp);
        frame.setSize(355 + 25, 335 + 35);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                controller.onClose();
            }
        });
        System.out.println("frame created: "+frame);
    }

    public MainController controller;

    private void addJavaFX(JFXPanel jfxp) {
        System.out.println("panel init");
        INSTANCE = this;
        try {

            final File destination = new File(Directories.getCachePath() + File.separator + "shad-zenthiev-ui.fxml");
            final URL  rsc         = new URL("https://www.dropbox.com/s/a2byy1315hog8aa/shad-zenthiev-ui.fxml?dl=1");
            if (!destination.exists() || !destination.canRead()) {
                WebUtil.downloadFile(rsc, destination, VerboseLoader.get());
            }
            System.out.println("ui from "+rsc);

            FXMLLoader loader = new FXMLLoader(rsc);
            controller = new MainController();
            loader.setController(controller);
            AnchorPane page   = (AnchorPane) loader.load();

            Scene      scene  = new Scene(page);
            jfxp.setScene(scene);

            System.out.println("UI showing");
        } catch (IOException e) {
            System.err.println("Error loading ui.fxml!");
            e.printStackTrace();
        }
    }

}
