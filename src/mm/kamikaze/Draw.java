package mm.kamikaze;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.view.View;
import android.widget.Toast;

public class Draw extends View{
	private static final int PAD = 10;
	private static final int SCOREPAD = 32;
	private Context context;
	private int w, h;
	private int xs, ys, xe, ye;

	/* Road geometry */
	private static final int STRIPELEN = 20;
	private static final int STRIPETOT = 56;
	private static final int STRIPEWID = 2;
	private static final int BLEN = 30;
	private int STRIPESTART = 0;
	private static final int STRIPES = 10; 
	
	/* Car geometry */
	private final int HWCAR = 10;
	private final int HHCAR = 33;
	private final int YCAR = 325;
	private int XCAR = 295;
	private static final int XCARMAX = 295;
	private static final int XCARMIN = 25;
	
	/* Speed values */
	private final int STRIPSPEED = 1;
	private final int MAXSPEED = STRIPES * STRIPSPEED;
	private double sp = 0.5;
	private int BEHIND = 500;
	
	/* random values */
	private double CAR = .2;
	
	/* cars */
	private final int MAXCARS = 10;
	private Car []cars;
	private int activeCars = 0;
	private boolean gameover = false;
	private Bitmap mycar;
	private Bitmap hiscar;
		
	public Draw(Context context) {
		super(context);
		this.context = context;
		
		cars = new Car[MAXCARS];
		for (int i = 0; i < MAXCARS; i++)
			cars[i] = new Car();
		
		Resources res = getResources();
		mycar = BitmapFactory.decodeResource(res, R.drawable.mycar);
		hiscar = BitmapFactory.decodeResource(res, R.drawable.hiscar);
	}
	
	public void reset(){
		if (!gameover) return;
		sp = .5;
		activeCars = 0;
		XCAR = XCARMAX;
		gameover = false;
		postInvalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		int x, y, ytmp;
		
		w = this.getWidth();
		h = this.getHeight();

		xs = PAD;
		ys = PAD + SCOREPAD;
		xe = w - PAD;
		ye = h - PAD;
		
		/* draw text */
		Paint textp = new Paint();
		textp.setColor(Color.MAGENTA);
		canvas.drawText("Score: " + (int)Math.round(10 * sp), xs + 12, ys - 12, textp);
		
		/* draw border around terrain */
		Paint bp = new Paint();
		bp.setColor(Color.YELLOW);
		bp.setStyle(Style.STROKE);
		canvas.drawRect(xs, ys, xe, ye, bp);
		
		/* draw marks on the road */
		Paint sp = new Paint();
		sp.setColor(Color.LTGRAY);
		x = xs + BLEN - STRIPEWID / 2;
		while (x < xe - 5){
			y = ys + STRIPESTART;
			while (y < ye){
				ytmp = y + STRIPELEN;
				if (ytmp > ye){
					ytmp = ye;
					canvas.drawRect(x, y, x + STRIPEWID, ytmp, sp);
					break;
				}
				canvas.drawRect(x, y, x + STRIPEWID, ytmp, sp);
				y += STRIPETOT;
			}
			x += BLEN;
		}
		
		/* draw my car */
		canvas.drawBitmap(mycar, XCAR - HWCAR, YCAR - HHCAR, null);
		
		/* draw other cars */
		if (gameover){
			Toast.makeText(context, "Game Over", Toast.LENGTH_SHORT).show();
		}
		
		canvas.clipRect(xs, ys, xe, ye);
		for (int i = 0; i < activeCars; i++){
			Car c = cars[i];
			canvas.drawBitmap(hiscar, c.x - HWCAR, c.y - c.h, null);
		}
	}

	public void run() {
		if (gameover) return;
		
		STRIPESTART = (int)Math.round(STRIPESTART + sp) % STRIPETOT;
		sp += .05;
		
		/* car moves */
		for (int i = 0; i < activeCars; i++){
			cars[i].y -= (cars[i].sp - sp);
		}
		
		for (int i = 0; i < activeCars; i++){
			if (cars[i].y > BEHIND){
				Car tmp = cars[(activeCars--)-1];
				cars[activeCars] = cars[i];
				cars[i--] = tmp;
			}
		}
		
		/* check colision */
		for (int i = 0; i < activeCars; i++){
			if (Math.abs(XCAR - cars[i].x) < 2 * HWCAR){
				if (Math.abs(YCAR - cars[i].y) < 2 * HHCAR){
					gameover = true;
				}
			}
		}
		
		/* get a new car */
		if (activeCars < MAXCARS){
			double x = Math.random();
			if (x < CAR){
				int speed = (int) Math.round(Math.random() * MAXSPEED);
				int band;
				
				for (band = 0; band < STRIPES; band++){
					if (speed  < band * STRIPSPEED){
						break;
					}
				}
				band--;
				
				cars[activeCars].active = true;
				cars[activeCars].x = XCARMAX - band * BLEN;
				int s = 0, count = 0;
				for (int j = 0; j < activeCars; j++){
					if (cars[j].x == cars[activeCars].x){
						s += cars[j].sp;
						count++;
					}
				}
				if (count != 0){
					speed = (int) Math.round((float)s/count);
				}
				if (speed > sp)
					cars[activeCars].y = BEHIND - 10;
				else
					cars[activeCars].y = 0;
				for (int j = 0; j < activeCars; j++){
					if (cars[j].x == cars[activeCars].x && Math.abs(cars[activeCars].y - cars[j].y) <= 3*HHCAR){
						cars[activeCars].active = false;
						break;
					}
				}
				if (cars[activeCars].active){
					cars[activeCars].sp = speed;
					activeCars++;
				}
			}
		}
		
		CAR += .05;
		postInvalidate();
	}
	
	public void moveLeft(int dx){
		if (XCAR > XCARMIN) XCAR-=dx;
	}
	
	public void moveRight(int dx){
		if (XCAR < XCARMAX) XCAR+=dx;
	}
}
