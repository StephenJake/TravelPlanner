    package data;

    import com.graphhopper.util.PointList;

    public class RoutingData {


        /**
         * @return the distance
         */
        public double getDistance() {
            return distance;
        }

        /**
         * @param distance the distance to set
         */
        public void setDistance(double distance) {
            this.distance = distance;
        }

        /**
         * @return the turnDescription
         */
        public String getTurnDescription() {
            return turnDescription;
        }

        /**
         * @param turnDescription the turnDescription to set
         */
        public void setTurnDescription(String turnDescription) {
            this.turnDescription = turnDescription;
        }

        /**
         * @return the pointList
         */
        public PointList getPointList() {   
            return pointList;
        }

        /**
         * @param pointList the pointList to set
         */
        public void setPointList(PointList pointList) {
            this.pointList = pointList;
        }

            public RoutingData(double distance, String turnDescription, PointList pointList) {
        this.distance = distance;
        this.turnDescription = turnDescription;
        this.pointList = pointList;
    }
            private double distance;
            private String turnDescription;
            private PointList pointList;
    }