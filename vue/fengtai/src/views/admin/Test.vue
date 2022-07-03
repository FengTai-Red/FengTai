<template>
  <div>
    <el-button size="small" @click="testA()">测试A</el-button>
    <el-button size="small" @click="testB()">测试B</el-button>
  </div>
</template>

<script>
  import axios from "axios"
  export default {
    created(){
    },
    data() {
      return {
        TextData: null,
      }
    },
    methods: {
      testA(){
        const _this = this
        axios.get('http://127.0.0.1:8181/test').then(function (resp){
          console.log(resp.data);
        })
      },

      testB(){
        const _this = this
        axios.get('http://127.0.0.1:8181/test/1').then(function (resp){
          console.log(resp.data);
        })
      },
    },
  }
</script>
