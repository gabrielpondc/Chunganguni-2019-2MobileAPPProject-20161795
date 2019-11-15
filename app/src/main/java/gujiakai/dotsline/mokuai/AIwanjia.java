package gujiakai.dotsline.mokuai;
import java.util.Vector;
import java.util.HashMap;
import java.util.ArrayList;


public class AIwanjia extends wanjia {
    private int nanyidu;
    private boolean[][] shuipinxian;
    private boolean[][] chuizhixian;
    private Hezi[][] hezi;
    private Vector<Xian> anquanxian;
    private Vector<Xian> youxian;
    private Vector<Xian> huaixian;
    private HashMap<Xian, Integer> youxianzhi;
    private HashMap<Xian, Integer> huanxianzhi;
    private HashMap<Xian, Integer> youxianzhonglei;

    private AIwanjia(int nanyidu, youxi Youxi) {
        this(null);
        this.setNanyidu(nanyidu);
        this.jiarudaoyouxi(Youxi);
    }

    public AIwanjia(String name) {
        super(name);
        shuipinxian = new boolean[6][5];
        chuizhixian = new boolean[5][6];
        hezi = new Hezi[5][5];
        anquanxian = new Vector<Xian>();
        youxian = new Vector<Xian>();
        huaixian = new Vector<Xian>();
        huanxianzhi = new HashMap<Xian, Integer>();
        youxianzhi = new HashMap<Xian, Integer>();
        youxianzhonglei = new HashMap<Xian, Integer>();

    }

    public void setNanyidu(int nanyidu) {
        this.nanyidu = nanyidu;
    }

    public Xian yidong() {
        chushihuamianban();
        chushihuaanquanxian();
        chushihuahaoxian();
        chushihuahuaixian();

        if (nanyidu <= 1) {
            return yiban();
        } else if (nanyidu == 2) {
            return kunnan();
        } else if (nanyidu >= 3) {
            return jiqikunnan();
        } else
            return suiji();
    }

    private Xian xian(Mianban vboard) {
        for (int a = 0; a < 6; a++) {
            for (int b = 0; b < 5; b++) {
                shuipinxian[a][b] = vboard.chuzhixian[a][b];
                chuizhixian[b][a] = vboard.shuipingxian[b][a];
            }
        }
        for (int a = 0; a < 5; a++) {
            for (int b = 0; b < 5; b++) {
                hezi[a][b] = new Hezi(chuizhixian[a][b], shuipinxian[a][b],
                        chuizhixian[a][b + 1], shuipinxian[a + 1][b]);
            }
        }
        chushihuaanquanxian();
        chushihuahaoxian();
        chushihuahuaixian();

        if (nanyidu == 1) {
            return yiban();
        } else if (nanyidu == 2) {
            return kunnan();
        } else if (nanyidu == 3) {
            return jiqikunnan();
        } else
            return suiji();
    }

    private Xian yiban() {
        if (youxian.size() != 0)
            return youxian.get((int) ((youxian.size()) * Math.random()));
        if (anquanxian.size() != 0)
            return anquanxian.get((int) ((anquanxian.size()) * Math.random()));
        else {
            Xian xiao = null;
            int zuixiaozhi = 26;
            chushihuahuaixianzhi();
            for (Xian xian : huaixian) {
                if (huanxianzhi.get(xian) < zuixiaozhi) {
                    xiao = xian;
                    zuixiaozhi = huanxianzhi.get(xian);
                }
            }
            return xiao;
        }
    }

    private Xian kunnan() {
        if (anquanxian.size() != 0) {
            if (youxian.size() != 0)
                return youxian.get((int) ((youxian.size()) * Math.random()));
            else
                return anquanxian.get((int) ((anquanxian.size()) * Math.random()));
        } else if (youxian.size() != 0) {
            if (huaixian.size() == 0)
                return youxian.get((int) ((youxian.size()) * Math.random()));
            chushihuahaoxianzhi();
            chushihuahuaixianzhi();

            ArrayList<Xian> huai = new ArrayList<Xian>();
            ArrayList<Xian> huai2 = new ArrayList<Xian>();
            ArrayList<Xian> hao = new ArrayList<Xian>();
            ArrayList<Xian> hao2 = new ArrayList<Xian>();
            int huaizhi = 26;
            int huai2zhi = 26;
            int haozhi = 0;
            int haozhi2 = 0;
            for (Xian xian : huaixian) {
                if (huanxianzhi.get(xian) < huaizhi) {
                    huai2.clear();
                    for (Xian zuixiao : huai) {
                        huai2.add(zuixiao);
                    }
                    huai2zhi = huaizhi;
                    huai.clear();
                    huai.add(xian);
                    huaizhi = huanxianzhi.get(xian);
                } else if (huanxianzhi.get(xian) == huaizhi) {
                    huai.add(xian);
                }
            }
            for (Xian Line : youxian) {
                if (youxianzhi.get(Line) > haozhi) {
                    hao2.clear();
                    for (Xian xiao : hao) {
                        hao2.add(xiao);
                    }
                    haozhi2 = haozhi;
                    hao.clear();
                    hao.add(Line);
                    haozhi = youxianzhi.get(Line);
                } else if (youxianzhi.get(Line) == haozhi) {
                    hao.add(Line);
                }
            }

            if (haozhi == 2 && huaizhi == 2 && huai.size() == 1
                    && huai2.size() != 0 && huai2zhi > 6) {
                if (haozhi2 > 2) {
                    return hao2.get((int) ((hao2.size()) * Math.random()));
                }
                chongzhihaoxianfang();
                for (Xian Line : hao) {
                    if (youxianzhonglei.get(Line) == 2) {
                        return Line;
                    }
                }
                return huai.get(0);
            } else
                return hao.get((int) ((hao.size()) * Math.random()));

        } else {
            Xian xiaozhi = null;
            int zuixiaozhi = 26;
            chushihuahuaixianzhi();
            for (Xian xian : huaixian) {
                if (huanxianzhi.get(xian) < zuixiaozhi) {
                    xiaozhi = xian;
                    zuixiaozhi = huanxianzhi.get(xian);
                }
            }
            return xiaozhi;
        }

    }

    private Xian jiqikunnan() {
        if (anquanxian.size() != 0) {
            if (youxian.size() != 0)
                return youxian.get((int) ((youxian.size()) * Math.random()));
            else
                return anquanxian.get((int) ((anquanxian.size()) * Math.random()));
        } else if (youxian.size() != 0) {
            if (huaixian.size() == 0)
                return youxian.get((int) ((youxian.size()) * Math.random()));
            chushihuahaoxianzhi();
            chushihuahuaixianzhi();

            ArrayList<Xian> huai = new ArrayList<Xian>();
            ArrayList<Xian> huai2 = new ArrayList<Xian>();
            ArrayList<Xian> hao = new ArrayList<Xian>();
            ArrayList<Xian> hao2 = new ArrayList<Xian>();
            int huaizhi = 26;
            int hua2zhi = 26;
            int haozhi = 0;
            int hao2zhi = 0;
            for (Xian xian : huaixian) {
                if (huanxianzhi.get(xian) < huaizhi) {
                    huai2.clear();
                    for (Xian xiao : huai) {
                        huai2.add(xiao);
                    }
                    hua2zhi = huaizhi;
                    huai.clear();
                    huai.add(xian);
                    huaizhi = huanxianzhi.get(xian);
                } else if (huanxianzhi.get(xian) == huaizhi) {
                    huai.add(xian);
                }
            }
            for (Xian xian : youxian) {
                if (youxianzhi.get(xian) > haozhi) {
                    hao2.clear();
                    for (Xian xiao : hao) {
                        hao2.add(xiao);
                    }
                    hao2zhi = haozhi;
                    hao.clear();
                    hao.add(xian);
                    haozhi = youxianzhi.get(xian);
                } else if (youxianzhi.get(xian) == haozhi) {
                    hao.add(xian);
                }
            }

            if (haozhi == 2 && huaizhi == 2 && huai.size() == 1
                    && huai2.size() != 0 && hua2zhi > 6) {
                if (hao2zhi > 2) {
                    return hao2.get((int) ((hao2.size()) * Math.random()));
                }
                chongzhihaoxianfang();
                for (Xian xian : hao) {
                    if (youxianzhonglei.get(xian) == 2) {
                        return xian;
                    }
                }
                return huai.get(0);
            } else
                return hao.get((int) ((hao.size()) * Math.random()));

        } else {
            Xian zuixiao = null;
            int zuixiaozhi = 26;
            chushihuahuaixianzhi();
            for (Xian xian : huaixian) {
                if (huanxianzhi.get(xian) < zuixiaozhi) {
                    zuixiao = xian;
                    zuixiaozhi = huanxianzhi.get(xian);
                }
            }
            return zuixiao;
        }

    }

    private Xian suiji() {
        if (youxian.size() != 0)
            return youxian.get((int) ((youxian.size()) * Math.random()));
        if (anquanxian.size() != 0)
            return anquanxian.get((int) ((anquanxian.size()) * Math.random()));
        Vector<Xian> linshi = new Vector<Xian>();
        for (int a = 0; a < 6; a++) {
            for (int d = 0; d < 5; d++) {
                if (!shuipinxian[a][d])
                    linshi.add(new Xian(fangxiang.SHUIPINGXIAN, a, d));
                if (!chuizhixian[d][a])
                    linshi.add(new Xian(fangxiang.CHUIZHIXIAN, d, a));
            }
        }
        return linshi.get((int) ((linshi.size()) * Math.random()));
    }

    private void chushihuahaoxian() {
        int jisuan = 0;
        boolean f1 = false;
        boolean f2 = false;
        youxian.clear();
        for (int a = 0; a < 6; a++) {
            for (int c = 0; c < 5; c++) {
                if (!shuipinxian[a][c]) {
                    if (a == 0) {
                        jisuan = 0;
                        if (hezi[a][c].zuo)
                            jisuan++;
                        if (hezi[a][c].dibu)
                            jisuan++;
                        if (hezi[a][c].you)
                            jisuan++;
                        if (hezi[a][c].baohan() == 3)
                            youxian.add(new Xian(fangxiang.SHUIPINGXIAN, a, c));
                    } else if (a == 5) {

                        jisuan = 0;
                        if (hezi[a - 1][c].zuo)
                            jisuan++;
                        if (hezi[a - 1][c].shang)
                            jisuan++;
                        if (hezi[a - 1][c].you)
                            jisuan++;
                        if (jisuan == 3)
                            youxian.add(new Xian(fangxiang.SHUIPINGXIAN, a, c));
                    } else {
                        jisuan = 0;
                        if (hezi[a][c].zuo)
                            jisuan++;
                        if (hezi[a][c].dibu)
                            jisuan++;
                        if (hezi[a][c].you)
                            jisuan++;
                        if (jisuan == 3)
                            f1 = true;
                        jisuan = 0;
                        if (hezi[a - 1][c].zuo)
                            jisuan++;
                        if (hezi[a - 1][c].shang)
                            jisuan++;
                        if (hezi[a - 1][c].you)
                            jisuan++;
                        if (jisuan == 3)
                            f2 = true;
                        if (f1 || f2)
                            youxian.add(new Xian(fangxiang.SHUIPINGXIAN, a, c));
                        f1 = false;
                        f2 = false;
                    }
                }
                if (!chuizhixian[c][a]) {
                    if (a == 0) {
                        jisuan = 0;
                        if (hezi[c][a].you)
                            jisuan++;
                        if (hezi[c][a].dibu)
                            jisuan++;
                        if (hezi[c][a].shang)
                            jisuan++;
                        if (jisuan == 3)
                            youxian.add(new Xian(fangxiang.CHUIZHIXIAN, c, a));
                    } else if (a == 5) {
                        jisuan = 0;
                        if (hezi[c][a - 1].zuo)
                            jisuan++;
                        if (hezi[c][a - 1].shang)
                            jisuan++;
                        if (hezi[c][a - 1].dibu)
                            jisuan++;
                        if (jisuan == 3)
                            youxian.add(new Xian(fangxiang.CHUIZHIXIAN, c, a));
                    } else {
                        jisuan = 0;
                        if (hezi[c][a].you)
                            jisuan++;
                        if (hezi[c][a].dibu)
                            jisuan++;
                        if (hezi[c][a].shang)
                            jisuan++;
                        if (jisuan == 3)
                            f1 = true;
                        jisuan = 0;
                        if (hezi[c][a - 1].zuo)
                            jisuan++;
                        if (hezi[c][a - 1].shang)
                            jisuan++;
                        if (hezi[c][a - 1].dibu)
                            jisuan++;
                        if (jisuan == 3)
                            f2 = true;
                        if (f1 || f2)
                            youxian.add(new Xian(fangxiang.CHUIZHIXIAN, c, a));
                        f1 = false;
                        f2 = false;
                    }
                }
            }
        }

    }

    private void chushihuahuaixian() {
        int jisuan = 0;
        boolean f1 = false;
        boolean f2 = false;
        huaixian.clear();
        for (int a = 0; a < 6; a++) {
            for (int c = 0; c < 5; c++) {
                if (!shuipinxian[a][c]) {
                    if (a == 0) {
                        jisuan = 0;
                        if (hezi[a][c].zuo)
                            jisuan++;
                        if (hezi[a][c].dibu)
                            jisuan++;
                        if (hezi[a][c].you)
                            jisuan++;
                        if (jisuan == 2)
                            huaixian.add(new Xian(fangxiang.SHUIPINGXIAN, a, c));
                    } else if (a == 5) {

                        jisuan = 0;
                        if (hezi[a - 1][c].zuo)
                            jisuan++;
                        if (hezi[a - 1][c].shang)
                            jisuan++;
                        if (hezi[a - 1][c].you)
                            jisuan++;
                        if (jisuan == 2)
                            huaixian.add(new Xian(fangxiang.SHUIPINGXIAN, a, c));
                    } else {
                        jisuan = 0;
                        if (hezi[a][c].zuo)
                            jisuan++;
                        if (hezi[a][c].dibu)
                            jisuan++;
                        if (hezi[a][c].you)
                            jisuan++;
                        if (jisuan == 2)
                            f1 = true;
                        jisuan = 0;
                        if (hezi[a - 1][c].zuo)
                            jisuan++;
                        if (hezi[a - 1][c].shang)
                            jisuan++;
                        if (hezi[a - 1][c].you)
                            jisuan++;
                        if (jisuan == 2)
                            f2 = true;
                        if (f1 || f2)
                            huaixian.add(new Xian(fangxiang.SHUIPINGXIAN, a, c));
                        f1 = false;
                        f2 = false;
                    }
                }
                if (!chuizhixian[c][a]) {
                    if (a == 0) {
                        jisuan = 0;
                        if (hezi[c][a].you)
                            jisuan++;
                        if (hezi[c][a].dibu)
                            jisuan++;
                        if (hezi[c][a].shang)
                            jisuan++;
                        if (jisuan == 2)
                            huaixian.add(new Xian(fangxiang.CHUIZHIXIAN, c, a));
                    } else if (a == 5) {
                        jisuan = 0;
                        if (hezi[c][a - 1].zuo)
                            jisuan++;
                        if (hezi[c][a - 1].shang)
                            jisuan++;
                        if (hezi[c][a - 1].dibu)
                            jisuan++;
                        if (jisuan == 2)
                            huaixian.add(new Xian(fangxiang.CHUIZHIXIAN, c, a));
                    } else {
                        jisuan = 0;
                        if (hezi[c][a].you)
                            jisuan++;
                        if (hezi[c][a].dibu)
                            jisuan++;
                        if (hezi[c][a].shang)
                            jisuan++;
                        if (jisuan == 2)
                            f1 = true;
                        jisuan = 0;
                        if (hezi[c][a - 1].zuo)
                            jisuan++;
                        if (hezi[c][a - 1].shang)
                            jisuan++;
                        if (hezi[c][a - 1].dibu)
                            jisuan++;
                        if (jisuan == 2)
                            f2 = true;
                        if (f1 || f2)
                            huaixian.add(new Xian(fangxiang.CHUIZHIXIAN, c, a));
                        f1 = false;
                        f2 = false;
                    }
                }
            }
        }
        for (Xian i : youxian) {
            try {
                for (Xian j : huaixian) {
                    if (i.fangxiang() == j.fangxiang() && i.lie() == j.lie() && i.yuanzhu() == j.yuanzhu()) {
                        huaixian.remove(j);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    private void chushihuaanquanxian() {
        int jisuan = 0;
        boolean f1 = false;
        boolean f2 = false;
        anquanxian.clear();

        for (int c = 0; c < 6; c++) {
            for (int a = 0; a < 5; a++) {
                if (!shuipinxian[c][a]) {
                    if (c == 0) {
                        jisuan = 0;
                        if (hezi[c][a].zuo)
                            jisuan++;
                        if (hezi[c][a].dibu)
                            jisuan++;
                        if (hezi[c][a].you)
                            jisuan++;
                        if (jisuan < 2)
                            anquanxian.add(new Xian(fangxiang.SHUIPINGXIAN, c, a));
                    } else if (c == 5) {

                        jisuan = 0;
                        if (hezi[c - 1][a].zuo)
                            jisuan++;
                        if (hezi[c - 1][a].shang)
                            jisuan++;
                        if (hezi[c - 1][a].you)
                            jisuan++;
                        if (jisuan < 2)
                            anquanxian.add(new Xian(fangxiang.SHUIPINGXIAN, c, a));
                    } else {
                        jisuan = 0;
                        if (hezi[c][a].zuo)
                            jisuan++;
                        if (hezi[c][a].dibu)
                            jisuan++;
                        if (hezi[c][a].you)
                            jisuan++;
                        if (jisuan < 2)
                            f1 = true;
                        jisuan = 0;
                        if (hezi[c - 1][a].zuo)
                            jisuan++;
                        if (hezi[c - 1][a].shang)
                            jisuan++;
                        if (hezi[c - 1][a].you)
                            jisuan++;
                        if (jisuan < 2)
                            f2 = true;
                        if (f1 && f2)
                            anquanxian.add(new Xian(fangxiang.SHUIPINGXIAN, c, a));
                        f1 = false;
                        f2 = false;
                    }
                }
                if (!chuizhixian[a][c]) {
                    if (c == 0) {
                        jisuan = 0;
                        if (hezi[a][c].you)
                            jisuan++;
                        if (hezi[a][c].dibu)
                            jisuan++;
                        if (hezi[a][c].shang)
                            jisuan++;
                        if (jisuan < 2)
                            anquanxian.add(new Xian(fangxiang.CHUIZHIXIAN, a, c));
                    } else if (c == 5) {
                        jisuan = 0;
                        if (hezi[a][c - 1].zuo)
                            jisuan++;
                        if (hezi[a][c - 1].shang)
                            jisuan++;
                        if (hezi[a][c - 1].dibu)
                            jisuan++;
                        if (jisuan < 2)
                            anquanxian.add(new Xian(fangxiang.CHUIZHIXIAN, a, c));
                    } else {
                        jisuan = 0;
                        if (hezi[a][c].you)
                            jisuan++;
                        if (hezi[a][c].dibu)
                            jisuan++;
                        if (hezi[a][c].shang)
                            jisuan++;
                        if (jisuan < 2)
                            f1 = true;
                        jisuan = 0;
                        if (hezi[a][c - 1].zuo)
                            jisuan++;
                        if (hezi[a][c - 1].shang)
                            jisuan++;
                        if (hezi[a][c - 1].dibu)
                            jisuan++;
                        if (jisuan < 2)
                            f2 = true;
                        if (f1 && f2)
                            anquanxian.add(new Xian(fangxiang.CHUIZHIXIAN, a, c));
                        f1 = false;
                        f2 = false;
                    }
                }
            }
        }
    }

    private void chushihuamianban() {
        for (int a = 0; a < 6; a++) {
            for (int c = 0; c < 5; c++) {
                if (huoquyouxi().xianshifoubeizhan(fangxiang.SHUIPINGXIAN, a, c))
                    shuipinxian[a][c] = true;
                else
                    shuipinxian[a][c] = false;
                if (huoquyouxi().xianshifoubeizhan(fangxiang.CHUIZHIXIAN, c, a))
                    chuizhixian[c][a] = true;
                else
                    chuizhixian[c][a] = false;
            }
        }
        for (int a = 0; a < 5; a++) {
            for (int c = 0; c < 5; c++) {
                hezi[a][c] = new Hezi(chuizhixian[a][c], shuipinxian[a][c],
                        chuizhixian[a][c + 1], shuipinxian[a + 1][c]);
            }
        }
    }

    private void chushihuahaoxianzhi() {
        if (youxian.isEmpty())
            return;
        youxianzhi.clear();
        for (Xian xian : youxian) {
            Mianban mianban = new Mianban(shuipinxian, chuizhixian);
            youxianzhi.put(xian, mianban.haoxianzhi(xian));
        }
    }

    private void chushihuahuaixianzhi() {
        if (huaixian.isEmpty())
            return;
        huanxianzhi.clear();
        for (Xian xian : huaixian) {
            Mianban mianban = new Mianban(shuipinxian, chuizhixian);
            huanxianzhi.put(xian, mianban.huoquhuaizhi(xian));
        }
    }

    private void chongzhihaoxianfang() {
        if (youxian.isEmpty())
            return;
        youxianzhonglei.clear();
        for (Xian xian : youxian) {
            if (xian.fangxiang() == fangxiang.SHUIPINGXIAN) {
                if (xian.lie() == 0)
                    youxianzhonglei.put(xian, 1);
                else if (xian.lie() == 5)
                    youxianzhonglei.put(xian, 1);
                else {
                    int jisuan = 0;
                    if (hezi[xian.lie()][xian.yuanzhu()].baohan() == 3)
                        jisuan++;
                    if (hezi[xian.lie() - 1][xian.yuanzhu()].baohan() == 3)
                        jisuan++;
                    youxianzhonglei.put(xian, jisuan);
                }
            } else {
                if (xian.yuanzhu() == 0)
                    youxianzhonglei.put(xian, 1);
                else if (xian.yuanzhu() == 5)
                    youxianzhonglei.put(xian, 1);
                else {
                    int jisuanqi = 0;
                    if (hezi[xian.lie()][xian.yuanzhu()].baohan() == 3)
                        jisuanqi++;
                    if (hezi[xian.lie()][xian.yuanzhu() - 1].baohan() == 3)
                        jisuanqi++;
                    youxianzhonglei.put(xian, jisuanqi);
                }

            }

        }
    }

    private static class Hezi {
        boolean zuo;
        boolean shang;
        boolean you;
        boolean dibu;
        boolean zhanling;

        Hezi(boolean a, boolean c, boolean d, boolean b) {
            this.zuo = a;
            this.shang = c;
            this.you = d;
            this.dibu = b;
            if (a && c && d && b)
                this.zhanling = true;
            else
                this.zhanling = false;
        }

        int baohan() {
            int counter = 0;
            if (this.zuo)
                counter++;
            if (this.you)
                counter++;
            if (this.shang)
                counter++;
            if (this.dibu)
                counter++;
            return counter;
        }

    }

    class Mianban {
        boolean[][] chuzhixian;
        boolean[][] shuipingxian;
        boolean[][] zhanling;
        boolean jixuzou;
        private Hezi[][] hezis;

        Mianban(boolean[][] h, boolean[][] v) {
            chuzhixian = new boolean[6][5];
            shuipingxian = new boolean[5][6];
            zhanling = new boolean[5][5];
            hezis = new Hezi[5][5];
            for (int a = 0; a < 6; a++) {
                for (int b = 0; b < 5; b++) {
                    chuzhixian[a][b] = h[a][b];
                    shuipingxian[b][a] = v[b][a];
                }
            }

        }

        void csh() {
            for (int a = 0; a < 5; a++) {
                for (int b = 0; b < 5; b++) {
                    hezis[a][b] = new Hezi(shuipingxian[a][b], chuzhixian[a][b],
                            shuipingxian[a][b + 1], chuzhixian[a + 1][b]);
                    if (hezis[a][b].zhanling)
                        zhanling[a][b] = true;
                }
            }
        }

        void tianjia(Xian xian) {
            fangxiang type = xian.fangxiang();
            int a = xian.lie();
            int b = xian.yuanzhu();
            jixuzou = false;

            switch (type) {
                case SHUIPINGXIAN:
                    if (!chuzhixian[a][b]) {
                        chuzhixian[a][b] = true;
                        if (a == 0) {
                            if (chuzhixian[a + 1][b] && shuipingxian[a][b]
                                    && shuipingxian[a][b + 1]) {
                                zhanling[a][b] = true;
                                jixuzou = true;
                            }
                        } else if (a == 5) {
                            if (chuzhixian[a - 1][b] && shuipingxian[a - 1][b]
                                    && shuipingxian[a - 1][b + 1]) {
                                zhanling[a - 1][b] = true;
                                jixuzou = true;
                            }
                        } else {
                            if (chuzhixian[a + 1][b] && shuipingxian[a][b]
                                    && shuipingxian[a][b + 1]) {
                                zhanling[a][b] = true;
                                jixuzou = true;
                            }
                            if (chuzhixian[a - 1][b] && shuipingxian[a - 1][b]
                                    && shuipingxian[a - 1][b + 1]) {
                                zhanling[a - 1][b] = true;
                                jixuzou = true;
                            }
                        }
                    } else {
                        jixuzou = true;
                    }
                    break;
                case CHUIZHIXIAN:
                    if (!shuipingxian[a][b]) {
                        shuipingxian[a][b] = true;
                        if (b == 0) {
                            if (shuipingxian[a][b + 1] && chuzhixian[a][b]
                                    && chuzhixian[a + 1][b]) {
                                zhanling[a][b] = true;
                                jixuzou = true;
                            }
                        } else if (b == 5) {
                            if (shuipingxian[a][b - 1] && chuzhixian[a][b - 1]
                                    && chuzhixian[a + 1][b - 1]) {
                                zhanling[a][b - 1] = true;
                                jixuzou = true;
                            }
                        } else {
                            if (shuipingxian[a][b + 1] && chuzhixian[a][b]
                                    && chuzhixian[a + 1][b]) {
                                zhanling[a][b] = true;
                                jixuzou = true;
                            }
                            if (shuipingxian[a][b - 1] && chuzhixian[a][b - 1]
                                    && chuzhixian[a + 1][b - 1]) {
                                zhanling[a][b - 1] = true;
                                jixuzou = true;
                            }
                        }
                    } else {
                        jixuzou = true;
                    }
                    break;
            }
        }

        int huoquzhanlingshu() {
            int jisuan = 0;
            for (int a = 0; a < 5; a++) {
                for (int b = 0; b < 5; b++) {
                    if (zhanling[a][b])
                        jisuan++;
                }
            }
            return jisuan;
        }

        int huoquhuaizhi(Xian xian) {
            int kaishi, jieshu;
            if (xian.fangxiang() == fangxiang.SHUIPINGXIAN)
                this.chuzhixian[xian.lie()][xian.yuanzhu()] = true;
            else
                this.shuipingxian[xian.lie()][xian.yuanzhu()] = true;
            AIwanjia ai = new AIwanjia(0, huoquyouxi());
            csh();
            kaishi = this.huoquzhanlingshu();
            jixuzou = true;
            while (jixuzou) {
                tianjia(ai.xian(this));
                if (this.huoquzhanlingshu() == 25)
                    break;
            }
            jieshu = this.huoquzhanlingshu();
            return (jieshu - kaishi);
        }

        int haoxianzhi(Xian xian) {
            int kaishi, jieshu;
            if (xian.fangxiang() == fangxiang.SHUIPINGXIAN)
                this.chuzhixian[xian.lie()][xian.yuanzhu()] = true;
            else
                this.shuipingxian[xian.lie()][xian.yuanzhu()] = true;
            AIwanjia computer = new AIwanjia(0, huoquyouxi());
            csh();
            kaishi = this.huoquzhanlingshu();
            jixuzou = true;
            while (jixuzou) {
                tianjia(computer.xian(this));
                if (this.huoquzhanlingshu() == 25)
                    break;
            }
            jieshu = this.huoquzhanlingshu() + 1;
            return (jieshu - kaishi);
        }

        int getCanWinValue(Xian xian) {
            csh();
            int zhunbeizhanlin = huoquzhanlingshu();
            if (xian.fangxiang() == fangxiang.SHUIPINGXIAN)
                this.chuzhixian[xian.lie()][xian.yuanzhu()] = true;
            else
                this.shuipingxian[xian.lie()][xian.yuanzhu()] = true;
            int ta = 0;
            int wo = 0;
            csh();
            wo = huoquzhanlingshu() - zhunbeizhanlin;
            AIwanjia computer = new AIwanjia(2, huoquyouxi());
            int xianzaikaishi;
            if (wo == 1)
                xianzaikaishi = 0;
            else
                xianzaikaishi = 1;

            while (this.huoquzhanlingshu() != 25) {
                if (xianzaikaishi == 0) {
                    int linshi = huoquzhanlingshu();
                    jixuzou = true;
                    while (jixuzou) {
                        tianjia(computer.xian(this));
                        if (this.huoquzhanlingshu() == 25)
                            break;
                    }
                    linshi = huoquzhanlingshu() - linshi;
                    wo = wo + linshi;
                    xianzaikaishi = 1;
                } else {
                    int linshi = huoquzhanlingshu();
                    jixuzou = true;
                    while (jixuzou) {
                        tianjia(computer.xian(this));
                        if (this.huoquzhanlingshu() == 25)
                            break;
                    }
                    linshi = huoquzhanlingshu() - linshi;
                    ta = ta + linshi;
                    xianzaikaishi = 0;
                }
            }
            return wo - ta;
        }
    }

}
