package gujiakai.dotsline.shitu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;
import java.io.DataInputStream;
import gujiakai.dotsline.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class shenyinshezhi extends Activity {
	static boolean yinyue;
	static boolean chumo;
	static ToggleButton yinyuekaiguan;
	static ToggleButton chumokaiguan;
	private Intent zhuyaoxindongzhi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shengyin);
		
		zhuyaoxindongzhi = new Intent(this, zhuyaodonzuo.class);
		ImageButton returnn = (ImageButton) findViewById(R.id.rupiananniu);
		returnn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				startActivity(zhuyaoxindongzhi);
				finish();


			}

		});
		
		yinyuekaiguan = (ToggleButton) findViewById(R.id.tupiananniu1);
		chumokaiguan = (ToggleButton) findViewById(R.id.tupiananniu2);
		load();
		yinyuekaiguan.setChecked(yinyue);
		chumokaiguan.setChecked(chumo);
		yinyuekaiguan.setOnCheckedChangeListener(new OnCheckedChangeListener() {
  
            @Override  
            public void onCheckedChanged(CompoundButton buttonView,  
                    boolean isChecked) {
                if (isChecked) {  
                    yinyue =true;
                    save();

                } else {  
                    yinyue =false;
                    save();

                    
                }  
            }  
        });  
		chumokaiguan.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			  
            @Override  
            public void onCheckedChanged(CompoundButton buttonView,  
                    boolean isChecked) {
                if (isChecked) {  
                    chumo =true;
                    save();
                } else {  
                    chumo =false;
                    save();
                }  
            }  
        });  
	}
	
		

	private void save() {
		try {
			FileOutputStream kkshi = openFileOutput("shenyin",0);
			DataOutputStream chumo = new DataOutputStream(kkshi);
			chumo.writeBoolean(yinyue);
			chumo.writeBoolean(shenyinshezhi.chumo);
			chumo.close();
		} catch (Exception e) {
System.out.println(e);
		}
	}

	private void load() {
		try {
			FileInputStream xiaochumo =openFileInput("shenyin");
			DataInputStream dachumo=new DataInputStream(xiaochumo);
			yinyue =dachumo.readBoolean();
			chumo =dachumo.readBoolean();
			dachumo.close();
		} catch (Exception e) {
			yinyue =true;
			chumo =true;
		}
	}

}
