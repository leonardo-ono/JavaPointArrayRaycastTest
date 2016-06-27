package core;

import java.awt.Color;

/**
 *
 * @author leonardo
 */
public class Ponto {

    public Color color;
    public String ch;
    public int x1;
    public int x2;
    public double z1;
    public double z2;
    public double zInv1;
    public double zInv2;
    public double dzInv;

    public Ponto(int x1, int x2, double z1, double z2, String ch, Color color) {
        this.x1 = x1;
        this.x2 = x2;
        this.z1 = z1;
        this.z2 = z2;
        this.zInv1 = 1 / z1;
        this.zInv2 = 1 / z2;
        this.dzInv = (zInv2 - zInv1) / (x2 - x1);
        this.ch = ch;
        this.color = color;
    }

    public double getZ(int x) {
        if (x2 - x1 == 0) {
            return 1 / zInv1;
        }
        return 1 / (dzInv * (x - x1) + zInv1);
    }
    
    public boolean isNearThan(Ponto p) {
        int px = (x1 + 1) > p.x2 ? p.x2 : (x1 + 1);
        return getZ(x1 + 1) > p.getZ(px);
    }
    
    public void adjustFarThan(Ponto newPoint) {
        zInv1 = dzInv * (newPoint.x2 + 1 - x1) + zInv1;
        x1 = newPoint.x2 + 1;
    }

    public boolean isValid() {
        return x2 >= x1;
    }
    
    public void clipLeftRightPlane(int left, int right) {
        if (x1 < left) {
            zInv1 = dzInv * (left - x1) + zInv1;
            x1 = left;
        }
        if (x2 > right) {
            zInv2 = dzInv * (right - x1) + zInv1;
            x2 = right;
        }
    }
    
}
