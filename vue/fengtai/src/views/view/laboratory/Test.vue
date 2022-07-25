<!--  -->
<template>
  <div class="common-layout">
    <el-container>
      <el-header>Header</el-header>
      <el-main>
        <el-input v-model="input" placeholder="Please input" @input="change($event)"/>
      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>
  </div>
</template>

<script>
  export default {
    change(e){
      this.$$forceUpdate()
    },
    data () {
      return {
      }
    }
  }
</script>

<style  scoped>

</style>
