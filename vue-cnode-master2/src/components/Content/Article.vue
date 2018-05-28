<template >
  <div class="article-detail">
    <div v-show="!isLoading" class="body">
      <div class="article-title">
        <h1><span class="flag" v-show="infos.top || infos.good">{{infos.top ? 'Top' : 'Recommendation'}}</span> <br/>{{infos.title}}</h1>
        <div class="desc">
    <p>Author:<router-link :to="{name:'user', params: {name: infos.author && infos.author.loginname}}">{{infos.author && infos.author.loginname}}
          </router-link><br/>This article comes from: {{types[infos.tab]}}</p>
        </div>
      </div>

      <div v-html="infos.content" class="md"></div>
      <div class="block">

        <el-rate v-model="value1"></el-rate>
      </div>
      <div class="reply">
        <div class="other">
          <div @click="collect" class="collect"><i :class="[isCollected ? 'collected' : 'collected-no']"></i> like </div>
          <br/>
          <div class="total-reply">{{infos.reply_count}} replies</div>
        </div>




        <div class="reply-input">
          <input v-model.trim="replyContent" type="text" placeholder="Please enter the content that you want to comment">
          <button @click="reply('')" type="button">Confirm</button>
        </div>

        <div v-for="(item,index) of replies" class="reply-item">

          <div class="reply-author">
            <div class="reply-avatar">
              <!--<img :src="item.author.avatar_url" alt="">-->
              <!--评论用户头像代码隐藏-->
              <div class="reply-desc">
                <router-link :to="{name: 'user', params: {name: item.author && item.author.loginname}}">{{item.author.loginname}}</router-link>
                <!--•{{changeTime(item.create_at)}}-->
                <!--隐藏评论时间 因为里面有时间（X个月前）-->
                <span @click="currentIndex=index" class="reply-at">Like</span>
               <i @click="ups(index, item.id, item)" :class="[item.ups.indexOf(userInfo.id) !== -1 ? 'ups-yes' : 'ups-no']" class="icon-reply-at">

               </i>
                <span class="ups-count">{{ item.ups.length }}</span>
                <!--赞个数-->
              </div>
            </div>
          </div>

          <div v-html="item.content" class="reply-content"></div>

          <transition name="slide-top">
            <div v-show="currentIndex===index" class="reply-one">
              <input type="text" name="" v-model.trim="replyOneContent" :placeholder="'@' + item.author.loginname">
              <button @click="reply(item.id, item.author.loginname)">Reply</button>
              <button @click="currentIndex=null;replyOneContent=''">Cancel</button>
            </div>
          </transition>


        </div>

      </div>

    </div>


  </div>
</template>

<script>
  export default {
    name: 'articleDetail',
    data() {
      return {
        id: this.$route.params.id,
        infos: {},
        types: { share: 'Share', ask: 'Ask and Answer', job: 'Jobs'},
        oImgs: [],
        isCollected: false,
        replyContent: '',
        replyOneContent: '',
        isReplyOne: false,
        isUps: false,
        flag: true,
        up: 0,
        currentIndex: null, // 回复某个人的 index
        currentUps: null,
        replies: [],
        value1: null
        // oArticleDetail: {}
      }
    },
    created() {
      this.$store.commit('viewArcticle', true);
      this.$store.commit('showInfo', false);
      this.$store.commit('showAsideMenu', false);
      this.axios.get('https://cnodejs.org/api/v1/topic/' + this.id)
        .then(result => result.data.data)
        .then(data => this.infos = data)
        .then(infos => this.replies = infos.replies.reverse())
        .then(() => this.$store.commit('viewArcticle', false))
        .then(() => {
          this.oImgs = document.querySelector('.md').querySelectorAll('img');
          for (let img of this.oImgs) {
            img.onclick = () => location.href = img.src;
          }
        })
    },

    computed: {
      isLoading() {
        return this.$store.state.isLoading;
      },
      ak() {
        return this.$store.state.ak;
      },
      collectTopics() {
        return this.$store.state.collectTopics;
      },
      userInfo() {
        return this.$store.state.userInfo;
      }
    },
    mounted() {
      if (!this.ak) {
        return;
      }
      this.axios.get(`https://cnodejs.org/api/v1/topic_collect/${this.userInfo.loginname}`)
        .then(result => result.data.data)
        .then(collectTopics => {
          this.$store.commit('updateCollect', collectTopics);
          collectTopics.forEach(item => {
            item.id === this.id ? this.isCollected = true : '';
          })
        })

    },
    methods: {
      // toScroll(event) {
      //   this.oArticleDetail = event.target;
      // },
      collect() {
        if (!this.ak) {
          this.$store.commit('showLogin', true);
          return;
        }
        if (!this.isCollected) {
          this.axios.post(`https://cnodejs.org/api/v1/topic_collect/collect`, {
            accesstoken: this.ak,
            topic_id: this.id
          }).then(result => {
            if (result.data.success) {
              this.isCollected = !this.isCollected;
            }
          })
        } else {
          this.axios.post(`https://cnodejs.org/api/v1/topic_collect/de_collect`, {
            accesstoken: this.ak,
            topic_id: this.id
          }).then(result => {
            console.log('result', result);
            if (result.data.success) {
              this.isCollected = !this.isCollected;
            }
          })
        }
      },

      reply(id, name) {
        if (!this.ak) {
          this.$store.commit('showLogin', true);
          return;
        }
        else if (!id) {
          this.axios.post(`https://cnodejs.org/api/v1/topic/${this.id}/replies`, {
            accesstoken: this.ak,
            content: this.replyContent
          }).then(() => {
            this.axios.get('https://cnodejs.org/api/v1/topic/' + this.id)
              .then(result => result.data.data)
              .then(data => this.infos = data)
              .then(infos => this.replies = infos.replies.reverse())
              .then(() => {
                // this.oArticleDetail.scrollTop = this.oArticleDetail.scrollHeight;
                this.replyContent ='';
              })
          })
        } else {
          this.axios.post(`https://cnodejs.org/api/v1/topic/${this.id}/replies`, {
            accesstoken: this.ak,
            content: '@' + name + ' ' + this.replyOneContent,
            reply_id: id
          }).then(() => {
            this.axios.get('https://cnodejs.org/api/v1/topic/' + this.id)
              .then(result => result.data.data)
              .then(data => this.infos = data)
              .then(infos => this.replies = infos.replies.reverse())
              .then(() => {
                // this.oArticleDetail.scrollTop = this.oArticleDetail.scrollHeight;
                this.currentIndex = null;
                this.replyOneContent = ''
              })
          })
        }
      },

      //点赞
      ups(index, upsId, item) {
        if (!this.ak) {
          this.$store.commit('showLogin', true);
          return;
        }
        if (item.author.loginname === this.userInfo.loginname) {
          alert('You can not reply to yourself!' )
          return;
        }
        this.axios.post(`https://cnodejs.org/api/v1/reply/${upsId}/ups`, {accesstoken: this.ak})
          .then(result => {
            if (result.data.success) {
              this.axios.get('https://cnodejs.org/api/v1/topic/' + this.id)
                .then(result => result.data.data)
                .then(data => this.infos = data)
                .then(infos => this.replies = infos.replies.reverse())
            }
          })

      }
    }
  }
</script>

<style lang="scss" scoped>

  .article-detail {
    position: relative;
    z-index: 1;
    height: 100%;

    padding: 18px 50px 20px 50px;
    background-color: ghostwhite;
    /*网页背景颜色*/
    overflow-x: hidden;
    overflow-y: auto;
    .body {
      background-color: white;
      height: 100%;
      /*width: 100%;*/

      border-radius: 20px;
      .article-title {
        width: 100%;
        padding-left: 20px;
        padding-top: 20px;
        padding-bottom: 20px;
        border-bottom: 2px dashed gray;
        h1 {
          font-size: 2.2rem;
          font-family:"Times New Roman",Georgia,Serif;

          .flag {//可以删除，精华和置顶
            background-color: gray;
            padding: 1.7px 4px;
            font-size: 100%;
            color: white;
            border-radius: 10px;
            margin-right: 10px;
          }
        }
        .desc {
          padding-top: 15px;

          p {
            font-size: 85%;
            color: royalblue;

          }
        }
      }
      .md {
        width: 100%;
        overflow: hidden;
      }

      .reply {
        .other {
          margin-top: 20px;
          margin-bottom: 15px;
          padding-left: 10px;
          display: flex;
          justify-content: space-between;
          i {
            display: inline-block;
            width: 18px;
            height: 18px;
            margin-right: 3px;
          }
          .collected {
            background: url('../../common/icons/collect-y.svg') no-repeat;
            background-size: contain;
            background-position: 0 3px;
          }
          .collected-no {
            background: url('../../common/icons/collect-n.svg') no-repeat;
            background-size: contain;
            background-position: 0 3px;
          }
        }

        .reply-input {
          width: 100%;
          height: 50px;
          background-color: white;
          margin-bottom: 10px;
          padding-left: 10px;
          padding-top: 5px;
          display: flex;
          justify-content: center;
          align-items: center;
          input {
            width: 80%;
            height: 40px;
            font-size: 1.3rem;
            border-bottom: 1px solid black;
            margin-right: 10px;
            padding-left: 5px;
            padding-right: 5px;
          }
          input:focus {
            border-bottom: 1px solid #2196f3;
          }
          button {
            font-size: 1.4rem;
            padding: 3px 5px;
            background-color: slateblue;
            color: ghostwhite;
            border-radius: 6px;
          }
        }

        .total-reply {
          text-align: center;
          width: 100px;
          background-color: #B2DFDB;
          border-radius: 3px;
          // padding-left: 10px;
          margin-bottom: 6px;
        }

        .reply-item {
          .reply-author {
            background-color: white;
            border-bottom: 1px solid rgba(0, 0, 0, .1);
            border-radius: 5px;
            padding-left: 10px;
            .reply-avatar {
              display: flex;
              width: 100%;
              height: 50px;
              align-items: center;
              img {
                width: 30px;
                height: 30px;
                border-radius: 3px;
              }
              .reply-desc {
                position: relative;
                flex: 1;
                padding-left: 10px;
                .reply-at {
                  position: absolute;
                  right: 60px;
                }
                .icon-reply-at {
                  position: absolute;
                  right: 35px;
                  top: -2px;
                  display: inline-block;
                  width: 20px;
                  height: 20px;

                }
                .ups-yes {
                  background: url('../../common/icons/clickgood.png') no-repeat;
                  background-size: contain;
                }
                .ups-no {
                  background: url('../../common/icons/1clicknogood.png') no-repeat;
                  background-size: contain;
                }
                span.ups-count {
                  position: absolute;
                  right: 20px;
                }
              }
            }
          }

          .reply-one {
            width: 100%;
            height: 30px;
            // background-color: red;
            margin-bottom: 20px;
            padding-top: 2px;

            input {
              width: 65%;
              height: 100%;
              background: none;
              border-bottom: 1px solid rgba(0, 0, 0, .1);
              font-size: 1.3rem;
              color: gray;
            }
            input:focus {
              border-bottom: 1px solid #00bcd4;
              color: black;
            }
            button {
              font-size: 1.2rem;
              vertical-align: bottom;
              padding: 2px 5px;
              border-radius: 2px;
            }
            button:nth-of-type(1) {
              background-color: #2196f3;
              color: white;
            }
            button:nth-of-type(2) {
              background-color: white;
              color: gray;
            }
          }
        }
      }

    }

    .back {
      position: fixed;
      top: 60%;
      left: -8px;
      width: 50px;
      height: 50px;

    }

  }
  @media screen and (min-width: 750px) {
    .article-detail {
      padding-left: 3%;
      padding-right: 3%;
      .article-title {
        text-align: center;
      }
    }
  }
</style>

