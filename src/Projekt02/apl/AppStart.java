package Projekt02.apl;

import Projekt02.controller.Controller3D;
import Projekt02.view.PGRFWindow;

import javax.swing.*;

public class AppStart {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PGRFWindow window = new PGRFWindow();
            new Controller3D(window.getRaster());
            window.setVisible(true);
        });

        /* Ovládání:
              Myš:  levé tlačíko myši pohyb camerou
                    pravé tlačítko myši rotace objektů
                    prostřední tlačítko myši pohyb objektů
              Tlačítka:
                    Reset(R)
                    Pohyb camera wasd + šipky
                    camera.up (E)
                    camera.up (Q)
                    Změna měřítka - zvětšení (F)
                    Změna měřítka - zmenšení (G)
                    Změna projection Ortogonální - Perspektivní (O)
       */
    }
}
