<template>
  <div>
    <!-- 列表 -->
    <p>{{}}</p>
    <el-button type="primary" style="margin-left: 20px;" @click="add()">新增</el-button>
    <el-table :data="CategoryData" style="width: 100%">
      <el-table-column label="分类名" width="180">
        <template #default="scope">
          <span style="margin-left: 10px">{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="简介" width="300">
        <template #default="scope">
          <span style="margin-left: 10px">{{ scope.row.description }}</span>
        </template>
      </el-table-column>
      <el-table-column label="图标" width="300">
        <template #default="scope">
          <span style="margin-left: 10px">{{ scope.row.icon }}</span>
        </template>
      </el-table-column>
      <el-table-column label="背景" width="300">
        <template #default="scope">
          <span style="margin-left: 10px">{{ scope.row.background }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" @click="handEdit(scope.row.id)">编辑</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.row.id)" >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 翻页 -->
    <el-pagination background layout="prev, pager, next" :page-count="Math.floor(totalElements/10)+1" @current-change="page"/>
    <!-- 编辑窗 -->
    <div>
      <el-dialog title="提示" v-model="upDialogVisible" width="50%" >
        <el-form ref="form" :model="updataCategory" label-width="80px">
          <el-form-item label="分类名">
            <el-input v-model="updataCategory.name"></el-input>
          </el-form-item>
          <el-form-item label="简介">
            <el-input v-model="updataCategory.description"></el-input>
          </el-form-item>
          <el-form-item label="图标">
            <el-select v-model="updataCategory.icon" class="m-2" placeholder="Select">
              <el-option v-for="item in categoryIconOptions" :key="item.value" :label="item.label" :value="item.value"/>
            </el-select>
          </el-form-item>
          <el-form-item label="背景">
            <el-select v-model="updataCategory.background" class="m-2" placeholder="Select">
              <el-option v-for="item in categoryBgOptions" :key="item.value" :label="item.label" :value="item.value"/>
            </el-select>
          </el-form-item>
        </el-form>
        <template v-slot:footer>
        <span class="dialog-footer">
          <el-button @click="upDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleUpload(updataCategory)">确 定</el-button>
        </span>
        </template>
      </el-dialog>
    </div>
    <!-- 新增窗 -->
    <div>
      <el-dialog title="新增" v-model="addDialogVisible" width="50%" >
        <el-form ref="form" :model="addCategory" label-width="80px">
          <el-form-item label="分类名">
            <el-input v-model="addCategory.name"></el-input>
          </el-form-item>
          <el-form-item label="简介">
            <el-input v-model="addCategory.description"></el-input>
          </el-form-item>
          <el-form-item label="图标">
            <el-select v-model="addCategory.icon" class="m-2" placeholder="Select">
              <el-option v-for="item in categoryIconOptions" :key="item.value" :label="item.label" :value="item.value"/>
            </el-select>
          </el-form-item>
          <el-form-item label="背景">
            <el-select v-model="addCategory.background" class="m-2" placeholder="Select">
              <el-option v-for="item in categoryBgOptions" :key="item.value" :label="item.label" :value="item.value"/>
            </el-select>
          </el-form-item>
        </el-form>
        <template v-slot:footer>
        <span class="dialog-footer">
          <el-button @click="addDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleAdd(addCategory)">确 定</el-button>
        </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import axios from "axios"
  export default {
    name: "Upload",
    created(){
      const _this = this
      axios.get('http://127.0.0.1:8181/category/page/1/10').then(function (resp){
        _this.CategoryData = resp.data.content
        _this.totalElements = resp.data.totalElements
      })
    },
    data() {
      return {
        totalElements: null,
        size: null,
        CategoryData: null,
        visible: null,
        upDialogVisible: false,
        addDialogVisible: false,
        updataCategory: {
          name: '',
          description: '',
          path: '',
        },
        addCategory: {
          name: '',
          description: '',
          path: '',
        },
        categoryIconOptions: this.$config.categoryIconOptions,
        categoryBgOptions: this.$config.categoryBgOptions,
      }
    },
    methods: {
      page(currentPage){
        const _this = this
        axios.get('http://127.0.0.1:8181/category/page/'+ currentPage+ '/10').then(function (resp){
          _this.CategoryData = resp.data.content
          _this.totalElements = resp.data.totalElements
          _this.size = resp.data.size
        })
      },
			handEdit(id) {
        const _this = this
				this.upDialogVisible = true;
        axios.get('http://127.0.0.1:8181/category/' + id).then(function (resp){
          _this.updataCategory = resp.data
        })
			},
      handleDelete(id){
        this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios.delete('http://127.0.0.1:8181/admin/category/deleteCategory/' + id)
          window.location.reload();  // 刷新窗口
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      },
			handleUpload (updataCategory) {
        const _this = this
				this.upDialogVisible = false
        axios.put('http://127.0.0.1:8181/admin/category/updateCategory', updataCategory).then(function(resp) {
          if (resp.data == 'success'){
            console.log('成功');
          }else{
            alert('修改失败')
          }
        })
        window.location.reload();  // 刷新窗口
			},
			add() {
        const _this = this
				this.addDialogVisible = true;
			},
      handleAdd(addCategory){
        const _this = this
				this.addDialogVisible = false
        axios.post('http://127.0.0.1:8181/admin/category/save', addCategory).then(function(resp) {
          if (resp.data == 'success'){
            console.log('成功');
          }else{
            alert('新增失败')
          }
        })
        window.location.reload();  // 刷新窗口
      }
    },
  }
</script>
