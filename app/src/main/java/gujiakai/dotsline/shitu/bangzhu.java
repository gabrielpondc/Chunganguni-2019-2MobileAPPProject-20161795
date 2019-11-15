package gujiakai.dotsline.shitu;

import gujiakai.dotsline.R;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.os.Bundle;
import android.app.Activity;

public class bangzhu extends Activity {
	private Intent zhushitu;
	@Override
	protected void onCreate(Bundle baocunshitu) {
		super.onCreate(baocunshitu);
		setContentView(R.layout.bangzhu);
		zhushitu = new Intent(this, zhuyaodonzuo.class);
		ImageButton returnn = (ImageButton) findViewById(R.id.chongzhianniu);
		returnn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				startActivity(zhushitu);
				finish();


			}

		});
	}

	

}
