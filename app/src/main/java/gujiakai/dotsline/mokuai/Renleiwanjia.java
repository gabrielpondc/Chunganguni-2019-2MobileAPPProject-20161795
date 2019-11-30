package gujiakai.dotsline.mokuai;

/**
 * Created by GUJIAKAI on 2019/11
 */
public class Renleiwanjia extends wanjia {
    private Xian[] shuruhuacun =new Xian[1];

    public Renleiwanjia(String mingcheng) {
        super(mingcheng);
    }

    public void add(Xian xian) {
        synchronized (shuruhuacun){
            shuruhuacun[0]=xian;
            shuruhuacun.notify();
        }
    }

    private Xian getInput(){
        synchronized (shuruhuacun){
            if(shuruhuacun[0]!=null){
                Xian linshi= shuruhuacun[0];
                shuruhuacun[0]=null;
                return linshi;
            }
            try {
                shuruhuacun.wait();
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
