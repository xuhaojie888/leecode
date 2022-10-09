package design_pattern;

public class A implements Cloneable{
        String a1;
        int a2;
        B b;


        public A(String a1, int a2, B b) {
            this.a1 = a1;
            this.a2 = a2;
            this.b = b;
        }

        public String getA1() {
            return a1;
        }

        public void setA1(String a1) {
            this.a1 = a1;
        }

        public int getA2() {
            return a2;
        }

        public void setA2(int a2) {
            this.a2 = a2;
        }

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }

        public A clone() throws CloneNotSupportedException {
            A clone = (A) super.clone();
//            design_pattern.B clone1 = b.clone();
            B clone1 = new B(b.getB());
            clone.setB(clone1);
            return clone;
        }



        @Override
        public String toString() {
            return this.hashCode()+"design_pattern.A{" +
                    "a1='" + a1 + '\'' +
                    ", a2=" + a2 +
                    ", b=" + b +
                    '}';
        }

    }