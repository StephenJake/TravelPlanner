package waypoint;

import static java.awt.Event.END;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import static javax.swing.text.html.HTML.Attribute.START;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

public class MyWayPoint extends DefaultWaypoint {

    /**
     * @return the pointType
     */
    public PointType getPointType() {
        return pointType;
    }

    /**
     * @param pointType the pointType to set
     */
    public void setPointType(PointType pointType) {
        this.pointType = pointType;
    }
    
        public String getName() {
        return name;
    }
        
        public void setName(String name) {
        this.name = name;
    }
        
        public JButton getButton() {
        return button;
    }
        
        public void setButton(JButton button) {
        this.button = button;
    }
        
        public MyWayPoint(String name,PointType pointType, EventWayPoint event, GeoPosition coord) {
        super(coord); 
        this.name = name;
        this.pointType = pointType;
        initButton(event); 
    }
       


    public MyWayPoint() {
    }
    
    private String name;
    private JButton button;
    private PointType pointType;

    private void initButton(EventWayPoint event)  {
        button = new ButtonWayPoint(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            event.selected(MyWayPoint.this);
            }
        });
    }   
    public static enum PointType {
    START,END
}
    }