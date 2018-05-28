<template lang="html">
  <div class="header">

    <div class="title">
      <i @click.stop.prevent="showAsideMenu" class="icon-menu"></i>
      <h1 > Sirius </h1>
      <i @click="showMsg" v-show="ak" class="icon-msg"></i>
      <el-input
        placeholder="Type something"
        prefix-icon="el-icon-search"
        v-model="searchinput" class="search"
        @keyup="get($event)"
        @keydown.down.prevent="selectdown"
        @keydown.up.prevent="selectUp">
      </el-input>
      <span v-show="ak" class="msg-count">{{msgCount}}</span>
    </div>

  </div>
</template>

<script>
export default {
  name: 'header',
  data() {
    return {
      msgCount: 0,
      search:'',
      myData:[]
    }
  },
  methods: {
    showAsideMenu() {
      this.$store.commit('showAsideMenu', true);
    },
    showMsg() {
      this.$store.commit('showMsg', true);
    }
  },
  computed: {
    ak() {
      return this.$store.state.ak;
    }
  },
  created() {
    if (!this.ak) {
      return;
    }
    this.axios.get('https://cnodejs.org/api/v1/message/count?accesstoken=' + this.ak)
      .then(result => result.data)
      .then(data => {
        if (data.success) {
          this.msgCount = data.data;
        }
      })
  },
  get(event) {
    if(event.keyCode == 38 || event.keyCode == 40){  //向上向下
      return ;
    }
    this.$http.jsonp('https://sug.so.360.cn/suggest?word=' + this.search + '&encodein=utf-8&encodeout=utf-8').then(function(res) {
      this.myData = res.data.s;

    }, function() {

    });
  }

}
</script>

<style lang="scss" scoped>
  .header {
    position: relative;
    display: flex;
    flex-direction: column;
    width: 100%;
    height: 70px;
    background-color: royalblue;
    color: white;
    justify-content: center;
    align-items: center;
    box-shadow: 0 1px 10px rgba(0, 0, 0, .2);
    i {
      position: absolute;
      z-index: 1;
      display: inline-block;
      flex: 1;
      width: 25px;
      height: 25px;
    }
    i.icon-menu {

      right: 30px;
      top: 25px;
      background: url('../../common/icons/icon-menu.svg') no-repeat;
      background-size: contain;
    }
    i.icon-msg {
      right: 80px;
      top: 25px;
      background: url('../../common/icons/icon-msg.svg') no-repeat;
      background-size: contain;
    }
    span.msg-count {
      position: absolute;
      display: flex;
      justify-content: center;
      align-items: center;
      z-index: 1;
      width: 20px;
      height: 20px;
      right: 70px;
      top: 20px;
      text-align: center;
      background-color: #ff4081;
      border-radius: 10px;
      font-size: 80%;
    }
    .search{
      width:300px;
      left: 130px;
      top: -40px;
    }

    h1 {
      margin-right: 1100px;
      flex: 5;
      margin-top: 40px;
      letter-spacing: 2px;
      transition: .3s ease-in-out;

    }

  }
</style>
