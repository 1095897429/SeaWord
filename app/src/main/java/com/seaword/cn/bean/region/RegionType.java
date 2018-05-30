package com.seaword.cn.bean.region;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zl on 2018/5/30.
 * 首页分区的上面的数据 -- 每个实体类中包含children集合
 */

public class RegionType {
        /**
         * tid : 65537
         * reid : 0
         * name : 直播
         * logo :
         * goto :
         * param :
         * children : [{"tid":12,"reid":13,"name":"手游直播","logo":"http://i0.hdslb.com/bfs/live/9c7dad347d8bc25e56a53e9c6f65526ce8161065.jpg","goto":"","param":""},{"tid":8,"reid":13,"name":"萌宅推荐","logo":"http://i0.hdslb.com/u_user/18ad593e4b2f90b233f817e028bee71d.png","goto":"","param":""},{"tid":9,"reid":13,"name":"绘画专区","logo":"http://i0.hdslb.com/u_user/405774aed11d0538a3548109a598fd80.png","goto":"","param":""},{"tid":3,"reid":13,"name":"网络游戏","logo":"","goto":"","param":""},{"tid":1,"reid":13,"name":"单机联机","logo":"http://i0.hdslb.com/u_user/a78fa47e8f25772d51db1a19fe8b310f.png","goto":"","param":""},{"tid":4,"reid":13,"name":"电子竞技","logo":"http://i0.hdslb.com/u_user/a78fa47e8f25772d51db1a19fe8b310f.png","goto":"","param":""},{"tid":10,"reid":13,"name":"唱见舞见","logo":"http://i0.hdslb.com/u_user/a78fa47e8f25772d51db1a19fe8b310f.png","goto":"","param":""},{"tid":6,"reid":13,"name":"生活娱乐","logo":"http://i0.hdslb.com/u_user/a78fa47e8f25772d51db1a19fe8b310f.png","goto":"","param":""},{"tid":2,"reid":13,"name":"御宅文化","logo":"http://i0.hdslb.com/u_user/a78fa47e8f25772d51db1a19fe8b310f.png","goto":"","param":""},{"tid":7,"reid":13,"name":"放映厅","logo":"http://i0.hdslb.com/u_user/a78fa47e8f25772d51db1a19fe8b310f.png","goto":"","param":""},{"tid":99,"reid":13,"name":"精彩轮播","logo":"http://i0.hdslb.com/u_user/a78fa47e8f25772d51db1a19fe8b310f.png","goto":"","param":""}]
         */

        private int tid;
        private int reid;
        private String name;
        private String logo;
        @SerializedName("goto")
        private String gotoX;
        private String param;
        private List<ChildrenBean> children;

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }

        public int getReid() {
            return reid;
        }

        public void setReid(int reid) {
            this.reid = reid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getGotoX() {
            return gotoX;
        }

        public void setGotoX(String gotoX) {
            this.gotoX = gotoX;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            /**
             * tid : 12
             * reid : 13
             * name : 手游直播
             * logo : http://i0.hdslb.com/bfs/live/9c7dad347d8bc25e56a53e9c6f65526ce8161065.jpg
             * goto :
             * param :
             */

            private int tid;
            private int reid;
            private String name;
            private String logo;
            @SerializedName("goto")
            private String gotoX;
            private String param;

            public int getTid() {
                return tid;
            }

            public void setTid(int tid) {
                this.tid = tid;
            }

            public int getReid() {
                return reid;
            }

            public void setReid(int reid) {
                this.reid = reid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getGotoX() {
                return gotoX;
            }

            public void setGotoX(String gotoX) {
                this.gotoX = gotoX;
            }

            public String getParam() {
                return param;
            }

            public void setParam(String param) {
                this.param = param;
            }
        }
    }

