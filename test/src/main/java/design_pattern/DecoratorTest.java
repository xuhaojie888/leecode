package design_pattern;

import org.junit.Test;

public class DecoratorTest {
    interface Component{
        void paizhao();
    }
    class Zhaoxiangji implements Component{
        @Override
        public void paizhao() {
            System.out.println("拍照");
        }
    }
    abstract class Decorator implements Component{
        Component component;

        public Decorator(Component component) {
            this.component = component;
        }
    }

    class Meiyanxiangji extends Decorator{
        public Meiyanxiangji(Component component) {
            super(component);
        }

        @Override
        public void paizhao() {
            System.out.println("添加美颜效果");
            component.paizhao();
        }
    }

    class LVjingxiangji extends Decorator{
        public LVjingxiangji(Component component) {
            super(component);
        }

        @Override
        public void paizhao() {
            System.out.println("添加滤镜效果");
            component.paizhao();
        }
    }

    @Test
    public  void test(){
        new Zhaoxiangji().paizhao();
        System.out.println();
        new LVjingxiangji(new Zhaoxiangji()).paizhao();
        System.out.println();
        new LVjingxiangji(new Meiyanxiangji(new Zhaoxiangji())).paizhao();
        System.out.println();
        new Meiyanxiangji(new Zhaoxiangji()).paizhao();
        System.out.println();
        new Meiyanxiangji(new LVjingxiangji(new Zhaoxiangji())).paizhao();
    }
}
