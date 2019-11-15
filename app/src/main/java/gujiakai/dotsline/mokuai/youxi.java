package gujiakai.dotsline.mokuai;
import java.util.Observable;
import java.util.ArrayList;


/**
 * Created by GUJIAKAI on 2019/11
 */
public class youxi extends Observable {
    public wanjia[] huoquwanjia() {
        return wanjiamen;
    }

    private wanjia[] wanjiamen;
    private int wanjiazhibiao;

    public int huoqukuan() {
        return kuan;
    }

    public int huoqugao() {
        return gao;
    }

    private int kuan;
    private int gao;
    private wanjia[][] zhanlin;
    private boolean[][] shuipingxianmen;
    private boolean[][] chuizhixianmen;
    private Xian zuixinxian;

    public Xian huoquzuixinxian() {
        return zuixinxian;
    }

    public youxi(int kuan, int gao, wanjia firstMover, wanjia... wanjiamen) {
        this.kuan = kuan;
        this.gao = gao;
        this.wanjiamen = wanjiamen;

        zhanlin = new wanjia[gao][kuan];
        shuipingxianmen = new boolean[gao + 1][kuan];
        chuizhixianmen = new boolean[gao][kuan + 1];

        this.wanjiazhibiao = 0;
        for (int a = 0; a < wanjiamen.length; a++) {
            if (wanjiamen[a] == null) {
                throw new java.lang.IllegalArgumentException("플레이어가 비어 있음");
            }
            if (wanjiamen[a] == firstMover) {
                wanjiazhibiao = a;
            }
            wanjiamen[a].jiarudaoyouxi(this);
        }

        if (wanjiamen.length == 0)
            throw new java.lang.IllegalArgumentException("플레이어가 없음");
        if (kuan < 1 || gao < 1)
            throw new java.lang.IllegalArgumentException("크기가 너무 작음");
    }

    public void kaishi() {
        while (!youxijieshupanduan()) {
            tianjiayidong(xianzaikaishi().yidong());
            setChanged();
            notifyObservers();
        }
    }

    public void tianjiayidong(Xian yidong) {
        if (xianshifoubeizhan(yidong)) {
            return;
        }
        boolean xinhezibeizhan = changshizhanyou(yidong);
        shezhizhanyouxian(yidong);
        zuixinxian =yidong;

        if (!xinhezibeizhan)
            xiayigewanjia();
    }

    public wanjia xianzaikaishi() {
        return wanjiamen[wanjiazhibiao];
    }

    public boolean xianshifoubeizhan(fangxiang Fangxiang, int lie, int yuanzhu) {
        return xianshifoubeizhan(new Xian(Fangxiang, lie, yuanzhu));
    }

    public boolean xianshifoubeizhan(Xian xian) {
        switch (xian.fangxiang()) {
            case SHUIPINGXIAN:
                return shuipingxianmen[xian.lie()][xian.yuanzhu()];
            case CHUIZHIXIAN:
                return chuizhixianmen[xian.lie()][xian.yuanzhu()];
        }
        throw new java.lang.IllegalArgumentException(xian.fangxiang().toString());
    }

    public wanjia huoquzhanyougezi(int lie, int hang){
        return zhanlin[lie][hang];
    }

    public int huoquwanjiazhanyouge(wanjia Wanjia) {
        int jisuan=0;
        for(int a = 0; a< zhanlin.length; a++) {
            for (int b = 0; b < zhanlin[0].length; b++) {
                if(huoquzhanyougezi(a,b)==Wanjia)
                    jisuan++;
            }
        }
        return jisuan;
    }

    private boolean changshizhanyou(Xian yidong) {
        boolean youbianzhanyou = changshizhanyouyoubiangezi(yidong);
        boolean xiamianzhanyou = changshizhanyouxiamiangezi(yidong);
        boolean shangmianzhanyou = changshizhanyoushangmiangezi(yidong);
        boolean zuobianzhanyou = changshizhanyouzuobiangezi(yidong);
        return zuobianzhanyou||youbianzhanyou||shangmianzhanyou||xiamianzhanyou;
    }

    private void shezhizhanyouxian(Xian xian) {
        switch (xian.fangxiang()) {
            case SHUIPINGXIAN:
                shuipingxianmen[xian.lie()][xian.yuanzhu()] = true;
                break;
            case CHUIZHIXIAN:
                chuizhixianmen[xian.lie()][xian.yuanzhu()] = true;
                break;
        }
    }

    private void shezhihezizhanyou(int lie, int hang, wanjia wanjia) {
        zhanlin[lie][hang] = wanjia;
    }

    private boolean changshizhanyoushangmiangezi(Xian yidong) {
        if (yidong.fangxiang() != fangxiang.SHUIPINGXIAN || yidong.lie() <= 0)
            return false;
        if (xianshifoubeizhan(fangxiang.SHUIPINGXIAN, yidong.lie() - 1, yidong.yuanzhu())
                && xianshifoubeizhan(fangxiang.CHUIZHIXIAN, yidong.lie() - 1, yidong.yuanzhu())
                && xianshifoubeizhan(fangxiang.CHUIZHIXIAN, yidong.lie() - 1, yidong.yuanzhu() + 1)) {
            shezhihezizhanyou(yidong.lie() - 1, yidong.yuanzhu(), xianzaikaishi());
            return true;
        } else {
            return false;
        }
    }

    private boolean changshizhanyouxiamiangezi(Xian yidong) {
        if (yidong.fangxiang() != fangxiang.SHUIPINGXIAN || yidong.lie() >= (gao))
            return false;
        if (xianshifoubeizhan(fangxiang.SHUIPINGXIAN, yidong.lie() + 1, yidong.yuanzhu())
                && xianshifoubeizhan(fangxiang.CHUIZHIXIAN, yidong.lie(), yidong.yuanzhu())
                && xianshifoubeizhan(fangxiang.CHUIZHIXIAN, yidong.lie(), yidong.yuanzhu() + 1)) {
            shezhihezizhanyou(yidong.lie(), yidong.yuanzhu(), xianzaikaishi());
            return true;
        } else {
            return false;
        }
    }

    private boolean changshizhanyouzuobiangezi(Xian yidong) {
        if (yidong.fangxiang() != fangxiang.CHUIZHIXIAN || yidong.yuanzhu() <= 0)
            return false;
        if (xianshifoubeizhan(fangxiang.CHUIZHIXIAN, yidong.lie(), yidong.yuanzhu() - 1)
                && xianshifoubeizhan(fangxiang.SHUIPINGXIAN, yidong.lie(), yidong.yuanzhu() - 1)
                && xianshifoubeizhan(fangxiang.SHUIPINGXIAN, yidong.lie() + 1, yidong.yuanzhu() - 1)) {
            shezhihezizhanyou(yidong.lie(), yidong.yuanzhu() - 1, xianzaikaishi());
            return true;
        } else {
            return false;
        }
    }

    private boolean changshizhanyouyoubiangezi(Xian yidong) {
        if (yidong.fangxiang() != fangxiang.CHUIZHIXIAN || yidong.yuanzhu() >= (kuan))
            return false;
        if (xianshifoubeizhan(fangxiang.CHUIZHIXIAN, yidong.lie(), yidong.yuanzhu() + 1)
                && xianshifoubeizhan(fangxiang.SHUIPINGXIAN, yidong.lie(), yidong.yuanzhu())
                && xianshifoubeizhan(fangxiang.SHUIPINGXIAN, yidong.lie() + 1, yidong.yuanzhu())) {
            shezhihezizhanyou(yidong.lie(), yidong.yuanzhu(), xianzaikaishi());
            return true;
        } else {
            return false;
        }
    }

    private void xiayigewanjia() {
        wanjiazhibiao = (wanjiazhibiao + 1) % wanjiamen.length;
    }

    private boolean youxijieshupanduan() {
        for (int a = 0; a < zhanlin.length; a++) {
            for (int b = 0; b < zhanlin[0].length; b++) {
                 if (zhanlin[a][b] == null)
                    return false;
            }
        }
        return true;
    }

    public wanjia[] huoquyinjia() {
        if(!youxijieshupanduan()){
            return null;
        }
        int jisuanyouxi = wanjiamen.length;
        int[] jisuanduoshaohezibeizhan = new int[jisuanyouxi];
        int jisuanzuidabeizhanyouhezi=0;
        for (int wanjia_a = 0; wanjia_a < jisuanyouxi; wanjia_a++) {
            jisuanduoshaohezibeizhan[wanjia_a]= huoquwanjiazhanyouge(wanjiamen[wanjia_a]);
            if(jisuanduoshaohezibeizhan[wanjia_a]>jisuanzuidabeizhanyouhezi)
                jisuanzuidabeizhanyouhezi=jisuanduoshaohezibeizhan[wanjia_a];
        }
        ArrayList<wanjia> yingjia=new ArrayList<>();
        for (int wanjia_a = 0; wanjia_a < jisuanyouxi; wanjia_a++) {
            if(jisuanduoshaohezibeizhan[wanjia_a]==jisuanzuidabeizhanyouhezi){
                yingjia.add(wanjiamen[wanjia_a]);
            }
        }
        return yingjia.toArray(new wanjia[0]);
    }
}
