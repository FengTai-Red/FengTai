<template>
  <div>
    <!-- 列表 -->
    <el-button type="primary" style="margin-left: 20px;" @click="add()">新增</el-button>
    <el-table :data="toolData" style="width: 100%">
      <el-table-column label="文件名" width="180">
        <template #default="scope">
          <span style="margin-left: 10px">{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="简介" width="450">
        <template #default="scope">
          <span style="margin-left: 10px">{{ scope.row.description }}</span>
        </template>
      </el-table-column>
      <el-table-column label="文件" width="450">
        <template #default="scope">
          <span style="margin-left: 10px">{{ scope.row.path }}</span>
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
      <el-dialog title="编辑" v-model="upDialogVisible" width="50%" >
        <el-form ref="updateForm" :model="updateTool" :rules="rules" label-width="80px">
          <el-form-item label="文件名" prop="name">
            <el-input v-model="updateTool.name"  maxlength="7"></el-input>
          </el-form-item>
          <el-form-item label="简介" prop="description">
            <el-input v-model="updateTool.description"  maxlength="23"></el-input>
          </el-form-item>
          <el-form-item label="文件">
            <span style="width: 300px;">
              <el-upload class="upload-demo" ref="upload" action="http://localhost:8181/admin/tool/upload" :auto-upload="false" :limit="1" :on-success="handleFilUploadSuccess" >
                <template #trigger>
                  <el-button type="primary">选择文件</el-button>
                </template>
              </el-upload>
              <el-button type="primary" @click=" handleUploadFile()">上 传</el-button>
            </span>
          </el-form-item>
        </el-form>
        <template v-slot:footer>
        <span class="dialog-footer">
          <el-button @click="upDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleUpload(updateTool)">确 定</el-button>
        </span>
        </template>
      </el-dialog>
    </div>
    <!-- 新增窗 -->
    <div>
      <el-dialog title="新增" v-model="addDialogVisible" width="50%" >
        <el-form ref="addForm" :model="addTool" :rules="rules" label-width="80px">
          <el-form-item label="文件名" prop="name">
            <el-input v-model="addTool.name" maxlength="7"></el-input>
          </el-form-item>
          <el-form-item label="简介" prop="description">
            <el-input v-model="addTool.description" maxlength="23"></el-input>
          </el-form-item>
          <el-form-item label="文件">
            <span style="width: 300px;">
              <el-upload class="upload-demo" ref="upload" action="http://localhost:8181/admin/tool/upload" :auto-upload="false" :limit="1" :on-success="handleFilUploadSuccess" >
                <template #trigger>
                  <el-button type="primary">选择文件</el-button>
                </template>
              </el-upload>
              <el-button type="primary" @click=" handleUploadFile()">上 传</el-button>
            </span>
          </el-form-item>
        </el-form>
        <template v-slot:footer>
        <span class="dialog-footer">
          <el-button @click="addDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleAdd(addTool)">确 定</el-button>
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
      axios.get('http://127.0.0.1:8181/tool/page/1/10').then(function (resp){
        _this.toolData = resp.data.content
        _this.totalElements = resp.data.totalElements
      })
    },
    data() {
      return {
        totalElements: null,
        size: null,
        toolData: null,
        visible: null,
        upDialogVisible: false,
        addDialogVisible: false,
        updateTool: {
          name: '',
          description: '',
          path: '',
        },
        addTool: {
          name: '',
          description: '',
          path: '',
        },
        rules: {
          name: [
            { required: true, message: '不能为空', trigger: 'blur'},
          ],
          description: [
            { required: true, message: '不能为空', trigger: 'blur'},
          ],
        },
      }
    },
    methods: {
      page(currentPage){
        const _this = this
        axios.get('http://127.0.0.1:8181/tool/page/'+ currentPage+ '/10').then(function (resp){
          _this.toolData = resp.data.content
          _this.totalElements = resp.data.totalElements
          _this.size = resp.data.size
        })
      },
			handEdit(id) {
        const _this = this
				this.upDialogVisible = true;
        axios.get('http://127.0.0.1:8181/tool/' + id).then(function (resp){
          _this.updateTool = resp.data
        })
			},
      handleDelete(id){
        this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios.delete('http://127.0.0.1:8181/admin/tool/deleteTool/' + id)
          window.location.reload();  // 刷新窗口
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      },
			handleUpload (updateTool) {
        this.$refs.updateForm.validate((valid) => {
          if (valid) {
            const _this = this
            this.upDialogVisible = false
            axios.put('http://127.0.0.1:8181/admin/tool/updateTool', updateTool).then(function(resp) {
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
      handleAdd(addTool){
        this.$refs.addForm.validate((valid) => {
          if (valid) {
            const _this = this
            this.addDialogVisible = false
            axios.post('http://127.0.0.1:8181/admin/tool/save', addTool).then(function(resp) {
              if (resp.data == 'success'){
                console.log('成功');
              }else{
                alert('新增失败')
              }
            })
            window.location.reload();  // 刷新窗口
          } else {
            console.log("校验失败");
          }
        })

      },
      handleRemove(file,fileList) {
        console.log(file,fileList);
      },
      submitUpload() {
        this.$refs.upload.submit();
      },
      // 文件上传成功时的函数
      handleFilUploadSuccess (res,file,fileList) {
        console.log(res,file,fileList)
        this.$message.success("上传成功")
      },
      handleUpdate () {
        this.dialogVisible = true;
      },
      // 处理文件上传的函数
      handleUploadFile () {
        // console.log(res,file)
        this.submitUpload()
        this.dialogVisible = false
      },
    },

  }
</script>
