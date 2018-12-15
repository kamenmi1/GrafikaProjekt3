package Projekt02.controller;

import Projekt02.model3D.*;
import Projekt02.view.Raster;
import transforms.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller3D {

    private Renderer3D renderer3D;
    private Solid cube;
    private Camera camera;
    private int mx, my;
    private Solid pyramid;
    private Solid axisX, axisY, axisZ;
    private int changeProjection = 1;

    public Controller3D(Raster raster) {
        renderer3D = new Renderer3D(raster);
        initObjects();
        initListeners(raster);
    }

    private void initListeners(Raster raster) {
        raster.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mx = e.getX();
                my = e.getY();
            }
        });
        raster.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    double diff = ((mx - e.getX()) / 500.0);
                    double azimut = camera.getAzimuth() + diff;
                    camera = camera.withAzimuth(azimut);

                    diff = ((my - e.getY()) / 500.0);
                    double zenit = camera.getZenith() + diff;
                    if (zenit > Math.PI / 2) {
                        zenit = Math.PI / 2;
                    }
                    if (zenit < -(Math.PI / 2)) {
                        zenit = -Math.PI / 2;
                    }
                    camera = camera.withZenith(zenit);

                    renderer3D.setView(camera.getViewMatrix());

                } else if (SwingUtilities.isRightMouseButton(e)) {
                    double rotX = ((mx - e.getX()) / -200.0);
                    double rotY = ((my - e.getY()) / -200.0);

                    Mat4 rot = renderer3D.getModel().mul(new Mat4RotXYZ(rotY, 0, rotX));
                    renderer3D.setModel(rot);
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    double rotX = ((mx - e.getX()) / 555.5);
                    double rotY = ((my - e.getY()) / -555.5);

                    Mat4 tra = renderer3D.getModel().mul(new Mat4Transl(rotX, rotY, 0.001));
                    renderer3D.setModel(tra);
                }
                mx = e.getX();
                my = e.getY();

            }
        });
        raster.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        camera = camera.forward(1);
                        renderer3D.setView(camera.getViewMatrix());
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        camera = camera.backward(1);
                        renderer3D.setView(camera.getViewMatrix());
                        break;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        camera = camera.right(1);
                        renderer3D.setView(camera.getViewMatrix());
                        break;
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        camera = camera.left(1);
                        renderer3D.setView(camera.getViewMatrix());
                        break;
                    case KeyEvent.VK_E:
                        camera = camera.up(1);
                        renderer3D.setView(camera.getViewMatrix());
                        break;
                    case KeyEvent.VK_Q:
                        camera = camera.down(1);
                        renderer3D.setView(camera.getViewMatrix());
                        break;
                    case KeyEvent.VK_F:
                        Mat4 scale = renderer3D.getModel().mul(new Mat4Scale(1.2, 1.2, 1.2));
                        renderer3D.setModel(scale);
                        break;
                    case KeyEvent.VK_G:
                        Mat4 scale1 = renderer3D.getModel().mul(new Mat4Scale(0.8, 0.8, 0.8));
                        renderer3D.setModel(scale1);
                        break;
                    case KeyEvent.VK_O:
                        if (changeProjection == 1) {
                            renderer3D.setProjection(new Mat4OrthoRH(Raster.WIDTH / 100.0, Raster.HEIGHT / 100.0, 0.1, 200));
                            renderer3D.setView(camera.getViewMatrix());
                            changeProjection = changeProjection - 1;
                        } else {
                            renderer3D.setProjection(new Mat4PerspRH(Math.PI / 4, Raster.HEIGHT / (float) Raster.WIDTH, 1, 200));
                            renderer3D.setView(camera.getViewMatrix());
                            changeProjection = changeProjection + 1;
                        }
                        break;
                    case KeyEvent.VK_R:
                        resetCamera();
                        renderer3D.setModel(new Mat4Identity());
                        break;
                }
            }
        });
    }

    private void resetCamera() {
        camera = new Camera(new Vec3D(0, -5, 3),
                Math.toRadians(90), Math.toRadians(-40), 1, true);
        renderer3D.setView(camera.getViewMatrix());
    }

    private void initObjects() {
        cube = new Cube(Color.CYAN);
        pyramid = new Pyramid(Color.PINK);
        axisX = new AxisX(Color.RED);
        axisY = new AxisY(Color.GREEN);
        axisZ = new AxisZ(Color.BLUE);
        renderer3D.add(new Spiral());
        Cubic3D cubic = new Cubic3D();
        Ferguson3D ferguson = new Ferguson3D();
        Coons3D coons = new Coons3D();
        coons.createCoons();
        ferguson.createFerguson();
        cubic.create();

        renderer3D.add(cube, pyramid, axisX, axisY, axisZ, cubic, ferguson, coons);
        resetCamera();
    }
}
