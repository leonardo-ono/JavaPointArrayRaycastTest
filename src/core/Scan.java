package core;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

/**
 *
 * @author leonardo
 */
public class Scan {

    private Ponto[] pontos;
    private int halfWidth;

    public Scan(int width) {
        pontos = new Ponto[width];
        halfWidth = width / 2;
    }

    public void clear() {
        Arrays.fill(pontos, null);
    }

    public Ponto getPonto(int x) {
        return pontos[x + halfWidth];
    }

    public void setPonto(int x, Ponto ponto) {
        pontos[x + halfWidth] = ponto;
    }

    public void add(Ponto newPonto) {
        Ponto existingPoint = getPonto(newPonto.x1);
        if (existingPoint == null) {
            setPonto(newPonto.x1, newPonto);
        } 
        else if (newPonto.isNearThan(existingPoint)) {
            setPonto(newPonto.x1, newPonto);
            if (newPonto.x2 < existingPoint.x2) {
                existingPoint.adjustFarThan(newPonto);
                add(existingPoint);
            }
        } 
        else if (newPonto.x2 > existingPoint.x2) {
            newPonto.adjustFarThan(existingPoint);
            add(newPonto);
        }
    }

    public void draw(Graphics g, double d, double dymax) {
        Ponto p1, p2;
        outer:
        for (int x = -halfWidth; x < halfWidth; x++) {
            p1 = getPonto(x);
            if (p1 == null) {
                continue;
            }
            inner:
            for (int x2 = p1.x1; x2 <= p1.x2; x2++) {
                p2 = getPonto(x2);
                if (p2 == null || p1 == p2) {
                    drawWall(g, p1, x2, d, dymax);
                    continue inner;
                } else if (p2.isNearThan(p1)) {
                    x = x2 - 1;
                    if (p1.x2 > p2.x2) {
                        p1.adjustFarThan(p2);
                        add(p1);
                    }
                    continue outer;
                } else {
                    drawWall(g, p1, x2, d, dymax);
                    if (p2.x2 > p1.x2) {
                        p2.adjustFarThan(p1);
                        add(p2);
                    }
                }
            }
            x = p1.x2;
        }
    }

    private void drawWall(Graphics g, Ponto ponto, int x2, double d, double dymax) {
        double dy = dymax * Math.sin(System.currentTimeMillis() / 100d);
        double z = ponto.getZ(x2);
        double p = 1 - z / -200;
        if (p<0.15) {
            p = 0.15;
        }
        else if (p>1) {
            p = 1;
        }
        int red = (int) (ponto.color.getRed() * p);
        int green = (int) (ponto.color.getGreen() * p);
        int blue = (int) (ponto.color.getBlue() * p);
        g.setColor(new Color(red, green, blue));
        int ya = (int) (d * (25 + dy)  / z);
        int yb = (int) (d * (-25 + dy) / z);
        g.drawLine(x2, yb, x2, ya);
    }
    
}
