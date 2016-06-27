package core;

/**
 *
 * @author leonardo
 */
public class Camera {

    public Vec3 position = new Vec3(70, 0, 100);
    public Vec3 direction = new Vec3(0, 0, -1);
    public double angle = Math.toRadians(30); // 0 quando esta apontando para (0, 0, -1)
    public double radius = 15;
    
    public void translate(double x, double z) {
        position.translate(x, 0, z);
    }

    public void rotate(double angle) {
        this.angle += angle;
    }

    public void moveForward(double v) {
        direction.set(0, 0, -1);
        direction.rotateY(angle);
        direction.scale(v);
        position.add(direction);
    }

    public void moveLateral(double v) {
        direction.set(0, 0, -1);
        direction.rotateY(angle - Math.toRadians(90));
        direction.scale(v);
        position.add(direction);
    }

    public void doViewTransformation(Wall wall) {
        wall.translate(-position.x, 0, -position.z);
        wall.rotateY(-angle);
    }
    
}
