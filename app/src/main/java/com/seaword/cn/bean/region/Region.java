package com.seaword.cn.bean.region;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zl on 2018/5/30.
 * result 返回的集合 -- 确定里面每一个实体Bean
 */

public class Region {
        /**
         * param : 13
         * type : bangumi
         * style : medium
         * title : 番剧区
         * body : [{"title":"鹿枫堂","cover":"http://i0.hdslb.com/bfs/archive/f732367443c5de9dada0b8ca2966002ce9d80bd6.jpg","uri":"http://bangumi.bilibili.com/anime/23873","param":"23873","goto":"bangumi","play":1238556,"index":"8","total_count":"12","mtime":"2018-05-30 00:30:00.0","status":2,"favourite":167802,"is_ad":false,"cm_mark":0},{"title":"高达创形者","cover":"http://i0.hdslb.com/bfs/archive/0e095c9ae52ede212602182ec73b8a42ac179502.jpg","uri":"http://bangumi.bilibili.com/anime/23595","param":"23595","goto":"bangumi","play":3754917,"index":"9","total_count":"0","mtime":"2018-05-29 18:00:00.0","status":2,"favourite":211553,"is_ad":false,"cm_mark":0},{"title":"宇宙战舰提拉米斯","cover":"http://i0.hdslb.com/bfs/archive/ed150072c3e26be98e9790ef216caded9a0dbd9d.jpg","uri":"http://bangumi.bilibili.com/anime/23861","param":"23861","goto":"bangumi","play":4268397,"index":"9","total_count":"0","mtime":"2018-05-29 01:00:00.0","status":2,"favourite":375070,"is_ad":false,"cm_mark":0},{"title":"魔法少女 俺","cover":"http://i0.hdslb.com/bfs/archive/21c4247d5200027f8bda19077fd3f27cad3dee4d.jpg","uri":"http://bangumi.bilibili.com/anime/23871","param":"23871","goto":"bangumi","play":4935930,"index":"9","total_count":"-1","mtime":"2018-05-28 22:00:00.0","status":7,"favourite":647337,"is_ad":false,"cm_mark":0}]
         */

        private String param;
        private String type;
        private String style;
        private String title;
        private List<BodyBean> body;

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<BodyBean> getBody() {
            return body;
        }

        public void setBody(List<BodyBean> body) {
            this.body = body;
        }

        public static class BodyBean {
            /**
             * title : 鹿枫堂
             * cover : http://i0.hdslb.com/bfs/archive/f732367443c5de9dada0b8ca2966002ce9d80bd6.jpg
             * uri : http://bangumi.bilibili.com/anime/23873
             * param : 23873
             * goto : bangumi
             * play : 1238556
             * index : 8
             * total_count : 12
             * mtime : 2018-05-30 00:30:00.0
             * status : 2
             * favourite : 167802
             * is_ad : false
             * cm_mark : 0
             */

            private String title;
            private String cover;
            private String uri;
            private String param;
            @SerializedName("goto")
            private String gotoX;
            private int play;
            private String index;
            private String total_count;
            private String mtime;
            private int status;
            private int favourite;//喜爱量 -- 番剧特有
            private boolean is_ad;
            private int cm_mark;
            public int danmaku;//弹幕量 -- 其他都有

            public int getDanmaku() {
                return danmaku;
            }

            public void setDanmaku(int danmaku) {
                this.danmaku = danmaku;
            }

            public boolean is_ad() {
                return is_ad;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }

            public String getParam() {
                return param;
            }

            public void setParam(String param) {
                this.param = param;
            }

            public String getGotoX() {
                return gotoX;
            }

            public void setGotoX(String gotoX) {
                this.gotoX = gotoX;
            }

            public int getPlay() {
                return play;
            }

            public void setPlay(int play) {
                this.play = play;
            }

            public String getIndex() {
                return index;
            }

            public void setIndex(String index) {
                this.index = index;
            }

            public String getTotal_count() {
                return total_count;
            }

            public void setTotal_count(String total_count) {
                this.total_count = total_count;
            }

            public String getMtime() {
                return mtime;
            }

            public void setMtime(String mtime) {
                this.mtime = mtime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getFavourite() {
                return favourite;
            }

            public void setFavourite(int favourite) {
                this.favourite = favourite;
            }

            public boolean isIs_ad() {
                return is_ad;
            }

            public void setIs_ad(boolean is_ad) {
                this.is_ad = is_ad;
            }

            public int getCm_mark() {
                return cm_mark;
            }

            public void setCm_mark(int cm_mark) {
                this.cm_mark = cm_mark;
            }
        }
    }

