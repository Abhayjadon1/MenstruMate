//package com.miniature.menstrumate.pill_reminder.selector;
//
//import android.support.v7.widget.RebindReportingHolder;
//import android.view.View;
//
//
//
//public abstract class MultiSelectorBindingHolder extends RebindReportingHolder implements SelectableHolder {
//    private final MultiSelector mMultiSelector;
//
//    public MultiSelectorBindingHolder(View itemView, MultiSelector multiSelector) {
//        super(itemView);
//        this.mMultiSelector = multiSelector;
//    }
//
//    protected void onRebind() {
//        this.mMultiSelector.bindHolder(this, this.getAdapterPosition(), this.getItemId());
//    }
//}
//
