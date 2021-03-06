package application.renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {

    private static final int WIDTH = 1600;
    private static final int HEIGHT = 830;
    private static final int FPS_CAP = 120;
    
    private static long lastFrameTime;
    private static float deltaTime;

    public static void createDisplay() {
        ContextAttribs attribs = new ContextAttribs(3, 2)
                .withForwardCompatible(true).withProfileCore(true);

        try {
            Display.setLocation(800-WIDTH/2, 0);
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.setTitle("User-Centered 3D Manipulation");
            Display.create(new PixelFormat(), attribs);
            Display.setFullscreen(true);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        GL11.glViewport(0, 0, WIDTH, HEIGHT);
        lastFrameTime = getCurrentTime();
    }

    public static void updateDisplay() {
//        Display.sync(FPS_CAP);
        Display.update();
        long currentFrameTime = getCurrentTime();
        deltaTime = (currentFrameTime = lastFrameTime ) / 1000f;
        lastFrameTime = getCurrentTime();
    }

    public static void closeDisplay() {
        Display.destroy();
    }

    private static long getCurrentTime() {
        return Sys.getTime() * 1000 / Sys.getTimerResolution();
    }
    
    public static float getFrameTimeSeconds() {
        return deltaTime;
    }
}
