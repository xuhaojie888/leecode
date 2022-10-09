package design_pattern;

import org.junit.Test;

public class ChainTest {

    @Test
    public void test(){
        ChainRequest chainRequest = new ChainRequest().rateLimitOK(false).loginOK(true);
        MyHandler myHandler = new RateLimitHandle(new LoginHandle(null));
        boolean process = myHandler.process(chainRequest);
        System.out.println(process);
    }

    class ChainRequest {
        boolean rateLimitOK;
        boolean loginOK;
        public ChainRequest() {
        }

        public ChainRequest rateLimitOK(boolean rateLimitOK){
            this.rateLimitOK = rateLimitOK;
            return this;
        }

        public ChainRequest loginOK(boolean loginOK){
            this.loginOK = loginOK;
            return this;
        }
    }

    abstract class MyHandler{
        MyHandler next;

        public MyHandler(MyHandler next) {
            this.next = next;
        }

        abstract boolean handle(ChainRequest request);

        boolean process(ChainRequest request){
            if(handle(request)){
                if(next == null){
                    return true;
                }
                return next.handle(request);
            }else {
                return false;
            }
        }
    }
    class RateLimitHandle extends MyHandler{

        public RateLimitHandle(MyHandler next) {
            super(next);
        }

        @Override
        boolean handle(ChainRequest request) {
            boolean rateLimitOK = request.rateLimitOK;
            System.out.println("rateLimitOK:"+rateLimitOK);
            return rateLimitOK;
        }
    }

    class LoginHandle extends MyHandler{

        public LoginHandle(MyHandler next) {
            super(next);
        }

        @Override
        boolean handle(ChainRequest request) {
            boolean loginOK = request.loginOK;
            System.out.println("loginOK:"+loginOK);
            return loginOK;
        }
    }
}
