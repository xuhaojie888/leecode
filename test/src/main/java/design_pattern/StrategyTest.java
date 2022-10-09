package design_pattern;

import org.junit.Test;

public class StrategyTest {

    @Test
    public void test(){
        Zombie normalZombie = new NormalZombie();
        normalZombie.start();
        normalZombie.setAttack(new Hit());
        normalZombie.start();

        Zombie flagZombie = new FlagZombie();
        flagZombie.start();
        flagZombie.setMoveble(new Jump());
        flagZombie.start();
    }

    abstract class Zombie{
        Moveble moveble;
        Attack attack;

        public Zombie(Moveble moveble, Attack attack) {
            this.moveble = moveble;
            this.attack = attack;
        }

        void start(){
            System.out.println("————————————————————");
            display();
            moveble.move();
            attack.attack();
        }

        abstract void display();

        public Moveble getMoveble() {
            return moveble;
        }

        public void setMoveble(Moveble moveble) {
            this.moveble = moveble;
        }

        public Attack getAttack() {
            return attack;
        }

        public void setAttack(Attack attack) {
            this.attack = attack;
        }
    }

    class NormalZombie extends Zombie{

        public NormalZombie(){
            this(new StepByStep(),new Bit());
        }

        public NormalZombie(Moveble moveble, Attack attack) {
            super(moveble, attack);
        }

        @Override
        void display() {
            System.out.println("我是普通僵尸");
        }
    }

    class FlagZombie extends Zombie{

        public FlagZombie(){
            this(new StepByStep(),new Bit());
        }

        public FlagZombie(Moveble moveble, Attack attack) {
            super(moveble, attack);
        }

        @Override
        void display() {
            System.out.println("我是旗手僵尸");
        }
    }

    interface Moveble{
        void move();
    }

    class StepByStep implements Moveble{
        @Override
        public void move() {
            System.out.println("一步一步往前走");
        }
    }

    class Jump implements Moveble{
        @Override
        public void move() {
            System.out.println("一跳一跳往前走");
        }
    }

    interface Attack{
        void attack();
    }

    class Hit implements Attack{
        @Override
        public void attack() {
            System.out.println("打");
        }
    }

    class Bit implements Attack{
        @Override
        public void attack() {
            System.out.println("咬");
        }
    }
}
