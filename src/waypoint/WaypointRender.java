    package waypoint;

    import java.awt.Color;
    import java.awt.Graphics2D;
    import java.awt.Point;
    import java.awt.Rectangle;
    import java.awt.geom.Point2D;
    import javax.swing.JButton;
    import org.jxmapviewer.JXMapViewer;
    import org.jxmapviewer.viewer.Waypoint;
    import org.jxmapviewer.viewer.WaypointPainter;

    public class WaypointRender extends WaypointPainter<MyWayPoint> {

        @Override
        protected void doPaint(Graphics2D g, JXMapViewer map, int width, int height) {
            for (MyWayPoint wp : getWaypoints()) {
                Point2D p = map.getTileFactory().geoToPixel(wp.getPosition(), map.getZoom());
                Rectangle rec=map.getViewportBounds();
                int x=(int) (p.getX()-rec.getX());
                int y=(int) (p.getY()-rec.getY());
                JButton cmd=wp.getButton();
                cmd.setLocation(x-cmd.getWidth()/2,y-cmd.getHeight());
            }
        }
    }
