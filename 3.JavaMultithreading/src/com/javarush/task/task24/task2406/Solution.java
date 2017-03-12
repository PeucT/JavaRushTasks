package com.javarush.task.task24.task2406;

import java.math.BigDecimal;

/* 
Наследование от внутреннего класса
*/
public class Solution {
    public class Building {
        public class Hall {
            private BigDecimal square;

            public Hall(BigDecimal square) {
                this.square = square;
            }
        }

        public class Apartments {
        }
    }

    public class Apt3Bedroom extends Building.Apartments{
        public Apt3Bedroom(Building building){
            building.super();
        }
    }

    public class BigHall extends Solution.Building.Hall{
        public BigHall(Solution.Building building){
            building.super(new BigDecimal("323"));
        }
    }

    public static void main(String[] args) {

    }
}
