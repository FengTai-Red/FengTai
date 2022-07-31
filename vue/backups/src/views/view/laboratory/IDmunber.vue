<!--  -->
<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <h1>随机生成号码</h1>
      </el-header>
      <el-main>
        <div style="text-align: -webkit-center; margin-top: 200px;">
          <div style="padding: 10px;">
            <el-tag class="ml-2" type="success">{{number}}</el-tag>
          </div>
          <el-button type="primary" @click="generate()">生成</el-button>
        </div>
      </el-main>
      <el-footer style="text-align: -webkit-center;"></el-footer>
    </el-container>
  </div>
</template>

<script>
  import "@/views/view/laboratory/IDmunberJs.js"
  export default {
    data () {
      return {
        number: null,
        AREA_INFO,
      }
    },
    methods: {
      generate(){
        var regionNum = AREA_INFO[this.getRandom(0, Math.floor(Math.random() * AREA_INFO.length))].value  // 地区码
        var randomdate = new Date(this.getRandom(new Date().getTime() - (31104000000 * 18), new Date(1970, 0, 1, 8).getTime()));
        var birthdayNum = (randomdate.getFullYear()) + (randomdate.getMonth()+ 1 + '').padStart(2, '0') + (new Date().getDate() + '').padStart(2, '0');  // 日期
        var orderNum = this.getRandom(10,99)  // 顺序码
        var sexNum = this.getRandom(0, 9)  // 性别
        this.number = regionNum + birthdayNum + orderNum + sexNum
        var checkNum = this.get_check_digit(this.number)  // 校验码
        this.number += checkNum
      },
      getRandom(min, max) {
        min = Math.ceil(min);
        max = Math.floor(max);
        return Math.floor(Math.random() * (max - min + 1)) + min;
      },
      get_check_digit(number) {
        var check_sum = 0
        for (let index = 0; index < 17; index++) {
          check_sum += ((1 << (17 - index)) % 11) * parseInt(number[index])
        }
        var check_digit = (12 - (check_sum % 11)) % 11
        return check_digit < 10 ? check_digit : 'X'
      },
    }
  }
</script>

<style  scoped>

</style>
