package com.keju.baby.activity.doctor;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.keju.baby.Constants;
import com.keju.baby.R;
import com.keju.baby.activity.base.BaseWebViewActivity;
import com.keju.baby.util.AndroidUtil;

/**
 * 会议通知
 * 
 * @author Zhoujun
 * @version 创建时间：2013-10-25 下午3:05:59
 */
public class MeetingNotifyAcitivity extends BaseWebViewActivity {
	private long exitTime;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fillData();
	}

	/**
	 * 数据填充
	 */
	private void fillData() {
		btnLeft.setVisibility(View.GONE);
		btnLeft.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (webView.canGoBack()) {
					webView.goBack();
				}
			}
		});
		btnLeft.setImageResource(R.drawable.btn_back_selector);
		btnRight.setVisibility(View.GONE);
		tvTitle.setText("会议通知");
		loadUrl(Constants.URL_MEETING_NOTIFY_LIST);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
			if (webView.canGoBack()) {
				webView.goBack();// goBack()表示返回webView的上一页面，而不直接关闭WebView
				return true;
			} else {
				if ((System.currentTimeMillis() - exitTime) > 2000) {
					showShortToast(R.string.try_again_logout);
					exitTime = System.currentTimeMillis();
				} else {
					AndroidUtil.exitApp(this);
					finish();
				}
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

}
