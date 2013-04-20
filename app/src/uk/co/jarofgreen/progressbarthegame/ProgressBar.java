package uk.co.jarofgreen.progressbarthegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.View;


/**
 * 
 * @author James Baster <james@jarofgreen.co.uk>
 * @copyright JMB Technology Limited
 * @license Open Source under the 3-clause BSD License
 * @url https://github.com/jarofgreen/Progress-Bar--The-Game-Android
 *  */
public class ProgressBar extends View {

	protected int percent = 0;

	private ShapeDrawable mDrawable;

	public ProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		mDrawable = new ShapeDrawable(new RectShape());
		mDrawable.getPaint().setColor(0xff74AC23);
	}

	public ProgressBar(Context context) {
		super(context);
		mDrawable = new ShapeDrawable(new RectShape());
		mDrawable.getPaint().setColor(0xff74AC23);
	}

	protected void onDraw(Canvas canvas) {
		if (percent > 0) {
			double m = ((double)percent) / (double)100.0;
			int w = (int)(canvas.getWidth() * m);
			mDrawable.setBounds(0, 0, w, canvas.getHeight());
		} else {
			mDrawable.setBounds(0, 0, 0, canvas.getHeight());
		}
		mDrawable.draw(canvas);
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}
	
}
