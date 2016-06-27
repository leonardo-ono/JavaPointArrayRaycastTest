package core;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author leonardo
 */
public class Wall {

    public Vec3 p1 = new Vec3();
    public Vec3 p2 = new Vec3();

    public Vec3 m1 = new Vec3();
    public Vec3 m2 = new Vec3();

    public Vec3 sp1 = new Vec3();
    public Vec3 sp2 = new Vec3();
    
    public Color color;
    public String id;
    
    public boolean culled;
    
    public Wall(double x1, double z1, double x2, double z2, Color color, String id) { 
        p1.x = x1;
        p1.z = z1;
        p2.x = x2;
        p2.z = z2;
        this.color = color;
        this.id = id;
    }
    
    public void resetModel() {
        m1.x = p1.x;
        m1.z = p1.z;
        m2.x = p2.x;
        m2.z = p2.z;
        culled = false;
    }

    public void translate(double x, double y, double z) {
        m1.translate(x, y, z);
        m2.translate(x, y, z);
    }

    public void rotateY(double a) {
        m1.rotateY(a);
        m2.rotateY(a);
    }

    public void doPerspectiveProjection(double d) {
        sp1.x = d * m1.x / -m1.z;
        sp2.x = d * m2.x / -m2.z;
    }
    
    public void drawTop(Graphics g) {
        g.setColor(color);
        g.drawLine((int) m1.x, (int) m1.z, (int) m2.x, (int) m2.z);
    }

    public void scan(double d, Scan scan, int left, int right) {
        // near z clip (antes do perspective projection)
        if (!clipNearPlane(-10)) {
            culled = true;
            return;
        }
        
        doPerspectiveProjection(d);
        
        int sp1x = (int) sp1.x;
        int sp2x = (int) sp2.x;
        double m1z = m1.z;
        double m2z = m2.z;
        
        if (sp1x > sp2x) {
            sp1x = (int) sp2.x;
            sp2x = (int) sp1.x;
            m1z = m2.z;
            m2z = m1.z;
        }
        
        Ponto newPonto = new Ponto(sp1x, sp2x, m1z, m2z, id, color);
        
        // clip left & right plane
        newPonto.clipLeftRightPlane(left, right);
        if (!newPonto.isValid()) {
            return;
        }
        
        
        scan.add(newPonto);
    }


    // false - fazer culling (descartar tudo)
    // true  - desenhar (pode ser que tenha sido "clippado")
    public boolean clipNearPlane(int zNear) {
        
        // oclusao total
        if (m1.z > zNear && m2.z > zNear) {
            return false;
        }
        
        // precisa exibir por inteiro
        else if (m1.z <= zNear && m2.z <= zNear) {
            return true;
        }
        
        // precisa fazer clip
        if (m1.z > zNear) {
            double nx1 = (m2.x - m1.x) * (zNear - m1.z) / (m2.z - m1.z) + m1.x;
            m1.x = nx1;
            m1.z = zNear;
        }
        else if (m2.z > zNear) {
            double nx2 = (m2.x - m1.x) * (zNear - m1.z) / (m2.z - m1.z) + m1.x;
            m2.x = nx2;
            m2.z = zNear;
        }
        
        return true;
    }
    
    public void update() {
        // aqui nao faz nada
    }
 
    // metodo que consegui implementar dentro da minha capacidade
    // primeiro verifica se os 2 pontos da linha estao separados pela reta perpendicular
    // se estiver, basta verificar a distancia entre o centro do circulo e a reta 
    // se nao estiver, basta verificar se um dos dois pontos esta dentro do circulos
    private Vec3 wall = new Vec3();
    private Vec3 p1cl = new Vec3();
    private Vec3 p2cl = new Vec3();
    private Vec3 perpWall = new Vec3();
    public boolean checkCollisionWithCamera(Camera camera) {
        Vec3 cameraPosition = camera.position;
        double cameraRadius = camera.radius;
        Vec3.sub(wall, m2, m1);
        Vec3.sub(p1cl, m1, cameraPosition);
        Vec3.sub(p2cl, m2, cameraPosition);
        Vec3.perp(perpWall, wall);
        if (perpWall.getSign(p1cl) * perpWall.getSign(p2cl) < 0) {
            wall.normalize();
            return (Math.abs(p1cl.perpDot(wall)) <= cameraRadius);
        } else {
            return (p1cl.getSize() <= cameraRadius) || (p2cl.getSize() <= cameraRadius);
        }
    }
    
}
