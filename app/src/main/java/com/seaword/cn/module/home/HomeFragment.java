package com.seaword.cn.module.home;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.seaword.cn.R;

/**
 * Created by zl on 2018/5/22.
 */

public class HomeFragment extends BaseHomeFragment {



    /** 通过这种形式获取Fragment */
    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_home;
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }


    /** 重写onCreateOptionsMenu方法来给当前Toolbar控件设置具体菜单条目 */
    /** 先clear一下，这样只有Fragment中设置的自己的了, 不会有Activity中的按钮 */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_main,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_game:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
