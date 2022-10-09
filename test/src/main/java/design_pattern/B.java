package design_pattern;

public class B implements Cloneable{
        private String b;

        public B(String b) {
            this.b = b;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public B clone() throws CloneNotSupportedException {
            return (B) super.clone();
        }

        @Override
        public String toString() {
            return hashCode()+"design_pattern.B{" +
                    "b='" + b + '\'' +
                    '}';
        }
    }