package com.seaword.cn.bean.chase;

import java.util.List;

/**
 * Created by zl on 2018/5/29.
 * 追番部分 -- 我的追番
 * FollowsBean -- 每个实体Bean
 */

public class ChaseBangumi {

        /**
         * delay_notice : []
         * follow_count : 8
         * follows : [{"brief":"神秘瑰丽的妖灵世界，奇奥无穷的时空妖灵之书，聂离追寻着世界的真相。美丽温柔的叶紫芸、倔强高傲的肖凝儿...","cover":"http://i0.hdslb.com/bfs/bangumi/c57c4f3a68a3df841cbbb534c407081b431ef2f7.jpg","ed_jump":5,"is_finish":"0","is_started":1,"limitGroupId":317,"new_ep":{"episode_id":"107005","episode_status":2,"index":"7","update_time":"2017-05-30 12:00:00.0"},"newest_ep_id":"107005","newest_ep_index":"7","pub_time":"2017-05-09 12:00:00","season_id":"6159","season_status":2,"squareCover":"http://i0.hdslb.com/bfs/bangumi/427394ad2c27c23d90acd1b63fa17dde110fd24a.jpg","title":"妖神记","total_count":"40","trailerAid":"-1","user_season":{"attention":"1","bp":0,"last_ep_id":"105246","last_ep_index":"1","last_time":"44","report_ts":1495900198000},"weekday":"-1"},{"brief":"总北高中自行车竞技部所属的一年级生·小野田坂道，与同年级的今泉俊辅和鸣子章吉、三年级的金城真护、卷岛...","cover":"http://i0.hdslb.com/bfs/bangumi/afcd8b966344aa3e085b19a955c5e721020d6ce1.jpg","ed_jump":5,"is_finish":"0","is_started":1,"limitGroupId":317,"new_ep":{"episode_id":"100449","episode_status":2,"index":"21","update_time":"2017-05-30 09:00:02.0"},"newest_ep_id":"100449","newest_ep_index":"21","pub_time":"2017-01-10 01:35:00","season_id":"5794","season_status":2,"squareCover":"http://i0.hdslb.com/bfs/bangumi/b5a5f41250f4f1b114b478e80053401ee964be1a.jpg","title":"飙速宅男 新世代","total_count":"13","trailerAid":"7325921","user_season":{"attention":"1","bp":0,"last_ep_id":"","last_ep_index":"","last_time":"0","report_ts":0},"weekday":"2"},{"brief":"某日早晨，普通的女子高校生・源内青收到一个神秘包裹。打开后发现里面放了名为FRAME ARMS G...","cover":"http://i0.hdslb.com/bfs/bangumi/2aca983b744cc949e2c3346a876f9f611be9352a.jpg","ed_jump":5,"is_finish":"0","is_started":1,"limitGroupId":317,"new_ep":{"episode_id":"103195","episode_status":2,"index":"9","update_time":"2017-05-30 01:30:00.0"},"newest_ep_id":"103195","newest_ep_index":"9","pub_time":"2017-04-04 01:30:00","season_id":"5994","season_status":2,"squareCover":"http://i0.hdslb.com/bfs/bangumi/e7ad94b23f3060bfa0018115478785ca5f37f234.jpg","title":"机甲少女 FRAME ARMS GIRL","total_count":"12","trailerAid":"-1","user_season":{"attention":"1","bp":0,"last_ep_id":"","last_ep_index":"","last_time":"0","report_ts":0},"weekday":"2"}]
         * update_count : 0
         */

        private int follow_count;
        private int update_count;
        private List<?> delay_notice;
        private List<FollowsBean> follows;

        public int getFollow_count() {
            return follow_count;
        }

        public void setFollow_count(int follow_count) {
            this.follow_count = follow_count;
        }

        public int getUpdate_count() {
            return update_count;
        }

        public void setUpdate_count(int update_count) {
            this.update_count = update_count;
        }

        public List<?> getDelay_notice() {
            return delay_notice;
        }

        public void setDelay_notice(List<?> delay_notice) {
            this.delay_notice = delay_notice;
        }

        public List<FollowsBean> getFollows() {
            return follows;
        }

        public void setFollows(List<FollowsBean> follows) {
            this.follows = follows;
        }

        public static class FollowsBean {
            /**
             * brief : 神秘瑰丽的妖灵世界，奇奥无穷的时空妖灵之书，聂离追寻着世界的真相。美丽温柔的叶紫芸、倔强高傲的肖凝儿...
             * cover : http://i0.hdslb.com/bfs/bangumi/c57c4f3a68a3df841cbbb534c407081b431ef2f7.jpg
             * ed_jump : 5
             * is_finish : 0
             * is_started : 1
             * limitGroupId : 317
             * new_ep : {"episode_id":"107005","episode_status":2,"index":"7","update_time":"2017-05-30 12:00:00.0"}
             * newest_ep_id : 107005
             * newest_ep_index : 7
             * pub_time : 2017-05-09 12:00:00
             * season_id : 6159
             * season_status : 2
             * squareCover : http://i0.hdslb.com/bfs/bangumi/427394ad2c27c23d90acd1b63fa17dde110fd24a.jpg
             * title : 妖神记
             * total_count : 40
             * trailerAid : -1
             * user_season : {"attention":"1","bp":0,"last_ep_id":"105246","last_ep_index":"1","last_time":"44","report_ts":1495900198000}
             * weekday : -1
             */

            private String brief;
            private String cover;
            private int ed_jump;
            private String is_finish;
            private int is_started;
            private int limitGroupId;
            private NewEpBean new_ep;
            private String newest_ep_id;
            private String newest_ep_index;
            private String pub_time;
            private String season_id;
            private int season_status;
            private String squareCover;
            private String title;
            private String total_count;
            private String trailerAid;
            private UserSeasonBean user_season;
            private String weekday;

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public int getEd_jump() {
                return ed_jump;
            }

            public void setEd_jump(int ed_jump) {
                this.ed_jump = ed_jump;
            }

            public String getIs_finish() {
                return is_finish;
            }

            public void setIs_finish(String is_finish) {
                this.is_finish = is_finish;
            }

            public int getIs_started() {
                return is_started;
            }

            public void setIs_started(int is_started) {
                this.is_started = is_started;
            }

            public int getLimitGroupId() {
                return limitGroupId;
            }

            public void setLimitGroupId(int limitGroupId) {
                this.limitGroupId = limitGroupId;
            }

            public NewEpBean getNew_ep() {
                return new_ep;
            }

            public void setNew_ep(NewEpBean new_ep) {
                this.new_ep = new_ep;
            }

            public String getNewest_ep_id() {
                return newest_ep_id;
            }

            public void setNewest_ep_id(String newest_ep_id) {
                this.newest_ep_id = newest_ep_id;
            }

            public String getNewest_ep_index() {
                return newest_ep_index;
            }

            public void setNewest_ep_index(String newest_ep_index) {
                this.newest_ep_index = newest_ep_index;
            }

            public String getPub_time() {
                return pub_time;
            }

            public void setPub_time(String pub_time) {
                this.pub_time = pub_time;
            }

            public String getSeason_id() {
                return season_id;
            }

            public void setSeason_id(String season_id) {
                this.season_id = season_id;
            }

            public int getSeason_status() {
                return season_status;
            }

            public void setSeason_status(int season_status) {
                this.season_status = season_status;
            }

            public String getSquareCover() {
                return squareCover;
            }

            public void setSquareCover(String squareCover) {
                this.squareCover = squareCover;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTotal_count() {
                return total_count;
            }

            public void setTotal_count(String total_count) {
                this.total_count = total_count;
            }

            public String getTrailerAid() {
                return trailerAid;
            }

            public void setTrailerAid(String trailerAid) {
                this.trailerAid = trailerAid;
            }

            public UserSeasonBean getUser_season() {
                return user_season;
            }

            public void setUser_season(UserSeasonBean user_season) {
                this.user_season = user_season;
            }

            public String getWeekday() {
                return weekday;
            }

            public void setWeekday(String weekday) {
                this.weekday = weekday;
            }

            public static class NewEpBean {
                /**
                 * episode_id : 107005
                 * episode_status : 2
                 * index : 7
                 * update_time : 2017-05-30 12:00:00.0
                 */

                private String episode_id;
                private int episode_status;
                private String index;
                private String update_time;

                public String getEpisode_id() {
                    return episode_id;
                }

                public void setEpisode_id(String episode_id) {
                    this.episode_id = episode_id;
                }

                public int getEpisode_status() {
                    return episode_status;
                }

                public void setEpisode_status(int episode_status) {
                    this.episode_status = episode_status;
                }

                public String getIndex() {
                    return index;
                }

                public void setIndex(String index) {
                    this.index = index;
                }

                public String getUpdate_time() {
                    return update_time;
                }

                public void setUpdate_time(String update_time) {
                    this.update_time = update_time;
                }
            }

            public static class UserSeasonBean {
                /**
                 * attention : 1
                 * bp : 0
                 * last_ep_id : 105246
                 * last_ep_index : 1
                 * last_time : 44
                 * report_ts : 1495900198000
                 */

                private String attention;
                private int bp;
                private String last_ep_id;
                private String last_ep_index;
                private String last_time;
                private long report_ts;

                public String getAttention() {
                    return attention;
                }

                public void setAttention(String attention) {
                    this.attention = attention;
                }

                public int getBp() {
                    return bp;
                }

                public void setBp(int bp) {
                    this.bp = bp;
                }

                public String getLast_ep_id() {
                    return last_ep_id;
                }

                public void setLast_ep_id(String last_ep_id) {
                    this.last_ep_id = last_ep_id;
                }

                public String getLast_ep_index() {
                    return last_ep_index;
                }

                public void setLast_ep_index(String last_ep_index) {
                    this.last_ep_index = last_ep_index;
                }

                public String getLast_time() {
                    return last_time;
                }

                public void setLast_time(String last_time) {
                    this.last_time = last_time;
                }

                public long getReport_ts() {
                    return report_ts;
                }

                public void setReport_ts(long report_ts) {
                    this.report_ts = report_ts;
                }
            }
        }
    }

