<template>
  <div>
    <!-- 列表 -->
    <el-button type="primary" style="margin-left: 20px;" @click="addPost()">新增</el-button>
    <el-table :data="postData" style="width: 90%" v-if="postData">
      <el-table-column label="标题">
        <template #default="scope">
          <span style="margin-left: 10px">{{ scope.row.title }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否发布" width="100">
        <template #default="scope">
          <span style="margin-left: 10px">{{ scope.row.published == true ? '是':'否' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="300">
        <template #default="scope">
          <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="分类" width="100">
        <template #default="scope">
          <span style="margin-left: 10px">{{ scope.row.category }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row.id)">编辑</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.row.id)" >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 翻页 -->
    <el-pagination background layout="prev, pager, next" :page-count="Math.floor(totalElements/10)+1" @current-change="page"/>
  </div>
</template>

<script>
  import axios from "axios"
  var controllerPath = ''
  export default {
    name: "Upload",
    created(){
      controllerPath = this.$config.controllerPath
      const _this = this
      axios.get(controllerPath + '/post/page/1/10').then(function (resp){
        _this.postData = resp.data.content
        _this.totalElements = resp.data.totalElements
      })
    },
    data() {
      return {
        totalElements: null,
        size: null,
        postData: null,
        visible: null,
      }
    },
    methods: {
      page(currentPage){
        const _this = this
        axios.get(controllerPath + '/post/page/'+ currentPage+ '/10').then(function (resp){
          _this.postData = resp.data.content
          _this.totalElements = resp.data.totalElements
          _this.size = resp.data.size
        })
      },
      handleDelete(id){
        this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios.delete(controllerPath + '/admin/post/deletePost/' + id)
          window.location.reload();  // 刷新窗口
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      handleEdit(id){
        this.$router.push({
          path: "/admin/PostEdit",
          query: {
            id: id,
          }
        })
      },
      addPost(){
        this.$router.push({
          path: "/admin/PostAdd",
        })
      },
    },
  }
</script>
