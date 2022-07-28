<!--  -->
<template>
  <div class="common-layout">
    <el-container>
      <el-header><h3 class="title">将BV号转为AV号</h3></el-header>
      <el-main>
        <div style="text-align: -webkit-center; margin-top: 200px;">
          <el-input v-model="input" id="x" name="BV" size="large" clearable style="width: 50%;"/>
          <el-button type="primary" @click="exchange()">确 定</el-button>
        </div>
      </el-main>
      <el-footer style="text-align: -webkit-center;"></el-footer>
    </el-container>
  </div>
</template>

<script>
  'use strict';
  const table = [...'fZodR9XQDSUm21yCkr6zBqiveYah8bt4xsWpHnJE7jL5VG3guMTKNPAwcF'];
  const s = [11, 10, 3, 8, 4, 6];
  const xor = 177451812;
  const add = 8728348608;
  const av2bv = (av) => {
      let num = NaN;
      if (Object.prototype.toString.call(av) === '[object Number]') {
          num = av;
      } else if (Object.prototype.toString.call(av) === '[object String]') {
          num = parseInt(av.replace(/[^0-9]/gu, ''));
      };
      if (isNaN(num) || num <= 0) {
          return '???';
      };
      num = (num ^ xor) + add;
      let result = [...'bv1  4 1 7  '];
      let i = 0;
      while (i < 6) {
          result[s[i]] = table[Math.floor(num / 58 ** i) % 58];
          i += 1;
      };
      return result.join('');
  };
  const bv2av = (bv) => {
      let str = '';
      if (bv.length === 12) {
          str = bv;
      } else if (bv.length === 10) {
          str = `BV${bv}`;
      } else if (bv.length === 9) {
          str = `BV1${bv}`;
      } else {
          return '???';
      };
      if (!str.match(/[Bb][Vv][fZodR9XQDSUm21yCkr6zBqiveYah8bt4xsWpHnJE7jL5VG3guMTKNPAwcF]{10}/gu)) {
          return '???';
      };
      let result = 0;
      let i = 0;
      while (i < 6) {
          result += table.indexOf(str[s[i]]) * 58 ** i;
          i += 1;
      };
      return `av${result - add ^ xor}`;
  };
  export default {
    data () {
      return {
        input:'',
      }
    },
    methods: {
      exchange(){
        var x = document.getElementById('x').value;
        if(x.substring(0,2).toLowerCase()=='bv'){
            document.getElementById('x').value = `${bv2av(x)}`;
        }else if(x.substring(0,2).toLowerCase()=='av'){
            document.getElementById('x').value = `${av2bv(x)}`;
        }else{
          this.$message({
            type: 'info',
            message: '请输入AV或BV开头的字符'
          }); 
        }
      },
      message(){
        this.$message({
          type: 'info',
          message: '请输入AV或BV开头的字符'
        }); 
      }
    }
  }
</script>

<style  scoped>

</style>
