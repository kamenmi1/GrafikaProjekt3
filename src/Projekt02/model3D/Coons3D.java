package Projekt02.model3D;

import transforms.Cubic;
import transforms.Point3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Coons3D extends Solid {
    private List<Point3D> points = new ArrayList<>();

    public Coons3D() {
        points.add(new Point3D(1, 1, 0));
        points.add(new Point3D(1.5, 1.5, 0));
        points.add(new Point3D(2, 1.9, 1));
        points.add(new Point3D(0.7, 2, 1));
        points.add(new Point3D(0.9, 0, 0));
        color = Color.BLUE;
    }

    public void createCoons() {
        List<Cubic> cubics = new ArrayList<>();

        cubics.add(new Cubic(Cubic.COONS, points.get(0), points.get(1), points.get(2), points.get(3)));
        cubics.add(new Cubic(Cubic.COONS, points.get(0), points.get(1), points.get(2), points.get(3)));
        cubics.add(new Cubic(Cubic.COONS, points.get(0), points.get(1), points.get(2), points.get(3)));
        cubics.add(new Cubic(Cubic.COONS, points.get(0), points.get(1), points.get(2), points.get(3)));

        cubics.add(new Cubic(Cubic.COONS, points.get(1), points.get(2), points.get(3), points.get(4)));
        cubics.add(new Cubic(Cubic.COONS, points.get(1), points.get(2), points.get(3), points.get(4)));
        cubics.add(new Cubic(Cubic.COONS, points.get(1), points.get(2), points.get(3), points.get(4)));
        cubics.add(new Cubic(Cubic.COONS, points.get(1), points.get(2), points.get(3), points.get(4)));

        int i = 0;
        for (Cubic cubic : cubics) {
            Point3D p1 = cubic.compute(0);
            for (double a = 0.1; a < 1; a += 0.1) {
                Point3D p2 = cubic.compute(a);
                vertices.add(p1);
                vertices.add(p2);
                p1 = p2;
                indices.add(i);
                indices.add(++i);
            }
        }
    }
}
