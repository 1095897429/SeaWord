package com.seaword.cn.mvp.presenter.app.video;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.seaword.cn.base.BaseSubscriber;
import com.seaword.cn.base.RxPresenter;
import com.seaword.cn.bean.app.video.VideoDetail;
import com.seaword.cn.bean.app.video.VideoDetailComment;
import com.seaword.cn.mvp.contract.app.video.VideoDetailContract;
import com.seaword.cn.network.RetrofitHelper;
import com.seaword.cn.rx.RxUtils;
import com.seaword.cn.utils.JsonUtils;

import org.reactivestreams.Publisher;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by zl on 2018/5/24.
 * 1.赋具体的值
 */

public class VideoDetailPresenter extends RxPresenter<VideoDetailContract.View> implements VideoDetailContract.Presenter<VideoDetailContract.View>{
    RetrofitHelper mRetrofitHelper;

    @Inject
    public VideoDetailPresenter(RetrofitHelper mRetrofitHelper){
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getVideoDetailData() {
        /** 网络 */
        addSubscribe(mRetrofitHelper.getVideoDetail()
                .flatMap(new Function<VideoDetail, Publisher<VideoDetailComment>>() {
                    @Override
                    public Publisher<VideoDetailComment> apply(@NonNull VideoDetail videoDetail) throws Exception {
                        mView.showVideoDetail(videoDetail.getData());
                        return mRetrofitHelper.getVideoDetailComment();
                    }
                })
                .compose(RxUtils.<VideoDetailComment>rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<VideoDetailComment>(mView) {
                    @Override
                    public void onSuccess(VideoDetailComment videoDetailComment) {
                        mView.showVideoDetailComment(videoDetailComment.data);
                    }
                }));
        /** 本地 */
        /*addSubscribe(Flowable.just(JsonUtils.readJson("videodetail.json"))
                .flatMap(new Function<String, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(@NonNull String string) throws Exception {
                        Gson gson = new Gson();
                        JsonObject object = new JsonParser().parse(string).getAsJsonObject();
                        VideoDetail.DataBean data = gson.fromJson(object,VideoDetail.DataBean .class);
                        mView.showVideoDetail(data);
                        return Flowable.just(JsonUtils.readJson("videodetailcomment.json"));
                    }
                })
                .compose(RxUtils.<String>rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<String>(mView) {
                    @Override
                    public void onSuccess(String string) {
                        Gson gson = new Gson();
                        JsonObject object = new JsonParser().parse(string).getAsJsonObject();
                        VideoDetailComment.DataBean data = gson.fromJson(object,VideoDetailComment.DataBean .class);
                        mView.showVideoDetailComment(data);
                    }
                }));*/
    }
}
