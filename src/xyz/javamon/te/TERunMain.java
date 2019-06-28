package xyz.javamon.te;

import xyz.javamon.te.component.TEFrame;

public class TERunMain {

    private static TEFrame frame;

    public static void main(String... args) {
        frame = new TEFrame();
    }

    public static TEFrame getFrame() {
        return frame;
    }

    private TERunMain() { }

}
