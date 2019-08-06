package com.windmillsteward.jukutech.interfaces;

import com.cpoopc.scrollablelayoutlib.ScrollableHelper;
import com.windmillsteward.jukutech.customview.MaxRecyclerView;

/**
 * 描述：
 * author:JK
 * 2019/2/25
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */
public class MyScrollableHelper extends ScrollableHelper {
    private ScrollableContainer container;

    @Override
    public void setCurrentScrollableContainer(ScrollableContainer scrollableContainer) {
        this.container = scrollableContainer;
    }

    @Override
    public boolean isTop() {
        if (container.getScrollableView()==null)return true;

        if (container.getScrollableView() instanceof MaxRecyclerView) {
            MaxRecyclerView scrollableView = (MaxRecyclerView) container.getScrollableView();
            if (scrollableView.isTop()) return true;
            else return false;
        }
        return super.isTop();
    }





}

