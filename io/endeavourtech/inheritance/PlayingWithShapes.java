package io.endeavourtech.inheritance;

import java.math.BigDecimal;

public class PlayingWithShapes {
    public static void main(String[] args) {
        Square square=new Square(new BigDecimal(5));
        Rectangle rectangle=new Rectangle(new BigDecimal(6),new BigDecimal(5));

        //Polymorphism using interface
        //Variable of type interface can point to an object of any class that implements Shape
        Shape circle=new Circle(new BigDecimal(7));

        Shape[] shapeArray=new Shape[]{square,rectangle,circle};

        calculateTotalArea(shapeArray);
    }

    private static void calculateTotalArea(Shape[] shapeArray) {

        BigDecimal totalArea=BigDecimal.ZERO;
        for (Shape eachShape : shapeArray) {
            totalArea=totalArea.add(eachShape.calculateArea());
        }
        System.out.println("The total area of all shapes are: "+totalArea);
    }
}
