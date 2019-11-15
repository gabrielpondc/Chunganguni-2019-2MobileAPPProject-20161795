package gujiakai.dotsline.mokuai;

/**
 * Created by GUJIAKAI on 2019/11
 */
public class Renleiwanjia extends wanjia {
    private Xian[] inputBuffer=new Xian[1];

    public Renleiwanjia(String name) {
        super(name);
    }

    public void add(Xian xian) {
        synchronized (inputBuffer){
            inputBuffer[0]=xian;
            inputBuffer.notify();
        }
    }

    private Xian getInput(){
        synchronized (inputBuffer){
            if(inputBuffer[0]!=null){
                Xian linshi=inputBuffer[0];
                inputBuffer[0]=null;
                return linshi;
            }
            try {
                inputBuffer.wait();
            } catch (InterruptedException e) {
            }
            return this.getInput();
        }
    }

    @Override
    public Xian yidong() {
        return getInput();
    }

}
