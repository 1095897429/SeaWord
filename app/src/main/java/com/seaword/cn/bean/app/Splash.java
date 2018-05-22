package com.seaword.cn.bean.app;

import java.util.List;

/**
 * Created by zl on 2018/5/21.
 */

public class Splash {

    /***
     * {
     "code": 0,
     "data": [{
     "id": 1275,
     "type": 1,
     "animate": 1,
     "duration": 3,
     "start_time": 1526832000,
     "end_time": 1526918340,
     "thumb": "http://i0.hdslb.com/bfs/archive/03de5f19271cfa1544c4cad44da7bdc793f85d46.jpg",
     "hash": "77f53b7ff594abbe274ac6000ad6d11e",
     "times": 5,
     "skip": 1,
     "uri": "http://b23.tv/QbyaUb"
     }, {
     "id": 1272,
     "type": 1,
     "animate": 1,
     "duration": 3,
     "start_time": 1526659200,
     "end_time": 1526745540,
     "thumb": "http://i0.hdslb.com/bfs/archive/9d5a6a9c9921de9e7d29a761fdd0691bff6cec8d.jpg",
     "hash": "7ac67d9535f95e11484c5fa16a63874c",
     "times": 5,
     "skip": 1,
     "uri": "https://www.bilibili.com/blackboard/topic/activity-H1pnRrK0M.html"
     }, {
     "id": 1269,
     "type": 1,
     "animate": 1,
     "duration": 3,
     "start_time": 1526572800,
     "end_time": 1526659140,
     "thumb": "http://i0.hdslb.com/bfs/archive/4fa5b06954b7d6d08e11bfcd671987d186753666.jpg",
     "hash": "a30c9ec10238766e3f5bcd47f758e4cb",
     "times": 5,
     "skip": 1,
     "uri": "https://www.bilibili.com/bangumi/play/ss6402/"
     }, {
     "id": 205,
     "type": 4,
     "animate": 1,
     "duration": 3,
     "start_time": 1479972295,
     "end_time": 1480490696,
     "thumb": "http://i0.hdslb.com/bfs/archive/caa9071bd04dd44f6802558213dc6ddd1ffe8709.png",
     "hash": "1de9413c1662624ea98c4b311c6f67c4",
     "times": 1,
     "skip": 0,
     "uri": ""
     }],
     "message": "0",
     "ver": "53787724998842327442018275"
     }
     */

    /**
     * code : 0
     * data : [{"id":1275,"type":1,"animate":1,"duration":3,"start_time":1526832000,"end_time":1526918340,"thumb":"http://i0.hdslb.com/bfs/archive/03de5f19271cfa1544c4cad44da7bdc793f85d46.jpg","hash":"77f53b7ff594abbe274ac6000ad6d11e","times":5,"skip":1,"uri":"http://b23.tv/QbyaUb"},{"id":1272,"type":1,"animate":1,"duration":3,"start_time":1526659200,"end_time":1526745540,"thumb":"http://i0.hdslb.com/bfs/archive/9d5a6a9c9921de9e7d29a761fdd0691bff6cec8d.jpg","hash":"7ac67d9535f95e11484c5fa16a63874c","times":5,"skip":1,"uri":"https://www.bilibili.com/blackboard/topic/activity-H1pnRrK0M.html"},{"id":1269,"type":1,"animate":1,"duration":3,"start_time":1526572800,"end_time":1526659140,"thumb":"http://i0.hdslb.com/bfs/archive/4fa5b06954b7d6d08e11bfcd671987d186753666.jpg","hash":"a30c9ec10238766e3f5bcd47f758e4cb","times":5,"skip":1,"uri":"https://www.bilibili.com/bangumi/play/ss6402/"},{"id":205,"type":4,"animate":1,"duration":3,"start_time":1479972295,"end_time":1480490696,"thumb":"http://i0.hdslb.com/bfs/archive/caa9071bd04dd44f6802558213dc6ddd1ffe8709.png","hash":"1de9413c1662624ea98c4b311c6f67c4","times":1,"skip":0,"uri":""}]
     * message : 0
     * ver : 53787724998842327442018275
     */

    private int code;
    private String message;
    private String ver;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1275
         * type : 1
         * animate : 1
         * duration : 3
         * start_time : 1526832000
         * end_time : 1526918340
         * thumb : http://i0.hdslb.com/bfs/archive/03de5f19271cfa1544c4cad44da7bdc793f85d46.jpg
         * hash : 77f53b7ff594abbe274ac6000ad6d11e
         * times : 5
         * skip : 1
         * uri : http://b23.tv/QbyaUb
         */

        private int id;
        private int type;
        private int animate;
        private int duration;
        private int start_time;
        private int end_time;
        private String thumb;
        private String hash;
        private int times;
        private int skip;
        private String uri;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getAnimate() {
            return animate;
        }

        public void setAnimate(int animate) {
            this.animate = animate;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public int getSkip() {
            return skip;
        }

        public void setSkip(int skip) {
            this.skip = skip;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }
    }


}
