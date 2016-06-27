package core;

/**
 *
 * @author leonardo
 */
public class Vec3 {

    public double x;
    public double y;
    public double z;

    public Vec3() {
    }

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3(Vec3 v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void rotateY(double angle) {
        double nz = z * Math.cos(angle) - x * Math.sin(angle);
        double nx = z * Math.sin(angle) + x * Math.cos(angle);
        double ny = y;
        set(nx, ny, nz);
    }

    public void translate(double x, double y, double z) {
        set(this.x + x, this.y + y, this.z + z);
    }

    public void scale(double s) {
        set(this.x * s, this.y * s, this.z * s);
    }
    
    public void add(Vec3 vec) {
        double nx = x + vec.x;
        double ny = y + vec.y;
        double nz = z + vec.z;
        set(nx, ny, nz);
    }

    public void sub(Vec3 vec) {
        double nx = x - vec.x;
        double ny = y - vec.y;
        double nz = z - vec.z;
        set(nx, ny, nz);
    }

    public static void sub(Vec3 r, Vec3 a, Vec3 b) {
        double nx = a.x - b.x;
        double ny = a.y - b.y;
        double nz = a.z - b.z;
        r.set(nx, ny, nz);
    }
    // http://www.oocities.org/pcgpe/math2d.html
    // http://gamedev.stackexchange.com/questions/45412/understanding-math-used-to-determine-if-vector-is-clockwise-counterclockwise-f
    public int getSign(Vec3 v) {
        return (z*v.x > x*v.z)?-1:1;
    }
    
    public double getSize() {
        return Math.sqrt(x * x + y * y + z * z);
    }
    
    // cria um vetor perpendicular a este (na verdade este eh para 2D somente)
    public Vec3 perp() {
        return new Vec3(-z, 0, x);
    }

    public static void perp(Vec3 r, Vec3 v) {
        double nx = -v.z;
        double ny = 0;
        double nz = v.x;
        r.set(nx, ny, nz);
    }
    
    public double dot(Vec3 v) {
        return x * v.x + y * v.y + z * v.z;
    }
    
    // http://johnblackburne.blogspot.com.br/2012/02/perp-dot-product.html
    public double perpDot(Vec3 v) {
        return perp().dot(v);
    }
    
    public void normalize() {
        double den = 1 / getSize();
        x *= den;
        y *= den;
        z *= den;
    }
    
    public double getRelativeAngleBetween(Vec3 v) {
        return getSign(v) * Math.acos(dot(v) / (getSize() * v.getSize()));
    }
    
    @Override
    public String toString() {
        return "Vec3{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
    
}
