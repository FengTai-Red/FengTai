<template>
  <div>
    <!-- 列表 -->
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
        <el-form ref="updateForm" :model="updateCategory" :rules="rules" label-width="80px">
          <el-form-item label="分类名" prop="name">
            <el-input v-model="updateCategory.name"  maxlength="5"></el-input>
          </el-form-item>
          <el-form-item label="简介" prop="description">
            <el-input v-model="updateCategory.description"  maxlength="10"></el-input>
          </el-form-item>
          <el-form-item label="图标" prop="icon">
            <el-select v-model="updateCategory.icon" class="m-2" placeholder="Select">
              <el-option v-for="item in categoryIconOptions" :key="item.value" :label="item.label" :value="item.value"/>
            </el-select>
          </el-form-item>
          <el-form-item label="背景" prop="background">
            <el-select v-model="updateCategory.background" class="m-2" placeholder="Select">
              <el-option v-for="item in categoryBgOptions" :key="item.value" :label="item.label" :value="item.value"/>
            </el-select>
          </el-form-item>
        </el-form>
        <template v-slot:footer>
        <span class="dialog-footer">
          <el-button @click="upDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleUpload(updateCategory)">确 定</el-button>
        </span>
        </template>
      </el-dialog>
    </div>
    <!-- 新增窗 -->
    <div>
      <el-dialog title="新增" v-model="addDialogVisible" width="50%" >
        <el-form ref="addForm" :model="addCategory" :rules="rules" label-width="80px">
          <el-form-item label="分类名" prop="name">
            <el-input v-model="addCategory.name" maxlength="5">
            </el-input>
          </el-form-item>
          <el-form-item label="简介" prop="description">
            <el-input v-model="addCategory.description" maxlength="10"></el-input>
          </el-form-item>
          <el-form-item label="图标" prop="icon">
            <el-select v-model="addCategory.icon" class="m-2" placeholder="Select">
              <el-option v-for="item in categoryIconOptions" :key="item.value" :label="item.label" :value="item.value"/>
            </el-select>
          </el-form-item>
          <el-form-item label="背景" prop="background">
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
  import axios from "axios";
  var controllerPath = '';
  export default {
    name: "Upload",
    created(){
      controllerPath = this.$config.controllerPath
      const _this = this
      axios.get(controllerPath + '/category/page/1/10').then(function (resp){
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
        updateCategory: {
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
        rules: {
          name: [
            { required: true, message: '不能为空', trigger: 'blur'},
          ],
          description: [
            { required: true, message: '不能为空', trigger: 'blur'},
          ],
          icon: [
            { required: true, message: '不能为空', trigger: 'blur'},
          ],
          background: [
            { required: true, message: '不能为空', trigger: 'blur'},
          ],
        },
      }
    },
    methods: {
      page(currentPage){
        const _this = this
        axios.get(controllerPath + '/category/page/'+ currentPage+ '/10').then(function (resp){
          _this.CategoryData = resp.data.content
          _this.totalElements = resp.data.totalElements
          _this.size = resp.data.size
        })
      },
			handEdit(id) {
        const _this = this
				this.upDialogVisible = true;
        axios.get(controllerPath + '/category/' + id).then(function (resp){
          _this.updateCategory = resp.data
        })
			},
      handleDelete(id){
        this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios.delete(controllerPath + '/admin/category/deleteCategory/' + id)
          window.location.reload();  // 刷新窗口
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      },
			handleUpload (updateCategory) {
        this.$refs.updateForm.validate((valid) => {
          if (valid) {
            const _this = this
            this.upDialogVisible = false
            axios.put(controllerPath + '/admin/category/updateCategory', updateCategory).then(function(resp) {
              if (resp.data == 'success'){
                console.log('成功');
              }else{
                alert('修改失败')
              }
            })
            window.location.reload();  // 刷新窗口
          } else {
            console.log("校验失败");
          }
        })
			},
			add() {
        const _this = this
				this.addDialogVisible = true;
			},
      handleAdd(addCategory){
        this.$refs.addForm.validate((valid) => {
          if(valid){
            const _this = this
            this.addDialogVisible = false
            axios.post(controllerPath + '/admin/category/save', addCategory).then(function(resp) {
              if (resp.data == 'success'){
                console.log('新增成功');
              }else{
                alert('新增失败')
              }
            })
            window.location.reload();  // 刷新窗口
          }
          else{
            console.log("校验失败");
          }
        });

      }
    },
  }
</script>
