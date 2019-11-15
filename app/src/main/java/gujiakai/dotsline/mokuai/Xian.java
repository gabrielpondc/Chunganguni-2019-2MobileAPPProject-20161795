package gujiakai.dotsline.mokuai;

public class Xian {
    private final fangxiang fangxiang;
    private final int lie;
    private final int yuanzhu;

    public Xian(fangxiang fangxiang, int lie, int yuanzhu) {
        this.fangxiang = fangxiang;
        this.lie = lie;
        this.yuanzhu = yuanzhu;
    }

    public fangxiang fangxiang() {
        return fangxiang;
    }

    public int lie() {
        return lie;
    }

    public int yuanzhu() {
        return yuanzhu;
    }

    @Override
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null || getClass() != y.getClass()) return false;//获得新类

        Xian xian = (Xian) y;

        if (lie != xian.lie) return false;
        if (yuanzhu != xian.yuanzhu) return false;
        return fangxiang == xian.fangxiang;

    }

    @Override
    public int hashCode() {
        int jieguo = fangxiang != null ? fangxiang.hashCode() : 0;
        jieguo = 31 * jieguo + lie;
        jieguo = 31 * jieguo + yuanzhu;
        return jieguo;
    }

    @Override
    public String toString() {
        return "fangxiang:"+ fangxiang().toString()+"lie:"+ lie +"yuanzhu"+ yuanzhu;
    }
}
