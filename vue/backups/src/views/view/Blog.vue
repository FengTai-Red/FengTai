<!--  -->
<template>
  <div>
    <!-- 主体 -->
    <main class="main">
      <div id="blog">
        <!-- 主体_文章容器 -->
        <div class="content">
          <article class="cursor aos-init aos-animate" v-for="(blog, index) in blogData" :key="blog" :index="index+''">
            <a @click="postView(blog.id, postPath)">
              <div class="post-header">
                <div>
                  <img title="" :src="require('@/assets/image/' + blog.firstPicture)">
                </div>
                <div class="post-head">
                  <h3>{{blog.title}}</h3>
                </div>
              </div>
              <div class="post-body">
                <div class="markdown">
                  <p>{{blog.description}}</p>
                </div>
              </div>
              <div class="post-meta">
                <span><i class="icon icon-calendar"></i>{{blog.createTime}}</span>
                <span><i class="icon icon-fire"></i>{{blog.views}}</span>
                <span><i class="icon icon-bookmark-empty"></i>{{blog.category}}</span>
              </div>
            </a>
          </article>
        </div>
        <div class="btn-group">
          <div class="pagination" @click="pagePrev()" v-show="!pageFirst">
            <div class="previous">
              <div class="btn cursor">
                <span>上一页</span>
              </div>
            </div>
          </div>
          <div class="pagination" @click="pageNext()" v-show="!pageLast">
            <div class="previous">
              <div class="btn cursor">
                <span>下一页</span>
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
  import axios from "axios";
  var pageNum = 1;
  export default {
    created(){
      const _this = this
      axios.get('http://127.0.0.1:8181/post/published/page/1/6').then(function (resp){
        _this.blogData = resp.data.content;
        _this.pageFirst = resp.data.first;
        _this.pageLast = resp.data.last;
      })
    },
    setup() {
      const router = useRouter();
      var routes = router.options.routes
      var postPath = routes[0].children[7].path;
      return {
        postPath,
      };
    },
    data() {
      return {
        blogData: null,
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
      pageNext(){
        pageNum += 1;
        const _this = this
        axios.get('http://127.0.0.1:8181/post/published/page/'+ pageNum + '/6').then(function (resp){
          _this.blogData = resp.data.content;
          _this.pageFirst = resp.data.first;
          _this.pageLast = resp.data.last;
        })
      },
      pagePrev(){
        pageNum -= 1;
        const _this = this
        axios.get('http://127.0.0.1:8181/post/published/page/'+ pageNum+ '/6').then(function (resp){
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
