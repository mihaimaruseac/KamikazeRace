package mm.kamikaze;

public class Car {
	public int x, y, sp;
	public int h;
	public boolean active;
	
	public Car() {
		defaultSettings();
	}
	
	private void defaultSettings(){
		x = 0;
		y = 0;
		h = 20;
		sp = 0;
		active = false;
	}
}
