package uk.co.jarofgreen.progressbarthegame;

import java.security.acl.LastOwnerException;
import java.util.Random;

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
		percentTextView = (TextView)findViewById(R.id.percent);
		nextLevelButton = (Button)findViewById(R.id.nextLevel);
		randomGenerator = new Random();
		handler.postDelayed(runnableStartGame, 250); 
	}
	
	final Runnable runnableStartGame = new Runnable() {
		public void run() {
			GameActivity.this.startLevel();
		}
	};
	
	protected boolean levelRunning = false;
	protected int level = 1;
	protected int currentPercent = 0;
	protected int timePerPercent = 100;
	protected int randomTimePerPercent = 100;
	protected ProgressBar progressBar;
	protected TextView levelTextView;
	protected TextView percentTextView;	
	protected Button nextLevelButton;
	private Random randomGenerator;
	
	Handler handler;
	final Runnable runnable = new Runnable() {
		public void run() {
			GameActivity.this.addOnePerCent();
		}
	};;
	
	
	
	
	@Override
	protected void onPause() {
		super.onPause();
		if (levelRunning) {
			handler.removeCallbacks(runnable);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (levelRunning) {
			handler.postDelayed(runnable, getTimeTillNextPerCent()); 
		}
	}

	protected void startLevel() {
		currentPercent = 0;
		timePerPercent = level * 75;
		randomTimePerPercent = level * 30;
		levelTextView.setText("Level "+Integer.toString(level));
		drawProgressBar();
		levelRunning = true;
		handler.postDelayed(runnable, getTimeTillNextPerCent()); 
	}
	
	protected void addOnePerCent() {
		currentPercent = currentPercent + 1;
		Log.d("CURRENT",Integer.toString(currentPercent));
		drawProgressBar();
		if (currentPercent >= 100) {
			Toast.makeText(this, "Congratulations!", Toast.LENGTH_LONG).show();
			nextLevelButton.setVisibility(View.VISIBLE);
			levelRunning = false;
		} else {
			handler.postDelayed(runnable, getTimeTillNextPerCent()); 
		}
	}
	
	protected void drawProgressBar() {
		progressBar.setProgress(currentPercent);
		percentTextView.setText(Integer.toString(currentPercent)+"%");
	}
	
	protected int getTimeTillNextPerCent() {
		if (randomGenerator.nextBoolean()) {
			return timePerPercent + randomGenerator.nextInt(randomTimePerPercent);
		} else {
			return timePerPercent - randomGenerator.nextInt(randomTimePerPercent);
		}
	}
	
	public void nextLevel(View v) {
		nextLevelButton.setVisibility(View.INVISIBLE);
		level = level + 1;
		startLevel();
	}
}
