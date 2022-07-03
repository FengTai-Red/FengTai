<!--  -->
<template>
  <div>
    <!-- 主体 -->
    <main class="main">
      <div id="test">
        <div class="page">
           <div class="quote">
              <i class="icon icon-quote-left"></i>
              <span>测试测试 测试测试</span>
              <i class="icon icon-quote-right"></i>
          </div>
          <div class="content">
            <div>
              <el-button type="text" @click="open">点击打开 Message Box</el-button>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
  export default {
    methods: {
      open() {
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      }
    }
  }
</script>


<style scoped="scoped">
	.el-upload {
		margin: 5px;
	}
</style>