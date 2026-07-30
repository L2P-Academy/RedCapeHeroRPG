package view;

public class CameraView {

    private int x;
    private int y;

    private final int worldWidth;
    private final int worldHeight;

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

    public void centerOn(int worldX, int worldY,
                         int viewportWidth, int viewportHeight) {

        x = worldX - viewportWidth / 2;
        y = worldY - viewportHeight / 2;

        clampToWorld(viewportWidth, viewportHeight);
    }

    private void clampToWorld(int viewportWidth, int viewportHeight) {

        int maxX = Math.max(0, worldWidth - viewportWidth);
        int maxY = Math.max(0, worldHeight - viewportHeight);

        x = Math.max(0, Math.min(x, maxX));
        y = Math.max(0, Math.min(y, maxY));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}