package io.endeavourtech.inheritance;

import java.math.BigDecimal;

public class Circle implements Shape{

    private BigDecimal radius;

    public Circle(BigDecimal radius) {
        this.radius = radius;
    }

    public BigDecimal getRadius() {
        return radius;
    }

    @Override
    public BigDecimal calculateArea() {
        return radius.multiply(radius).multiply(new BigDecimal(Math.PI));
    }
}

