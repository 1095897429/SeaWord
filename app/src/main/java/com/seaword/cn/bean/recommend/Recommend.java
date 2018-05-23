package com.seaword.cn.bean.recommend;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zl on 2018/5/23.
 *  [{}....{}]第一个代表是广告栏，第二个代表的是推荐商品 -- Recommend是将两个参数都放在一起
 */

public class Recommend {
        /**
         * param : 0
         * goto : banner
         * idx : 1496197869
         * banner_item : [{"id":47954,"title":"生活0531","image":"http://i0.hdslb.com/bfs/archive/0316d456b65201d29c07c3b582c3ac5c48e6cc0d.jpg","hash":"d2ea6b8394d9073665364da3a7dc8ccb","uri":"http://www.bilibili.com/blackboard/topic/activity-S1bVTw8--.html","request_id":"1496197858463","server_type":0,"resource_id":631,"index":1,"cm_mark":0},{"id":0,"title":"碧蓝","image":"https://i0.hdslb.com/bfs/archive/559e3d6b7030dca1bb9ef2f7350b60e6fc649655.jpg","hash":"aed9daa33a476e7a441c24abc4c7dac9","uri":"http://blhx.biligame.com/h5","request_id":"1496197858507q172a18a61a110q206","creative_id":17595,"src_id":703,"is_ad_loc":true,"ad_cb":"CLuJARCtMBiQNyAAKAAwADi/BUIfMTQ5NjE5Nzg1ODUwN3ExNzJhMThhNjFhMTEwcTIwNkjLid7ixStSCeadreW3nuW4gloJ5rWZ5rGf55yBYgbkuK3lm71oAXABeACAAQCIAa8VkgEPMTI1LjEyMC4yMjUuMTc0","client_ip":"125.120.225.174","server_type":1,"resource_id":631,"index":2,"cm_mark":2},{"id":0,"title":"【深夜福利】这TM是要搞事啊！！！","image":"https://i0.hdslb.com/bfs/archive/e0b75b518221b0f59352b169a27cbbc7525b966b.jpg","hash":"aaa89e8e949ac95222e8d5bb591564f8","uri":"bilibili://video/10815497","request_id":"1496197858507q172a18a61a110q206","creative_id":17504,"src_id":704,"is_ad":true,"is_ad_loc":true,"ad_cb":"COCIARCIMBjqNiAAKAAwADjABUIfMTQ5NjE5Nzg1ODUwN3ExNzJhMThhNjFhMTEwcTIwNkjLid7ixStSCeadreW3nuW4gloJ5rWZ5rGf55yBYgbkuK3lm71oAXABeACAAQCIAbgYkgEPMTI1LjEyMC4yMjUuMTc0","click_url":"http://t.cr-nielsen.com/hat?_t=r&type=clk&hat_id=MTM3JjI0MCY4MTAwNTI2NSYxMTI1OTE3JjMxODgwMjQ3JoTy&_z=m&rnd=42547380","client_ip":"125.120.225.174","server_type":1,"resource_id":631,"index":3,"cm_mark":1},{"id":48016,"title":"弹幕问卷","image":"http://i0.hdslb.com/bfs/archive/a2daba1a34d7a19449d5447600ac4005f99d8b27.jpg","hash":"011b676d7a00486edc29510c1f6b1136","uri":"http://www.bilibili.com/blackboard/activity-rynk7IHZ-.html","request_id":"1496197858463","server_type":0,"resource_id":631,"index":4,"cm_mark":0}]
         * title : 明唐——定国篇（上）相遇
         * cover : http://i1.hdslb.com/bfs/archive/0ea061024e4aa81425d9cb84f00f8e76952020f1.jpg
         * uri : bilibili://video/10930244
         * desc : 因明唐CP玩起了剑三
         没上80级就手痒开始玩剑网三视频编辑器
         三天肝出来的视频  希望大家喜欢
         另外  重点是
         求各种段子  各种梗  各种剧情  以对白为主
         只要维持住炮哥的正点的形象就行了
         谢谢大家
         * play : 380
         * danmaku : 17
         * reply : 23
         * favorite : 25
         * coin : 17
         * share : 1
         * tid : 65
         * tname : 网络游戏
         * tag : {"tag_id":1052085,"tag_name":"明唐大法好","count":{"atten":155}}
         * dislike_reasons : [{"reason_id":4,"reason_name":"UP主:秋上君"},{"reason_id":2,"reason_name":"分区:网络游戏"},{"reason_id":3,"reason_name":"标签:明唐大法好"},{"reason_id":1,"reason_name":"不感兴趣"}]
         * ctime : 1496085629
         * duration : 173
         * mid : 28650198
         * name : 秋上君
         * face : http://static.hdslb.com/images/member/noface.gif
         * online : 2772
         * area : 单机联机
         * area_id : 1
         * open : 1496186842
         */

        private String param;
        @SerializedName("goto")
        private String gotoX;
        private int idx;
        private String title;
        private String cover;
        private String uri;
        private String desc;
        private int play;
        private int danmaku;
        private int reply;
        private int favorite;
        private int coin;
        private int share;
        private int tid;
        private String tname;
        private TagBean tag;
        private int ctime;
        private int duration;
        private int mid;
        private String name;
        private String face;
        private int online;
        private String area;
        private int area_id;
        private int open;
        private List<BannerItemBean> banner_item;
        private List<DislikeReasonsBean> dislike_reasons;

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

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
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

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getPlay() {
            return play;
        }

        public void setPlay(int play) {
            this.play = play;
        }

        public int getDanmaku() {
            return danmaku;
        }

        public void setDanmaku(int danmaku) {
            this.danmaku = danmaku;
        }

        public int getReply() {
            return reply;
        }

        public void setReply(int reply) {
            this.reply = reply;
        }

        public int getFavorite() {
            return favorite;
        }

        public void setFavorite(int favorite) {
            this.favorite = favorite;
        }

        public int getCoin() {
            return coin;
        }

        public void setCoin(int coin) {
            this.coin = coin;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public TagBean getTag() {
            return tag;
        }

        public void setTag(TagBean tag) {
            this.tag = tag;
        }

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public int getOnline() {
            return online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public int getOpen() {
            return open;
        }

        public void setOpen(int open) {
            this.open = open;
        }

        public List<BannerItemBean> getBanner_item() {
            return banner_item;
        }

        public void setBanner_item(List<BannerItemBean> banner_item) {
            this.banner_item = banner_item;
        }

        public List<DislikeReasonsBean> getDislike_reasons() {
            return dislike_reasons;
        }

        public void setDislike_reasons(List<DislikeReasonsBean> dislike_reasons) {
            this.dislike_reasons = dislike_reasons;
        }

        public static class TagBean {
            /**
             * tag_id : 1052085
             * tag_name : 明唐大法好
             * count : {"atten":155}
             */

            private int tag_id;
            private String tag_name;
            private CountBean count;

            public int getTag_id() {
                return tag_id;
            }

            public void setTag_id(int tag_id) {
                this.tag_id = tag_id;
            }

            public String getTag_name() {
                return tag_name;
            }

            public void setTag_name(String tag_name) {
                this.tag_name = tag_name;
            }

            public CountBean getCount() {
                return count;
            }

            public void setCount(CountBean count) {
                this.count = count;
            }

            public static class CountBean {
                /**
                 * atten : 155
                 */

                private int atten;

                public int getAtten() {
                    return atten;
                }

                public void setAtten(int atten) {
                    this.atten = atten;
                }
            }
        }

        public static class BannerItemBean {
            /**
             * id : 47954
             * title : 生活0531
             * image : http://i0.hdslb.com/bfs/archive/0316d456b65201d29c07c3b582c3ac5c48e6cc0d.jpg
             * hash : d2ea6b8394d9073665364da3a7dc8ccb
             * uri : http://www.bilibili.com/blackboard/topic/activity-S1bVTw8--.html
             * request_id : 1496197858463
             * server_type : 0
             * resource_id : 631
             * index : 1
             * cm_mark : 0
             * creative_id : 17595
             * src_id : 703
             * is_ad_loc : true
             * ad_cb : CLuJARCtMBiQNyAAKAAwADi/BUIfMTQ5NjE5Nzg1ODUwN3ExNzJhMThhNjFhMTEwcTIwNkjLid7ixStSCeadreW3nuW4gloJ5rWZ5rGf55yBYgbkuK3lm71oAXABeACAAQCIAa8VkgEPMTI1LjEyMC4yMjUuMTc0
             * client_ip : 125.120.225.174
             * is_ad : true
             * click_url : http://t.cr-nielsen.com/hat?_t=r&type=clk&hat_id=MTM3JjI0MCY4MTAwNTI2NSYxMTI1OTE3JjMxODgwMjQ3JoTy&_z=m&rnd=42547380
             */

            private int id;
            private String title;
            private String image;
            private String hash;
            private String uri;
            private String request_id;
            private int server_type;
            private int resource_id;
            private int index;
            private int cm_mark;
            private int creative_id;
            private int src_id;
            private boolean is_ad_loc;
            private String ad_cb;
            private String client_ip;
            private boolean is_ad;
            private String click_url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getHash() {
                return hash;
            }

            public void setHash(String hash) {
                this.hash = hash;
            }

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }

            public String getRequest_id() {
                return request_id;
            }

            public void setRequest_id(String request_id) {
                this.request_id = request_id;
            }

            public int getServer_type() {
                return server_type;
            }

            public void setServer_type(int server_type) {
                this.server_type = server_type;
            }

            public int getResource_id() {
                return resource_id;
            }

            public void setResource_id(int resource_id) {
                this.resource_id = resource_id;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public int getCm_mark() {
                return cm_mark;
            }

            public void setCm_mark(int cm_mark) {
                this.cm_mark = cm_mark;
            }

            public int getCreative_id() {
                return creative_id;
            }

            public void setCreative_id(int creative_id) {
                this.creative_id = creative_id;
            }

            public int getSrc_id() {
                return src_id;
            }

            public void setSrc_id(int src_id) {
                this.src_id = src_id;
            }

            public boolean isIs_ad_loc() {
                return is_ad_loc;
            }

            public void setIs_ad_loc(boolean is_ad_loc) {
                this.is_ad_loc = is_ad_loc;
            }

            public String getAd_cb() {
                return ad_cb;
            }

            public void setAd_cb(String ad_cb) {
                this.ad_cb = ad_cb;
            }

            public String getClient_ip() {
                return client_ip;
            }

            public void setClient_ip(String client_ip) {
                this.client_ip = client_ip;
            }

            public boolean isIs_ad() {
                return is_ad;
            }

            public void setIs_ad(boolean is_ad) {
                this.is_ad = is_ad;
            }

            public String getClick_url() {
                return click_url;
            }

            public void setClick_url(String click_url) {
                this.click_url = click_url;
            }
        }

        public static class DislikeReasonsBean {
            /**
             * reason_id : 4
             * reason_name : UP主:秋上君
             */

            private int reason_id;
            private String reason_name;

            public int getReason_id() {
                return reason_id;
            }

            public void setReason_id(int reason_id) {
                this.reason_id = reason_id;
            }

            public String getReason_name() {
                return reason_name;
            }

            public void setReason_name(String reason_name) {
                this.reason_name = reason_name;
            }
        }
}
