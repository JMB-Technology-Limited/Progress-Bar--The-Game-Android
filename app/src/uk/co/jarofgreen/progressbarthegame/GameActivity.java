package uk.co.jarofgreen.progressbarthegame;

import java.security.acl.LastOwnerException;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * 
 * @author James Baster <james@jarofgreen.co.uk>
 * @copyright JMB Technology Limited
 * @license Open Source under the 3-clause BSD License
 * @url https://github.com/jarofgreen/Progress-Bar--The-Game-Android
 *  */
public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		progressBar = (ProgressBar)findViewById(R.id.progressBar);
		progressBar.setMax(100);
		handler=new Handler();
		levelTextView = (TextView)findViewById(R.id.level);
		startLevel();
	}

	protected int level = 1;
	protected int timePerPercent = 100;
	protected ProgressBar progressBar;
	protected TextView levelTextView;
	
	Handler handler;
	final Runnable runnable = new Runnable() {
		public void run() {
			GameActivity.this.addOnePerCent();
		}
	};;
	
	
	protected void startLevel() {
		timePerPercent = level * 100;
		levelTextView.setText("Level "+Integer.toString(level));
		progressBar.setProgress(0);
		handler.postDelayed(runnable, getTimeTillNextPerCent()); 
	}
	
	protected void addOnePerCent() {
		int current = progressBar.getProgress();
		Log.d("CURRENT",Integer.toString(current));
		if (current == 99) {
			
		} else {
			progressBar.setProgress(current+1);
			handler.postDelayed(runnable, getTimeTillNextPerCent()); 
		}
	}
	
	protected int getTimeTillNextPerCent() {
		return timePerPercent;
	}
}
