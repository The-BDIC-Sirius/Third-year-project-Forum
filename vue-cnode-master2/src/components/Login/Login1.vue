
<template lang="html">
  <div class="login">
    <div class="login-header">
      <i @click="showLogin" class="icon-back"></i>
    </div>

    <div class="login-wrap" v-show="showloginview">
      <h2>Login</h2>
      <br/>
      <br/>
      <p v-show="showTishi">{{tishi}}</p>
      <input v-model="username" type="text" placeholder="账号">
      <input v-model="password" type="password" placeholder="密码">
      <button @click.stop.prevent="login">Login</button>
      <span v-on:click="ToRegister">no account? click to resiger</span>
    </div>
    <div class="register-wrap" v-show="showRegister">
      <h2>Resiger</h2>
      <br/>
      <br/>
      <p v-show="showTishi">{{tishi}}</p>
      <input type="text" placeholder="nickname" v-model="newUsername">
      <input type="text" placeholder="firstname" v-model="firstname">
      <input type="text" placeholder="lastname" v-model="lastname">
      <input type="password" placeholder="password" v-model="newPassword">
      <button v-on:click="register">resiger</button>
      <br>
      <span v-on:click="ToLogin">already have an account? click to login</span>
    </div>

  </div>
</template>

<script>
  export default {
    name: 'login',

    data() {
      return {
        username : '',
        password:'',
        firstname:'',
        lastname: '',
        showloginview: true,
        showRegister: false
      }
    },

    methods: {
      ToRegister(){
        this.showRegister = true
        this.showloginview = false
      },
      ToLogin(){
        this.showRegister = false
        this.showloginview = true
      },
      register(){
        if(this.newUsername == "" || this.newPassword == ""||this.firstname==""||this.lastname==""){
          alert("请输入完整信息")
        }else{
          let data = {'nickname':this.newUsername,'firstName':this.firstname,
            'lastName':this.lastname,'password':this.newPassword}


          this.$http.post('http://localhost:8085/test/register',data)
            .then((res)=>{
            console.log(res)
            if(res.data == "ok"){
              alert(res.data)
              this.tishi = "注册成功"
              this.showTishi = true
              this.username = ''
              this.password = ''
              /*注册成功之后再跳回登录页*/
              setTimeout(function(){
                this.showRegister = false
                this.showloginview = true
                this.showTishi = false
              }.bind(this),1000)
            }else {
              alert(res.data)
            }
          })
        }
      },
      showLogin() {
        this.$store.commit('showLogin', false);
        this.$store.commit('showMsg', false);
      },
      login(){
        if(this.username == "" || this.password == ""){
          alert("请输入用户名或密码")
        }else{
          let data = {'username':this.username,'userpwd':this.password}
          /*接口请求*/
          this.$http.post('http://localhost:8085/test/login',data).
          then((res)=>{
            console.log(res)
            /*接口的传值是(-1,该用户不存在),(0,密码错误)，同时还会检测管理员账号的值*/
            if(res.data == -1){
              this.tishi = "该用户不存在"
              this.showTishi = true
            }else if(res.data == 0){
              this.tishi = "密码输入错误"
              this.showTishi = true
            }else if(res.status === 200){
              /*路由跳转this.$router.push*/

              this.showTishi = true
              this.tishi = "登录成功"
              alert("登录成功")
              return res.data
            }
          })
            .catch(function (error) {
              console.log('验证失败',error);
            })
            .then(userInfo => {
              alert(JSON.stringify(userInfo))
              this.$store.commit('updateUserInfo', userInfo);
              this.$store.commit('updateAk', userInfo["id"]);
              localStorage.userInfo = JSON.stringify(userInfo);
              localStorage.ak = userInfo["id"];
              this.showLogin();
            })

        }
      }
    },

    computed: {
      usernameinit() {
        return this.$store.state.userInfo.username;
      }
    },
    mounted() {

      this.username = this.usernameinit();
    }
  }
</script>

<style lang="scss" scoped>
  .login {
    position: absolute;
    display: flex;
    flex-direction: column;
    // justify-content: center;
    align-items: center;
    z-index: 2;
    top: 0;
    bottom: 0;
    right: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: white;

    .login-header {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 100%;
      height: 60px;
      background-color: #2196f3;
      color: white;
      font-size: 1.4rem;

      i.icon-back {
        position: absolute;
        left: 10px;
        display: inline-block;
        width: 38px;
        height: 38px;
        background: url('../../common/icons/icon-back.svg') no-repeat;
        background-size: contain;
      }
    }

    .login-wrap{
      text-align:center;
    padding-top: 40px;


      }

    .register-wrap{text-align:center;
      padding-top: 40px;}
    input{display:block; width:250px; height:40px; line-height:40px; margin:0 auto; margin-bottom: 10px; outline:none; border:1px solid #888; padding:10px; box-sizing:border-box;}
    p{color:red;}
    button{display:block; width:250px; height:40px; line-height: 40px; margin:0 auto; border:none; background-color:#41b883; color:#fff; font-size:16px; margin-bottom:5px;}
    span{cursor:pointer;}
    span:hover{color:#41b883;}
    .tips {
      flex: 2;
      color: gray;
      // box-shadow: 0 0 10px gray;
      // border: 1px solid gray;
      li {
        // list-style: none;
      }
    }

  }
</style>
