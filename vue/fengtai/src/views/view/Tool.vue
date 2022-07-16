<!--  -->
<template>
  <div>
    <!-- 主体 -->
    <main class="main">
      <div id="tool">
        <div class="page">
           <div class="quote">
              <i class="icon icon-quote-left"></i>
              <span>詠奏妖華 明鏡止水</span>
              <i class="icon icon-quote-right"></i>
          </div>
          <div class="content">
            <div class="segment" :style="{'borderColor':colors[index % 12]}" v-for="(tool, index) in toolData" :key="tool" :index="index+''">
              <div class="label" :style="{'color':colors[index % 12]}">
                <span>{{tool.name}}</span>
              </div>
              <button @click="downloadTool(tool.id)" class="download" type="primary" :style="{'borderColor':colors[index % 12], 'background-color':colors[index % 12]}">下载</button>
              <div class="markdown">
                <p style="padding-left: 30px;">{{tool.description}}</p>
              </div>
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
        </div>
      </div>
    </main>
  </div>
</template>

<script>
  import { shuffle } from '@/utils'
  import axios from "axios";
  var pageNum = 1;
  var controllerPath = '';
  export default {
    created(){
      controllerPath = this.$config.controllerPath
      const _this = this
      axios.get(controllerPath + '/tool/page/1/6').then(function (resp){
        _this.toolData = resp.data.content;
        _this.pageFirst = resp.data.first;
        _this.pageLast = resp.data.last;
      })
    },
    data() {
      var colors = shuffle(this.$config.themeColors);
      return {
        toolData: null,
        colors,
        pageFirst: null,
        pageLast: null,
      }
    },
    methods: {
       download(path) {
        window.open(path);
      },
      downloadTool(toolId) {
        window.open(controllerPath + '/tool/download/' + toolId);
      },
      pageNext(){
        pageNum += 1;
        const _this = this
        axios.get(controllerPath + '/tool/page/'+ pageNum + '/6').then(function (resp){
          _this.toolData = resp.data.content;
          _this.pageFirst = resp.data.first;
          _this.pageLast = resp.data.last;
        })
      },
      pagePrev(){
        pageNum -= 1;
        const _this = this
        axios.get(controllerPath + '/tool/page/'+ pageNum+ '/6').then(function (resp){
          _this.toolData = resp.data.content;
          _this.pageFirst = resp.data.first;
          _this.pageLast = resp.data.last;
        })
      }
    },
  }
</script>


<style style lang = "scss" >

</style>
