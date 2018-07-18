package com.mango.leo.dcom.change.bean;

import java.util.List;

/**
 * Created by admin on 2018/7/18.
 */

public class MethodBeans {
    private List<MethodItem> methodItems;

    public List<MethodItem> getMethodItems() {
        return methodItems;
    }

    public void setMethodItems(List<MethodItem> methodItems) {
        this.methodItems = methodItems;
    }

    public static class MethodItem {
        private int step;
        private String detail;

        public MethodItem(int step, String detail) {
            this.step = step;
            this.detail = detail;
        }

        public MethodItem() {

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
            return "MethodItem{" +
                    "step='" + step + '\'' +
                    ", detail='" + detail + '\'' +
                    '}';
        }
    }
}
