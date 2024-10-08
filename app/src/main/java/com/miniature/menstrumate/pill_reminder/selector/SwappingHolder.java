//package com.miniature.menstrumate.pill_reminder.selector;
//
//
//
//
//import android.animation.AnimatorInflater;
//import android.animation.StateListAnimator;
//import android.content.Context;
//import android.content.res.Resources;
//import android.graphics.drawable.ColorDrawable;
//import android.graphics.drawable.Drawable;
//import android.graphics.drawable.StateListDrawable;
//import android.os.Build.VERSION;
//import android.util.StateSet;
//import android.util.TypedValue;
//import android.view.View;
//
//import com.miniature.menstrumate.R;
//
//
//public class SwappingHolder extends MultiSelectorBindingHolder implements SelectableHolder {
//    private MultiSelector mMultiSelector;
//    private boolean mIsSelectable;
//    private Drawable mSelectionModeBackgroundDrawable;
//    private Drawable mDefaultModeBackgroundDrawable;
//    private StateListAnimator mSelectionModeStateListAnimator;
//    private StateListAnimator mDefaultModeStateListAnimator;
//
//    public SwappingHolder(View itemView, MultiSelector multiSelector) {
//        super(itemView, multiSelector);
//        this.mIsSelectable = false;
//        this.mMultiSelector = multiSelector;
//        if (VERSION.SDK_INT >= 21) {
//            this.setSelectionModeStateListAnimator(getRaiseStateListAnimator(itemView.getContext()));
//            this.setDefaultModeStateListAnimator(itemView.getStateListAnimator());
//        }
//
//        this.setSelectionModeBackgroundDrawable(getAccentStateDrawable(itemView.getContext()));
//        this.setDefaultModeBackgroundDrawable(itemView.getBackground());
//    }
//
//    public SwappingHolder(View itemView) {
//        this(itemView, (MultiSelector)null);
//    }
//
//    private static Drawable getAccentStateDrawable(Context context) {
//        TypedValue typedValue = new TypedValue();
//        Resources.Theme theme = context.getTheme();
//        theme.resolveAttribute(androidx.appcompat.R.attr.colorAccent, typedValue, true);
//        Drawable colorDrawable = new ColorDrawable(typedValue.data);
//        StateListDrawable stateListDrawable = new StateListDrawable();
//        stateListDrawable.addState(new int[]{16843518}, colorDrawable);
//        stateListDrawable.addState(StateSet.WILD_CARD, (Drawable)null);
//        return stateListDrawable;
//    }
//
//    private static StateListAnimator getRaiseStateListAnimator(Context context) {
//        return VERSION.SDK_INT >= 21 ? AnimatorInflater.loadStateListAnimator(context, R.anim.raise) : null;
//    }
//
//
//    public Drawable getSelectionModeBackgroundDrawable() {
//        return this.mSelectionModeBackgroundDrawable;
//    }
//
//    public void setSelectionModeBackgroundDrawable(Drawable selectionModeBackgroundDrawable) {
//        this.mSelectionModeBackgroundDrawable = selectionModeBackgroundDrawable;
//        if (this.mIsSelectable) {
//            this.itemView.setBackgroundDrawable(selectionModeBackgroundDrawable);
//        }
//
//    }
//
//    public Drawable getDefaultModeBackgroundDrawable() {
//        return this.mDefaultModeBackgroundDrawable;
//    }
//
//    public void setDefaultModeBackgroundDrawable(Drawable defaultModeBackgroundDrawable) {
//        this.mDefaultModeBackgroundDrawable = defaultModeBackgroundDrawable;
//        if (!this.mIsSelectable) {
//            this.itemView.setBackgroundDrawable(this.mDefaultModeBackgroundDrawable);
//        }
//
//    }
//
//    public StateListAnimator getSelectionModeStateListAnimator() {
//        return this.mSelectionModeStateListAnimator;
//    }
//
//    public void setSelectionModeStateListAnimator(StateListAnimator selectionModeStateListAnimator) {
//        this.mSelectionModeStateListAnimator = selectionModeStateListAnimator;
//    }
//
//    public void setSelectionModeStateListAnimator(int resId) {
//        if (VERSION.SDK_INT >= 21) {
//            StateListAnimator animator = AnimatorInflater.loadStateListAnimator(this.itemView.getContext(), resId);
//            this.setSelectionModeStateListAnimator(animator);
//        }
//
//    }
//
//    public StateListAnimator getDefaultModeStateListAnimator() {
//        return this.mDefaultModeStateListAnimator;
//    }
//
//    public void setDefaultModeStateListAnimator(int resId) {
//        if (VERSION.SDK_INT >= 21) {
//            StateListAnimator animator = AnimatorInflater.loadStateListAnimator(this.itemView.getContext(), resId);
//            this.setDefaultModeStateListAnimator(animator);
//        }
//
//    }
//
//    public void setDefaultModeStateListAnimator(StateListAnimator defaultModeStateListAnimator) {
//        this.mDefaultModeStateListAnimator = defaultModeStateListAnimator;
//    }
//
//    public boolean isActivated() {
//        return this.itemView.isActivated();
//    }
//
//    public void setActivated(boolean isActivated) {
//        this.itemView.setActivated(isActivated);
//    }
//
//    public boolean isSelectable() {
//        return this.mIsSelectable;
//    }
//
//    public void setSelectable(boolean isSelectable) {
//        boolean changed = isSelectable != this.mIsSelectable;
//        this.mIsSelectable = isSelectable;
//        if (changed) {
//            this.refreshChrome();
//        }
//
//    }
//
//    private void refreshChrome() {
//        Drawable backgroundDrawable = this.mIsSelectable ? this.mSelectionModeBackgroundDrawable : this.mDefaultModeBackgroundDrawable;
//        this.itemView.setBackgroundDrawable(backgroundDrawable);
//        if (backgroundDrawable != null) {
//            backgroundDrawable.jumpToCurrentState();
//        }
//
//        if (VERSION.SDK_INT >= 21) {
//            StateListAnimator animator = this.mIsSelectable ? this.mSelectionModeStateListAnimator : this.mDefaultModeStateListAnimator;
//            this.itemView.setStateListAnimator(animator);
//            if (animator != null) {
//                animator.jumpToCurrentState();
//            }
//        }
//
//    }
//}
//
