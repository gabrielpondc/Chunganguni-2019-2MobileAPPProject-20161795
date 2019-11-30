package gujiakai.dotsline.mokuai;

/**
 * Created by GUJIAKAI on 2019/11
 */
public abstract class wanjia {
    public abstract Xian yidong();
    private String name;//获取玩家名称

    public youxi huoquyouxi() {
        return game;
    }

    public void jiarudaoyouxi(youxi game) {
        this.game = game;
    }

    private youxi game;

    public wanjia(String mingcheng){
        this.name=mingcheng;
    }

    public String huoqumingcheng(){
        return name;
    }

    public static int zhibiao(wanjia Wanjia, wanjia... wanjia){
        for(int a=0;a<wanjia.length;a++){
            if (Wanjia==wanjia[a]) //得到玩家的数量
                return a;
        }
        return -1;
    }

    @Override
    public boolean equals(Object xiangmu) {
        if (this == xiangmu) return true;//重置新类，返回
        if (xiangmu == null || getClass() != xiangmu.getClass()) return false;
//玩家类
        wanjia Wanjia = (wanjia) xiangmu;

        return !(name != null ? !name.equals(Wanjia.name) : Wanjia.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }//哈希值来重置
}
