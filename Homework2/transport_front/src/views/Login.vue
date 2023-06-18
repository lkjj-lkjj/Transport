<template>
<!--  test-->
  <div class="main-box">
    <div :class="['container', 'container-register', { 'is-txl': isLogin }]">
      <form>
        <h2 class="title">Create Account</h2>
        <span class="text">or use email for registration</span>
        <input class="form__input" type="text" v-model="registerData.name" placeholder="Name" />
        <input class="form__input" type="text" v-model="registerData.email" placeholder="Email" />
        <input class="form__input" type="password" v-model="registerData.password" placeholder="Password" />
        <input class="form__input" type="password" v-model="registerData.repassword" placeholder="Confirm Password" />
        <div class="form__input">
          <div class="my-2 flex items-center text-sm" style="padding-left: 55px; padding-top: 4px">
            <el-radio-group v-model="registerData.gender" class="ml-4">
              <el-radio label="1" style="color: #a0a5a8">male</el-radio>
              <el-radio label="2" style="color: #a0a5a8">female</el-radio>
            </el-radio-group>
          </div>
        </div>
        <div class="primary-btn" @click="register" style="font-weight: bold">Register</div>
      </form>
    </div>
    <div
        :class="['container', 'container-login', { 'is-txl is-z200': isLogin }]"
    >
      <form>
        <h2 class="title">Sign in to Website</h2>
        <span class="text">or use email for registration</span>
        <input class="form__input" type="text" v-model="form.email" placeholder="Username" />
        <input class="form__input" type="password" v-model="form.password" placeholder="Password" />
        <div style="width: 350px; height: 40px">
          <input class="form__input" style="width: 170px" v-model="form.ImageCode" type="text" placeholder="CheckCode" />
          <div style="width: 150px; float: right; margin: 4px 0">
            <image-code :change="data.change_img_code" @click="changeImageCode" @getCode="backImageCode" ></image-code>
          </div>
        </div>
        <div class="primary-btn" @click="login" style="font-weight: bold">Sign In</div>
      </form>
    </div>
    <div :class="['switch', { login: isLogin }]">
      <div class="switch__circle"></div>
      <div class="switch__circle switch__circle_top"></div>
      <div class="switch__container">
        <h2>{{ isLogin ? 'Hello Friend !' : 'Welcome Back !' }}</h2>
        <p>
          {{
            isLogin
                ? 'Enter your personal details and start journey with us'
                : 'To keep connected with us please login with your personal info'
          }}
        </p>
        <div class="primary-btn" @click="isLogin = !isLogin" style="font-weight: bold">
          {{ isLogin ? 'Register' : 'Sign In' }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ImageCode from "@/components/ImageCode.vue";
import md5 from 'js-md5'
import {reactive} from "vue";
import {Lock, User} from "@element-plus/icons";
import request from "@/utils/request";
export default {
  name: 'Login',
  data() {
    return {
      form: {},
      registerData: {repassword: "", gender:""},
      isLogin: false
    }
  },
  setup(){
    const data = reactive({
      change_img_code: false, // 刷新验证码
      img_code:'',// 加密后的验证码值
    })

    // 刷新验证码操作
    const changeImageCode = ()=> {
      data.change_img_code = !data.change_img_code
    }

    // 接收组件返回加密后的验证码值
    const backImageCode = (code) =>{
      data.img_code = code
      console.log('data',data.img_code)
    }
    return{
      User,
      Lock,
      changeImageCode,
      backImageCode,
      data
    }
  },
  methods: {
    login(){
      if(this.data.img_code !== md5(this.form.ImageCode)){
        this.changeImageCode()
        this.$message({
          type: "error",
          message: "Captcha error"
        })
      }
      else{
        request.post("/login", {username: this.form.email, password: this.form.password}).then(res=>{
          if(res.code === "200"){
            sessionStorage.setItem("JWT", res.data.token)
            sessionStorage.setItem("auth", res.data.auth)
            sessionStorage.setItem("id",res.data.id)
            this.$message({
              type: "success",
              message: "Success"
            })
            sessionStorage.setItem("username",this.form.email)
            if(sessionStorage.getItem("auth") === "0"){
              this.$router.push("/createorder")
            }else{
              this.$router.push("/receive")
            }
          }
          if(res.code === "500"){
            this.$message({
              type: "error",
              message: "Account or Password wrong"
            })
            this.changeImageCode()
          }
        }).catch(err=>{
          this.$message({
            type: "error",
            message: "Wrong!"
          })
          this.changeImageCode()
        })
      }
    },
    register(){
      if(this.registerData.password !== this.registerData.repassword){
        this.$message({
          type: "error",
          message: "Passwords do not match"
        })
      }
      else{
        request.post("/register", {username: this.registerData.name, password: this.registerData.password}).then(res=>{
          if(res.code === "500"){
            this.$message({
              type: "error",
              message: "User exist"
            })
          }
          if(res.code === "200"){
            this.$message({
              type: "success",
              message: "Success"
            })
            this.registerData = {}
          }
        }).catch(err=>{
          this.$message({
            type: "error",
            message: "don't know why"
          })
        })
      }
    }
  },
  components:{
    ImageCode
  }
}
</script>

<style lang="scss" scoped>
.main-box {
  margin: 130px auto 0;
  position: relative;
  width: 1000px;
  min-width: 1000px;
  min-height: 600px;
  height: 600px;
  padding-top: 100px;
  background-color: #ecf0f3;
  box-shadow: 10px 10px 10px #d1d9e6, -10px -10px 10px #f9f9f9;
  border-radius: 12px;
  overflow: hidden;
  .container {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    top: 0;
    width: 600px;
    height: 100%;
    padding: 25px;
    background-color: #ecf0f3;
    transition: all 1.25s;
    form {
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      width: 100%;
      height: 100%;
      color: #a0a5a8;
      .title {
        font-size: 34px;
        font-weight: 700;
        line-height: 3;
        color: #181818;
      }
      .text {
        margin-top: 30px;
        margin-bottom: 12px;
      }
      .form__input {
        width: 350px;
        height: 40px;
        margin: 4px 0;
        padding-left: 25px;
        font-size: 15px;
        letter-spacing: 0.15px;
        border: none;
        outline: none;
        font-family: 'Montserrat', sans-serif;
        background-color: #ecf0f3;
        transition: 0.25s ease;
        border-radius: 8px;
        box-shadow: inset 2px 2px 4px #d1d9e6, inset -2px -2px 4px #f9f9f9;
        &::placeholder {
          color: #a0a5a8;
        }
      }
    }
  }
  .container-register {
    z-index: 100;
    left: calc(100% - 600px);
  }
  .container-login {
    left: calc(100% - 600px);
    z-index: 0;
  }
  .is-txl {
    left: 0;
    transition: 1.25s;
    transform-origin: right;
  }
  .is-z200 {
    z-index: 200;
    transition: 1.25s;
  }
  .switch {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    top: 0;
    left: 0;
    height: 100%;
    width: 400px;
    padding: 50px;
    z-index: 200;
    transition: 1.25s;
    background-color: #ecf0f3;
    overflow: hidden;
    box-shadow: 4px 4px 10px #d1d9e6, -4px -4px 10px #f9f9f9;
    color: #a0a5a8;
    .switch__circle {
      position: absolute;
      width: 500px;
      height: 500px;
      border-radius: 50%;
      background-color: #ecf0f3;
      box-shadow: inset 8px 8px 12px #d1d9e6, inset -8px -8px 12px #f9f9f9;
      bottom: -60%;
      left: -60%;
      transition: 1.25s;
    }
    .switch__circle_top {
      top: -30%;
      left: 60%;
      width: 300px;
      height: 300px;
    }
    .switch__container {
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      position: absolute;
      width: 400px;
      padding: 50px 55px;
      transition: 1.25s;
      h2 {
        font-size: 34px;
        font-weight: 700;
        line-height: 3;
        color: #181818;
      }
      p {
        font-size: 14px;
        letter-spacing: 0.25px;
        text-align: center;
        line-height: 1.6;
      }
    }
  }
  .login {
    left: calc(100% - 400px);
    .switch__circle {
      left: 0;
    }
  }
  .primary-btn {
    width: 180px;
    height: 50px;
    border-radius: 25px;
    margin-top: 30px;
    text-align: center;
    line-height: 50px;
    font-size: 14px;
    letter-spacing: 2px;
    background-color: #4b70e2;
    color: #f9f9f9;
    cursor: pointer;
    box-shadow: 8px 8px 16px #d1d9e6, -8px -8px 16px #f9f9f9;
    &:hover {
      box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, 50%),
      -4px -4px 6px 0 rgba(116, 125, 136, 50%),
      inset -4px -4px 6px 0 rgba(255, 255, 255, 20%),
      inset 4px 4px 6px 0 rgba(0, 0, 0, 40%);
    }
  }
}
</style>