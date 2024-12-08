package travel;

import data.RoutingData;
import data.RoutingService;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;
import waypoint.EventWayPoint;
import waypoint.MyWayPoint;
import waypoint.WaypointRender;

    

public class Main extends javax.swing.JFrame {

    private final Set<MyWayPoint> waypoints = new HashSet<>();
    private List<RoutingData> routingData = new ArrayList<>();
    private EventWayPoint event;
    private Point mousePosition;
    private GeoPosition startPosition = null;
    private GeoPosition endPosition = null;
    
    private static final String API_KEY = "e8c4eebf87cc450cb6f25be41699d04c";
    
    public Main() {
        initComponents();
        init();
    }

    private void init(){
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapViewer.setTileFactory(tileFactory);
        GeoPosition geo=new GeoPosition(6.4950702,124.8448955);
        jXMapViewer.setAddressLocation(geo);
        jXMapViewer.setZoom(12);
        
        //To move mouse
        MouseInputListener mm = new PanMouseInputListener(jXMapViewer);
        jXMapViewer.addMouseListener(mm);
        jXMapViewer.addMouseMotionListener(mm);
        jXMapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(jXMapViewer));   

        event = getEvent();
    }
    
     private void addWayPoint(MyWayPoint waypoint) {
        // Remove existing waypoints of the same type
        Iterator<MyWayPoint> iter = waypoints.iterator();
        while (iter.hasNext()) {
            MyWayPoint existing = iter.next();
            if (existing.getPointType() == waypoint.getPointType()) {
                jXMapViewer.remove(existing.getButton());
                iter.remove();
            }
        }
        waypoints.add(waypoint);
        
        if (waypoint.getPointType() == MyWayPoint.PointType.START) {
            startPosition = waypoint.getPosition();
        } else if (waypoint.getPointType() == MyWayPoint.PointType.END) {
            endPosition = waypoint.getPosition();
        }
        
        initWaypoint();
    }
    
    private void initWaypoint(){
        // Clear existing waypoints from map
        for (MyWayPoint d : waypoints) {
            jXMapViewer.remove(d.getButton());
        }
        
        // Create waypoint painter
        WaypointPainter<MyWayPoint> wp = new WaypointRender();
        wp.setWaypoints(waypoints);
        jXMapViewer.setOverlayPainter(wp);
        
        // Add waypoint buttons
        for (MyWayPoint d : waypoints) {    
            jXMapViewer.add(d.getButton());
        }
        
        // Routing
        if (startPosition != null && endPosition != null) {
            // Use RoutingService to get routing data
            routingData = RoutingService.getInstance().routing(
                startPosition.getLatitude(), 
                startPosition.getLongitude(), 
                endPosition.getLatitude(), 
                endPosition.getLongitude()
            );
            
            // Set routing data on map
            jXMapViewer.setRoutingData(routingData);
        } else {
            routingData.clear();
        }
    }
    
    private void clearWaypoint(){
        for (MyWayPoint d : waypoints) {
            jXMapViewer.remove(d.getButton());
        }
        routingData.clear();
        waypoints.clear();
        startPosition = null;
        endPosition = null;
        initWaypoint();
    }
    
    private EventWayPoint getEvent() {
        return new EventWayPoint() {
            @Override
            public void selected(MyWayPoint waypoint) {
                JOptionPane.showMessageDialog(Main.this, "Selected: " + waypoint.getName());
            }
        };
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        menuStart = new javax.swing.JMenuItem();
        menuEND = new javax.swing.JMenuItem();
        jXMapViewer = new data.JXMapViewerCustom();
        cmdClear = new javax.swing.JButton();
        comboMapType = new javax.swing.JComboBox<>();

        menuStart.setText("Start");
        menuStart.setToolTipText("");
        menuStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuStartActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menuStart);

        menuEND.setText("End");
        menuEND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuENDActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menuEND);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jXMapViewer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jXMapViewerMouseReleased(evt);
            }
        });

        cmdClear.setText("Clear Waypoint");
        cmdClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdClearActionPerformed(evt);
            }
        });

        comboMapType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Open Street", "Virtual Earth", "Hybrid", "Satellite" }));
        comboMapType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMapTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jXMapViewerLayout = new javax.swing.GroupLayout(jXMapViewer);
        jXMapViewer.setLayout(jXMapViewerLayout);
        jXMapViewerLayout.setHorizontalGroup(
            jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXMapViewerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdClear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 659, Short.MAX_VALUE)
                .addComponent(comboMapType, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jXMapViewerLayout.setVerticalGroup(
            jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXMapViewerLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboMapType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdClear))
                .addContainerGap(515, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXMapViewer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXMapViewer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void comboMapTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMapTypeActionPerformed
        // TODO add your handling code here:
        TileFactoryInfo info;
        int index = comboMapType.getSelectedIndex();
        if (index == 0) {
            info = new OSMTileFactoryInfo();  
        } else if (index == 1) {
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
        } else if (index == 2) {
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID);
        } else {
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.SATELLITE);
        }

        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapViewer.setTileFactory(tileFactory);
    }//GEN-LAST:event_comboMapTypeActionPerformed

    private void cmdClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdClearActionPerformed
        clearWaypoint();
    }//GEN-LAST:event_cmdClearActionPerformed

    private void menuENDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuENDActionPerformed
        GeoPosition geop = jXMapViewer.convertPointToGeoPosition(mousePosition);
        
        // Create end waypoint
        MyWayPoint wayPoint = new MyWayPoint(
            "End Location", 
            MyWayPoint.PointType.END, 
            event, 
            new GeoPosition(geop.getLatitude(), geop.getLongitude())
        );
        addWayPoint(wayPoint);  
    }//GEN-LAST:event_menuENDActionPerformed

    private void menuStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStartActionPerformed
       GeoPosition geop = jXMapViewer.convertPointToGeoPosition(mousePosition);
        
        // Create start waypoint
        MyWayPoint wayPoint = new MyWayPoint(
            "Start Location", 
            MyWayPoint.PointType.START, 
            event, 
            new GeoPosition(geop.getLatitude(), geop.getLongitude())
        );
        
        // Add waypoint and attempt to create route
        addWayPoint(wayPoint);
    }//GEN-LAST:event_menuStartActionPerformed

    private void jXMapViewerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXMapViewerMouseReleased
        if (SwingUtilities.isRightMouseButton(evt)) {
        mousePosition = evt.getPoint();
        jPopupMenu1.show(jXMapViewer, evt.getX(), evt.getY());
    }
    }//GEN-LAST:event_jXMapViewerMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdClear;
    private javax.swing.JComboBox<String> comboMapType;
    private javax.swing.JPopupMenu jPopupMenu1;
    private data.JXMapViewerCustom jXMapViewer;
    private javax.swing.JMenuItem menuEND;
    private javax.swing.JMenuItem menuStart;
    // End of variables declaration//GEN-END:variables
}
