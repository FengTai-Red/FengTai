<!--  -->
<template>
  <div class="common-layout">
    <el-container>
      <el-header>Header</el-header>
      <el-main>
        <div style="text-align: -webkit-center; margin-top: 200px;">
          <h1>测试用页面</h1>
          <el-button type="primary" @click="getTime()">测试</el-button>
          <p>{{time}}</p>
        </div>
      </el-main>
      <el-footer style="text-align: -webkit-center;">Footer</el-footer>
    </el-container>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        time: null,
      }
    },
    methods: {
      getTime(){
        var that = this;
        let yy = new Date().getFullYear();
        let mm = (new Date().getMonth()+ 1 + '').padStart(2, '0');
        let dd = (new Date().getDate() + '').padStart(2, '0');
        let hh = new Date().getHours();
        let mf = new Date().getMinutes()<10 ? '0'+new Date().getMinutes() : new Date().getMinutes();
        let ss = new Date().getSeconds()<10 ? '0'+new Date().getSeconds() : new Date().getSeconds();
        that.gettime = yy+'_'+mm+'_'+dd+'_'+hh+'_'+mf+'_'+ss;
        // console.log(that.gettime)  
        //赋值你的变量
        this.time = that.gettime
      }
    },
  }
</script>

<style  scoped>

</style>
