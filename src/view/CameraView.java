package view;

public class CameraView {

    private double x;
    private double y;
    
    private double targetX;
    private double targetY;

    private final int worldWidth;
    private final int worldHeight;
    
    private static final double CAMERA_SMOOTHNESS = 0.08;

    public CameraView(int worldWidth, int worldHeight) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
    }

    public void move(int deltaX, int deltaY,
                     int viewportWidth, int viewportHeight) {

        x += deltaX;
        y += deltaY;

        clampToWorld(viewportWidth, viewportHeight);
    }

    public void setPosition(int x, int y,
                            int viewportWidth, int viewportHeight) {

        this.x = x;
        this.y = y;

        clampToWorld(viewportWidth, viewportHeight);
    }

    public void centerOn(double worldX, double worldY,
                         int viewportWidth, int viewportHeight) {

        targetX = worldX - viewportWidth / 2.0;
        targetY = worldY - viewportHeight / 2.0;

        clampToWorld(viewportWidth, viewportHeight);
    }
    
    public void update() {
    		x += (targetX - x) * CAMERA_SMOOTHNESS;
    		y += (targetY - y) * CAMERA_SMOOTHNESS;
    		
    		if (Math.abs(targetX - x) < 0.05) {
				x = targetX;
		}
    		
    		if (Math.abs(targetY - y) < 0.05) {
				y = targetY;
		}    		
    }
    
	private void moveTarget(double deltaX, double deltaY, int viewPortWidth, int viewPortHeight) {
		targetX += deltaX;
		targetY += deltaY;
		
		clampTargetToWorld(viewPortWidth, viewPortHeight);
	}
	
	 public void setPositionImmediately(
	            double x,
	            double y,
	            int viewportWidth,
	            int viewportHeight) {

	        this.x = x;
	        this.y = y;

	        targetX = x;
	        targetY = y;

	        clampTargetToWorld(viewportWidth, viewportHeight);

	        this.x = targetX;
	        this.y = targetY;
	    }

	    private void clampTargetToWorld(
	            int viewportWidth,
	            int viewportHeight) {

	        double maxX = Math.max(
	                0,
	                worldWidth - viewportWidth
	        );

	        double maxY = Math.max(
	                0,
	                worldHeight - viewportHeight
	        );

	        targetX = Math.max(
	                0,
	                Math.min(targetX, maxX)
	        );

	        targetY = Math.max(
	                0,
	                Math.min(targetY, maxY)
	        );
	    }

    private void clampToWorld(int viewportWidth, int viewportHeight) {

        int maxX = Math.max(0, worldWidth - viewportWidth);
        int maxY = Math.max(0, worldHeight - viewportHeight);

        x = Math.max(0, Math.min(x, maxX));
        y = Math.max(0, Math.min(y, maxY));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}