package uk.co.jarofgreen.progressbarthegame;

import java.security.acl.LastOwnerException;

import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


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
		nextLevelButton = (Button)findViewById(R.id.nextLevel);
		startLevel();
	}

	protected int level = 1;
	protected int timePerPercent = 100;
	protected ProgressBar progressBar;
	protected TextView levelTextView;
	protected Button nextLevelButton;
	
	Handler handler;
	final Runnable runnable = new Runnable() {
		public void run() {
			GameActivity.this.addOnePerCent();
		}
	};;
	
	
	protected void startLevel() {
		timePerPercent = level * 75;
		levelTextView.setText("Level "+Integer.toString(level));
		progressBar.setProgress(0);
		handler.postDelayed(runnable, getTimeTillNextPerCent()); 
	}
	
	protected void addOnePerCent() {
		int current = progressBar.getProgress();
		Log.d("CURRENT",Integer.toString(current));
		if (current == 99) {
			Toast.makeText(this, "Congratulations!", Toast.LENGTH_LONG).show();
			progressBar.setProgress(100);
			nextLevelButton.setVisibility(View.VISIBLE);
		} else {
			progressBar.setProgress(current+1);
			handler.postDelayed(runnable, getTimeTillNextPerCent()); 
		}
	}
	
	protected int getTimeTillNextPerCent() {
		return timePerPercent;
	}
	
	public void nextLevel(View v) {
		nextLevelButton.setVisibility(View.INVISIBLE);
		level = level + 1;
		startLevel();
	}
}
