package gujiakai.dotsline.shitu;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.os.Bundle;
import android.app.Activity;
import gujiakai.dotsline.R;

public class zhuye extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhuye);


		final Intent shuangren=new Intent(this, shuangren.class);
		final Intent bangzhu=new Intent(this, bangzhu.class);
		final Intent danren=new Intent(this, danren.class);
		final Intent shenyin=new Intent(this, shenyinshezhi.class);
		ImageButton danren0=(ImageButton)findViewById(R.id.danren);
		ImageButton shuangrenn=(ImageButton)findViewById(R.id.duoren);
		ImageButton bangzhuu=(ImageButton)findViewById(R.id.bangzhu);
		ImageButton shenying=(ImageButton)findViewById(R.id.shengying);
		
		shenying.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				startActivity(shenyin);
				
			}
			
		});
		
		shuangrenn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				startActivity(shuangren);
				
			}
			
		});
		bangzhuu.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				startActivity(bangzhu);
				
			}
			
		});
		danren0.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				startActivity(danren);
				
			}
			
		});
	}



}
