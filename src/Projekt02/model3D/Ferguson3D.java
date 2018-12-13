package Projekt02.model3D;

import transforms.Cubic;
import transforms.Point3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Ferguson3D extends Solid{
    private List<Point3D> points = new ArrayList<>();

    public Ferguson3D() {
        points.add(new Point3D(0.8, 0.8, 0));
        points.add(new Point3D(0.5, 0.9, 0));
        points.add(new Point3D(0.3, 0.5, 2));
        points.add(new Point3D(0.8, 0, 0));
        color = Color.PINK;
    }

    public void createFerguson() {
        List<Cubic> cubics = new ArrayList<>();
            cubics.add(new Cubic(Cubic.FERGUSON, points.get(0), points.get(1), points.get(2), points.get(3)));
            cubics.add(new Cubic(Cubic.FERGUSON, points.get(0), points.get(1), points.get(2), points.get(3)));
            cubics.add(new Cubic(Cubic.FERGUSON, points.get(0), points.get(1), points.get(2), points.get(3)));
            cubics.add(new Cubic(Cubic.FERGUSON, points.get(0), points.get(1), points.get(2), points.get(3)));
        int i = 0;
        for (Cubic cubic : cubics) {
            Point3D p1 = cubic.compute(0);
            for (double a = 0.1; a < 1; a += 0.1) {
                Point3D p2 = cubic.compute(a);
                vertices.add(p1);
                vertices.add(p2);
                p1=p2;
                indices.add(i);
                indices.add(++i);
            }
        }
    }
}
