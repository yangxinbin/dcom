package com.mango.leo.dcom.change.bean;

import java.util.List;

/**
 * Created by admin on 2018/7/18.
 */

public class RevertBeans {
    private List<RevertBeans.RevertItem> RevertItems;

    public List<RevertBeans.RevertItem> getRevertItems() {
        return RevertItems;
    }

    public void setRevertItems(List<RevertBeans.RevertItem> RevertItems) {
        this.RevertItems = RevertItems;
    }

    public static class RevertItem {
        private int step;
        private String detail;

        public RevertItem(int step, String detail) {
            this.step = step;
            this.detail = detail;
        }

        public RevertItem() {

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
            return "RevertItem{" +
                    "step='" + step + '\'' +
                    ", detail='" + detail + '\'' +
                    '}';
        }
    }
}
