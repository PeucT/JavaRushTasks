package com.javarush.task.task21.task2109;

/* 
Запретить клонирование
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        protected A clone() throws CloneNotSupportedException {
            return new A(this.i, this.j);
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        protected B clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }

        public String getName() {
            return name;
        }
    }

    public static class C extends B {
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        protected C clone()  {
            return new C(super.getI(), super.getJ(), super.getName());
        }

        @Override
        public int hashCode() {
            return (super.getName() != null ? ( 31 * super.getI() + super.getJ() + super.getName().hashCode()) : ( 31 * super.getI() + super.getJ() ) );
        }

        // лай вариант сравнения, для частного тестирования
        @Override
        public boolean equals(Object o) {
            if ( o == null) return false;
            if (!(o instanceof C)) return false;
            C c = (C) o;
            if ( (c.getI() == this.getI()) && ( c.getJ() == this.getJ()) && this.getName().equals(c.getName())) return true;
            else return false;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        C c = new C(10, 20, "Class C");
        C newC = c.clone();
        System.out.println(c.toString());
        System.out.println(newC.toString());
        System.out.print(c.equals(newC));
    }
}
