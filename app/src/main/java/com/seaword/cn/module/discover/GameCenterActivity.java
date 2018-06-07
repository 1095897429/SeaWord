package com.seaword.cn.module.discover;

import com.seaword.cn.R;
import com.seaword.cn.base.BaseRefreshActivity;
import com.seaword.cn.bean.discover.GameCenter;
import com.seaword.cn.mvp.contract.discover.GameCenterContract;
import com.seaword.cn.mvp.presenter.discover.GameCenterPresenter;

/**
 * Created by zl on 2018/6/7.
 */

public class GameCenterActivity extends BaseRefreshActivity<GameCenterPresenter,GameCenter.DataBean> implements GameCenterContract.View{

    // TODO 这个界面感觉不方便
    @Override
    protected int getLayoutId() {
        return R.layout.layout_game_center;
    }

    @Override
    public void showGameCenterData(GameCenter gameCenter) {

    }
}
