<!--  -->
<template>
  <div>
    <!-- 主体 -->
    <main class="main">
      <div id="categorylist">
        <div class="page">
          <div class="quote">
            <i class="icon icon-quote-left"></i>
            <span>華枝春滿 天心月圓</span>
            <i class="icon icon-quote-right"></i>
          </div>
          <div class="clean">
            <span>Category:</span>
            <router-link :to="category" v-if="blogData">
              <span class="clean-btn cursor">{{blogData[0].category}}<i class="icon icon-cancel-outline"></i></span>
            </router-link>
          </div>
          <div class="archive">
            <ul class="content">
              <li class="cursor" :style="{'borderTopColor':colors[index % 12]}" v-for="(blog, index) in blogData" :key="blog" :index="index+''">
                <a @click="postView(blog.id, postPath)">
                  <h3>{{blog.title}}</h3>
                  <div class="post-meta">
                    <span><i class="icon icon-calendar"></i>{{blog.cretime}}</span>
                    <span><i class="icon icon-fire"></i>{{blog.views}}</span>
                    <span><i class="icon icon-bookmark-empty"></i>{{blog.category}}</span>
                  </div>
                </a>
              </li>
            </ul>
            <div class="btn-group">
              <div class="pagination" @click="pagePrev(blogData[0].category)" v-show="!pageFirst">
                <div class="previous">
                  <div class="btn cursor">
                    <span>上一页</span>
                  </div>
                </div>
              </div>
              <div class="pagination" @click="pageNext(blogData[0].category)" v-show="!pageLast">
                <div class="previous">
                  <div class="btn cursor">
                    <span>下一页</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
  import { useRouter } from 'vue-router';
  import { shuffle } from '@/utils';
  import axios from "axios";
  const appConfig = require("../../config");
  var controllerPath =appConfig.default.controllerPath;
  var pageNum = 1;
  
  export default {
      created(){
      const _this = this
      axios.get(controllerPath + '/categoryList/published/' + this.$route.query.id + '/page/1/6').then(function(resp){
        _this.blogData = resp.data.content;
        _this.pageFirst = resp.data.first;
        _this.pageLast = resp.data.last;
      })
    }, 
    setup() {
      const router = useRouter();
      var routes = router.options.routes
      var postPath = routes[0].children[7].path;
      var category = routes[0].children[1].path;
      return {
        postPath,
        category,
      };
    },
    data() {
      var colors = shuffle(this.$config.themeColors);
      return {
        blogData: null,
        colors,
        pageFirst: null,
        pageLast: null,
      }
    },
    methods: {
      postView(postId, postPath){
        this.$router.push({
          path: postPath,
          query: {
            id: postId,
          }
        })
      },
      pageNext(category){
        pageNum += 1;
        const _this = this
        axios.get(controllerPath + '/categoryList/published/' + category + '/page/' + pageNum + '/6').then(function (resp){
          _this.blogData = resp.data.content;
          _this.pageFirst = resp.data.first;
          _this.pageLast = resp.data.last;
        })
      },
      pagePrev(category){
        pageNum -= 1;
        const _this = this
        axios.get(controllerPath + '/categoryList/published/' + category + '/page/' + pageNum + '/6').then(function (resp){
          _this.blogData = resp.data.content;
          _this.pageFirst = resp.data.first;
          _this.pageLast = resp.data.last;
        })
      }
    },
  }
</script>

<style  scoped>

</style>
