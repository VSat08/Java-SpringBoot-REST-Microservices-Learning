
import java.util.*;

abstract class Shape {
    abstract void draw();
}

class Rectangle extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing Rectangel");
    }
}

class Circle extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing Circle");
    }
}

public class GenericMethodsDemo {
    public static void drawShapes(List<? extends Shape> lists) { //upper bounded Wildcard
        for (Shape s : lists) {
            s.draw();
        }
    }

    public static void main(String[] args) {
        List<Rectangle> shapes = new ArrayList<>();
        shapes.add(new Rectangle());

        List<Circle> shapes2 = new ArrayList<>();
        shapes2.add(new Circle());
        shapes2.add(new Circle());

        drawShapes(shapes);
        drawShapes(shapes2);
    }
}
