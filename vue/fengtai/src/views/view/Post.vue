<template>
  <div>
    <!-- 主体 -->
    <main class="main">
      <div id="post">
        <article  v-if="postData">
          <div class="post-header">
            <div maskheight="0.8rem">
              <img :src="require('@/assets/image/' + postData.firstPicture)" alt="defaultCover">
            </div>
            <div class="post-head">
              <div class="post-title">
                <h1>{{postData.title}}</h1>
              </div>
              <div class="post-meta">
                <i class="icon icon-calendar"></i><span>{{postData.createTime}}</span>
                <i class="icon icon-fire"></i><span>{{postData.views}}</span>
                <i class="icon icon-bookmark-empty"></i><span>{{postData.category}}</span>
              </div>
            </div>
          </div>
          <div class="post-body">
            <div class="markdown" v-html="postData.content"></div>
          </div>
        </article> 
      </div>
    </main>
  </div>
</template>

<script>
  import "@/assets/js/prism/prism.js"
  import "@/assets/js/prism/prism-core.js"
  import "@/assets/js/prism/prism-autoloader.js"
  import '@/assets/css/components/markDownPrism.css'
  import '@/assets/css/components/markDownView.css'
  import axios from "axios";
  const appConfig = require("../../config");
  var controllerPath =appConfig.default.controllerPath;
  
  export default {
    created(){
      const _this = this
      axios.get(controllerPath + '/post/' + this.$route.query.id).then(function(resp){
        _this.postData = resp.data;
      })
    },
    data() {
      return {
        postData: null,
      }
    },
  }
</script>


<style style lang = "scss" >

</style>
