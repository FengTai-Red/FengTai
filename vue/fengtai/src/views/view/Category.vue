<!--  -->
<template>
  <div>
    <!-- 主体 -->
    <main class="main">
      <div id="category">
        <div class="page">
          <div class="quote">
            <i class="icon icon-quote-left"></i>
            <span>華枝春滿 天心月圓</span>
            <i class="icon icon-quote-right"></i>
          </div>
          <ul class="content">
            <li class="cursor" v-for="(category, index) in categoryData" :key="category" :index="index+''">
              <a @click="postCategory(category.name, categorylist)">
              <img :src="require('@/assets/image/' + category.background)" alt="" class="bg">
              <div class="meta">
                <div>
                  <img :src="require('@/assets/image/icon/' + category.icon)" alt="" class="avatar">
                  <span>{{category.name}}</span>
                </div>
                  <p>{{category.description}}</p>
              </div>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
  import { useRouter } from 'vue-router';
  import axios from "axios";
  const appConfig = require("../../config");
  var controllerPath =appConfig.default.controllerPath;
  
  export default {
    created(){
      const _this = this
      axios.get(controllerPath + '/category/findAll').then(function (resp){
        _this.categoryData = resp.data;
      })
    },
    setup() {
      const router = useRouter();
      var routes = router.options.routes
      var categorylist = routes[0].children[2].path;
      return {
        categorylist,
      };
    },
    data() {
      return {
        categoryData: null,
      }
    },
    methods: {
      postCategory(category, categorylist){
        this.$router.push({
          path: categorylist,
          query: {
            id: category,
          }
        })
      },
    },
  }
</script>

<style  scoped>

</style>
