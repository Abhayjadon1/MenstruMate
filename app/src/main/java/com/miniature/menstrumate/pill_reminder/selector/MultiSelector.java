package com.miniature.menstrumate.pill_reminder.selector;

import android.os.Bundle;
import android.util.SparseBooleanArray;




import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiSelector {
    private static final String SELECTION_POSITIONS = "position";
    private static final String SELECTIONS_STATE = "state";
    private SparseBooleanArray mSelections = new SparseBooleanArray();
    private WeakHolderTracker mTracker = new WeakHolderTracker();
    private boolean mIsSelectable;

    public MultiSelector() {
    }

    public boolean isSelectable() {
        return this.mIsSelectable;
    }

    public void setSelectable(boolean isSelectable) {
        this.mIsSelectable = isSelectable;
        this.refreshAllHolders();
    }

    public void setSelected(SelectableHolder holder, boolean isSelected) {
        this.setSelected(holder.getAdapterPosition(), holder.getItemId(), isSelected);
    }

    public void setSelected(int position, long id, boolean isSelected) {
        this.mSelections.put(position, isSelected);
        this.refreshHolder(this.mTracker.getHolder(position));
    }

    public boolean isSelected(int position, long id) {
        return this.mSelections.get(position);
    }

    public void clearSelections() {
        this.mSelections.clear();
        this.refreshAllHolders();
    }

    public List<Integer> getSelectedPositions() {
        List<Integer> positions = new ArrayList();

        for(int i = 0; i < this.mSelections.size(); ++i) {
            if (this.mSelections.valueAt(i)) {
                positions.add(this.mSelections.keyAt(i));
            }
        }

        return positions;
    }

    public void bindHolder(SelectableHolder holder, int position, long id) {
        this.mTracker.bindHolder(holder, position);
        this.refreshHolder(holder);
    }

    public boolean tapSelection(SelectableHolder holder) {
        return this.tapSelection(holder.getAdapterPosition(), holder.getItemId());
    }

    public boolean tapSelection(int position, long itemId) {
        if (this.mIsSelectable) {
            boolean isSelected = this.isSelected(position, itemId);
            this.setSelected(position, itemId, !isSelected);
            return true;
        } else {
            return false;
        }
    }

    public void refreshAllHolders() {
        Iterator var1 = this.mTracker.getTrackedHolders().iterator();

        while(var1.hasNext()) {
            SelectableHolder holder = (SelectableHolder)var1.next();
            this.refreshHolder(holder);
        }

    }

    private void refreshHolder(SelectableHolder holder) {
        if (holder != null) {
            holder.setSelectable(this.mIsSelectable);
            boolean isActivated = this.mSelections.get(holder.getAdapterPosition());
            holder.setActivated(isActivated);
        }
    }

    public Bundle saveSelectionStates() {
        Bundle information = new Bundle();
        information.putIntegerArrayList("position", (ArrayList)this.getSelectedPositions());
        information.putBoolean("state", this.isSelectable());
        return information;
    }

    public void restoreSelectionStates(Bundle savedStates) {
        List<Integer> selectedPositions = savedStates.getIntegerArrayList("position");
        this.restoreSelections(selectedPositions);
        this.mIsSelectable = savedStates.getBoolean("state");
    }

    private void restoreSelections(List<Integer> selected) {
        if (selected != null) {
            this.mSelections.clear();

            for(int i = 0; i < selected.size(); ++i) {
                int position = (Integer)selected.get(i);
                this.mSelections.put(position, true);
            }

            this.refreshAllHolders();
        }
    }
}

