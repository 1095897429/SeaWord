package com.seaword.cn.bean.live;

import java.util.List;

/**
 * Created by zl on 2018/5/25.
 * 直播分区 [bannner + entranceIcons + partitions + navigator] -- 个人感觉只需[bannner  + partitions ]
 */

public class LivePartition {

        private List<BannerBean> banner;
        private List<EntranceIconsBean> entranceIcons;
        private List<PartitionsBean> partitions;
        private List<NavigatorBean> navigator;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<EntranceIconsBean> getEntranceIcons() {
            return entranceIcons;
        }

        public void setEntranceIcons(List<EntranceIconsBean> entranceIcons) {
            this.entranceIcons = entranceIcons;
        }

        public List<PartitionsBean> getPartitions() {
            return partitions;
        }

        public void setPartitions(List<PartitionsBean> partitions) {
            this.partitions = partitions;
        }

        public List<NavigatorBean> getNavigator() {
            return navigator;
        }

        public void setNavigator(List<NavigatorBean> navigator) {
            this.navigator = navigator;
        }

        public static class BannerBean {
            /**
             * title : 绝地求生中国地区职业邀请赛！
             * img : http://i0.hdslb.com/bfs/vc/5a4179ed673b39b5ab9e47d542db1453a1d4d346.jpg
             * link : https://link.bilibili.com/p/eden/news#/newsdetail?id=509
             */

            private String title;
            private String img;
            private String link;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }

        public static class EntranceIconsBean {
            /**
             * id : 9
             * name : 绘画专区
             * entrance_icon : {"src":"http://static.hdslb.com/live-static/images/mobile/android/big/xxhdpi/9_big.png?20171116172700","height":"132","width":"132"}
             */

            private int id;
            private String name;
            private EntranceIconBean entrance_icon;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public EntranceIconBean getEntrance_icon() {
                return entrance_icon;
            }

            public void setEntrance_icon(EntranceIconBean entrance_icon) {
                this.entrance_icon = entrance_icon;
            }

            public static class EntranceIconBean {
                /**
                 * src : http://static.hdslb.com/live-static/images/mobile/android/big/xxhdpi/9_big.png?20171116172700
                 * height : 132
                 * width : 132
                 */

                private String src;
                private String height;
                private String width;

                public String getSrc() {
                    return src;
                }

                public void setSrc(String src) {
                    this.src = src;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getWidth() {
                    return width;
                }

                public void setWidth(String width) {
                    this.width = width;
                }
            }
        }

        public static class PartitionsBean {
            /**
             * partition : {"id":9,"name":"绘画专区","area":"draw","sub_icon":{"src":"http://static.hdslb.com/live-static/images/mobile/android/small/xxhdpi/9.png?20171116172700","height":"63","width":"63"},"count":62}
             * lives : [{"owner":{"face":"http://i1.hdslb.com/bfs/face/aa32ed01421756cd15c80a3c980cbef5c5c320b5.jpg","mid":11174717,"name":"云深不知触"},"cover":{"src":"http://i0.hdslb.com/bfs/live/c4eaac98f88a79289781bb5293a45bfe25a936ca.jpg","height":180,"width":320},"title":"赶个人设单","room_id":3970454,"check_version":0,"online":624,"area":"绘画专区","area_id":9,"playurl":"http://qn.live-play.acgvideo.com/live-qn/886922/live_11174717_9982260.flv?wsSecret=34bf1ce1143be007243ec4d51b399d6b&wsTime=1527216302","accept_quality":"4","broadcast_type":0,"is_tv":0},{"owner":{"face":"http://i1.hdslb.com/bfs/face/a8e4252d0002f20b3b5c13b4d515be47ab9cd37a.jpg","mid":4300133,"name":"秋秋秋夜璇"},"cover":{"src":"http://i0.hdslb.com/bfs/live/a8e799d5716b9b98e5f95dc2b5cba8c4a665d0db.jpg","height":180,"width":320},"title":"失踪人口回归(๑\u2022́ωก̀๑)","room_id":1858891,"check_version":0,"online":87,"area":"绘画专区","area_id":9,"playurl":"http://js.live-play.acgvideo.com/live-js/388761/live_4300133_2868891.flv?wsSecret=fb406604402ef6f7d7c89d75470a0366&wsTime=1527216302","accept_quality":"4","broadcast_type":0,"is_tv":0},{"owner":{"face":"http://i1.hdslb.com/bfs/face/315201ebb5bf02238a245f2c9699bb8712358752.jpg","mid":3619550,"name":"虚元"},"cover":{"src":"http://i0.hdslb.com/bfs/live/6213e7c81c98c597d9d99534fffdfe7e44e0cf65.jpg","height":180,"width":320},"title":"【虚元】画稿稿~撑不了多久~","room_id":30799,"check_version":0,"online":977,"area":"绘画专区","area_id":9,"playurl":"http://txy.live-play.acgvideo.com/live-txy/747055/live_3619550_332_c521e483.flv?wsSecret=74cbbbdbf7e0d480124c305e16f1656a&wsTime=1527216302","accept_quality":"4","broadcast_type":0,"is_tv":0},{"owner":{"face":"http://i2.hdslb.com/bfs/face/2e2508de8523f789209de08de1e9407c47819d89.jpg","mid":14306,"name":"恋恋"},"cover":{"src":"http://i0.hdslb.com/bfs/live/487546bd76859f7731812ae4e3d7628f783f87fd.jpg","height":180,"width":320},"title":"东方立绘0.0","room_id":3259503,"check_version":0,"online":330,"area":"绘画专区","area_id":9,"playurl":"http://js.live-play.acgvideo.com/live-js/519630/live_14306_2322972.flv?wsSecret=568bcbe60f3c314e48b1b5be2840e387&wsTime=1527216302","accept_quality":"4","broadcast_type":0,"is_tv":0},{"owner":{"face":"http://i1.hdslb.com/bfs/face/2b8067d11ff019e8bf50f5268882fa82111d1c0a.jpg","mid":32684068,"name":"絵好きな日語菌"},"cover":{"src":"http://i0.hdslb.com/bfs/live/0ed4d8243f03057a401fbcc7a077c928793756b1.jpg","height":180,"width":320},"title":"开始一年的绘画修行！","room_id":5498135,"check_version":0,"online":15,"area":"绘画专区","area_id":9,"playurl":"http://qn.live-play.acgvideo.com/live-qn/915492/live_32684068_1587914.flv?wsSecret=ef6e95eb6b7aadb739ebd7a32630feb2&wsTime=1527216302","accept_quality":"4","broadcast_type":0,"is_tv":0},{"owner":{"face":"http://static.hdslb.com/images/member/noface.gif","mid":317352321,"name":"末摘花黑冢"},"cover":{"src":"http://i0.hdslb.com/bfs/live/d03ce5c5bd959e24ccc4911840fda42f47e0885f.jpg","height":180,"width":320},"title":"练习日常","room_id":10616575,"check_version":0,"online":860,"area":"绘画专区","area_id":9,"playurl":"http://qn.live-play.acgvideo.com/live-qn/944965/live_317352321_5797311.flv?wsSecret=ace8dd78e36ef225bbd9e870918eaa33&wsTime=1527216302","accept_quality":"4","broadcast_type":0,"is_tv":0},{"owner":{"face":"http://i2.hdslb.com/bfs/face/1db10d6a376710d858715c1ed0641f8c2a37fcfa.jpg","mid":32039939,"name":"wuli-叶紫紫"},"cover":{"src":"http://i0.hdslb.com/bfs/live/2d9ba70842b9890267f942d25d7f54ba164f2021.jpg","height":180,"width":320},"title":"小姐姐立绘","room_id":337825,"check_version":0,"online":876,"area":"绘画专区","area_id":9,"playurl":"http://qn.live-play.acgvideo.com/live-qn/748186/live_32039939_8227840.flv?wsSecret=2475ee6e6535b3dce752160372bc3273&wsTime=1527216302","accept_quality":"4","broadcast_type":0,"is_tv":0},{"owner":{"face":"http://i1.hdslb.com/bfs/face/96549714663ba5f84061cb830d012a99152498ee.jpg","mid":22540416,"name":"田工头"},"cover":{"src":"http://i0.hdslb.com/bfs/live/31a248e7ffc010dbd13ff4809deb14aeb2fd7679.jpg","height":180,"width":320},"title":"田工头的原画大讲堂","room_id":67983,"check_version":0,"online":10538,"area":"绘画专区","area_id":9,"playurl":"http://qn.live-play.acgvideo.com/live-qn/567675/live_22540416_7090428.flv?wsSecret=6f0af1cc63f5feb2b5ce60145cf7ff7a&wsTime=1527216302","accept_quality":"4","broadcast_type":0,"is_tv":0},{"owner":{"face":"http://i1.hdslb.com/bfs/face/038889f6a44a5a03cbaf390c54d21c301c9f583f.jpg","mid":14427998,"name":"位于爆炸临界点的凯"},"cover":{"src":"http://i0.hdslb.com/bfs/live/694f015a558a191a839a074aa35699a4a148ac17.jpg","height":180,"width":320},"title":"位于爆炸临界点的凯的直播间","room_id":3599128,"check_version":0,"online":1234,"area":"绘画专区","area_id":9,"playurl":"http://js.live-play.acgvideo.com/live-js/196821/live_14427998_4885962.flv?wsSecret=6612ffa9f8b6d91616aeb6036270c5f4&wsTime=1527216302","accept_quality":"4","broadcast_type":0,"is_tv":0},{"owner":{"face":"http://i2.hdslb.com/bfs/face/7ec06b4a04c121cb56b29c7963c283a142ef075f.jpg","mid":40365202,"name":"ivy齐齐"},"cover":{"src":"http://i0.hdslb.com/bfs/live/8bb7fdd65da769b6090511420cbcb0ab148c4535.jpg","height":180,"width":320},"title":"凡秋","room_id":2873610,"check_version":0,"online":55,"area":"绘画专区","area_id":9,"playurl":"http://js.live-play.acgvideo.com/live-js/283910/live_40365202_7728341.flv?wsSecret=a2d48358a0bedceaacf9da489c7a095c&wsTime=1527216302","accept_quality":"4","broadcast_type":0,"is_tv":0}]
             */

            private PartitionBean partition;
            private List<LivesBean> lives;

            public PartitionBean getPartition() {
                return partition;
            }

            public void setPartition(PartitionBean partition) {
                this.partition = partition;
            }

            public List<LivesBean> getLives() {
                return lives;
            }

            public void setLives(List<LivesBean> lives) {
                this.lives = lives;
            }

            public static class PartitionBean {
                /**
                 * id : 9
                 * name : 绘画专区
                 * area : draw
                 * sub_icon : {"src":"http://static.hdslb.com/live-static/images/mobile/android/small/xxhdpi/9.png?20171116172700","height":"63","width":"63"}
                 * count : 62
                 */

                private int id;
                private String name;
                private String area;
                private SubIconBean sub_icon;
                private int count;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
                }

                public SubIconBean getSub_icon() {
                    return sub_icon;
                }

                public void setSub_icon(SubIconBean sub_icon) {
                    this.sub_icon = sub_icon;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public static class SubIconBean {
                    /**
                     * src : http://static.hdslb.com/live-static/images/mobile/android/small/xxhdpi/9.png?20171116172700
                     * height : 63
                     * width : 63
                     */

                    private String src;
                    private String height;
                    private String width;

                    public String getSrc() {
                        return src;
                    }

                    public void setSrc(String src) {
                        this.src = src;
                    }

                    public String getHeight() {
                        return height;
                    }

                    public void setHeight(String height) {
                        this.height = height;
                    }

                    public String getWidth() {
                        return width;
                    }

                    public void setWidth(String width) {
                        this.width = width;
                    }
                }
            }

            public static class LivesBean {
                /**
                 * owner : {"face":"http://i1.hdslb.com/bfs/face/aa32ed01421756cd15c80a3c980cbef5c5c320b5.jpg","mid":11174717,"name":"云深不知触"}
                 * cover : {"src":"http://i0.hdslb.com/bfs/live/c4eaac98f88a79289781bb5293a45bfe25a936ca.jpg","height":180,"width":320}
                 * title : 赶个人设单
                 * room_id : 3970454
                 * check_version : 0
                 * online : 624
                 * area : 绘画专区
                 * area_id : 9
                 * playurl : http://qn.live-play.acgvideo.com/live-qn/886922/live_11174717_9982260.flv?wsSecret=34bf1ce1143be007243ec4d51b399d6b&wsTime=1527216302
                 * accept_quality : 4
                 * broadcast_type : 0
                 * is_tv : 0
                 */

                private OwnerBean owner;
                private CoverBean cover;
                private String title;
                private int room_id;
                private int check_version;
                private int online;
                private String area;
                private int area_id;
                private String playurl;
                private String accept_quality;
                private int broadcast_type;
                private int is_tv;

                public OwnerBean getOwner() {
                    return owner;
                }

                public void setOwner(OwnerBean owner) {
                    this.owner = owner;
                }

                public CoverBean getCover() {
                    return cover;
                }

                public void setCover(CoverBean cover) {
                    this.cover = cover;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getRoom_id() {
                    return room_id;
                }

                public void setRoom_id(int room_id) {
                    this.room_id = room_id;
                }

                public int getCheck_version() {
                    return check_version;
                }

                public void setCheck_version(int check_version) {
                    this.check_version = check_version;
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

                public String getPlayurl() {
                    return playurl;
                }

                public void setPlayurl(String playurl) {
                    this.playurl = playurl;
                }

                public String getAccept_quality() {
                    return accept_quality;
                }

                public void setAccept_quality(String accept_quality) {
                    this.accept_quality = accept_quality;
                }

                public int getBroadcast_type() {
                    return broadcast_type;
                }

                public void setBroadcast_type(int broadcast_type) {
                    this.broadcast_type = broadcast_type;
                }

                public int getIs_tv() {
                    return is_tv;
                }

                public void setIs_tv(int is_tv) {
                    this.is_tv = is_tv;
                }

                public static class OwnerBean {
                    /**
                     * face : http://i1.hdslb.com/bfs/face/aa32ed01421756cd15c80a3c980cbef5c5c320b5.jpg
                     * mid : 11174717
                     * name : 云深不知触
                     */

                    private String face;
                    private int mid;
                    private String name;

                    public String getFace() {
                        return face;
                    }

                    public void setFace(String face) {
                        this.face = face;
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
                }

                public static class CoverBean {
                    /**
                     * src : http://i0.hdslb.com/bfs/live/c4eaac98f88a79289781bb5293a45bfe25a936ca.jpg
                     * height : 180
                     * width : 320
                     */

                    private String src;
                    private int height;
                    private int width;

                    public String getSrc() {
                        return src;
                    }

                    public void setSrc(String src) {
                        this.src = src;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }
                }
            }
        }

        public static class NavigatorBean {
            /**
             * id : 9
             * name : 绘画
             * entrance_icon : {"src":"http://static.hdslb.com/live-static/images/mobile/blink/9_3x.png?20180428172700","height":"132","width":"132"}
             */

            private int id;
            private String name;
            private EntranceIconBeanX entrance_icon;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public EntranceIconBeanX getEntrance_icon() {
                return entrance_icon;
            }

            public void setEntrance_icon(EntranceIconBeanX entrance_icon) {
                this.entrance_icon = entrance_icon;
            }

            public static class EntranceIconBeanX {
                /**
                 * src : http://static.hdslb.com/live-static/images/mobile/blink/9_3x.png?20180428172700
                 * height : 132
                 * width : 132
                 */

                private String src;
                private String height;
                private String width;

                public String getSrc() {
                    return src;
                }

                public void setSrc(String src) {
                    this.src = src;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getWidth() {
                    return width;
                }

                public void setWidth(String width) {
                    this.width = width;
                }
            }
        }
    }

