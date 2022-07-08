<!--  -->
<template>
  <div>
    <!-- 主体 -->
    <main class="main">
      <div id="navigation">
        <div class="page">
          <div class="quote">
            <i class="icon icon-quote-left"></i>
            <span>桜華月想 暮色蒼然</span>
            <i class="icon icon-quote-right"></i>
          </div>
          <div class="archive">
            <ul class="content">
              <li class="cursor" :style="{'borderTopColor':colors[index % 12]}" v-for="(navigat, index) in navigatData" :key="navigat" :index="index+''">
                <a @click="toHref(navigat.path)">
                  <h3>{{navigat.name}}</h3>
                  <div class="post-meta">
                    <span><i class="icon icon-tag"></i>{{navigat.description}}</span>
                  </div>
                </a>
              </li>
            </ul>
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
        </div>
      </div>
    </main>
  </div>
</template>

<script>
  import { shuffle } from '@/utils';
  import axios from "axios";
  var pageNum = 1;
  export default {
    created(){
      const _this = this;
      axios.get('http://127.0.0.1:8181/navigation/page/1/6').then(function (resp){
        _this.navigatData = resp.data.content;
        _this.pageFirst = resp.data.first;
        _this.pageLast = resp.data.last;
      })
    },
    data() {
      var colors = shuffle(this.$config.themeColors);
      return {
        navigatData: null,
        colors,
        pageFirst: null,
        pageLast: null,
      }
    },
    methods: {
       toHref(path) {
        window.open(path);
      },
      pageNext(){
        pageNum += 1;
        const _this = this
        axios.get('http://127.0.0.1:8181/navigation/page/'+ pageNum + '/6').then(function (resp){
          _this.navigatData = resp.data.content;
          _this.pageFirst = resp.data.first;
          _this.pageLast = resp.data.last;
        })
      },
      pagePrev(){
        pageNum -= 1;
        const _this = this
        axios.get('http://127.0.0.1:8181/navigation/page/'+ pageNum+ '/6').then(function (resp){
          _this.navigatData = resp.data.content;
          _this.pageFirst = resp.data.first;
          _this.pageLast = resp.data.last;
        })
      }
    },
  }
</script>

<style style lang = "scss" >

</style>
