<!--  -->
<template>
  <div>
    <el-container style="height: 800px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu router :default-openeds="['0']">
          <el-menu-item v-for="(item) in routes[3].children" :key="item" :index="item.path" v-show="item.show" >
            <p>{{item.name}}</p>
          </el-menu-item>
        </el-menu>
        <div class="toolbar">
          <el-button type="primary" style="margin-left: 70px; margin-top: 100%;" @click="logout()">登出</el-button>
        </div>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import axios from "axios"
  import { defineComponent, ref } from 'vue';
  import { useRouter } from 'vue-router';
  export default defineComponent({
    setup() {
      const router = useRouter();
      var str = JSON.stringify(router); // 将对象转成字符串
      var routes = router.options.routes  // 获取router/index/js中的routes对象
      var routerpath = router.currentRoute.value.fullPath  // 获取全路径
      return {
        routes,
        routerpath
      };
    },
    methods: {
      logout(){
        const that = this
        axios.get('http://127.0.0.1:8181/logout').then(function (resp){
          if (resp.data == 'success'){
            console.log('成功');
            sessionStorage.setItem('isLogin',0);
            that.$router.push({
              path: "/Login",
            })
          }else{
            console.log("xxx");
          }
        })
      },
    },
  });
</script>
<style>
  .el-header {
    background-color: #b3c0d1;
    color: var(--el-text-color-primary);
    line-height: 60px;
  }

  .el-aside {
    color: var(--el-text-color-primary);
  }
</style>