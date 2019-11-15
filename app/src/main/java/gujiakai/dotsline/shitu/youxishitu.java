package gujiakai.dotsline.shitu;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import gujiakai.dotsline.mokuai.Xian;
import gujiakai.dotsline.mokuai.fangxiang;
import gujiakai.dotsline.mokuai.wanjia;
import gujiakai.dotsline.mokuai.youxi;
import gujiakai.dotsline.mokuai.Renleiwanjia;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.annotation.SuppressLint;


@SuppressLint("ClickableViewAccessibility")
public class youxishitu extends View implements Observer {

    protected static final int[] wanjiayanse = new int[]{0xFF6C69FF, 0x88E5004F};
    public static final int huise = 0xFF666666;
    protected youxi Youxi;
    protected Xian yidong;
    protected Paint tuya;
    protected int shituchang;
    protected int shitugao;
    protected wanjiazhuangtaishitu wanjiazhuangtai;

    public youxishitu(Context wenzi, AttributeSet zhuangtaishezhi) {
        super(wenzi, zhuangtaishezhi);
        tuya = new Paint();
        tuya.setAntiAlias(true);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View shitu, MotionEvent shijian) {
                wanjiazhuangtai.wanjiakaishichumo();
                huoqushuruzhi(shijian);
                return false;
            }
        });

    }
    public void kaishiyouxi(wanjia diyiciyidong, wanjia... wanjias) {
        Youxi = new youxi(5, 5, diyiciyidong, wanjias);
        Youxi.addObserver(this);
        new Thread() {
            @Override
            public void run() {
                Youxi.kaishi();
            }
        }.start();
        shengjiwanjiazhuangtai();
        invalidate();
    }

    public void setWanjiazhuangtai(wanjiazhuangtaishitu wanjiazhuangtai) {
        this.wanjiazhuangtai = wanjiazhuangtai;
    }


    protected static final float yuanzhoulv = (float) 14 / 824;//计算
    protected static final float kaishi = (float) 6 / 824;//最终
    protected static final float tingzhi = (float) 819 / 824;//产值
    protected static final float jia1 = (float) 18 / 824;//玩家1
    protected static final float jia2 = (float) 2 / 824;//玩家2
    protected static final float jia3 = (float) 14 / 824;//玩家3
    protected static final float jia4 = (float) 141 / 824;//玩家4
    protected static final float jia5 = (float) 159 / 824;//玩家5
    protected static final float jia6 = (float) 9 / 824;//玩家6

    @Override
    protected void onDraw(Canvas yanselei) {

        super.onDraw(yanselei);
        if (Youxi == null)
            return;
        yanselei.drawColor(0x00FFFFFF);
        shituchang = this.getWidth();
        shitugao = this.getHeight();
        int zuixiaozhi = Math.min(shituchang, shitugao);
        float radius = youxishitu.yuanzhoulv * zuixiaozhi;
        float kaishi = youxishitu.kaishi * zuixiaozhi;
        float tingzhi = youxishitu.tingzhi * zuixiaozhi;
        float jia1 = youxishitu.jia1 * zuixiaozhi;
        float jia2 = youxishitu.jia2 * zuixiaozhi;
        float jia4 = youxishitu.jia4 * zuixiaozhi;
        float jia5 = youxishitu.jia5 * zuixiaozhi;
        float jia6 = youxishitu.jia6 * zuixiaozhi;

        //涂鸦数轴代码
        tuya.setColor(wanjiaxianzaiyanse());
        float linshi = jia2 / 2;
        for (int a = 1; a < 6; a++) {
            yanselei.drawLine(linshi * a, linshi * a, zuixiaozhi - linshi * (a - 1), linshi * a, tuya);
            yanselei.drawLine(linshi * a, linshi * a, linshi * a, zuixiaozhi - linshi * (a - 1), tuya);
            yanselei.drawLine(zuixiaozhi - linshi * (a - 1), linshi * a, zuixiaozhi - linshi * (a - 1), zuixiaozhi - linshi * (a - 1), tuya);
            yanselei.drawLine(linshi * a, zuixiaozhi - linshi * (a - 1), zuixiaozhi - linshi * (a - 1), zuixiaozhi - linshi * (a - 1), tuya);
        }

        //涂鸦面板代码
        tuya.setColor(0xFF777777);
        for (int a = 0; a < 6; a++) {
            yanselei.drawLine(kaishi + jia5 * a, kaishi, kaishi + jia5 * a, tingzhi,
                    tuya);
            yanselei.drawLine(kaishi + jia5 * a + jia1, kaishi, kaishi + jia5 * a
                    + jia1, tingzhi, tuya);
            yanselei.drawLine(kaishi, kaishi + jia5 * a, tingzhi, kaishi + jia5 * a,
                    tuya);
            yanselei.drawLine(kaishi, kaishi + jia5 * a + jia1, tingzhi, kaishi + jia5
                    * a + jia1, tuya);
        }

        //涂鸦线
        tuya.setColor(0xFF000000);
        for (int a = 0; a < 6; a++) {
            for (int b = 0; b < 5; b++) {
                Xian shuipingxian = new Xian(fangxiang.SHUIPINGXIAN, a, b);
                if (shuipingxian.equals(Youxi.huoquzuixinxian())) {
                    tuya.setColor(0xFFFF7700);
                } else if (Youxi.xianshifoubeizhan(shuipingxian)) {
                    tuya.setColor(0xFF000000);
                } else {
                    tuya.setColor(0xFFFFFFFF);
                }
                yanselei.drawRect(kaishi + jia5 * b + jia1, kaishi + jia5 * a
                        + jia2, kaishi + jia5 * (b + 1), kaishi + jia5 * a + jia1
                        - jia2, tuya);

                Xian chuizhixian = new Xian(fangxiang.CHUIZHIXIAN, b, a);
                if (chuizhixian.equals(Youxi.huoquzuixinxian())) {
                    tuya.setColor(0xFFFF7700);
                } else if (Youxi.xianshifoubeizhan(chuizhixian)) {
                    tuya.setColor(0xFF000000);
                } else {
                    tuya.setColor(0xFFFFFFFF);
                }
                yanselei.drawRect(kaishi + jia5 * a + jia2, kaishi + jia5 * b
                        + jia1, kaishi + jia5 * a + jia1 - jia2, kaishi + jia5
                        * (b + 1), tuya);
            }
        }

        //tuya boxes
        for (int a = 0; a < Youxi.huoqukuan(); a++) {
            for (int b = 0; b < Youxi.huoqugao(); b++) {
                tuya.setColor(Youxi.huoquzhanyougezi(b, a) == null ? Color.TRANSPARENT : wanjiayanse[wanjia.zhibiao(Youxi.huoquzhanyougezi(b, a), Youxi.huoquwanjia())]);
                yanselei.drawRect(kaishi + jia5 * a + jia1 + jia2, kaishi
                        + jia5 * b + jia1 + jia2, kaishi + jia5 * a + jia1
                        + jia4 - jia2, kaishi + jia5 * b + jia1 + jia4
                        - jia2, tuya);
            }
        }

        //tuya points
        tuya.setColor(huise);
        for (int a = 0; a < 6; a++) {
            for (int b = 0; b < 6; b++) {
                yanselei.drawCircle(kaishi + jia6 + b * jia5 + 1, kaishi + jia6 + a
                        * jia5 + 1, radius, tuya);
            }
        }

        invalidate();
    }

    private void huoqushuruzhi(MotionEvent shijian) {
        if (shijian.getAction() != MotionEvent.ACTION_DOWN)
            return;
        if (!(Youxi.xianzaikaishi() instanceof Renleiwanjia)) {
            return;
        }
        float xzhou = shijian.getX();
        float yzhou = shijian.getY();
        int zuixiaozhi = Math.min(shituchang, shitugao);
        float kaishi = youxishitu.kaishi * zuixiaozhi;
        float add1 = youxishitu.jia1 * zuixiaozhi;
        float add2 = youxishitu.jia2 * zuixiaozhi;
        float add3 = youxishitu.jia3 * zuixiaozhi;
        float add5 = youxishitu.jia5 * zuixiaozhi;
        int f = -1, a = -1, b = -1;
        for (int e = 0; e < 6; e++) {
            for (int g = 0; g < 5; g++) {
                if ((kaishi + add5 * g + add1 - add3) <= xzhou
                        && xzhou <= (kaishi + add5 * (g + 1) + add3)
                        && yzhou >= kaishi + add5 * e + add2 - add3
                        && yzhou <= kaishi + add5 * e + add1 - add2 + add3) {
                    f = 0;
                    a = e;
                    b = g;
                }
                if (kaishi + add5 * e + add2 - add3 <= xzhou
                        && xzhou <= kaishi + add5 * e + add1 - add2 + add3
                        && yzhou >= kaishi + add5 * g + add1 - add3
                        && yzhou <= kaishi + add5 * (g + 1) + add3) {
                    f = 1;
                    a = g;
                    b = e;
                }
            }
        }

        if (a != -1 && b != -1 && f != -1) {
            fangxiang Fangxiang;
            if (f == 0)
                Fangxiang = fangxiang.SHUIPINGXIAN;
            else
                Fangxiang = fangxiang.CHUIZHIXIAN;
            yidong = new Xian(Fangxiang, a, b);
            ((Renleiwanjia) Youxi.xianzaikaishi()).add(yidong);

        }
    }

    private void shengjiwanjiazhuangtai() {

    }

    @Override
    public void update(Observable shouwangzhe, Object shuju) {
        wanjiazhuangtai.setXianzaikaishi(Youxi.xianzaikaishi());
        Map<wanjia, Integer> wanjizhanlingezi = new HashMap<>();
        for (wanjia Wanjia : Youxi.huoquwanjia()) {
            wanjizhanlingezi.put(Wanjia, Youxi.huoquwanjiazhanyouge(Wanjia));
        }
        wanjiazhuangtai.duiwanjijinxinjishu(wanjizhanlingezi);

        wanjia[] shenzhe = Youxi.huoquyinjia();
        if (shenzhe != null) {
            wanjiazhuangtai.shezhiyinjia(shenzhe);
        }
    }

    private int wanjiaxianzaiyanse() {
        int wanjiazhibiao = wanjia.zhibiao(Youxi.xianzaikaishi(), Youxi.huoquwanjia());
        return wanjiazhibiao == -1 ? Color.BLACK : wanjiayanse[wanjiazhibiao];
    }
}
