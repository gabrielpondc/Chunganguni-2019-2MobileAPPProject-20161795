package gujiakai.dotsline.shitu;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import gujiakai.dotsline.R;
import gujiakai.dotsline.mokuai.Renleiwanjia;
import gujiakai.dotsline.mokuai.wanjia;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.Map;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Activity;


public class shuangren extends Activity implements wanjiazhuangtaishitu {

    private static shuangren woziji;
    protected youxishitu youxishitu;
    TextView wanjiazhuangtai, wanjia2zhuangtai, wanjiazhanyou, wanjia2zhanyou;
    ImageView wanjiaxianzaidian;
    boolean yinyue, chumoyin;
    SoundPool yinchi;
    MediaPlayer bofangqi;
    wanjia[] wanjia;
    Integer[] wanjiazhanyouzhi = new Integer[]{0, 0};
    wanjia xianzaikaishi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youxi);
        bofangqi = MediaPlayer.create(this, R.raw.beijing);
        yinchi = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        yinchi.load(this, R.raw.yinyue, BIND_ADJUST_WITH_ACTIVITY);
        try {
            FileInputStream kaishi = openFileInput("shenyin");
            DataInputStream dedao = new DataInputStream(kaishi);
            yinyue = dedao.readBoolean();
            chumoyin = dedao.readBoolean();
            dedao.close();
        } catch (Exception e) {
            yinyue = true;
            chumoyin = true;
        }
        if (yinyue) {
            bofangqi.start();
        }


        woziji = this;
        ImageButton returnButton = (ImageButton) findViewById(R.id.chongzhianniu);
        returnButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitDialog();
            }
        });
        wanjiazhuangtai = (TextView) findViewById(R.id.wanjia1zhuangtai);
        wanjia2zhuangtai = (TextView) findViewById(R.id.wanjia2zhuangtai);
        wanjiazhanyou = (TextView) findViewById(R.id.wanjia1zhanyougezishu);
        wanjia2zhanyou = (TextView) findViewById(R.id.wanjia2zhanyougezishu);
        ImageButton refresh = (ImageButton) findViewById(R.id.shuaxin);
        refresh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showRefreshDialog();
            }
        });

        wanjiaxianzaidian = (ImageView
                ) findViewById(R.id.wanjiaxinzhanyoudian);

        youxishitu = (youxishitu) findViewById(R.id.youxishitu);
        youxishitu.setWanjiazhuangtai(this);

        wanjia = chushihuawanjia();
        showChooseFirstMoverDialog();
    }

    public void shengjizhuangtai() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (xianzaikaishi == wanjia[0]) {
                    wanjiazhuangtai.setText("생각중");
                    wanjia2zhuangtai.setText("대기중");
                    wanjiaxianzaidian.setImageResource(R.mipmap.lansejiantou);
                } else {
                    wanjia2zhuangtai.setText("생각중");
                    wanjiazhuangtai.setText("대기중");
                    wanjiaxianzaidian.setImageResource(R.mipmap.hongsejiantou);
                }
                wanjiazhanyou.setText("점령 중 " + wanjiazhanyouzhi[0]);
                wanjia2zhanyou.setText("점령 중 " + wanjiazhanyouzhi[1]);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bofangqi.stop();//停止播放
    }
    @Override
    public boolean onKeyDown(int shuru, KeyEvent shuzi8hi) {
        if (shuru == KeyEvent.KEYCODE_BACK) {
            showExitDialog();
        }
        return true;

    }

    private void showExitDialog() {
        new AlertDialog.Builder(woziji)
                .setTitle("Dots to Line")
                .setMessage(
                        "정말 그만두고 싶으세요?\n기존 게임에서는 패배한다..")
                .setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                finish();
                            }
                        })
                .setNegativeButton("아니요",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        }).show();
    }

    private void showRefreshDialog() {
        new AlertDialog.Builder(woziji)
                .setTitle("Dots to Line")
                .setMessage(
                        "정말 칠판을 청소하시겠습니까?\n기존 게임에서는 패배한다..")
                .setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                recreate();
                            }
                        })
                .setNegativeButton("아니요",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        }).show();
    }

    protected wanjia[] chushihuawanjia() {
        return new wanjia[]{new Renleiwanjia("플래이어1"), new Renleiwanjia("플래이어2")};
    }

    private void showChooseFirstMoverDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Dots to Line")
                .setMessage("플레이어 이동을 먼저 선택하십시오.")
                .setPositiveButton(R.string.Player1,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                kaishiyouxi(0);//开始游戏
                            }
                        })
                .setNegativeButton(R.string.Player2,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                kaishiyouxi(1);
                            }
                        }).show();
    }

    private void kaishiyouxi(int zhidingdiyigeyidongwanjia) {
        xianzaikaishi = wanjia[zhidingdiyigeyidongwanjia];
        youxishitu.kaishiyouxi(xianzaikaishi, wanjia);
        shengjizhuangtai();
    }

    @Override
    public void setXianzaikaishi(wanjia Wanjia) {
        xianzaikaishi = Wanjia;
        shengjizhuangtai();
    }

    @Override
    public void duiwanjijinxinjishu(Map<wanjia, Integer> wanjiaIntegerMap) {
        wanjiazhanyouzhi[0] = (wanjiaIntegerMap.get(wanjia[0]));
        wanjiazhanyouzhi[1] = (wanjiaIntegerMap.get(wanjia[1]));
        shengjizhuangtai();
    }

    @Override
    public void wanjiakaishichumo() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (chumoyin)
                    yinchi.play(1, (float) 0.8, (float) 0.8, 0, 0, 1);
            }
        });
    }

    @Override
    public void shezhiyinjia(final wanjia[] yinjia) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String wanjiaxinming = "";
                for (wanjia Wanjia : yinjia) {
                    wanjiaxinming += Wanjia.huoqumingcheng();
                    wanjiaxinming += " ";
                }
                new AlertDialog.Builder(shuangren.this)

                        .setTitle("Dots to Line")
                        .setMessage(wanjiaxinming + "승리!")
                        .setPositiveButton("확인",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        recreate();
                                    }
                                }).show();
            }
        });

    }
}
