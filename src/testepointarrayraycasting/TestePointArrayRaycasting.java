package testepointarrayraycasting;

import core.Ponto;
import core.Scan;

/**
 *
 * @author leonardo
 */
public class TestePointArrayRaycasting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scan scan = new Scan(50);
        
        scan.add(new Ponto(10, 15, -30d, -30d, "C", null));
        scan.add(new Ponto(3, 7, -20d, -20d, "B", null));
        scan.add(new Ponto(5, 10, -10d, -10d, "A", null));
        
        //scan.printDebug();
    }
}

/*
 * 
    public void printDebug() {
        Ponto p1 = null, p2 = null;
        outer:
        for (int x=-halfWidth; x<halfWidth; x++) {
            p1 = getPonto(x);
            if (p1 == null) {
                System.out.print("."); // printDebug background
                continue;
            }
            inner:
            for (int x2=p1.x1; x2<=p1.x2; x2++) {
                p2 = getPonto(x2);
                if (p2 == null || p1 == p2) {
                    System.out.print(p1.ch);
                    continue inner;
                }
                else if (p2.isNearThan(p1)) {
                    if (p1.x2 > p2.x2) {
                        p1.adjustFarThan(p2);
                        add(p1);
                    }
                    x = x2 - 1;
                    continue outer;
                }
                else {
                    if (p2.x2 > p1.x2) {
                        p2.adjustFarThan(p1);
                        add(p2);
                    }
                    System.out.print(p1.ch);
                }
            }
            x = p1.x2;
        }
    }

 */