package uk.co.jarofgreen.progressbarthegame;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;


/**
 * 
 * @author James Baster <james@jarofgreen.co.uk>
 * @copyright JMB Technology Limited
 * @license Open Source under the 3-clause BSD License
 * @url https://github.com/jarofgreen/Progress-Bar--The-Game-Android
 *  */
public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

}
