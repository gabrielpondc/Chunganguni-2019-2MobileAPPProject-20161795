package gujiakai.dotsline.shitu;
import gujiakai.dotsline.mokuai.zhinengwanjia;
import gujiakai.dotsline.mokuai.Renleiwanjia;
import gujiakai.dotsline.mokuai.wanjia;
import gujiakai.dotsline.R;
import android.os.Bundle;
import android.app.AlertDialog;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.DialogInterface;

public class danren extends shuangren {
    private zhinengwanjia diannao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView wanjia2 = (TextView) findViewById(R.id.player2name);
        wanjia2.setText("컴퓨터");
        ImageView wanjia2tupian = (ImageView) findViewById(R.id.wanjia2tupian);
        wanjia2tupian.setImageResource(R.mipmap.diannan);

        xianshinanyidu();
    }

    @Override
    protected wanjia[] chushihuawanjia() {
        diannao = new zhinengwanjia("인공지능");
        return new wanjia[]{new Renleiwanjia("플레이어"), diannao};
    }

    private void xianshinanyidu() {
        new AlertDialog.Builder(this)
                .setTitle("Dots and Line")
                .setMessage("난이도가 선택하시오")
                .setPositiveButton("일반",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                diannao.setNanyidu(1);
                            }
                        })
                .setNeutralButton("어려운", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        diannao.setNanyidu(2);
                    }
                })
                .setNegativeButton("변태",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                diannao.setNanyidu(3);
                            }
                        }).show();
    }

}
