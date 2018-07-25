package com.mango.leo.dcom.event.bean;

import java.util.List;

/**
 * Created by admin on 2018/7/18.
 */

public class MeasureBeans {
    private List<MeasureBeans.MeasureItem> MeasureItems;

    public List<MeasureBeans.MeasureItem> getMeasureItems() {
        return MeasureItems;
    }

    public void setMeasureItems(List<MeasureBeans.MeasureItem> MeasureItems) {
        this.MeasureItems = MeasureItems;
    }

    public static class MeasureItem {
        private String threat;
        private String measure;

        public MeasureItem(String threat, String measure) {
            this.threat = threat;
            this.measure = measure;
        }

        public MeasureItem() {
        }

        public String getThreat() {
            return threat;
        }

        public void setThreat(String threat) {
            this.threat = threat;
        }

        public String getMeasure() {
            return measure;
        }

        public void setMeasure(String measure) {
            this.measure = measure;
        }

        @Override
        public String toString() {
            return "MeasureItem{" +
                    "threat='" + threat + '\'' +
                    ", measure='" + measure + '\'' +
                    '}';
        }
    }
}
