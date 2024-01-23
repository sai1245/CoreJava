package io.endeavourtech.inheritance;

import java.math.BigDecimal;

public class Square2 implements Shape{

    private BigDecimal side;

    public Square2(BigDecimal side){
        this.side=side;
    }

    public BigDecimal side(){
        return side;
    }

    @Override
    public BigDecimal calculateArea(){
        return side.multiply(side);
    }

}
