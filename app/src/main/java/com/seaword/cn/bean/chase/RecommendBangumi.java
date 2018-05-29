package com.seaword.cn.bean.chase;

import java.util.List;

/**
 * Created by zl on 2018/5/29.
 * 追番部分 -- 国产动画推荐 + 番剧推荐
 */

public class RecommendBangumi {

        /**
         * ad : []
         * recommend_cn : {"foot":[{"cover":"http://i0.hdslb.com/bfs/bangumi/cdd184ce6e51aefd7055103d54753fd63d47e061.jpg","cursor":1496246400706,"desc":"叶峰要玩自己的音乐，楚天歌想让麦云洁另眼相看，盖世爱要出风头，石小松要当一流鼓手，四个男孩组成了乐队，感受到青春与音乐的魅力。","id":5046,"is_new":1,"link":"http://bangumi.bilibili.com/anime/2169","onDt":"2017-06-01 00:00:00","title":"六一快乐\u2022童年的《我为歌狂》"}],"recommend":[{"cover":"http://i0.hdslb.com/bfs/bangumi/186560d36c229efd888ef959f796d58abd2fd685.jpg","favourites":"18056","is_auto":0,"is_finish":0,"is_started":1,"last_time":1496318400,"newest_ep_index":"41","pub_time":1469116800,"season_id":6052,"season_status":2,"title":"叫我僵小鱼 日常篇","total_count":-1,"watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/6e87284e07edb2a7bb0fbe6bd2af0e4601a13f7b.jpg","favourites":"12383","is_auto":0,"is_finish":0,"is_started":1,"last_time":1496304000,"newest_ep_index":"2","pub_time":1495641600,"season_id":6187,"season_status":2,"title":"盗墓笔记之沙海 第二季","total_count":-1,"watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/6bf4ed92c56f6d9cb51d8e88f8eb8c599476b47b.jpg","favourites":"20689","is_auto":0,"is_finish":0,"is_started":1,"last_time":1496282400,"newest_ep_index":"23","pub_time":1482940800,"season_id":6022,"season_status":2,"title":"菊叔5岁画","total_count":-1,"watching_count":0}]}
         * recommend_jp : {"foot":[{"cover":"http://i0.hdslb.com/bfs/bangumi/f57423fba6a308193cd4bf252c41b10ba9c91b62.jpg","cursor":1496102400307,"desc":"房东是个可爱的高中生！","id":4850,"link":"http://bangumi.bilibili.com/anime/3116","onDt":"2017-05-30 08:00:00","title":"【泡面档】房东妹子青春期！"}],"recommend":[{"cover":"http://i0.hdslb.com/bfs/bangumi/8aa87787edd29b84c4091e8e483ae48422b7bbd0.jpg","favourites":"926481","is_auto":0,"is_finish":0,"is_started":1,"last_time":1496365202,"newest_ep_index":"9","pub_time":1491494400,"season_id":5996,"season_status":2,"title":"恋爱暴君","total_count":12,"watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/6fccd08f6f747343c2d3bbcd54e4912a9fa51418.jpg","favourites":"156031","is_auto":0,"is_finish":0,"is_started":1,"last_time":1496341680,"newest_ep_index":"9","pub_time":1491494400,"season_id":5991,"season_status":2,"title":"青春歌舞伎","total_count":12,"watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/5ce66f68d54052e3a305524b9b06a76977e974fc.jpg","favourites":"688042","is_auto":0,"is_finish":0,"is_started":1,"last_time":1496335080,"newest_ep_index":"8","pub_time":1491408000,"season_id":5989,"season_status":2,"title":"月色真美","total_count":12,"watching_count":0}]}
         */

        private RecommendCnBean recommend_cn;
        private RecommendJpBean recommend_jp;
        private List<AdBean> ad;

        public RecommendCnBean getRecommend_cn() {
            return recommend_cn;
        }

        public void setRecommend_cn(RecommendCnBean recommend_cn) {
            this.recommend_cn = recommend_cn;
        }

        public RecommendJpBean getRecommend_jp() {
            return recommend_jp;
        }

        public void setRecommend_jp(RecommendJpBean recommend_jp) {
            this.recommend_jp = recommend_jp;
        }

        public List<AdBean> getAd() {
            return ad;
        }

        public void setAd(List<AdBean> ad) {
            this.ad = ad;
        }

        public static class RecommendCnBean {
            private List<FootBean> foot;
            private List<RecommendBean> recommend;

            public List<FootBean> getFoot() {
                return foot;
            }

            public void setFoot(List<FootBean> foot) {
                this.foot = foot;
            }

            public List<RecommendBean> getRecommend() {
                return recommend;
            }

            public void setRecommend(List<RecommendBean> recommend) {
                this.recommend = recommend;
            }

            public static class FootBean {
                /**
                 * cover : http://i0.hdslb.com/bfs/bangumi/cdd184ce6e51aefd7055103d54753fd63d47e061.jpg
                 * cursor : 1496246400706
                 * desc : 叶峰要玩自己的音乐，楚天歌想让麦云洁另眼相看，盖世爱要出风头，石小松要当一流鼓手，四个男孩组成了乐队，感受到青春与音乐的魅力。
                 * id : 5046
                 * is_new : 1
                 * link : http://bangumi.bilibili.com/anime/2169
                 * onDt : 2017-06-01 00:00:00
                 * title : 六一快乐•童年的《我为歌狂》
                 */

                private String cover;
                private long cursor;
                private String desc;
                private int id;
                private int is_new;
                private String link;
                private String onDt;
                private String title;

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public long getCursor() {
                    return cursor;
                }

                public void setCursor(long cursor) {
                    this.cursor = cursor;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getIs_new() {
                    return is_new;
                }

                public void setIs_new(int is_new) {
                    this.is_new = is_new;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getOnDt() {
                    return onDt;
                }

                public void setOnDt(String onDt) {
                    this.onDt = onDt;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class RecommendBean {
                /**
                 * cover : http://i0.hdslb.com/bfs/bangumi/186560d36c229efd888ef959f796d58abd2fd685.jpg
                 * favourites : 18056
                 * is_auto : 0
                 * is_finish : 0
                 * is_started : 1
                 * last_time : 1496318400
                 * newest_ep_index : 41
                 * pub_time : 1469116800
                 * season_id : 6052
                 * season_status : 2
                 * title : 叫我僵小鱼 日常篇
                 * total_count : -1
                 * watching_count : 0
                 */

                private String cover;
                private String favourites;
                private int is_auto;
                private int is_finish;
                private int is_started;
                private int last_time;
                private String newest_ep_index;
                private int pub_time;
                private int season_id;
                private int season_status;
                private String title;
                private int total_count;
                private int watching_count;

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getFavourites() {
                    return favourites;
                }

                public void setFavourites(String favourites) {
                    this.favourites = favourites;
                }

                public int getIs_auto() {
                    return is_auto;
                }

                public void setIs_auto(int is_auto) {
                    this.is_auto = is_auto;
                }

                public int getIs_finish() {
                    return is_finish;
                }

                public void setIs_finish(int is_finish) {
                    this.is_finish = is_finish;
                }

                public int getIs_started() {
                    return is_started;
                }

                public void setIs_started(int is_started) {
                    this.is_started = is_started;
                }

                public int getLast_time() {
                    return last_time;
                }

                public void setLast_time(int last_time) {
                    this.last_time = last_time;
                }

                public String getNewest_ep_index() {
                    return newest_ep_index;
                }

                public void setNewest_ep_index(String newest_ep_index) {
                    this.newest_ep_index = newest_ep_index;
                }

                public int getPub_time() {
                    return pub_time;
                }

                public void setPub_time(int pub_time) {
                    this.pub_time = pub_time;
                }

                public int getSeason_id() {
                    return season_id;
                }

                public void setSeason_id(int season_id) {
                    this.season_id = season_id;
                }

                public int getSeason_status() {
                    return season_status;
                }

                public void setSeason_status(int season_status) {
                    this.season_status = season_status;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getTotal_count() {
                    return total_count;
                }

                public void setTotal_count(int total_count) {
                    this.total_count = total_count;
                }

                public int getWatching_count() {
                    return watching_count;
                }

                public void setWatching_count(int watching_count) {
                    this.watching_count = watching_count;
                }
            }
        }

        public static class RecommendJpBean {
            private List<FootBeanX> foot;
            private List<RecommendBeanX> recommend;

            public List<FootBeanX> getFoot() {
                return foot;
            }

            public void setFoot(List<FootBeanX> foot) {
                this.foot = foot;
            }

            public List<RecommendBeanX> getRecommend() {
                return recommend;
            }

            public void setRecommend(List<RecommendBeanX> recommend) {
                this.recommend = recommend;
            }

            public static class FootBeanX {
                /**
                 * cover : http://i0.hdslb.com/bfs/bangumi/f57423fba6a308193cd4bf252c41b10ba9c91b62.jpg
                 * cursor : 1496102400307
                 * desc : 房东是个可爱的高中生！
                 * id : 4850
                 * link : http://bangumi.bilibili.com/anime/3116
                 * onDt : 2017-05-30 08:00:00
                 * title : 【泡面档】房东妹子青春期！
                 */

                private String cover;
                private long cursor;
                private String desc;
                private int id;
                public int is_new;
                private String link;
                private String onDt;
                private String title;

                public int getIs_new() {
                    return is_new;
                }

                public void setIs_new(int is_new) {
                    this.is_new = is_new;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public long getCursor() {
                    return cursor;
                }

                public void setCursor(long cursor) {
                    this.cursor = cursor;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getOnDt() {
                    return onDt;
                }

                public void setOnDt(String onDt) {
                    this.onDt = onDt;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class RecommendBeanX {
                /**
                 * cover : http://i0.hdslb.com/bfs/bangumi/8aa87787edd29b84c4091e8e483ae48422b7bbd0.jpg
                 * favourites : 926481
                 * is_auto : 0
                 * is_finish : 0
                 * is_started : 1
                 * last_time : 1496365202
                 * newest_ep_index : 9
                 * pub_time : 1491494400
                 * season_id : 5996
                 * season_status : 2
                 * title : 恋爱暴君
                 * total_count : 12
                 * watching_count : 0
                 */

                private String cover;
                private String favourites;
                private int is_auto;
                private int is_finish;
                private int is_started;
                private int last_time;
                private String newest_ep_index;
                private int pub_time;
                private int season_id;
                private int season_status;
                private String title;
                private int total_count;
                private int watching_count;

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getFavourites() {
                    return favourites;
                }

                public void setFavourites(String favourites) {
                    this.favourites = favourites;
                }

                public int getIs_auto() {
                    return is_auto;
                }

                public void setIs_auto(int is_auto) {
                    this.is_auto = is_auto;
                }

                public int getIs_finish() {
                    return is_finish;
                }

                public void setIs_finish(int is_finish) {
                    this.is_finish = is_finish;
                }

                public int getIs_started() {
                    return is_started;
                }

                public void setIs_started(int is_started) {
                    this.is_started = is_started;
                }

                public int getLast_time() {
                    return last_time;
                }

                public void setLast_time(int last_time) {
                    this.last_time = last_time;
                }

                public String getNewest_ep_index() {
                    return newest_ep_index;
                }

                public void setNewest_ep_index(String newest_ep_index) {
                    this.newest_ep_index = newest_ep_index;
                }

                public int getPub_time() {
                    return pub_time;
                }

                public void setPub_time(int pub_time) {
                    this.pub_time = pub_time;
                }

                public int getSeason_id() {
                    return season_id;
                }

                public void setSeason_id(int season_id) {
                    this.season_id = season_id;
                }

                public int getSeason_status() {
                    return season_status;
                }

                public void setSeason_status(int season_status) {
                    this.season_status = season_status;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getTotal_count() {
                    return total_count;
                }

                public void setTotal_count(int total_count) {
                    this.total_count = total_count;
                }

                public int getWatching_count() {
                    return watching_count;
                }

                public void setWatching_count(int watching_count) {
                    this.watching_count = watching_count;
                }
            }
        }

    public static class AdBean {
        /**
         * img : http://i0.hdslb.com/bfs/bangumi/095df0181442362ac1bc445d7f542eb6bf66f7da.jpg
         * index : 1
         * link : http://bangumi.bilibili.com/anime/5997
         * title : 黄漫老师
         */

        public String img;
        public int index;
        public String link;
        public String title;
    }

    }
