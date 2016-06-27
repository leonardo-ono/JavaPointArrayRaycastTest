package testepointarrayraycasting;

import core.Camera;
import core.Scan;
import core.Vec3;
import core.Wall;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author leonardo
 */
public class View extends javax.swing.JFrame {
    
    private BufferedImage bi;
    private Scan scan;
    private Wall[] walls = new Wall[21];
    private Camera camera = new Camera();
    private double dymax = 0;
    
    /**
     * Creates new form View
     */
    public View() {
        initComponents();
        setLocationRelativeTo(null);
        
        bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        
        scan = new Scan(getWidth());
        
        //walls[0] = new Wall(-50, -300, 50, -100, Color.RED, "RED");

        walls[0] = new Wall(-100, 0, 0, 0, Color.WHITE, "RED") {
            private double xoffset;
            @Override
            public void update() {
                xoffset = Math.abs(100 * Math.sin(System.currentTimeMillis()/1000d));
                translate(-xoffset + 50, 0, 0);
            }
        };
        
        walls[1] = new Wall(-50, -50, 50, -50, new Color(255, 0, 0), "GREEN");
        walls[2] = new Wall(-50, -50, -50, 0, new Color(0, 255, 0), "GREEN");
        walls[3] = new Wall(50, -50, 50, 0, new Color(0, 0, 255), "GREEN");
        
        walls[4] = new Wall(-50, 0, -100, 50, new Color(255, 255, 0), "BLUE");
        
        walls[5] = new Wall(-100, 50, -200, 50, new Color(255, 0, 255), "YELLOW");
        walls[16] = new Wall(-100, 100, -200, 100, new Color(0, 255, 255), "YELLOW");
        
        walls[6] = new Wall(-100, 100, -50, 150, new Color(255, 0, 0), "YELLOW");
        
        walls[7] = new Wall(-50, 150, 50, 150, new Color(0, 255, 0), "YELLOW");
        
        walls[8] = new Wall(50, 150, 100, 100, new Color(0, 0, 255), "YELLOW");
        
        walls[9] = new Wall(100, 100, 200, 100, new Color(255, 255, 0), "YELLOW");
        walls[10] = new Wall(100, 50, 200, 50, new Color(255, 0, 255), "YELLOW");
        
        walls[11] = new Wall(100, 50, 50, 0, new Color(0, 255, 255), "YELLOW");

        walls[12] = new Wall(-7, -7, 7, -7, new Color(255, 0, 0), "YELLOW") {
            private double angle;
            @Override
            public void update() {
                angle += 0.01;
                rotateY(angle);
                translate(0, 0, 110);
            }
        };
        walls[13] = new Wall(-7, 7, 7, 7, new Color(0, 255, 0), "YELLOW"){
            private double angle;
            @Override
            public void update() {
                angle += 0.01;
                rotateY(angle);
                translate(0, 0, 110);
            }
        };
        walls[14] = new Wall(-7, -7, -7, 7, new Color(0, 0, 255), "YELLOW"){
            private double angle;
            @Override
            public void update() {
                angle += 0.01;
                rotateY(angle);
                translate(0, 0, 110);
            }
        };
        walls[15] = new Wall(7, -7, 7, 7, new Color(255, 255, 0), "YELLOW"){
            private double angle;
            @Override
            public void update() {
                angle += 0.01;
                rotateY(angle);
                translate(0, 0, 110);
            }
        };

        walls[17] = new Wall(-7, -7, 7, -7, new Color(255, 0, 0), "YELLOW") {
            private double angle;
            @Override
            public void update() {
                angle += 0.01;
                rotateY(angle);
                translate(0, 0, 40);
            }
        };
        walls[18] = new Wall(-7, 7, 7, 7, new Color(0, 255, 0), "YELLOW"){
            private double angle;
            @Override
            public void update() {
                angle += 0.01;
                rotateY(angle);
                translate(0, 0, 40);
            }
        };
        walls[19] = new Wall(-7, -7, -7, 7, new Color(0, 0, 255), "YELLOW"){
            private double angle;
            @Override
            public void update() {
                angle += 0.01;
                rotateY(angle);
                translate(0, 0, 40);
            }
        };
        walls[20] = new Wall(7, -7, 7, 7, new Color(255, 255, 0), "YELLOW"){
            private double angle;
            @Override
            public void update() {
                angle += 0.01;
                rotateY(angle);
                translate(0, 0, 40);
            }
        };
        
        /*
        
        walls[4] = new Wall(0, 0, 10, 90, Color.MAGENTA, "MAGENTA");
        
        walls[5] = new Wall(120, 0, 150, 0, Color.GRAY, "GRAY");
        walls[6] = new Wall(120, 30, 150, 30, Color.GRAY, "GRAY");
        walls[7] = new Wall(120, 0, 120, 30, Color.DARK_GRAY, "GRAY");
        walls[8] = new Wall(150, 0, 150, 30, Color.DARK_GRAY, "GRAY");

        walls[9]  = new Wall(120 - 200, 0 + 5, 150 - 200, 0 + 5, Color.GRAY, "GRAY");
        walls[10] = new Wall(120 - 200, 30 + 5, 150 - 200, 30 + 5, Color.GRAY, "GRAY");
        walls[11] = new Wall(120 - 200, 0 + 5, 120 - 200, 30 + 5, Color.DARK_GRAY, "GRAY");
        walls[12] = new Wall(150 - 200, 0 + 5, 150 - 200, 30 + 5, Color.DARK_GRAY, "GRAY");
        */ 
        
        new Timer().schedule(new MainLoop(), 10, 35);
        
        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 863, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 603, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private Vec3 wallVec = new Vec3();
    private void updateCamera() {
        boolean isMoving = false;
        
        if (Keyboard.keydown[37] || Keyboard.keydown[65]) {
            camera.moveLateral(-1);
            isMoving = true;
            Wall cw = checkCollisionWithWalls();
            if (cw != null) {
                camera.moveLateral(+1);
                //Vec3.sub(wallVec, cw.m1, cw.m2);
                //walkSlideWall(1);
            }
        }
        else if (Keyboard.keydown[39] || Keyboard.keydown[68]) {
            camera.moveLateral(1);
            isMoving = true;
            Wall cw = checkCollisionWithWalls();
            if (cw != null) {
                camera.moveLateral(-1);
                //Vec3.sub(wallVec, cw.m1, cw.m2);
                //walkSlideWall(1);
            }
        }
        if (Keyboard.keydown[38] || Keyboard.keydown[87]) {
            camera.moveForward(1);
            isMoving = true;
            Wall cw = checkCollisionWithWalls();
            if (cw != null) {
                camera.moveForward(-1);
                Vec3.sub(wallVec, cw.m1, cw.m2);
                walkSlideWall(-1);
            }
        }
        else if (Keyboard.keydown[40] || Keyboard.keydown[83]) {
            camera.moveForward(-1);
            isMoving = true;
            Wall cw = checkCollisionWithWalls();
            if (cw != null) {
                camera.moveForward(+1);
                Vec3.sub(wallVec, cw.m1, cw.m2);
                walkSlideWall(-1);
            }
        }
        
        // simula o movimento
        // vertical enquanto esta andando
        if (isMoving) {
            dymax = 1.25;
        }
        else {
            dymax = 0;
        }
    }
    
    private Wall checkCollisionWithWalls() {
        for (Wall wall : walls) {
            if (wall.checkCollisionWithCamera(camera)) {
                return wall;
            }
        }
        return null;
    }

    private void walkSlideWall(int sign) {
        // faz com que o jogador deslize pela parede
        double a = camera.direction.getRelativeAngleBetween(wallVec);
        wallVec.normalize();
        wallVec.scale(sign * Math.cos(a));
        camera.position.add(wallVec);
    }
    
    public void update() {
        for (Wall wall : walls) {
            wall.resetModel();
            wall.update();
        }
        updateCamera();
    }
    
    @Override
    public void paint(Graphics g) {
        // bi.getGraphics().clearRect(0, 0, bi.getWidth(), bi.getHeight());
        draw(bi.getGraphics());
        g.drawImage(bi, 0, 0, null);
    }
    
    private void drawFloor(Graphics g) {
        GradientPaint floorPaint = new GradientPaint(0, 0, Color.DARK_GRAY, 0, getHeight()/2, Color.BLACK);
        ((Graphics2D) g).setPaint(floorPaint);
        g.fillRect(0, 0, getWidth(), getHeight()/2);
        
        floorPaint = new GradientPaint(0, getHeight()/2, Color.BLACK, 0, getHeight(), Color.DARK_GRAY);
        ((Graphics2D) g).setPaint(floorPaint);
        g.fillRect(0, getHeight()/2, getWidth(), getHeight());
        
        ((Graphics2D) g).setPaint(Color.BLACK);
    }
    
    public void draw(Graphics g) {
        drawFloor(g);
        
        g.translate(getWidth()/2, getHeight()/2);
        ((Graphics2D) g).scale(1, 1);
        
        update();
        
        scan.clear();
        for (Wall wall : walls) {
            camera.doViewTransformation(wall);
            wall.scan(400, scan, -getWidth()/2, getWidth()/2);
        }
        scan.draw(g, 400, dymax);
        
        //g.setColor(Color.LIGHT_GRAY);
        //g.drawLine(-getWidth()/2, 0, getWidth()/2, 0);
        //g.drawLine(0, -getHeight()/2, 0, getHeight()/2);

        // drawTop top
        for (Wall wall : walls) {
            // se a parede ultrapassar o near Z plane, pode fazer culling
            if (!wall.culled) {
                wall.drawTop(g);
            }
        }
        
        // drawCamera
        g.setColor(Color.WHITE);
        g.drawOval((int) (-camera.radius), (int) (-camera.radius), (int) (camera.radius * 2), (int) (camera.radius * 2));
        
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            Keyboard.keydown[e.getKeyCode()] = true;
        }
        else if (e.getID() == KeyEvent.KEY_RELEASED) {
            Keyboard.keydown[e.getKeyCode()] = false;
        }
    }
    
    private class MainLoop extends TimerTask {
        @Override
        public void run() {
            update();
            repaint();
        }
    }
    
    public static class Keyboard {
        public static boolean[] keydown = new boolean[255];
    }

    private int mouseClickX;
    private double angleOriginal;
    private class MouseHandler extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            mouseClickX = e.getX();
            angleOriginal = camera.angle;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int difX = e.getX() - mouseClickX;
            double difAngle = Math.toRadians(difX / 5d);
            camera.angle = angleOriginal - difAngle;
        }
        
    }
    
}
