<template>
  <body>
  <div id = "Login" style="opacity: 0.85;position: relative;height: 1000px">
    <div id = "admin">
      <div class="pos" >
        <h1 class = "adminh1">酒店登陆管理平台 </h1>
        <h2 class = "adminh1"> 用户登录</h2>
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm"  label-width="20px" class="demo-ruleForm">
          <el-form-item prop="name">
            <el-input v-model="ruleForm.name" placeholder="username"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input type="password" v-model="ruleForm.password" placeholder="password"></el-input>
          </el-form-item>
          <el-row >
            <el-col :span="12">
              <el-form-item prop="captchaCode">
                <el-input v-model="ruleForm.captchaCode" placeholder="captcha"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item>
                <el-image :src="`http://localhost:8080/captcha/get_captcha`" ></el-image>
              </el-form-item>
            </el-col>
          </el-row>


          <el-row>
            <el-col :span="12">
              <el-form-item>
                <el-link>
                  <router-link :to="{name: 'Register'}" style = "color: black; text-decoration: none;">新用户注册</router-link>
                </el-link>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item>
                <el-link >
                  <router-link :to="{name: 'ForgetPassword'}" style = "color: black; text-decoration: none;">忘记密码</router-link>
                </el-link>
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item>
            <el-button type="primary" @click="LoginForm('ruleForm')">登录</el-button>
            <el-button @click="resetForm('ruleForm')">重置密码</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
  </body>
</template>

<script>
import axios from 'axios';
import {ElMessage} from "element-plus";
export default {
  name: "Login",
  data() {
    return {
      activeName:'first',
      registerForm:{
        name:'',
        avatar:'',
        email:'',
        credit:'',
        password:'',
        phone:'',
        sex:'',
        status:'',
        userType:'',
        age:''
      },
      ruleForm: {
        name: 'markerhub',
        password: '123456',
        phone:'',
        email:'',
        captchaCode: '',
        accountType: 'USER',
        loginType:'name'
      },
      emailForm: {
        name: 'markerhub',
        password: '',
        phone:'',
        email:'',
        captchaCode: '',
        accountType: 'USER',
        loginType:'email'
      },
      registerRules:{

      },
      rules: {
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: "请输入用户密码", trigger: "blur" },
          { min: 6, max: 12, message: "长度在 6 到 12 个字符", trigger: "blur" }
        ]
      },
      emailRules: {
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: "请输入用户邮箱", trigger: "blur" },
          { pattern:/^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/, message: "邮箱格式不正确", trigger: "blur" }
        ],
        captchaCode:[
          { required: true, message: "请输入邮箱验证码", trigger: "blur" },
        ],
      }
    };
  },
  methods: {
    RefleshImg() {
      console.log('HHHHHHHH')
      // this.("#captcha_img").attr("src","http://localhost:8080/api/v1/captcha/get_captcha")
      // $('#captcha_img').reload();
    },
    SignUpForm(formName){
      this.$refs[formName].validate((valid)=>{
        if(valid){
          console.log('addStaff')
          axios.post('account/addstaff')
        }else{
          return false;
        }
      })
    },
    LoginForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('/account/userlogin', this.ruleForm).then(res => {
            const jwt = res.headers['authorization']
            const userInfo = res.data.data
            // console.log(userInfo)
            // console.log(jwt)
            // 把数据共享出去
            this.$store.commit("SET_TOKEN", jwt)
            this.$store.commit("SET_USERINFO", userInfo)
            // 获取
            // console.log(this.$store.getters.getUser)

            this.$router.push("/hotelInfo")

          })

        } else {
          // console.log('error submit!!');
          return false;
        }
      });
    },
    EmailLoginForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('/account/userlogin', this.emailForm).then(res => {
            const jwt = res.headers['authorization']
            const userInfo = res.data.data
            // console.log(userInfo)
            // console.log(jwt)
            // 把数据共享出去
            this.$store.commit("SET_TOKEN", jwt)
            this.$store.commit("SET_USERINFO", userInfo)
            // 获取
            // console.log(this.$store.getters.getUser)

            this.$router.push("/notices")

          })

        } else {
          // console.log('error submit!!');
          return false;
        }
      });
    },

    getEmailCaptchaCode(){
      if(!this.emailForm.email){
        this.$message.warning("请输入邮箱账号!")
        return;
      }else{
        //
        // this.emailForm.email=JSON.stringify(this.emailForm.email)
        console.log(this.emailForm.email)
        axios.post('/captcha/email_captcha',this.emailForm).then(res=>{
          if(res.data.code===200){
            ElMessage({
              type: 'success',
              message: '发送成功',
            })
          }else{
            ElMessage({
              type:'warning',
              message:res.data.msg
            });
          }
        })
      }
    },

    SignInForm(formName) {
      // this.$refs[formName].validate((valid)=>{
      // 	if(valid){
      // 		axios.post('/signIn')
      // 	}
      // })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>
<style lang="less" scoped>

body{
  background:url("coupon.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
}
#Login {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
#admin {
  position: absolute;
  top: 30%;
  left: 50%;
  margin-top: -200px;
  margin-left: -250px;
  width: 500px;
  height: 450px;
  background: #fff;
  border-radius: 10%;
  box-shadow: 8px 10px 10px rgb(177, 223, 242);
  text-align: center;
}
.adminh1 {
  margin: 20px 0;
  /*text-shadow: 10px 13px 3px rgb(207, 207, 207);*/
  text-align: center;
}
.pos {
  width: 450px;
  height: 350px;
  position: absolute;
  top: 25px;
  left: 25px;
}
.item {
  margin-top: 3px;
  margin-right: 6px;
}
.el-col {
  border-radius: 4px;
  flex-wrap: wrap !important ;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
/*@media only screen and (min-width: 481px){*/
/*	.el-aside {*/
/*		width: 0%!important;*/
/*	}*/
/*}*/
/*@media only screen and (min-width: 561px){*/
/*	.el-aside {*/
/*		width: 20%!important;*/
/*	}*/
/*}*/
/*@media only screen and (min-width: 641px){*/
/*	.el-aside {*/
/*		width: 20%!important;*/
/*	}*/
/*}*/
</style>
