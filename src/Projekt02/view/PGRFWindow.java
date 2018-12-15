package Projekt02.view;

import javax.swing.*;

public class PGRFWindow extends JFrame {

    private final Raster raster;

    public PGRFWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Raster.WIDTH, Raster.HEIGHT);
        setLocationRelativeTo(null);
        setTitle("PGRF1 projekt třetí část");

        raster = new Raster();
        add(raster);
        raster.setFocusable(true);
        raster.grabFocus();

    }
    public Raster getRaster(){
        return raster;
    }

}
