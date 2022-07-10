<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <h1 style="margin-top: 200px;">后台管理</h1>
      </el-header>
      <el-main>
        <div>
          <el-form ref="form" :model="loginForm" :rules="rules" class="login-form">
            <el-form-item prop="username">
              <el-input
                v-model="loginForm.username"
                placeholder="账号"
                maxlength="10"
                clearable>
                </el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input 
                type="password" 
                v-model="loginForm.password" 
                placeholder="密码"
                maxlength="10"
                clearable>
              </el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" style="width:100%;margin-bottom:20px;" @click="login(loginForm)">登录</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-main>
    </el-container>
  </div>
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
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur'},
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur'},
          ],
        },
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
            console.log('验证失败');
            that.$message({
              type: 'warning',
              message: '验证失败'
            });
          }
        })
      },
    },
  };
</script>

<style lang="scss" scoped>
  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }
</style>