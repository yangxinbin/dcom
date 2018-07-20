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
        private int step;
        private String detail;

        public MeasureItem(int step, String detail) {
            this.step = step;
            this.detail = detail;
        }

        public MeasureItem() {

        }

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        @Override
        public String toString() {
            return "MeasureItem{" +
                    "step='" + step + '\'' +
                    ", detail='" + detail + '\'' +
                    '}';
        }
    }
}
