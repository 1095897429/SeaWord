package com.seaword.cn.widget.section;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.socks.library.KLog;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * A custom RecyclerView with Sections with custom Titles.
 * Sections are displayed in the same order they were added.
 * 修改 @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 *
 * @author Gustavo Pagani
 */
public class SectionedRVAdapter extends RecyclerView.Adapter<ViewHolder> {

    public final static int VIEW_TYPE_HEADER = 0;
    public final static int VIEW_TYPE_FOOTER = 1;
    public final static int VIEW_TYPE_ITEM_LOADED = 2;
    public final static int VIEW_TYPE_LOADING = 3;
    public final static int VIEW_TYPE_FAILED = 4;

    private LinkedHashMap<String, Section> sections;
    private HashMap<String, Integer> sectionViewTypeNumbers;
    private int viewTypeCount = 0;
    private final static int VIEW_TYPE_QTY = 5;
    private Context mContext;

    public SectionedRVAdapter() {
        sections = new LinkedHashMap<>();
        sectionViewTypeNumbers = new HashMap<>();
    }



    private ViewHolder getItemViewHolder(ViewGroup parent, Section section) {
        View view = LayoutInflater.from(parent.getContext()).inflate(section.getItemResourceId(),
                parent, false);
        // get the item viewholder from the section
        return section.getItemViewHolder(view);
    }


    private ViewHolder getHeaderViewHolder(ViewGroup parent, Section section) {
        Integer resId = section.getHeaderResourceId();

        if (resId == null)
            throw new NullPointerException("Missing 'header' resource id");

        View view = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);

        // get the header viewholder from the section
        return section.getHeaderViewHolder(view);
    }


    private ViewHolder getFooterViewHolder(ViewGroup parent, Section section) {
        Integer resId = section.getFooterResourceId();

        if (resId == null)
            throw new NullPointerException("Missing 'footer' resource id");

        View view = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
        // get the footer viewholder from the section
        return section.getFooterViewHolder(view);
    }

    private ViewHolder getLoadingViewHolder(ViewGroup parent, Section section) {
        Integer resId = section.getLoadingResourceId();

        if (resId == null) throw new NullPointerException("Missing 'loading state' resource id");

        View view = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
        // get the loading viewholder from the section
        return section.getLoadingViewHolder(view);
    }

    private ViewHolder getFailedViewHolder(ViewGroup parent, Section section) {
        Integer resId = section.getFailedResourceId();

        if (resId == null) throw new NullPointerException("Missing 'failed state' resource id");

        View view = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
        // get the failed load viewholder from the section
        return section.getFailedViewHolder(view);
    }

    /**
     * Add a section to this recyclerview.
     *
     * @param tag     unique identifier of the section
     * @param section section to be added
     */
    public void addSection(String tag, Section section) {
        this.sections.put(tag, section);
        this.sectionViewTypeNumbers.put(tag, viewTypeCount);
        viewTypeCount += VIEW_TYPE_QTY;//每次都加上5
    }

    /**
     * Add a section to this recyclerview with a random tag;
     *
     * @param section section to be added
     * @return generated tag
     */
    public String addSection(Section section) {
        String tag = UUID.randomUUID().toString();

        addSection(tag, section);

        return tag;
    }

    /**
     * Return the section with the tag provided.
     *
     * @param tag unique identifier of the section
     * @return section
     */
    public Section getSection(String tag) {
        return this.sections.get(tag);
    }

    /**
     * Remove section from this recyclerview.
     *
     * @param tag unique identifier of the section
     */
    public void removeSection(String tag) {
        this.sections.remove(tag);
    }

    /**
     * Remove all sections from this recyclerview.
     */
    public void removeAllSections() {
        this.sections.clear();
    }

    //拿到viewType创建不同的viewHolder
    //因为 之前返回的viewType是做了处理的。
    //上面返回viewType = 6 是底部

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        mContext = parent.getContext();
        //循环找到对应viewType的区间
        for (Map.Entry<String, Integer> entry : sectionViewTypeNumbers.entrySet()) {
            if (viewType >= entry.getValue() && viewType < entry.getValue() + VIEW_TYPE_QTY) {
                //通过hashmap中的key找到section,后面需要用到section中存储的布局资源,因为两个数据结构都用的是相同的key
                Section section = sections.get(entry.getKey());
                section.mContext = mContext; //设置section 的 context
                //这个位置减去section的起始位置，比如2是内容，0就是头部。。。
                int sectionViewType = viewType - entry.getValue();
                switch (sectionViewType) {
                    case VIEW_TYPE_HEADER: {
                        viewHolder = getHeaderViewHolder(parent, section);
                        break;
                    }
                    case VIEW_TYPE_FOOTER: {
                        viewHolder = getFooterViewHolder(parent, section);
                        break;
                    }
                    case VIEW_TYPE_ITEM_LOADED: {
                        viewHolder = getItemViewHolder(parent, section);
                        break;
                    }
                    case VIEW_TYPE_LOADING: {
                        viewHolder = getLoadingViewHolder(parent, section);
                        break;
                    }
                    case VIEW_TYPE_FAILED: {
                        viewHolder = getFailedViewHolder(parent, section);
                        break;
                    }
                    default:
                        throw new IllegalArgumentException("Invalid viewType");
                }
            }
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        int currentPos = 0;

        for (Map.Entry<String, Section> entry : sections.entrySet()) {
            Section section = entry.getValue();

            // ignore invisible sections
            if (!section.isVisible()) continue;

            int sectionTotal = section.getSectionItemsTotal();
            //position从索引0开始
            if (position >= currentPos && position <= (currentPos + sectionTotal - 1)) {

                if (section.hasHeader()) {
                    if (position == currentPos) {
                        // delegate the binding to the section header
                        getSectionForPosition(position).onBindHeaderViewHolder(holder);
                        return;
                    }
                }

                if (section.hasFooter()) {
                    if (position == (currentPos + sectionTotal - 1)) {
                        // delegate the binding to the section header
                        getSectionForPosition(position).onBindFooterViewHolder(holder);
                        return;
                    }
                }

                // delegate the binding to the section content
                getSectionForPosition(position).onBindContentViewHolder(holder,
                        getPositionInSection(position));
                return;
            }

            currentPos += sectionTotal;
        }

        throw new IndexOutOfBoundsException("Invalid position");
    }

    @Override
    public int getItemCount() {
        int count = 0;

        for (Map.Entry<String, Section> entry : sections.entrySet()) {
            Section section = entry.getValue();

            // ignore invisible sections
            if (!section.isVisible()) continue;

            count += section.getSectionItemsTotal();
        }

        return count;
    }

    //返回viewType --
    //每调用一次，position就会增加一
    @Override
    public int getItemViewType(int position) {
        /*
         Each Section has 5 "viewtypes":
         1) header
         2) footer
         3) items
         4) loading
         5) load failed
         */
        try {


        } catch (Exception e) {


        }
        //LiveBannerSection  LiveEntranceSection LiveRecommendSection LiveRecommendBannerSection LiveRecommendSection 多个LiveRecommendPartitionSection

        int currentPos = 0;//当前索引变量，每次都从0开始
        for (Map.Entry<String, Section> entry : sections.entrySet()) {
            Section section = entry.getValue();

            // ignore invisible sections  默认为true
            if (!section.isVisible()) continue;
            //得到每一个section的item的总数量 + header + footer -- 从而return对应不同的类型
            int sectionTotal = section.getSectionItemsTotal();//TODO 每次都循环，第二次时，第一次肯定不匹配，重新赋值currentPos

            //  sectionTotal - 1是section部分数据的索引,看是否大于上一个数据的最后索引 || 就是通过section的数量 和 每一次调用此方法position会增加1和记录当前位置来确定viewType
            if (position >= currentPos && position <= (currentPos + sectionTotal - 1)) {

                //默认为0，这个代表的是从0|5|10...开始比较
                int viewType = sectionViewTypeNumbers.get(entry.getKey());
                //是头部直接返回，下次循环再获取类型
                if (section.hasHeader()) {
                    if (position == currentPos) {
                        return viewType;
                    }
                }

                if (section.hasFooter()) {
                    if (position == (currentPos + sectionTotal - 1)) {
                        return viewType + 1;
                    }
                }

                switch (section.getState()) {
                    case LOADED:
                        return viewType + 2;
                    case LOADING:
                        return viewType + 3;
                    case FAILED:
                        return viewType + 4;
                    default:
                        throw new IllegalStateException("Invalid state");
                }

            }

            currentPos += sectionTotal;
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    /**
     * Returns the Section ViewType of an item based on the position in the adapter:
     * <p>
     * - SectionedRVAdapter.VIEW_TYPE_HEADER
     * - SectionedRVAdapter.VIEW_TYPE_FOOTER
     * - SectionedRVAdapter.VIEW_TYPE_ITEM_LOAViewHolderDED
     * - SectionedRVAdapter.VIEW_TYPE_LOADING
     * - SectionedRVAdapter.VIEW_TYPE_FAILED
     *
     * @param position position in the adapter
     * @return SectionedRVAdapter.VIEW_TYPE_HEADER, VIEW_TYPE_FOOTER,
     * VIEW_TYPE_ITEM_LOADED, VIEW_TYPE_LOADING or VIEW_TYPE_FAILED
     */
    public int getSectionItemViewType(int position) {
        int viewType = getItemViewType(position);

        return viewType % VIEW_TYPE_QTY;
    }

    /**
     * Returns the Section object for a position in the adapter.
     * 返回不同区域的Section
     *
     * @param position position in the adapter
     * @return Section object for that position
     */
    public Section getSectionForPosition(int position) {

        int currentPos = 0;

        for (Map.Entry<String, Section> entry : sections.entrySet()) {
            Section section = entry.getValue();

            // ignore invisible sections
            if (!section.isVisible()) continue;

            int sectionTotal = section.getSectionItemsTotal();

            // check if position is in this section
            if (position >= currentPos && position <= (currentPos + sectionTotal - 1)) {
                return section;
            }

            currentPos += sectionTotal;
        }

        throw new IndexOutOfBoundsException("Invalid position");
    }

    /**
     * @deprecated Use {@link #getPositionInSection getPositionInSection} instead.
     */
    @Deprecated
    public int getSectionPosition(int position) {
        return getPositionInSection(position);
    }

    /**
     * Return the item position relative to the section.
     * 返回每个section中的position
     * @param position position of the item in the adapter
     * @return position of the item in the section
     */
    public int getPositionInSection(int position) {
        int currentPos = 0;

        for (Map.Entry<String, Section> entry : sections.entrySet()) {
            Section section = entry.getValue();

            // ignore invisible sections
            if (!section.isVisible()) continue;

            int sectionTotal = section.getSectionItemsTotal();

            // check if position is in this section
            if (position >= currentPos && position <= (currentPos + sectionTotal - 1)) {
                return position - currentPos - (section.hasHeader() ? 1 : 0);//5 - 4 - 1 = 0 position4是头部的布局，5是内容布局
            }

            currentPos += sectionTotal;
        }

        throw new IndexOutOfBoundsException("Invalid position");
    }

    /**
     * Return the section position in the adapter.
     *
     * @param tag unique identifier of the section
     * @return position of the section in the adapter
     */
    public int getSectionPosition(String tag) {
        int currentPos = 0;

        for (Map.Entry<String, Section> entry : sections.entrySet()) {
            Section section = entry.getValue();

            // ignore invisible sections
            if (!section.isVisible()) continue;

            int sectionTotal = section.getSectionItemsTotal();

            if (entry.getKey().equalsIgnoreCase(tag)) {
                return currentPos;
            }

            currentPos += sectionTotal;
        }

        throw new IllegalArgumentException("Invalid tag: " + tag);
    }

    /**
     * Return a map with all sections of this adapter.
     *
     * @return a map with all sections
     */
    public LinkedHashMap<String, Section> getSectionsMap() {
        return sections;
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemInserted notifyItemInserted}.
     *
     * @param tag      unique identifier of the section
     * @param position position of the item in the section
     */
    public void notifyItemInsertedInSection(String tag, int position) {
        Section section = getValidSectionOrThrowException(tag);

        callSuperNotifyItemInserted(getSectionPosition(tag) + (section.hasHeader ? 1 : 0) + position);
    }

    @VisibleForTesting
    void callSuperNotifyItemInserted(int position) {
        super.notifyItemInserted(position);
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemRangeInserted notifyItemRangeInserted}.
     *
     * @param tag           unique identifier of the section
     * @param positionStart position of the first item that was inserted in the section
     * @param itemCount     number of items inserted in the section
     */
    public void notifyItemRangeInsertedInSection(String tag, int positionStart, int itemCount) {
        Section section = getValidSectionOrThrowException(tag);

        callSuperNotifyItemRangeInserted(
                getSectionPosition(tag) + (section.hasHeader ? 1 : 0) + positionStart, itemCount);
    }

    @VisibleForTesting
    void callSuperNotifyItemRangeInserted(int positionStart, int itemCount) {
        super.notifyItemRangeInserted(positionStart, itemCount);
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemRemoved notifyItemRemoved}.
     *
     * @param tag      unique identifier of the section
     * @param position position of the item in the section
     */
    public void notifyItemRemovedFromSection(String tag, int position) {
        Section section = getValidSectionOrThrowException(tag);

        callSuperNotifyItemRemoved(getSectionPosition(tag) + (section.hasHeader ? 1 : 0) + position);
    }

    @VisibleForTesting
    void callSuperNotifyItemRemoved(int position) {
        super.notifyItemRemoved(position);
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemRangeRemoved notifyItemRangeRemoved}.
     *
     * @param tag           unique identifier of the section
     * @param positionStart previous position of the first item that was removed from the section
     * @param itemCount     number of items removed from the section
     */
    public void notifyItemRangeRemovedFromSection(String tag, int positionStart, int itemCount) {
        Section section = getValidSectionOrThrowException(tag);

        callSuperNotifyItemRangeRemoved(
                getSectionPosition(tag) + (section.hasHeader ? 1 : 0) + positionStart, itemCount);
    }

    @VisibleForTesting
    void callSuperNotifyItemRangeRemoved(int positionStart, int itemCount) {
        super.notifyItemRangeRemoved(positionStart, itemCount);
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemChanged notifyItemChanged}.
     *
     * @param tag      unique identifier of the section
     * @param position position of the item in the section
     */
    public void notifyItemChangedInSection(String tag, int position) {
        Section section = getValidSectionOrThrowException(tag);

        callSuperNotifyItemChanged(getSectionPosition(tag) + (section.hasHeader ? 1 : 0) + position);
    }

    @VisibleForTesting
    void callSuperNotifyItemChanged(int position) {
        super.notifyItemChanged(position);
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemRangeChanged notifyItemRangeChanged}.
     *
     * @param tag           unique identifier of the section
     * @param positionStart position of the first item that was inserted in the section
     * @param itemCount     number of items inserted in the section
     */
    public void notifyItemRangeChangedInSection(String tag, int positionStart, int itemCount) {
        Section section = getValidSectionOrThrowException(tag);

        callSuperNotifyItemRangeChanged(
                getSectionPosition(tag) + (section.hasHeader ? 1 : 0) + positionStart, itemCount);
    }

    @VisibleForTesting
    void callSuperNotifyItemRangeChanged(int positionStart, int itemCount) {
        super.notifyItemRangeChanged(positionStart, itemCount);
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemRangeChanged notifyItemRangeChanged}.
     *
     * @param tag           unique identifier of the section
     * @param positionStart position of the first item that was inserted in the section
     * @param itemCount     number of items inserted in the section
     * @param payload       optional parameter, use null to identify a "full" update
     */
    public void notifyItemRangeChangedInSection(String tag, int positionStart, int itemCount,
                                                Object payload) {
        Section section = getValidSectionOrThrowException(tag);

        callSuperNotifyItemRangeChanged(
                getSectionPosition(tag) + (section.hasHeader ? 1 : 0) + positionStart, itemCount,
                payload);
    }

    @VisibleForTesting
    void callSuperNotifyItemRangeChanged(int positionStart, int itemCount, Object payload) {
        super.notifyItemRangeChanged(positionStart, itemCount, payload);
    }

    /**
     * Helper method that receives position in relation to the section, calculates the relative
     * position in the adapter and calls {@link #notifyItemMoved notifyItemMoved}.
     *
     * @param tag          unique identifier of the section
     * @param fromPosition previous position of the item in the section
     * @param toPosition   new position of the item in the section
     */
    public void notifyItemMovedInSection(String tag, int fromPosition, int toPosition) {
        Section section = getValidSectionOrThrowException(tag);

        callSuperNotifyItemMoved(getSectionPosition(tag) + (section.hasHeader ? 1 : 0) + fromPosition,
                getSectionPosition(tag) + (section.hasHeader ? 1 : 0) + toPosition);
    }

    @VisibleForTesting
    void callSuperNotifyItemMoved(int fromPosition, int toPosition) {
        super.notifyItemMoved(fromPosition, toPosition);
    }

    @NonNull
    private Section getValidSectionOrThrowException(String tag) {
        Section section = getSection(tag);

        if (section == null) {
            throw new IllegalArgumentException("Invalid tag: " + tag);
        }

        return section;
    }

}
