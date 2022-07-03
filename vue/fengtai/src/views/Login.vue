<template>
  <body id="login-page">
    <el-form ref="form" :model="loginForm">
      <h3>系统登录</h3>
      <el-form-item label="账号">
        <el-input v-model="loginForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input type="password" v-model="loginForm.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="login(loginForm)">登录</el-button>
      </el-form-item>
    </el-form>
  </body>
</template>

<script>
  import axios from "axios"
  export default {
    name: "Login",
    data() {
      return {
        loginForm: {
          username: "",
          password: "",
        },
        responseResult: [],
      };
    },
    methods: {
      login(loginForm){
        console.log(loginForm);
        const that = this
        axios.post('http://127.0.0.1:8181/login', loginForm).then(function(resp) {
          if (resp.data == 'success'){
            console.log('成功');
            sessionStorage.setItem('isLogin',1);
            that.$router.push({
              path: "/admin/PostList",
            })
          }else{
            console.log("xxx");
          }
        })
      },
    },
  };
</script>